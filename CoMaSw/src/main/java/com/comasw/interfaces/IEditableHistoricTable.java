/**
 * 
 */
package com.comasw.interfaces;

import javax.faces.event.ValueChangeEvent;

/**
 * @author catuxa
 *
 */
public interface IEditableHistoricTable extends IEditableTable, IHistoricTable {
	

	/**
	 * Action to change the status of the historic value
	 */
	public void changeStatus(ValueChangeEvent e);

	/**
	 * Revert the cancelled status changed of subsequent rows
	 */

	public void revertSubsequentCancelStatus();

	/**
	 * Action to push the confirm change status button
	 */
	public void pushConfirmButtonChangeStatus();
	

	/**
	 * Defines the propper header for the current action
	 */
	public void changeNewDialogHeader();

	
	/**
	 * Defines the propper message for the current delete action
	 */
	public void changeDeleteMessage() ;
	
	
	/**
	 * Defines the propper message for the current change status Action
	 */
	public void changeStatusMessage();
	
}
