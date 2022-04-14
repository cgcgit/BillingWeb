package com.billingweb.generalClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


public class SimpleRelatedTypeClass extends BasicClassWithLists {
	
	Logger logger = (Logger) LogManager.getLogger(SimpleRelatedTypeClass.class);
	


	/**
	 * Indicates if the data form must be shown
	 */
	protected boolean showDependentData = false;
	
	
	
	
	/**
	 * @return the showDependentData
	 */
	public boolean isShowDependentData() {
		return showDependentData;
	}





	/**
	 * @param showDependentData the showDependentData to set
	 */
	public void setShowDependentData(boolean showDependentData) {
		this.showDependentData = showDependentData;
	}


}
