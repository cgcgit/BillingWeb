package com.billingweb.generalClass;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.billingweb.ejb.parameterization.ApplicationLevelEJBLocal;
import com.billingweb.ejb.parameterization.StatusEJBLocal;
import com.billingweb.model.tables.pojos.PtApplicationLevel;
import com.billingweb.model.tables.pojos.PtStatus;


public class SimpleTableBasicClassWithLists extends SimpleTableBasicClass {
	
	Logger logger = (Logger) LogManager.getLogger(SimpleTableBasicClassWithLists.class);
	
	
	@EJB
	private StatusEJBLocal statusEJB;
	
	
	@EJB
	private ApplicationLevelEJBLocal applicationLevelEJB;
	
	/*
	 * Return a boolean list
	 */

	public List<SelectItem> booleanSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		SelectItem trueItem = new SelectItem();
		trueItem.setLabel("True");
		trueItem.setValue(true);
		selectItem.add(trueItem);

		SelectItem falseItem = new SelectItem();
		falseItem.setLabel("False");
		falseItem.setValue(false);
		selectItem.add(falseItem);

		return selectItem;
	}
	

	/*
	 * Return the list of select items with the status data
	 */
	public List<SelectItem> statusSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<PtStatus> list = statusEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find status list");
		} else {
			for (PtStatus p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getStatusId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}
	
	/*
	 * Return the list of select items with the application level data
	 */
	public List<SelectItem> applicationLevelSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<PtApplicationLevel> list = applicationLevelEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find application level list");
		} else {
			for (PtApplicationLevel p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getApplicationLevelId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}
	
	
	
	
	
	

}
