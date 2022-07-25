/**
 * 
 */
package com.comasw.interfaces;

import javax.faces.validator.ValidatorException;

import org.primefaces.event.RowEditEvent;

/**
 * @author catuxa
 * 
 *         Manages the editable tables for the entities
 *
 */
public interface IEditableTable extends ITableAdd{
	

	// -------------- EVENT MANAGEMENT ON THE TABLES

	/**
	 * Initializes the row edition of the table
	 *
	 * @param event
	 */
	public void onRowInit(RowEditEvent<?> event);

	/**
	 * Storages the data for the row edited. If the data has some invalid value, it
	 * throws a ValidatorException
	 *
	 * @param event
	 */
	public void onRowEdit(RowEditEvent<?> event) throws ValidatorException;

	/**
	 * Cancels the editing row of the table
	 *
	 * @param event
	 */
	public void onRowCancel(RowEditEvent<?> event);
	
	


}
