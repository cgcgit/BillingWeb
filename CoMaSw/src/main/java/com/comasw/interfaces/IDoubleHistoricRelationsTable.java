/**
 * 
 */
package com.comasw.interfaces;

import javax.faces.event.ValueChangeEvent;

/**
 * @author catuxa
 *
 */
public interface IDoubleHistoricRelationsTable extends ISimpleHistoricRelationsTable{
	
	/**
	 * Action to do when changes the second search date
	 * @param e - change event
	 */
	public void changeSecondSearchDate(ValueChangeEvent e) ;

}
