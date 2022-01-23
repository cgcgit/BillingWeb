package com.billingweb.ejb.miscelaneous;

import javax.ejb.Local;

import org.jooq.Result;

import com.billingweb.model.tables.records.MtApplicationMenuRecord;

@Local
public interface ApplicationMenuEJBLocal {
	
	/**
	 * Return all data from applicationMenu table for a gived profile
	 * @param profileCode profile Code for the menu
	 * @return Result<MtApplicationMenuRecord> with all the menu data for the chriteria
	 */
	Result<MtApplicationMenuRecord> findMenuByProfileCode (String profileCode);
	
	
	/**
	 * Return all data from applicationMenu table for a gived level 
	 * and profile code
	 * @param profileCode profile Code for the menu	 
	 * @param level level of the menu
	 * @return Result<MtApplicationMenuRecord> with all the menu data for the chriteria
	 */
	Result<MtApplicationMenuRecord> findMenuByLevelAndProfileCode (String profileCode, Integer level);
	
	/**
	 * Return root item menu from applicationMenu table for a gived profile code
	 * @param profileCode profile chriteria to search
	 * @return Result<MtApplicationMenuRecord> with root item menu (APPLICATION_PARENT_MENU_ID is null)
	 */
	
	Result<MtApplicationMenuRecord> findRootItemMenuByProfileCode (String profileCode);
	
	/**
	 * Return children item menu from applicationMenu table for a giving parent item menu and profile code
	 * @param profileCode profile chriteria to search
	 * @param parentMenuId parent menu id 
	 * @return Result<MtApplicationMenuRecord> with root item menu (APPLICATION_PARENT_MENU_ID is null)
	 */
	
	Result<MtApplicationMenuRecord> findChildrenItemMenuByProfileCode(String profileCode, Integer parentMenuId);
	
	
	

}
