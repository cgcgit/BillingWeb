package com.billingweb.generalClass;



/**
 * @author catuxa
 *
 *         Basic class for entities showing in simple tables
 */

public class SimpleTableBasicClass extends BasicClass{



	/**
	 * Indicates if there are any rows in edit mode
	 */
	protected boolean editingMode = false;

	/**
	 * Specify if the save button must be disabled.
	 */
	protected boolean disableSaveButton = true;

	/**
	 * Indicates if there has been any change in the data
	 */
	protected boolean changedData = false;

	/**
	 * Identifies whether the functionality associated with the edit button
	 * corresponds to the user's action (pressing the button) or the behavior of the
	 * managed bean
	 */
	protected boolean pushEditButtonFromUser = false;
	
    /**
     * Current row of the table
     */
	//protected int currentRow;
	
	
	
	
	
	

	/**
	 * @return the editingMode
	 */
	public boolean isEditingMode() {
		return editingMode;
	}



	/**
	 * @param editingMode the editingMode to set
	 */
	public void setEditingMode(boolean editingMode) {
		this.editingMode = editingMode;
	}





	/**
	 * @return the disableSaveButton
	 */
	public boolean isDisableSaveButton() {
		return disableSaveButton;
	}





	/**
	 * @param disableSaveButton the disableSaveButton to set
	 */
	public void setDisableSaveButton(boolean disableSaveButton) {
		this.disableSaveButton = disableSaveButton;
	}





	/**
	 * @return the changedData
	 */
	public boolean isChangedData() {
		return changedData;
	}





	/**
	 * @param changedData the changedData to set
	 */
	public void setChangedData(boolean changedData) {
		this.changedData = changedData;
	}





	/**
	 * @return the pushEditButtonFromUser
	 */
	public boolean isPushEditButtonFromUser() {
		return pushEditButtonFromUser;
	}





	/**
	 * @param pushEditButtonFromUser the pushEditButtonFromUser to set
	 */
	public void setPushEditButtonFromUser(boolean pushEditButtonFromUser) {
		this.pushEditButtonFromUser = pushEditButtonFromUser;
	}



	/**
	 * @return the currentRow
	 */
/*	public int getCurrentRow() {
		return currentRow;
	}
*/


	/**
	 * @param currentRow the currentRow to set
	 */
/*	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

*/

}
	
    
    
	