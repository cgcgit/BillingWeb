/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

*/

package com.comasw.viewController.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.omnifaces.util.Ajax;

import com.comasw.model.tables.pojos.ItUsers;
import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.ejb.user.ApplicationUserEJBLocal;
import com.comasw.generalClass.BasicClass;
import com.comasw.utilities.Utilities;

@Named
@ViewScoped
public class UserProfileController extends BasicClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1215092050426785353L;

	private static final Integer MAX_LENGTH_NAME = Integer
			.valueOf(dbDefinitions.getString("USER_NAME_FIELD_LENGTH_MAX"));;
	private static final Integer MAX_LENGTH_SURNAME = Integer
			.valueOf(dbDefinitions.getString("USER_SURNAME_FIELD_LENGTH_MAX"));;
	private static final Integer MAX_LENGTH_EMAIL = Integer
			.valueOf(dbDefinitions.getString("USER_EMAIL_FIELD_LENGTH_MAX"));;
	private static final Integer MAX_LENGTH_PHONE_CONTACT = Integer
			.valueOf(dbDefinitions.getString("USER_PHONE_CONTACT_FIELD_LENGTH_MAX"));;
	private static final Integer MIN_LENGTH_PASSWORD = Integer
			.valueOf(dbDefinitions.getString("USER_PASSWORD_FIELD_LENGTH_MIN"));;
	private static final Integer MAX_LENGTH_PASSWORD = Integer
			.valueOf(dbDefinitions.getString("USER_PASSWORD_FIELD_LENGTH_MAX"));;

	Logger logger = (Logger) LogManager.getLogger(UserProfileController.class);

	// @Inject
	private ItUsers userInject;

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private ApplicationUserEJBLocal applicationUserEJB;

	private boolean editMode;

	private boolean editPassword;

	private ItUsers backupData;

	private String profileCode = ((VwUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
			.get("applicationUser")).getProfileCode();

	private String password;

	public UserProfileController() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * @return the userInject
	 */
	public ItUsers getUserInject() {
		return userInject;
	}

	/**
	 * @param userInject the userInject to set
	 */
	public void setUserInject(ItUsers userInject) {
		this.userInject = userInject;
	}

	/**
	 * @return the editMode
	 */
	public Boolean getEditMode() {
		return editMode;
	}

	/**
	 * @param editMode the editMode to set
	 */
	public void setEditMode(Boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * @return the editPassword
	 */
	public boolean isEditPassword() {
		return editPassword;
	}

	/**
	 * @param editPassword the editPassword to set
	 */
	public void setEditPassword(boolean editPassword) {
		this.editPassword = editPassword;
	}

	/**
	 * @return the backupData
	 */
	public ItUsers getBackupData() {
		return backupData;
	}

	/**
	 * @param backupData the backupData to set
	 */
	public void setBackupData(ItUsers backupData) {
		this.backupData = backupData;
	}

	/**
	 * @return the profileCode
	 */
	public String getProfileCode() {
		return profileCode;
	}

	/**
	 * @param profileCode the profileCode to set
	 */
	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@PostConstruct
	public void init() {

		if (userInject == null) {
			userInject = new ItUsers();
		}
		if (backupData == null) {
			backupData = new ItUsers();
		}
		// this.initValues();
		// this.loadData();

	}

	public void initValues() {
		this.editMode = false;
		this.editPassword = false;
	}

	public void loadData() {
		String message = "LOAD DATA";
		String messageDetail;

		VwUsers data = (VwUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("applicationUser");
		try {
			this.userInject = applicationUserEJB.findDataByUserCode(data.getUserCode());
		} catch (Exception e) {
			messageDetail = "ERROR - " + e.getMessage();
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
		}

	}

	/** CHANGE PASSWORD **/

	public void pushChangePasswordButton() {
		String message = "CHANGE PASSWORD";
		String messageDetail;

		this.editPassword = true;

		messageDetail = "Change password for user code " + userInject.getUserCode();
		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

		Ajax.update("form");

	}

	public void pushSavePasswordButton() {
		String message = "SAVE NEW PASSWORD";
		String messageDetail;
		boolean error = false;

		try {
			// validates the data
			if (!this.passwordValidation()) {
				error = true;
			} else {
				this.userInject.setPassword(Utilities.MD5(this.password));
				applicationUserEJB.updateData(this.userInject);
				messageDetail = "New password saved succesfully";
				logger.info(messageDetail);
				logger.info("User new data: " + this.userInject.toString());
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
			}
		} catch (EJBException e) {
			error = true;
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwDataAccessException")) {
				messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			} else if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
				messageDetail = "PARSE ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			} else {
				messageDetail = "ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);
			}

		} catch (Exception e) {
			error = true;
			messageDetail = "ERROR - " + e.getMessage();
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);
		} finally {
			this.password = null;
			if (error) {
				facesContext.validationFailed();
			} else {
				this.loadData();
				// this.initValues();
				this.editPassword = false;
				Ajax.update("form");
			}
		}

	}

	public boolean passwordValidation() {
		String message = "PASSWORD VALIDATION";
		String messageDetail = "";

		boolean result = true;

		if ((this.password == null) || (this.password.length() == 0)) {
			result = false;
			messageDetail = "Password field can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else if ((((Integer) this.password.length()).compareTo(MIN_LENGTH_PASSWORD) < 0)
				|| (((Integer) this.password.length()).compareTo(MAX_LENGTH_PASSWORD) > 0)) {

			// length characters exceeds the maximum length
			messageDetail = "Error - The password (" + password.length() + " characters) must be between "
					+ MIN_LENGTH_PASSWORD.toString() + "and " + MAX_LENGTH_PASSWORD.toString() + " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			result = false;
		}

		return result;
	}

	public void pushCancelPasswordButton() {

		String message = "CHANGE PASSWORD CANCELLED";
		String messageDetail = "Change password cancelled";
		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

		this.loadData();
		// this.initValues();
		this.editPassword = false;
		Ajax.update("form");

	}

	/** MODIFY DATA **/

	public void pushEditButton() {
		String message = "EDIT DATA";
		String messageDetail;

		this.backupData = this.userInject;
		this.editMode = true;

		messageDetail = "Edit data for user code " + userInject.getUserCode();
		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

		Ajax.update("form");

	}

	public void pushSaveButton() {

		String message = "SAVE DATA";
		String messageDetail;

		boolean error = false;

		try {
			if (objectValidation(this.userInject)) {
				applicationUserEJB.updateData(this.userInject);
				messageDetail = "Data saved succesfully";
				logger.info(messageDetail);
				logger.info("User new data: " + this.userInject.toString());
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

			} else {
				error = true;
			}
		} catch (EJBException e) {
			error = true;
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwDataAccessException")) {
				messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			} else if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
				messageDetail = "PARSE ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			} else {
				messageDetail = "ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);
			}

		} catch (Exception e) {
			error = true;
			messageDetail = "ERROR - " + e.getMessage();
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);
		} finally {
			if (error) {
				facesContext.validationFailed();
			} else {
				this.loadData();
				// this.initValues();
				this.editMode = false;
				Ajax.update("form");
			}
		}

	}

	public void pushCancelButton() {
		String message = "EDIT DATA CANCELLED";
		String messageDetail = "Edit data cancelled";

		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

		this.loadData();
		// this.initValues();
		this.editMode = false;
		Ajax.update("form");
	}

	public boolean objectValidation(Object dataObject) {
		boolean result = true;
		ItUsers objectToValidate = (ItUsers) dataObject;
		String message = "DATA VALIDATION";
		String messageDetail = "";

		if (objectToValidate != null) {

			if (objectToValidate.getName() != null) {
				objectToValidate.setName(objectToValidate.getName().trim());
			}

			if (objectToValidate.getSurname() != null) {
				objectToValidate.setSurname(objectToValidate.getSurname().trim());
			}

			if (objectToValidate.getEmail() != null) {
				objectToValidate.setEmail(objectToValidate.getEmail().trim());
			}
			if (objectToValidate.getPhoneContact() != null) {
				objectToValidate.setPhoneContact(objectToValidate.getPhoneContact().trim());
			}

			if (objectToValidate.getName() == null || objectToValidate.getName().length() == 0) {
				messageDetail = "ERROR - The user name can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getName().length()).compareTo(MAX_LENGTH_NAME) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The user name (" + objectToValidate.getName().length()
						+ " characters) exceeds the limit of " + MAX_LENGTH_NAME.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}

			if (objectToValidate.getSurname() == null || objectToValidate.getSurname().length() == 0) {
				messageDetail = "ERROR - The user surname can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if ((((Integer) objectToValidate.getSurname().length()).compareTo(MAX_LENGTH_SURNAME) > 0)) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The user surname (" + objectToValidate.getSurname().length()
						+ " characters) exceeds the limit of " + MAX_LENGTH_SURNAME.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}

			if (objectToValidate.getEmail() == null || objectToValidate.getEmail().length() == 0) {
				messageDetail = "ERROR - The email can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getEmail().length()).compareTo(MAX_LENGTH_EMAIL) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The email (" + objectToValidate.getEmail().length()
						+ " characters) exceeds the limit of " + MAX_LENGTH_EMAIL.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else {
				if (!Utilities.emailValidation(objectToValidate.getEmail())) {
					messageDetail = "Error in the email validation format";
					logger.error(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
					result = false;

				}

			}

			if (((Integer) objectToValidate.getPhoneContact().length()).compareTo(MAX_LENGTH_PHONE_CONTACT) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The phone contact (" + objectToValidate.getPhoneContact().length()
						+ " characters) exceeds the limit of " + MAX_LENGTH_PHONE_CONTACT.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else {
				if (!Utilities.phoneNumberValidation((objectToValidate.getPhoneContact()))) {
					messageDetail = "Error in the phone validation format";
					logger.error(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
					result = false;
				}
			}
		} else {
			messageDetail = "ERROR - Empty values";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			result = false;
		}

		return result;

	}

}
