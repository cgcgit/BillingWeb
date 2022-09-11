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

package com.comasw.generalClass;

import java.util.Optional;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.exception.CoMaSwGeneralException;

/**
 * @author catuxa
 *
 *         Basic class
 * @param <T>
 */

public class BasicClass {

	Logger logger = (Logger) LogManager.getLogger(BasicClass.class);

	protected static ResourceBundle profilesFile = ResourceBundle.getBundle("com.comasw.properties.modifyingProfile");

	protected static ResourceBundle uiValues = ResourceBundle.getBundle("com.comasw.properties.uiValues");

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.comasw.properties.dataBaseDefinitions");

	/**
	 * Data Base definitions
	 */

	protected static Integer CODE_FIELD_LENGTH_MIN = Integer.valueOf(dbDefinitions.getString("CODE_FIELD_LENGTH_MIN"));
	protected static Integer CODE_FIELD_LENGTH_MAX = Integer.valueOf(dbDefinitions.getString("CODE_FIELD_LENGTH_MAX"));
	protected static Integer NAME_FIELD_LENGTH_MIN = Integer.valueOf(dbDefinitions.getString("NAME_FIELD_LENGTH_MIN"));
	protected static Integer NAME_FIELD_LENGTH_MAX = Integer.valueOf(dbDefinitions.getString("NAME_FIELD_LENGTH_MAX"));
	protected static Integer DESCRIPTION_FIELD_LENGTH_MIN = Integer
			.valueOf(dbDefinitions.getString("DESCRIPTION_FIELD_LENGTH_MIN"));
	protected static Integer DESCRIPTION_FIELD_LENGTH_MAX = Integer
			.valueOf(dbDefinitions.getString("DESCRIPTION_FIELD_LENGTH_MAX"));

	// ----- DATA USER -----\\

	/**
	 * Application user
	 **/
	protected VwUsers loggedUser;

	/**
	 * @return the loggedUser
	 */
	public VwUsers getLoggedUser() {
		return loggedUser;
	}

	/**
	 * @param loggedUser the loggedUser to set
	 */
	public void setLoggedUser(VwUsers loggedUser) {
		this.loggedUser = loggedUser;
	}

	// -------------------
	// METHODS
	// -------------------

	public boolean canUserModify() {

		boolean result = false;
		Optional<VwUsers> user = null;
		String errorMessage;

		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

			user = Optional.ofNullable((VwUsers) externalContext.getSessionMap().get("applicationUser"));
			if (user.isPresent()) {
				if (profilesFile.getString(user.get().getProfileCode()).equals("true")) {
					result = true;
				}
			}

		} catch (Exception e) {
			if (!user.isPresent()) {
				errorMessage = "Error checking the modify functionality. The user is null - " + e.getMessage();
				logger.error(errorMessage);
				throw new CoMaSwGeneralException(errorMessage, e);
			} else {
				errorMessage = "Error checking the modify functionality for the user: " + user.get().getUserCode()
						+ " - " + e.getMessage();
				logger.error(errorMessage);
				throw new CoMaSwGeneralException(errorMessage, e);
			}

		}

		return result;

	}
	
	public boolean adminPermissions() {
		if(this.getLoggedUser().getProfileCode().compareToIgnoreCase("ADMIN")== 0) {
			return true;
		} else {
			return false;
		}
	}

	public void createMessage(FacesContext facesContext, ExternalContext externalContext,
			FacesMessage.Severity messageConcept, String textMessage, String textMessageDetail) {

		externalContext.getFlash().setKeepMessages(true);
		facesContext.addMessage(uiValues.getString("growlMessageID"),
				new FacesMessage(messageConcept, textMessage, textMessageDetail));
		facesContext.getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null,
				textMessage);
	}

}
