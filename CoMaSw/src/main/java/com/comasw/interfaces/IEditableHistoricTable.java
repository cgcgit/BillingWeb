/**
 * 
 */
package com.comasw.interfaces;

import javax.faces.event.ValueChangeEvent;

import org.primefaces.component.datatable.DataTable;


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
	 * Defines the proper header for the current action
	 */
	public void changeNewDialogHeader();

	/**
	 * Defines the proper message for the current delete action
	 */
	public void changeDeleteMessage();

	/**
	 * Defines the proper message for the current change status Action
	 */
	public void changeStatusMessage();
	
	/**
	 * Function that split a row to insert a new row in the data table
	 * 
	 * @param dataTable
	 * @param row
	 * @param newRow
	 * @return
	 */
	public boolean splitRecords(DataTable dataTable, int row, Object newRow);
	
	/**
	 * Defines the proper message for the result data search data table
	 */
	public void changeSearchDataTableTitle();

}
