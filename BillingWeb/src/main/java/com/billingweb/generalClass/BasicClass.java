package com.billingweb.generalClass;

import java.util.Optional;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.billingweb.exception.BillingWebGeneralException;
import com.billingweb.model.tables.pojos.VwUsers;

/**
 * @author catuxa
 *
 *         Basic class 
 */


public class BasicClass {
	
	Logger logger = (Logger) LogManager.getLogger(BasicClass.class);
	
	protected static ResourceBundle profilesFile = ResourceBundle
			.getBundle("com.billingweb.properties.modifyingProfile");
	
	protected static ResourceBundle uiValues = ResourceBundle
			.getBundle("com.billingweb.properties.uiValues");

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.billingweb.properties.dataBaseDefinitions");
	
	
	
	/**
	 * Data Base definitions
	 */
	
	protected static Integer CODE_FIELD_LENGTH_MIN = Integer.valueOf(dbDefinitions.getString("CODE_FIELD_LENGTH_MIN"));
	protected static Integer CODE_FIELD_LENGTH_MAX = Integer.valueOf(dbDefinitions.getString("CODE_FIELD_LENGTH_MAX"));
	protected static Integer NAME_FIELD_LENGTH_MIN = Integer.valueOf(dbDefinitions.getString("NAME_FIELD_LENGTH_MIN"));
	protected static Integer NAME_FIELD_LENGTH_MAX = Integer.valueOf(dbDefinitions.getString("NAME_FIELD_LENGTH_MAX"));
	protected static Integer DESCRIPTION_FIELD_LENGTH_MIN = Integer.valueOf(dbDefinitions.getString("DESCRIPTION_FIELD_LENGTH_MIN"));
	protected static Integer DESCRIPTION_FIELD_LENGTH_MAX = Integer.valueOf(dbDefinitions.getString("DESCRIPTION_FIELD_LENGTH_MAX"));
	
	
	
	

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
				throw new BillingWebGeneralException(errorMessage, e);				
			} else {
				errorMessage = "Error checking the modify functionality for the user: "
						+ user.get().getUserCode() + " - " + e.getMessage();
				logger.error(errorMessage);
				throw new BillingWebGeneralException(errorMessage, e);
			}

		}

		return result;

	}
	
	
	public void createMessage(FacesContext facesContext, ExternalContext externalContext, FacesMessage.Severity messageConcept,  String textMessage, String textMessageDetail) {

        externalContext.getFlash().setKeepMessages(true);
        //facesContext.getExternalContext().getFlash().setKeepMessages(true);        
        facesContext.addMessage(uiValues.getString("growlMessageID"), new FacesMessage(messageConcept, textMessage, textMessageDetail));
        //facesContext.addMessage(null, new FacesMessage(messageConcept, textMessage, textMessageDetail));
        facesContext.getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, textMessage);
    }


}
