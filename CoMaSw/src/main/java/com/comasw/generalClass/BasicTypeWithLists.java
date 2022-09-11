/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
 any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

*/
package com.comasw.generalClass;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.comasw.ejb.catalog.type.BillCycleTypeEJBLocal;
import com.comasw.ejb.parameterization.ApplicationLevelEJBLocal;
import com.comasw.ejb.parameterization.ConsumptionClassEJBLocal;
import com.comasw.ejb.parameterization.DiscountTypeEJBLocal;
import com.comasw.ejb.parameterization.PaymentMethodEJBLocal;
import com.comasw.ejb.parameterization.StatusEJBLocal;
import com.comasw.ejb.parameterization.TaxTypeEJBLocal;
import com.comasw.model.tables.pojos.CtBillCycleType;
import com.comasw.model.tables.pojos.PtApplicationLevel;
import com.comasw.model.tables.pojos.PtConsumptionClass;
import com.comasw.model.tables.pojos.PtDiscountType;
import com.comasw.model.tables.pojos.PtPaymentMethod;
import com.comasw.model.tables.pojos.PtStatus;
import com.comasw.model.tables.pojos.PtTaxType;


public class BasicTypeWithLists<T> extends BasicType<T> {

	Logger logger = (Logger) LogManager.getLogger(BasicTypeWithLists.class);

	@EJB
	private StatusEJBLocal statusEJB;

	@EJB
	private ApplicationLevelEJBLocal applicationLevelEJB;

	@EJB
	private DiscountTypeEJBLocal discountTypeEJB;

	@EJB
	private ConsumptionClassEJBLocal consumptionClassEJB;

	@EJB
	private PaymentMethodEJBLocal paymentMethodEJB;
	
	@EJB
	private TaxTypeEJBLocal taxTypeEJB;
	
	@EJB
	private BillCycleTypeEJBLocal billCycleTypeEJB;


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
	 * Return the list of select items with the status data
	 */
	public List<SelectItem> statusCatalogSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<PtStatus> list = statusEJB.findDataForCatalog();

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

	/*
	 * Return the list of select items with the discount type data
	 */
	public List<SelectItem> discountTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<PtDiscountType> list = discountTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find discount type list");
		} else {
			for (PtDiscountType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getDiscountTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	/*
	 * Return the list of select items with the consumption class data
	 */
	public List<SelectItem> consumptionClassSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<PtConsumptionClass> list = consumptionClassEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find consumption class list");
		} else {
			for (PtConsumptionClass p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getConsumptionClassId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	/*
	 * Return the list of select items with the payment method  data
	 */
	public List<SelectItem> paymentMethodSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<PtPaymentMethod> list = paymentMethodEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find payment method list");
		} else {
			for (PtPaymentMethod p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getPaymentMethodId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	
	/*
	 * Return the list of select items with the consumption class data
	 */
	public List<SelectItem> taxTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<PtTaxType> list = taxTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find payment method list");
		} else {
			for (PtTaxType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getTaxTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	public List<SelectItem> ordinaryBillCycleSelectItemsMenu() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtBillCycleType> list = billCycleTypeEJB.findOrdinaryCycleType();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find profile list");
		} else {
			for (CtBillCycleType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getBillCycleTypeId());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	public List<SelectItem> correctiveBillCycleSelectItemsMenu() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtBillCycleType> list = billCycleTypeEJB.findCorrectiveCycleType();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find profile list");
		} else {
			for (CtBillCycleType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getBillCycleTypeId());
				selectItem.add(item);
			}
		}
		return selectItem;
	}
	
}
