package com.comasw.interfaces;

import javax.faces.event.ValueChangeEvent;

public interface IInstanceTable extends IEditableHistoricTable {
	
	/**
	 * Action to do when push the search customer button in create new account form
	 */
	public void pushSearchParentButton() ;
	
	
	public void addParentToInstanceRowButton() ;
	
	/**
	 * Action to change the cancelled date (all dates of the records must be the same)
	 */
	public void changeCancelledDate(ValueChangeEvent e);
	
	/**
	 * Action to change the active date (all dates of the records must be the same)
	 */
	public void changeActiveDate(ValueChangeEvent e);

}
