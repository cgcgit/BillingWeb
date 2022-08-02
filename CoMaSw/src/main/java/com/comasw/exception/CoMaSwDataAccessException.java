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
