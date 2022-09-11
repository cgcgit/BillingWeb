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

package com.comasw.auth;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.ejb.user.ApplicationUserEJBLocal;
import com.comasw.utilities.Utilities;

@ApplicationScoped
public class UserServiceIdentityStore implements IdentityStore {

	Logger logger = (Logger) LogManager.getLogger(UserServiceIdentityStore.class);

	@Inject
	private ApplicationUserEJBLocal applicationUser;

	@Override
	public CredentialValidationResult validate(Credential credential) {

		UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

		String userName = login.getCaller();

		String passwordMD5 = Utilities.MD5(login.getPasswordAsString());

		//logger.debug("user " + userName + " - password: " + passwordMD5);

		Optional<VwUsers> optionalUser = Optional.ofNullable(applicationUser.activeUser(userName, passwordMD5));

		if (optionalUser.isPresent()) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("applicationUser",
					optionalUser.get());
			logger.info("INFO - Credential validation OK for user: " + userName);
			return new CredentialValidationResult(optionalUser.get().getUserCode(),
					new HashSet<>(Arrays.asList(optionalUser.get().getProfileCode())));

		} else {
			logger.error("ERROR - Credential validation KO for user: " + userName);
			return CredentialValidationResult.INVALID_RESULT;
		}
	}
}