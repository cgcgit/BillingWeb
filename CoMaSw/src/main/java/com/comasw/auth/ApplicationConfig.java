package com.comasw.auth;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/login_page.xhtml", useForwardToLogin = false, errorPage = "/error_page.xhtml"))

@FacesConfig
@ApplicationScoped

public class ApplicationConfig {
	//					
}