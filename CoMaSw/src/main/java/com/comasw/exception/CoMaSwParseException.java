/**
 * 
 */
package com.comasw.exception;

import static java.lang.String.format;

/**
 * @author catuxa
 *
 */
public class CoMaSwParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8512970311626215379L;

	public CoMaSwParseException(String message) {
		super(message);
	}

	public CoMaSwParseException(String message, Throwable cause) {
		super(message, cause);
	}

	// -------------------------------------------------------------------------
	// No String/variable interpolation in Java. Use format instead.
	// -------------------------------------------------------------------------
	public CoMaSwParseException(String template, Object arg1) {
		this(format(template, arg1));
	}

	public CoMaSwParseException(String template, Object arg1, Throwable cause) {
		this(format(template, arg1), cause);
	}

	public CoMaSwParseException(String template, Object arg1, Object arg2) {
		this(format(template, arg1, arg2));
	}

	public CoMaSwParseException(String template, Object arg1, Object arg2, Throwable cause) {
		this(format(template, arg1, arg2), cause);
	}

}
