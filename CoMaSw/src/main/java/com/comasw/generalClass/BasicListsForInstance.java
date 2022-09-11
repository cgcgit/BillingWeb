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

import com.comasw.ejb.catalog.relationType.ProductFeeTypeEJBLocal;
import com.comasw.ejb.catalog.relationType.ProductPromotionTypeEJBLocal;
import com.comasw.ejb.catalog.relationType.ServiceFeeTypeEJBLocal;
import com.comasw.ejb.catalog.relationType.ServicePromotionTypeEJBLocal;
import com.comasw.ejb.catalog.type.AccountTypeEJBLocal;
import com.comasw.ejb.catalog.type.CustomerTypeEJBLocal;
import com.comasw.ejb.catalog.type.FeeTypeEJBLocal;
import com.comasw.ejb.catalog.type.ProductTypeEJBLocal;
import com.comasw.ejb.catalog.type.PromotionTypeEJBLocal;
import com.comasw.ejb.catalog.type.ServiceTypeEJBLocal;
import com.comasw.ejb.parameterization.IdentityCardTypeEJBLocal;
import com.comasw.ejb.parameterization.StatusEJBLocal;
import com.comasw.model.tables.pojos.CtAccountType;
import com.comasw.model.tables.pojos.CtCustomerType;
import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.model.tables.pojos.CtProductType;
import com.comasw.model.tables.pojos.CtPromotionType;
import com.comasw.model.tables.pojos.CtServiceType;
import com.comasw.model.tables.pojos.PtIdentityCardType;
import com.comasw.model.tables.pojos.PtStatus;

/**
 * @author catuxa
 *
 */
public class BasicListsForInstance<T> extends BasicTypeWithLists<T> {

	Logger logger = (Logger) LogManager.getLogger(BasicListsForInstance.class);

	@EJB
	private StatusEJBLocal statusEJB;

	@EJB
	private IdentityCardTypeEJBLocal identityCardTypeEJB;

	@EJB
	private CustomerTypeEJBLocal customerTypeEJB;

	@EJB
	private AccountTypeEJBLocal accountTypeEJB;

	@EJB
	private ProductTypeEJBLocal productTypeEJB;

	@EJB
	private ServiceTypeEJBLocal serviceTypeEJB;

	@EJB
	private PromotionTypeEJBLocal promotionTypeEJB;

	@EJB
	private FeeTypeEJBLocal feeTypeEJB;

	@EJB
	private ProductPromotionTypeEJBLocal productPromotionTypeEJB;

	@EJB
	private ServicePromotionTypeEJBLocal servicePromotionTypeEJB;

	@EJB
	private ProductFeeTypeEJBLocal productFeeTypeEJB;

	@EJB
	private ServiceFeeTypeEJBLocal serviceFeeTypeEJB;

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

	/*
	 * Return the list of select items with the identity card type data
	 */
	public List<SelectItem> identityCardTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<PtIdentityCardType> list = identityCardTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find identity card type list");
		} else {
			for (PtIdentityCardType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getIdentityCardTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	/*
	 * Return the list of select items with the customer type data
	 */
	public List<SelectItem> customerTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtCustomerType> list = customerTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find customer type list");
		} else {
			for (CtCustomerType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getCustomerTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	/*
	 * Return the list of select items with the account type data
	 */
	public List<SelectItem> accountTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtAccountType> list = accountTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find account type list");
		} else {
			for (CtAccountType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getAccountTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	/*
	 * Return the list of select items with the product type data
	 */
	public List<SelectItem> productTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtProductType> list = productTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find product type list");
		} else {
			for (CtProductType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getProductTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	/*
	 * Return the list of select items with the service type data
	 */
	public List<SelectItem> serviceTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtServiceType> list = serviceTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find service type list");
		} else {
			for (CtServiceType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getServiceTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	/*
	 * Return the list of select items with the promotion type data
	 */
	public List<SelectItem> promotionTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtPromotionType> list = promotionTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find promotion type list");
		} else {
			for (CtPromotionType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getPromotionTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	/*
	 * Return the list of select items with the fee type data
	 */
	public List<SelectItem> feeTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtFeeType> list = feeTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find fee type list");
		} else {
			for (CtFeeType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getFeeTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	/*
	 * Return the list of select items with the service fee type relation data
	 */
	public List<SelectItem> feeProductRelatedTypeSelectItems(Integer productTypeId) {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtFeeType> list;
		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (productTypeId != null) {
			list = productFeeTypeEJB.findEntityTypeRelated(productTypeId, BasicListsForInstance.ACTIVE_STATUS_CODE);
			if (list.isEmpty()) {
				logger.error("ERROR - Not find product fee type relation list");
			} else {
				for (CtFeeType p : list) {
					SelectItem item = new SelectItem();
					item.setLabel(p.getCode());
					item.setValue(p.getFeeTypeId());
					item.setDescription(p.getDescription());
					selectItem.add(item);
				}
			}
		} else {
			logger.error("ERROR - The product type id is null");
		}

		return selectItem;
	}

	/*
	 * Return the list of select items with the service fee type relation data
	 */
	public List<SelectItem> feeServiceRelatedTypeSelectItems(Integer serviceTypeId) {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtFeeType> list;

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (serviceTypeId != null) {
			list = serviceFeeTypeEJB.findEntityTypeRelated(serviceTypeId, BasicListsForInstance.ACTIVE_STATUS_CODE);
			if (list.isEmpty()) {
				logger.error("ERROR - Not find service fee type relation list");
			} else {
				for (CtFeeType p : list) {
					SelectItem item = new SelectItem();
					item.setLabel(p.getCode());
					item.setValue(p.getFeeTypeId());
					item.setDescription(p.getDescription());
					selectItem.add(item);
				}
			}
		} else {
			logger.error("ERROR - The service type id is null");
		}

		return selectItem;
	}

	/*
	 * Return the list of select items with the product promotion type relation data
	 */
	public List<SelectItem> promotionProductRelatedTypeSelectItems(Integer productTypeId) {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtPromotionType> list;

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (productTypeId != null) {
			list = productPromotionTypeEJB.findEntityTypeRelated(productTypeId,
					BasicListsForInstance.ACTIVE_STATUS_CODE);
			if (list.isEmpty()) {
				logger.error("ERROR - Not find product promotion type relation list");
			} else {
				for (CtPromotionType p : list) {
					SelectItem item = new SelectItem();
					item.setLabel(p.getCode());
					item.setValue(p.getPromotionTypeId());
					item.setDescription(p.getDescription());
					selectItem.add(item);
				}
			}
		} else {
			logger.error("ERROR - The product type id is null");
		}

		return selectItem;
	}

	/*
	 * Return the list of select items with the service promotion type relation data
	 */
	public List<SelectItem> promotionServiceRelatedTypeSelectItems(Integer serviceTypeId) {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtPromotionType> list;

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (serviceTypeId != null) {
			list = servicePromotionTypeEJB.findEntityTypeRelated(serviceTypeId,
					BasicListsForInstance.ACTIVE_STATUS_CODE);
			if (list.isEmpty()) {
				logger.error("ERROR - Not find service promotion type relation list");
			} else {
				for (CtPromotionType p : list) {
					SelectItem item = new SelectItem();
					item.setLabel(p.getCode());
					item.setValue(p.getPromotionTypeId());
					item.setDescription(p.getDescription());
					selectItem.add(item);
				}
			}
		} else {
			logger.error("ERROR - The service type id is null");
		}

		return selectItem;
	}

}
