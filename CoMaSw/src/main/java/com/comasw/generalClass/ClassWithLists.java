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
import com.comasw.ejb.parameterization.ApplicationLevelEJBLocal;
import com.comasw.ejb.parameterization.ConsumptionClassEJBLocal;
import com.comasw.ejb.parameterization.DiscountTypeEJBLocal;
import com.comasw.ejb.parameterization.IdentityCardTypeEJBLocal;
import com.comasw.ejb.parameterization.PaymentMethodEJBLocal;
import com.comasw.ejb.parameterization.StatusEJBLocal;
import com.comasw.ejb.parameterization.TaxTypeEJBLocal;
import com.comasw.model.tables.pojos.CtAccountType;
import com.comasw.model.tables.pojos.CtCustomerType;
import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.model.tables.pojos.CtProductType;
import com.comasw.model.tables.pojos.CtPromotionType;
import com.comasw.model.tables.pojos.CtServiceType;
import com.comasw.model.tables.pojos.PtApplicationLevel;
import com.comasw.model.tables.pojos.PtConsumptionClass;
import com.comasw.model.tables.pojos.PtDiscountType;
import com.comasw.model.tables.pojos.PtIdentityCardType;
import com.comasw.model.tables.pojos.PtPaymentMethod;
import com.comasw.model.tables.pojos.PtStatus;
import com.comasw.model.tables.pojos.PtTaxType;

public class ClassWithLists extends BasicClass{
	
	Logger logger = (Logger) LogManager.getLogger(ClassWithLists.class);
	
	
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
