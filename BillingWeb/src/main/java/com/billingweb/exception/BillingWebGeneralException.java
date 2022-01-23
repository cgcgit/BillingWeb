package com.billingweb.exception;


import static java.lang.String.format; 

/*
 *  source http://www.programcreek.com/java-api-examples/index.php?source_dir=steve-master/src/main/java/de/rwth/idsg/steve/SteveException.java
 *  
 */
public class BillingWebGeneralException extends RuntimeException {

	public BillingWebGeneralException(String message) {
		super(message);
	}

	public BillingWebGeneralException(String message, Throwable cause) {
		super(message, cause);
	}

	// -------------------------------------------------------------------------
	// No String/variable interpolation in Java. Use format instead.
	// -------------------------------------------------------------------------

	public BillingWebGeneralException(String template, Object arg1) {
		this(format(template, arg1));
	}

	public BillingWebGeneralException(String template, Object arg1, Throwable cause) {
		this(format(template, arg1), cause);
	}

	public BillingWebGeneralException(String template, Object arg1, Object arg2) {
		this(format(template, arg1, arg2));
	}

	public BillingWebGeneralException(String template, Object arg1, Object arg2, Throwable cause) {
		this(format(template, arg1, arg2), cause);
	}

}
