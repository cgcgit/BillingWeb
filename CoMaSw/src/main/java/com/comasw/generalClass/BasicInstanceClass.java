/**
 * 
 */
package com.comasw.generalClass;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.comasw.ejb.parameterization.StatusEJBLocal;
import com.comasw.model.tables.pojos.PtStatus;

/**
 * @author catuxa
 *
 */
public class BasicInstanceClass<T> extends BasicHistoricWithLists<T> {
	

	Logger logger = (Logger) LogManager.getLogger(BasicInstanceClass.class);

	@EJB
	private StatusEJBLocal statusEJB;
	

	
	/*
	 * Return the list of select items with the status data
	 */
	public List<SelectItem> statusInstanceSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<PtStatus> list = statusEJB.findDataForInstance();

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

}
