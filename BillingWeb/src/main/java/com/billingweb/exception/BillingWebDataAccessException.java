package com.billingweb.exception;


import static java.lang.String.format; 

/*
 *  source http://www.programcreek.com/java-api-examples/index.php?source_dir=steve-master/src/main/java/de/rwth/idsg/steve/SteveException.java
 *  
 */
public class BillingWebDataAccessException extends RuntimeException {

	public BillingWebDataAccessException(String message) {
		super(message);
	}

	public BillingWebDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	// -------------------------------------------------------------------------
	// No String/variable interpolation in Java. Use format instead.
	// -------------------------------------------------------------------------

	public BillingWebDataAccessException(String template, Object arg1) {
		this(format(template, arg1));
	}

	public BillingWebDataAccessException(String template, Object arg1, Throwable cause) {
		this(format(template, arg1), cause);
	}

	public BillingWebDataAccessException(String template, Object arg1, Object arg2) {
		this(format(template, arg1, arg2));
	}

	public BillingWebDataAccessException(String template, Object arg1, Object arg2, Throwable cause) {
		this(format(template, arg1, arg2), cause);
	}

}
