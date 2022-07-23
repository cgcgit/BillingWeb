package com.comasw.exception;


import static java.lang.String.format; 

/*
 *  source http://www.programcreek.com/java-api-examples/index.php?source_dir=steve-master/src/main/java/de/rwth/idsg/steve/SteveException.java
 *  
 */
public class CoMaSwGeneralException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5828455852759111818L;

	public CoMaSwGeneralException(String message) {
		super(message);
	}

	public CoMaSwGeneralException(String message, Throwable cause) {
		super(message, cause);
	}

	// -------------------------------------------------------------------------
	// No String/variable interpolation in Java. Use format instead.
	// -------------------------------------------------------------------------

	public CoMaSwGeneralException(String template, Object arg1) {
		this(format(template, arg1));
	}

	public CoMaSwGeneralException(String template, Object arg1, Throwable cause) {
		this(format(template, arg1), cause);
	}

	public CoMaSwGeneralException(String template, Object arg1, Object arg2) {
		this(format(template, arg1, arg2));
	}

	public CoMaSwGeneralException(String template, Object arg1, Object arg2, Throwable cause) {
		this(format(template, arg1, arg2), cause);
	}

}
