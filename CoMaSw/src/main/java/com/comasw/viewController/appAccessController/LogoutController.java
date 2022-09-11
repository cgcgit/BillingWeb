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

package com.comasw.viewController.appAccessController;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named("logoutController")
@ApplicationScoped
public class LogoutController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2313368455041720201L;

	protected static ResourceBundle urlFile = ResourceBundle.getBundle("com.comasw.properties.urlPage");

	@Inject
	private HttpServletRequest request;

	public String submit() throws ServletException {
		request.logout();
		request.getSession().invalidate();
		return urlFile.getString("loginPage") + urlFile.getString("redirect");
	}
}