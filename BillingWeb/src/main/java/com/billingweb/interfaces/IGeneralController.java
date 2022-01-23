package com.billingweb.interfaces;

/**
 * @author catuxa
 *
 *         General interface for the controllers (page's manager)
 */

public interface IGeneralController extends IEditableTable {

	/**
	 * Sets the init variables to default value
	 */
	public void setInitVariablesToDefault();

	/**
	 * Sets the control variables to default value
	 */
	public void setControlVariablesToDefault();

}
