package com.comasw.viewController.templateController;

import java.io.Serializable;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.omnifaces.util.Ajax;
import org.primefaces.PrimeFaces;

import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.exception.CoMaSwGeneralException;

@Named
@ViewScoped
public class MainTemplateController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6171018974278400217L;

	Logger logger = (Logger) LogManager.getLogger(MainTemplateController.class);
	
	
	private Boolean hiddenPanel;
	
	
	/**
	 * @return the showMenu
	 */
	public Boolean getHiddenPanel() {
		return hiddenPanel;
	}

	/**
	 * @param showMenu the showMenu to set
	 */
	public void setHiddenPanel(Boolean hiddenPanel) {
		this.hiddenPanel = hiddenPanel;
	}
	
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
			throw new CoMaSwGeneralException(errorMessage, e);
		}

	}
	
	
	@PostConstruct
	public void init() {
		this.hiddenPanel=true;
		
	}
	
	public void hiddenPanel() {
		if (this.hiddenPanel == true) {			
			this.hiddenPanel = false;
			PrimeFaces.current().executeScript("PF('sidebar').show();");
		} else {
			this.hiddenPanel = true;
			PrimeFaces.current().executeScript("PF('sidebar').hide();");
		}
		
		Ajax.update("mainFormTop");
		
				
	}


}
