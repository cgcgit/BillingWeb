/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
 any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

*/

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

	// private final ResourceBundle pageTitleProperties =
	// ResourceBundle.getBundle("pageTitle.properties");

	public void verifySession() {
		String errorMessage;
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

			Optional<VwUsers> user = Optional
					.ofNullable((VwUsers) externalContext.getSessionMap().get("applicationUser"));
			if (!user.isPresent()) {
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
		this.hiddenPanel = true;

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
