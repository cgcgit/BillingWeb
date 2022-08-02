/**
 * 
 */
package com.comasw.generalClass;

import java.time.LocalDateTime;

/**
 * @author catuxa
 *
 */
public class DoubleHistoricRelationWithList<T, S, U> extends SimpleHistoricRelationWithList<T, S, U> {

	/**
	 * Second search date
	 */
	protected LocalDateTime secondSearchDate;

	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	/**
	 * @return the secondSearchDate
	 */
	public LocalDateTime getSecondSearchDate() {
		return secondSearchDate;
	}

	/**
	 * @param secondSearchDate the secondSearchDate to set
	 */
	public void setSecondSearchDate(LocalDateTime secondSearchDate) {
		this.secondSearchDate = secondSearchDate;
	}

}
