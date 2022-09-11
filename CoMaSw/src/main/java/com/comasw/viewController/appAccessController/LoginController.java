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

package com.comasw.viewController.appAccessController;

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

@Named("loginController")
@ViewScoped
public class LoginController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 709402449883178816L;

	protected static ResourceBundle urlFile = ResourceBundle.getBundle("com.comasw.properties.urlPage");

	Logger logger = (Logger) LogManager.getLogger(LoginController.class);

	@NotNull
	private String user;

	@NotNull
	private String password;

	@Inject
	private SecurityContext securityContext;

	@Inject
	private ExternalContext externalContext;

	@Inject
	private FacesContext facesContext;

	@Inject
	private Flash flash;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {

		this.user = user;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public LoginController() {
		// TODO Auto-generated constructor stub
	}

	public void submit() {
		switch (continueAuthentication()) {
		case SEND_CONTINUE: // authentication in progresss
			facesContext.responseComplete();
			break;
		case SEND_FAILURE: // authentication failed
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
			logger.error("Login failed - User: " + this.user);
			break;
		case SUCCESS: // authentication succeeded
			flash.setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));
			logger.info("Login succeed - User: " + this.user);
			try {
				externalContext
						.redirect(externalContext.getRequestContextPath() + "/app" + urlFile.getString("homePage"));
				logger.info("redirect to: " + externalContext.getRequestContextPath() + "/app" + urlFile.getString("homePage"));

			} catch (IOException e) {
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "IOException: " + e.getMessage(), null));
				logger.error("IOException: " + e.getMessage());
				e.printStackTrace();
			}
			break;
		case NOT_DONE:

		}
	}

	private AuthenticationStatus continueAuthentication() {		
		AuthenticationStatus auth = securityContext.authenticate((HttpServletRequest) externalContext.getRequest(),
				(HttpServletResponse) externalContext.getResponse(),
				AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(user, password)));
		return auth;
	}

}
