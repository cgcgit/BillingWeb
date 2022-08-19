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
			logger.error("ERROR - Not find promotion type list");
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
	

}
