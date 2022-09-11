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

package com.comasw.exception;

import static java.lang.String.format;

/*
 *  source http://www.programcreek.com/java-api-examples/index.php?source_dir=steve-master/src/main/java/de/rwth/idsg/steve/SteveException.java
 *  
 */
public class CoMaSwDataAccessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2601717122769155491L;

	/**
	 * 
	 */

	public CoMaSwDataAccessException(String message) {
		super(message);
	}

	public CoMaSwDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	// -------------------------------------------------------------------------
	// No String/variable interpolation in Java. Use format instead.
	// -------------------------------------------------------------------------

	public CoMaSwDataAccessException(String template, Object arg1) {
		this(format(template, arg1));
	}

	public CoMaSwDataAccessException(String template, Object arg1, Throwable cause) {
		this(format(template, arg1), cause);
	}

	public CoMaSwDataAccessException(String template, Object arg1, Object arg2) {
		this(format(template, arg1, arg2));
	}

	public CoMaSwDataAccessException(String template, Object arg1, Object arg2, Throwable cause) {
		this(format(template, arg1, arg2), cause);
	}

}
