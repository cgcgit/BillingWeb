package com.billingweb.viewController.appAccessController;


import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named ("logoutController")
@ApplicationScoped
public class LogoutController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2313368455041720201L;
	
	protected static ResourceBundle urlFile = ResourceBundle
			.getBundle("com.billingweb.properties.urlPage");
	
	@Inject
	private HttpServletRequest request;

	public String submit() throws ServletException {
		request.logout();
		request.getSession().invalidate();		
		return urlFile.getString("loginPage")+ urlFile.getString("redirect");
	}
}