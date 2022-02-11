package com.billingweb.viewController.templateController;

import java.io.Serializable;
import java.util.Optional;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.billingweb.exception.BillingWebGeneralException;

import com.billingweb.model.tables.pojos.VwUsers;

@Named
@ViewScoped
public class MainTemplateController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6171018974278400217L;

	Logger logger = (Logger) LogManager.getLogger(MainTemplateController.class);
	
	//private final ResourceBundle pageTitleProperties = ResourceBundle.getBundle("pageTitle.properties");
	
	public void verifySession() {
		String errorMessage;
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

			Optional<VwUsers> user = Optional
					.ofNullable((VwUsers) externalContext.getSessionMap().get("applicationUser"));
			if (! user.isPresent()) {
				externalContext.redirect("../errorPage.xthml");
			}

		} catch (Exception e) {
			errorMessage = "Error while try to verify session - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebGeneralException(errorMessage, e);
		}

	}
	


}
