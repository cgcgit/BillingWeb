/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

*/

package com.comasw.viewController.instance;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.omnifaces.util.Ajax;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.comasw.ejb.instance.AccountEJBLocal;
import com.comasw.ejb.instance.ContractEJBLocal;
import com.comasw.ejb.instance.CustomerEJBLocal;
import com.comasw.ejb.instance.FeeEJBLocal;
import com.comasw.ejb.instance.ProductEJBLocal;
import com.comasw.ejb.instance.PromotionEJBLocal;
import com.comasw.ejb.instance.ServiceEJBLocal;
import com.comasw.generalClass.ClassWithLists;
import com.comasw.model.tables.pojos.VwCustomerInstance;
import com.comasw.model.tables.pojos.ItAccount;
import com.comasw.model.tables.pojos.ItCustomer;
import com.comasw.model.tables.pojos.ItFee;
import com.comasw.model.tables.pojos.ItProduct;
import com.comasw.model.tables.pojos.ItPromotion;
import com.comasw.model.tables.pojos.ItService;
import com.comasw.model.tables.pojos.VwAccountInstance;
import com.comasw.model.tables.pojos.VwProductInstance;
import com.comasw.model.tables.pojos.VwServiceInstance;
import com.comasw.model.tables.pojos.VwFeeInstance;
import com.comasw.model.tables.pojos.VwPromotionInstance;

@Named
@ViewScoped
/**
 * @author catuxa
 *
 */
public class HierarchyController extends ClassWithLists implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3458267485033998206L;

	Logger logger = (Logger) LogManager.getLogger(HierarchyController.class);

	private static String APPLICATION_LEVEL_CODE_PROD = dbDefinitions.getString("APPLICATION_LEVEL_CODE_PROD");
	private static String APPLICATION_LEVEL_CODE_SERV = dbDefinitions.getString("APPLICATION_LEVEL_CODE_SERV");

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private AccountEJBLocal accountEJB;

	@EJB
	private CustomerEJBLocal customerEJB;

	@EJB
	private ContractEJBLocal contractEJB;

	@EJB
	private ProductEJBLocal productEJB;

	@EJB
	private ServiceEJBLocal serviceEJB;

	@EJB
	private FeeEJBLocal feeEJB;

	@EJB
	private PromotionEJBLocal promotionEJB;

	// ---- SEARCH CRITERIA ----\\

	private LocalDateTime searchDate;

	private String searchServiceNr;

	private Integer searchProductId;

	private Integer searchAccountId;

	private String searchAccountIdentityCard;

	private String searchContractNr;

	private Integer searchCustomerId;

	private String searchCustomerIdentityCard;

	private List<VwCustomerInstance> customerDataList;

	private List<VwAccountInstance> accountDataList;
	private List<VwProductInstance> productDataList;
	private List<VwServiceInstance> serviceDataList;

	private List<VwPromotionInstance> promotionProductDataList;
	private List<VwPromotionInstance> promotionServiceDataList;
	private List<VwFeeInstance> feeProductDataList;
	private List<VwFeeInstance> feeServiceDataList;
	
	private List<ItCustomer> customerHistoricDataList;
	private List<ItAccount> accountHistoricDataList;
	private List<ItProduct> productHistoricDataList;
	private List<ItService> serviceHistoricDataList;
	private List<ItPromotion> promotionHistoricDataList;
	private List<ItFee> feeHistoricDataList;
	

	/*
	 * TreeNode<ItCustomer> customer; TreeNode<ItAccount> account;
	 * TreeNode<ItProduct> product; TreeNode<ItPromotion> productPromotion;
	 * TreeNode<ItFee> productFee; TreeNode<ItService> service;
	 * TreeNode<ItPromotion> servicePromotion; TreeNode<ItFee> serviceFee;
	 */

	private TreeNode tree;

	private TreeNode selectedNode;
	
	private boolean showCustomerInfo;
	private boolean showAccountInfo;
	private boolean showProductInfo;
	private boolean showServiceInfo;
	
	private boolean showCustomerHistoricInfo;
	private boolean showAccountHistoricInfo;
	private boolean showProductHistoricInfo;
	private boolean showServiceHistoricInfo;
	private boolean showPromotionHistoricInfo;
	private boolean showFeeHistoricInfo;
	
	
	
	

	/**
	 * @return the showCustomerHistoricInfo
	 */
	public boolean isShowCustomerHistoricInfo() {
		return showCustomerHistoricInfo;
	}

	/**
	 * @param showCustomerHistoricInfo the showCustomerHistoricInfo to set
	 */
	public void setShowCustomerHistoricInfo(boolean showCustomerHistoricInfo) {
		this.showCustomerHistoricInfo = showCustomerHistoricInfo;
	}

	/**
	 * @return the showAccountHistoricInfo
	 */
	public boolean isShowAccountHistoricInfo() {
		return showAccountHistoricInfo;
	}

	/**
	 * @param showAccountHistoricInfo the showAccountHistoricInfo to set
	 */
	public void setShowAccountHistoricInfo(boolean showAccountHistoricInfo) {
		this.showAccountHistoricInfo = showAccountHistoricInfo;
	}

	/**
	 * @return the showProductHistoricInfo
	 */
	public boolean isShowProductHistoricInfo() {
		return showProductHistoricInfo;
	}

	/**
	 * @param showProductHistoricInfo the showProductHistoricInfo to set
	 */
	public void setShowProductHistoricInfo(boolean showProductHistoricInfo) {
		this.showProductHistoricInfo = showProductHistoricInfo;
	}

	/**
	 * @return the showServiceHistoricInfo
	 */
	public boolean isShowServiceHistoricInfo() {
		return showServiceHistoricInfo;
	}

	/**
	 * @param showServiceHistoricInfo the showServiceHistoricInfo to set
	 */
	public void setShowServiceHistoricInfo(boolean showServiceHistoricInfo) {
		this.showServiceHistoricInfo = showServiceHistoricInfo;
	}

	/**
	 * @return the showPromotionHistoricInfo
	 */
	public boolean isShowPromotionHistoricInfo() {
		return showPromotionHistoricInfo;
	}

	/**
	 * @param showPromotionHistoricInfo the showPromotionHistoricInfo to set
	 */
	public void setShowPromotionHistoricInfo(boolean showPromotionHistoricInfo) {
		this.showPromotionHistoricInfo = showPromotionHistoricInfo;
	}

	/**
	 * @return the showFeeHistoricInfo
	 */
	public boolean isShowFeeHistoricInfo() {
		return showFeeHistoricInfo;
	}

	/**
	 * @param showFeeHistoricInfo the showFeeHistoricInfo to set
	 */
	public void setShowFeeHistoricInfo(boolean showFeeHistoricInfo) {
		this.showFeeHistoricInfo = showFeeHistoricInfo;
	}

	/**
	 * @param showProductInfo the showProductInfo to set
	 */
	public void setShowProductInfo(boolean showProductInfo) {
		this.showProductInfo = showProductInfo;
	}

	/**
	 * @return the showCustomerInfo
	 */
	public boolean getShowCustomerInfo() {
		return showCustomerInfo;
	}

	/**
	 * @param showCustomerInfo the showCustomerInfo to set
	 */
	public void setShowCustomerInfo(boolean showCustomerInfo) {
		this.showCustomerInfo = showCustomerInfo;
	}

	/**
	 * @return the showAccountInfo
	 */
	public boolean getShowAccountInfo() {
		return showAccountInfo;
	}

	/**
	 * @param showAccountInfo the showAccountInfo to set
	 */
	public void setShowAccountInfo(boolean showAccountInfo) {
		this.showAccountInfo = showAccountInfo;
	}

	/**
	 * @return the showProductInfo
	 */
	public boolean getShowProductInfo() {
		return showProductInfo;
	}

	/**
	 * @param showProductInfo the showProductInfo to set
	 */
	public void setShowProductInfo(Boolean showProductInfo) {
		this.showProductInfo = showProductInfo;
	}

	/**
	 * @return the showServiceInfo
	 */
	public boolean getShowServiceInfo() {
		return showServiceInfo;
	}

	/**
	 * @param showServiceInfo the showServiceInfo to set
	 */
	public void setShowServiceInfo(boolean showServiceInfo) {
		this.showServiceInfo = showServiceInfo;
	}

	/**
	 * @return the selectedNode
	 */
	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	/**
	 * @param selectedNode the selectedNode to set
	 */
	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	/**
	 * @return the tree
	 */
	public TreeNode getTree() {
		return tree;
	}

	/**
	 * @param tree the tree to set
	 */
	public void setTree(TreeNode tree) {
		this.tree = tree;
	}

	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	/**
	 * @return the searchDate
	 */
	public LocalDateTime getSearchDate() {
		return searchDate;
	}

	/**
	 * @param searchDate the searchDate to set
	 */
	public void setSearchDate(LocalDateTime searchDate) {
		this.searchDate = searchDate;
	}

	/**
	 * @return the searchServiceNr
	 */
	public String getSearchServiceNr() {
		return searchServiceNr;
	}

	/**
	 * @param searchServiceNr the searchServiceNr to set
	 */
	public void setSearchServiceNr(String searchServiceNr) {
		this.searchServiceNr = searchServiceNr;
	}

	/**
	 * @return the searchProductId
	 */
	public Integer getSearchProductId() {
		return searchProductId;
	}

	/**
	 * @param searchProductId the searchProductId to set
	 */
	public void setSearchProductId(Integer searchProductId) {
		this.searchProductId = searchProductId;
	}

	/**
	 * @return the searchAccountId
	 */
	public Integer getSearchAccountId() {
		return searchAccountId;
	}

	/**
	 * @param searchAccountId the searchAccountId to set
	 */
	public void setSearchAccountId(Integer searchAccountId) {
		this.searchAccountId = searchAccountId;
	}

	/**
	 * @return the searchAccountIdentityCard
	 */
	public String getSearchAccountIdentityCard() {
		return searchAccountIdentityCard;
	}

	/**
	 * @param searchAccountIdentityCard the searchAccountIdentityCard to set
	 */
	public void setSearchAccountIdentityCard(String searchAccountIdentityCard) {
		this.searchAccountIdentityCard = searchAccountIdentityCard;
	}

	/**
	 * @return the searchContractNr
	 */
	public String getSearchContractNr() {
		return searchContractNr;
	}

	/**
	 * @param searchContractNr the searchContractNr to set
	 */
	public void setSearchContractNr(String searchContractNr) {
		this.searchContractNr = searchContractNr;
	}

	/**
	 * @return the searchCustomerId
	 */
	public Integer getSearchCustomerId() {
		return searchCustomerId;
	}

	/**
	 * @param searchCustomerId the searchCustomerId to set
	 */
	public void setSearchCustomerId(Integer searchCustomerId) {
		this.searchCustomerId = searchCustomerId;
	}

	/**
	 * @return the searchCustomerIdentityCard
	 */
	public String getSearchCustomerIdentityCard() {
		return searchCustomerIdentityCard;
	}

	/**
	 * @param searchCustomerIdentityCard the searchCustomerIdentityCard to set
	 */
	public void setSearchCustomerIdentityCard(String searchCustomerIdentityCard) {
		this.searchCustomerIdentityCard = searchCustomerIdentityCard;
	}

	/**
	 * @return the customerDataList
	 */
	public List<VwCustomerInstance> getCustomerDataList() {
		return customerDataList;
	}

	/**
	 * @param customerDataList the customerDataList to set
	 */
	public void setCustomerDataList(List<VwCustomerInstance> customerDataList) {
		this.customerDataList = customerDataList;
	}

	/**
	 * @return the accountDataList
	 */
	public List<VwAccountInstance> getAccountDataList() {
		return accountDataList;
	}

	/**
	 * @param accountDataList the accountDataList to set
	 */
	public void setAccountDataList(List<VwAccountInstance> accountDataList) {
		this.accountDataList = accountDataList;
	}

	/**
	 * @return the productDataList
	 */
	public List<VwProductInstance> getProductDataList() {
		return productDataList;
	}

	/**
	 * @param productDataList the productDataList to set
	 */
	public void setProductDataList(List<VwProductInstance> productDataList) {
		this.productDataList = productDataList;
	}

	/**
	 * @return the serviceDataList
	 */
	public List<VwServiceInstance> getServiceDataList() {
		return serviceDataList;
	}

	/**
	 * @param serviceDataList the serviceDataList to set
	 */
	public void setServiceDataList(List<VwServiceInstance> serviceDataList) {
		this.serviceDataList = serviceDataList;
	}

	/**
	 * @return the promotionProductDataList
	 */
	public List<VwPromotionInstance> getPromotionProductDataList() {
		return promotionProductDataList;
	}

	/**
	 * @param promotionProductDataList the promotionProductDataList to set
	 */
	public void setPromotionProductDataList(List<VwPromotionInstance> promotionProductDataList) {
		this.promotionProductDataList = promotionProductDataList;
	}

	/**
	 * @return the promotionServiceDataList
	 */
	public List<VwPromotionInstance> getPromotionServiceDataList() {
		return promotionServiceDataList;
	}

	/**
	 * @param promotionServiceDataList the promotionServiceDataList to set
	 */
	public void setPromotionServiceDataList(List<VwPromotionInstance> promotionServiceDataList) {
		this.promotionServiceDataList = promotionServiceDataList;
	}

	/**
	 * @return the feeProductDataList
	 */
	public List<VwFeeInstance> getFeeProductDataList() {
		return feeProductDataList;
	}

	/**
	 * @param feeProductDataList the feeProductDataList to set
	 */
	public void setFeeProductDataList(List<VwFeeInstance> feeProductDataList) {
		this.feeProductDataList = feeProductDataList;
	}

	/**
	 * @return the feeServiceDataList
	 */
	public List<VwFeeInstance> getFeeServiceDataList() {
		return feeServiceDataList;
	}

	/**
	 * @param feeServiceDataList the feeServiceDataList to set
	 */
	public void setFeeServiceDataList(List<VwFeeInstance> feeServiceDataList) {
		this.feeServiceDataList = feeServiceDataList;
	}

	
	/**
	 * @return the customerHistoricDataList
	 */
	public List<ItCustomer> getCustomerHistoricDataList() {
		return customerHistoricDataList;
	}

	/**
	 * @param customerHistoricDataList the customerHistoricDataList to set
	 */
	public void setCustomerHistoricDataList(List<ItCustomer> customerHistoricDataList) {
		this.customerHistoricDataList = customerHistoricDataList;
	}

	/**
	 * @return the accountHistoricDataList
	 */
	public List<ItAccount> getAccountHistoricDataList() {
		return accountHistoricDataList;
	}

	/**
	 * @param accountHistoricDataList the accountHistoricDataList to set
	 */
	public void setAccountHistoricDataList(List<ItAccount> accountHistoricDataList) {
		this.accountHistoricDataList = accountHistoricDataList;
	}

	/**
	 * @return the productHistoricDataList
	 */
	public List<ItProduct> getProductHistoricDataList() {
		return productHistoricDataList;
	}

	/**
	 * @param productHistoricDataList the productHistoricDataList to set
	 */
	public void setProductHistoricDataList(List<ItProduct> productHistoricDataList) {
		this.productHistoricDataList = productHistoricDataList;
	}

	/**
	 * @return the serviceHistoricDataList
	 */
	public List<ItService> getServiceHistoricDataList() {
		return serviceHistoricDataList;
	}

	/**
	 * @param serviceHistoricDataList the serviceHistoricDataList to set
	 */
	public void setServiceHistoricDataList(List<ItService> serviceHistoricDataList) {
		this.serviceHistoricDataList = serviceHistoricDataList;
	}

	/**
	 * @return the promotionHistoricDataList
	 */
	public List<ItPromotion> getPromotionHistoricDataList() {
		return promotionHistoricDataList;
	}

	/**
	 * @param promotionHistoricDataList the promotionHistoricDataList
	 *                                         to set
	 */
	public void setPromotionHistoricDataList(List<ItPromotion> promotionHistoricDataList) {
		this.promotionHistoricDataList = promotionHistoricDataList;
	}

	/**
	 * @return the feeHistoricDataList
	 */
	public List<ItFee> getFeeHistoricDataList() {
		return feeHistoricDataList;
	}

	/**
	 * @param feeHistoricDataList the feeHistoricDataList to set
	 */
	public void setFeeHistoricDataList(List<ItFee> feeHistoricDataList) {
		this.feeHistoricDataList = feeHistoricDataList;
	}
	
	


	

	// -------------------
	// METHODS
	// -------------------


	/**
	 * 
	 */
	public HierarchyController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		if (this.getTree() == null) {
			this.setTree(new DefaultTreeNode("root", null));
		}

		if (this.getSearchDate() == null) {
			this.setSearchDate(LocalDate.now().atStartOfDay());
		}

		if (this.getCustomerDataList() == null) {
			this.setCustomerDataList(new ArrayList<VwCustomerInstance>());
		}

		if (this.getAccountDataList() == null) {
			this.setAccountDataList(new ArrayList<VwAccountInstance>());
		}

		if (this.getProductDataList() == null) {
			this.setProductDataList(new ArrayList<VwProductInstance>());
		}

		
		if (this.getServiceDataList() == null) {
			this.setServiceDataList(new ArrayList<VwServiceInstance>());
		}

		
		if (this.getPromotionProductDataList() == null) {
			this.setPromotionProductDataList(new ArrayList<VwPromotionInstance>());
		}

		if (this.getPromotionServiceDataList() == null) {
			this.setPromotionServiceDataList(new ArrayList<VwPromotionInstance>());
		}

		
		if (this.getFeeServiceDataList() == null) {
			this.setFeeServiceDataList(new ArrayList<VwFeeInstance>());
		}

		
		if (this.getCustomerHistoricDataList() == null) {
			this.setCustomerHistoricDataList(new ArrayList<ItCustomer>());
		}
		
		
		
		if (this.getAccountHistoricDataList() == null) {
			this.setAccountHistoricDataList(new ArrayList<ItAccount>());
		}
		
		
		if (this.getProductHistoricDataList() == null) {
			this.setProductHistoricDataList(new ArrayList<ItProduct>());
		}
		
		
		
		if (this.getServiceHistoricDataList() == null) {
			this.setServiceHistoricDataList(new ArrayList<ItService>());
		}
		
		
		
		if (this.getPromotionHistoricDataList() == null) {
			this.setPromotionHistoricDataList(new ArrayList<ItPromotion>());
		}
		
		
		
		if (this.getFeeHistoricDataList() == null) {
			this.setFeeHistoricDataList(new ArrayList<ItFee>());
		}
		
		
	}

	private void searchParentCustomer() {
		String messageDetail, message;
		boolean continueSearch = true;

		message = "SEARCH HIERACHY DATA";

		if ((this.getSearchDate() == null) && (this.getSearchCustomerId() == null)
				&& (this.getSearchCustomerIdentityCard() == null) && (this.getSearchAccountId() == null)
				&& (this.getSearchAccountIdentityCard() == null) && (this.getSearchContractNr() == null)
				&& (this.getSearchProductId() == null) && (this.getSearchServiceNr() == null)) {
			messageDetail = "Error - The search criteria is null. Please fill at least one of the criteria search fill.";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {

			if ((this.getSearchCustomerIdentityCard() != null) && (!this.getSearchCustomerIdentityCard().isEmpty())) {
				this.setSearchCustomerIdentityCard(this.getSearchCustomerIdentityCard().toUpperCase().trim());
			}

			if ((this.getSearchAccountIdentityCard() != null) && (!this.getSearchAccountIdentityCard().isEmpty())) {
				this.setSearchAccountIdentityCard(this.getSearchAccountIdentityCard().toUpperCase().trim());
			}

			if ((this.getSearchContractNr() != null) && (!this.getSearchContractNr().isEmpty())) {
				this.setSearchContractNr(this.getSearchContractNr().toUpperCase().trim());
			}

			if ((this.getSearchServiceNr() != null) && (!this.getSearchServiceNr().isEmpty())) {
				this.setSearchServiceNr(this.getSearchServiceNr().toUpperCase().trim());
			}

			if (this.getSearchServiceNr() != null) {
				continueSearch = false;
				this.findRootByService();
				if (this.getCustomerDataList().isEmpty()) {
					messageDetail = "Error - No data to show for the specified search criteria.";
					logger.error(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
				}
			}

			if ((continueSearch) && (this.getSearchProductId() != null)) {
				this.findRootByProduct();
				if (this.getCustomerDataList().isEmpty()) {
					messageDetail = "Error - No data to show for the specified search criteria.";
					logger.error(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
				}
			}

			if ((continueSearch) && ((this.getSearchAccountId() != null)
					|| (this.getSearchAccountIdentityCard() != null) || (this.getSearchContractNr() != null))) {
				this.findRootByAccount();
				if (this.getCustomerDataList().isEmpty()) {
					messageDetail = "Error - No data to show for the specified search criteria.";
					logger.error(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
				}
			}
			if ((continueSearch)
					&& ((this.getSearchCustomerId() != null) || (this.getSearchCustomerIdentityCard() != null))) {
				this.setCustomerDataList(customerEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate),
						Optional.ofNullable(this.getSearchCustomerId()), Optional.empty(), Optional.empty(),
						Optional.ofNullable(this.getSearchCustomerIdentityCard()), Optional.empty(), Optional.empty(),
						Optional.empty(), Optional.empty()));

				this.setCustomerHistoricDataList(customerEJB.findInstanceWithParameters(Optional.ofNullable(searchDate),
						Optional.ofNullable(this.getSearchCustomerId()), Optional.empty(), Optional.empty(),
						Optional.ofNullable(this.getSearchCustomerIdentityCard()), Optional.empty(), Optional.empty(),
						Optional.empty(), Optional.empty()));

				if (this.getCustomerDataList().isEmpty()) {
					messageDetail = "Error - No data to show for the specified search criteria.";
					logger.error(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
				}
			}

		}

	}

	public void findRootByService() {
		List<VwServiceInstance> currentEntityList = new ArrayList<VwServiceInstance>();
		List<VwCustomerInstance> list = new ArrayList<VwCustomerInstance>();
		List<ItCustomer> historicList = new ArrayList<ItCustomer>();

		currentEntityList = serviceEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate), true,
				Optional.empty(), Optional.empty(), Optional.ofNullable(this.getSearchServiceNr()), Optional.empty(),
				Optional.ofNullable(this.getSearchProductId()), Optional.empty(),
				Optional.ofNullable(this.getSearchContractNr()), Optional.ofNullable(this.getSearchAccountId()),
				Optional.ofNullable(this.getSearchAccountIdentityCard()), Optional.empty(),
				Optional.ofNullable(this.getSearchCustomerId()),
				Optional.ofNullable(this.getSearchCustomerIdentityCard()), Optional.empty());

		if (!currentEntityList.isEmpty()) {
			this.getCustomerDataList().clear();
			this.getCustomerHistoricDataList().clear();

			for (VwServiceInstance p : currentEntityList) {
				list = customerEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate),
						Optional.ofNullable(p.getCustomerId()), Optional.empty(), Optional.empty(), Optional.empty(),
						Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
				if (!list.isEmpty()) {
					// add the customer record to customer list
					for (VwCustomerInstance q : list) {
						if (!this.getCustomerDataList().contains(q)) {
							this.getCustomerDataList().add(q);
							historicList = customerEJB.findDataByCustomerId(q.getCustomerId());
							for (ItCustomer r : historicList) {
								if (!this.getCustomerHistoricDataList().contains(r)) {
									// add the customer historic record to customer record list
									this.getCustomerHistoricDataList().add(r);
								}
							}

						}
					}
				}
			}
		}

	}

	public void findRootByProduct() {
		List<VwProductInstance> currentEntityList = new ArrayList<VwProductInstance>();
		List<VwCustomerInstance> list = new ArrayList<VwCustomerInstance>();
		List<ItCustomer> historicList = new ArrayList<ItCustomer>();

		currentEntityList = productEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate), true,
				Optional.ofNullable(this.getSearchProductId()), Optional.empty(), Optional.empty(),
				Optional.ofNullable(this.getSearchContractNr()), Optional.ofNullable(this.getSearchAccountId()),
				Optional.ofNullable(this.getSearchAccountIdentityCard()), Optional.empty(),
				Optional.ofNullable(this.getSearchCustomerId()),
				Optional.ofNullable(this.getSearchCustomerIdentityCard()), Optional.empty());

		if (!currentEntityList.isEmpty()) {
			this.getCustomerDataList().clear();
			this.getCustomerHistoricDataList().clear();

			for (VwProductInstance p : currentEntityList) {
				list = customerEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate),
						Optional.ofNullable(p.getCustomerId()), Optional.empty(), Optional.empty(), Optional.empty(),
						Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
				if (!list.isEmpty()) {
					// add the customer record to customer list
					for (VwCustomerInstance q : list) {
						if (!this.getCustomerDataList().contains(q)) {
							this.getCustomerDataList().add(q);
							historicList = customerEJB.findDataByCustomerId(q.getCustomerId());
							for (ItCustomer r : historicList) {
								if (!this.getCustomerHistoricDataList().contains(r)) {
									// add the customer historic record to customer record list
									this.getCustomerHistoricDataList().add(r);
								}
							}

						}
					}
				}
			}
		}

	}

	public void findRootByAccount() {
		List<VwAccountInstance> currentEntityList = new ArrayList<VwAccountInstance>();
		List<VwCustomerInstance> list = new ArrayList<VwCustomerInstance>();
		List<ItCustomer> historicList = new ArrayList<ItCustomer>();

		currentEntityList = accountEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate),
				Optional.ofNullable(this.getSearchContractNr()), Optional.ofNullable(this.getSearchAccountId()),
				Optional.empty(), Optional.ofNullable(this.getSearchAccountIdentityCard()),
				Optional.ofNullable(this.getSearchCustomerId()), Optional.empty(),
				Optional.ofNullable(this.getSearchCustomerIdentityCard()));

		if (!currentEntityList.isEmpty()) {
			this.getCustomerDataList().clear();
			this.getCustomerHistoricDataList().clear();

			for (VwAccountInstance p : currentEntityList) {
				list = customerEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate),
						Optional.ofNullable(p.getCustomerId()), Optional.empty(), Optional.empty(), Optional.empty(),
						Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
				if (!list.isEmpty()) {
					// add the customer record to customer list
					for (VwCustomerInstance q : list) {
						if (!this.getCustomerDataList().contains(q)) {
							this.getCustomerDataList().add(q);
							historicList = customerEJB.findDataByCustomerId(q.getCustomerId());
							for (ItCustomer r : historicList) {
								if (!this.getCustomerHistoricDataList().contains(r)) {
									// add the customer historic record to customer record list
									this.getCustomerHistoricDataList().add(r);
								}
							}

						}
					}
				}
			}
		}
	}

	public TreeNode myTree() {
		String message, messageDetail;
		message = "CREATING DATA TREE";

		
		tree = new DefaultTreeNode("root", null);		

		try {		
			for (VwCustomerInstance c : this.getCustomerDataList()) {
				TreeNode custNode = new DefaultTreeNode("customer", c, tree);
				this.setAccountDataList(accountEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate),
						Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
						Optional.ofNullable(c.getCustomerId()), Optional.empty(), Optional.empty()));
				for (VwAccountInstance a : this.getAccountDataList()) {
					TreeNode accNode = new DefaultTreeNode("account", a, custNode);
					this.setProductDataList(productEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate),
							true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
							Optional.ofNullable(a.getAccountId()), Optional.empty(), Optional.empty(),
							Optional.ofNullable(a.getCustomerId()), Optional.empty(), Optional.empty()));
					for (VwProductInstance p : this.getProductDataList()) {
						TreeNode prodNode = new DefaultTreeNode("product", p, accNode);
						this.setServiceDataList(
								serviceEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate), true,
										Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
										Optional.ofNullable(p.getProductId()), Optional.empty(), Optional.empty(),
										Optional.ofNullable(p.getAccountId()), Optional.empty(), Optional.empty(),
										Optional.ofNullable(p.getCustomerId()), Optional.empty(), Optional.empty()));
						for (VwServiceInstance s : this.getServiceDataList()) {
							TreeNode servNode = new DefaultTreeNode("service", s, prodNode);
							this.setFeeServiceDataList(feeEJB.findInstanceViewWithParameters(
									Optional.ofNullable(searchDate), true, Optional.empty(), Optional.empty(),
									Optional.empty(), Optional.empty(),
									Optional.ofNullable(HierarchyController.APPLICATION_LEVEL_CODE_SERV),
									Optional.ofNullable(s.getServiceId()), Optional.empty(), Optional.empty(),
									Optional.ofNullable(s.getProductId()), Optional.empty(), Optional.empty(),
									Optional.ofNullable(s.getAccountId()), Optional.empty(), Optional.empty(),
									Optional.ofNullable(s.getCustomerId()), Optional.empty(), Optional.empty()));
							for (VwFeeInstance fs : this.getFeeServiceDataList()) {
								TreeNode feeServNode = new DefaultTreeNode("feeService", fs, servNode);
							}

							this.setPromotionServiceDataList(
									promotionEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate), true,
											Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
											Optional.ofNullable(HierarchyController.APPLICATION_LEVEL_CODE_SERV),
											Optional.ofNullable(s.getServiceId()), Optional.empty(), Optional.empty(),
											Optional.ofNullable(s.getProductId()), Optional.empty(), Optional.empty(),
											Optional.ofNullable(s.getAccountId()), Optional.empty(), Optional.empty(),
											Optional.ofNullable(s.getCustomerId()), Optional.empty(), Optional.empty()));
							for (VwPromotionInstance ps : this.getPromotionServiceDataList()) {
								TreeNode promoServNode = new DefaultTreeNode("promotionService", ps, servNode);
							}

						}

						this.setFeeProductDataList(
								feeEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate), true,
										Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
										Optional.ofNullable(HierarchyController.APPLICATION_LEVEL_CODE_PROD),
										Optional.empty(), Optional.empty(), Optional.empty(),
										Optional.ofNullable(p.getProductId()), Optional.empty(), Optional.empty(),
										Optional.ofNullable(p.getAccountId()), Optional.empty(), Optional.empty(),
										Optional.ofNullable(p.getCustomerId()), Optional.empty(), Optional.empty()));
						for (VwFeeInstance fp : this.getFeeProductDataList()) {
							TreeNode feeProdNode = new DefaultTreeNode("feeProduct", fp, prodNode);
						}

						this.setPromotionProductDataList(
								promotionEJB.findInstanceViewWithParameters(Optional.ofNullable(searchDate), true,
										Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
										Optional.ofNullable(HierarchyController.APPLICATION_LEVEL_CODE_PROD),
										Optional.empty(), Optional.empty(), Optional.empty(),
										Optional.ofNullable(p.getProductId()), Optional.empty(), Optional.empty(),
										Optional.ofNullable(p.getAccountId()), Optional.empty(), Optional.empty(),
										Optional.ofNullable(p.getCustomerId()), Optional.empty(), Optional.empty()));
						for (VwPromotionInstance pp : this.getPromotionProductDataList()) {
							TreeNode promoProdNode = new DefaultTreeNode("promotionProduct", pp, prodNode);
						}

					}

				}
			}
		} catch (EJBException e) {
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwDataAccessException")) {
				messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			} else if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
				messageDetail = "PARSE ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			} else {
				messageDetail = "ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);
			}

		} catch (Exception e) {
			messageDetail = "ERROR - " + e.getMessage();
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);
		}

		return tree;

	}

	public void pushSearchButton() {
		
		this.setShowConditionsToDefault();
		this.searchParentCustomer();
		this.myTree();
		Ajax.update("form:tree");

	}

	public void onNodeExpand(NodeExpandEvent event) {
		// 
		
	}

	public void onNodeCollapse(NodeCollapseEvent event) {
		
	}

	public void onNodeSelect(NodeSelectEvent event) {
		//TreeNode object = (TreeNode) event.getTreeNode().getData();
		this.setShowConditionsToDefault();
		switch(((TreeNode) event.getTreeNode()).getType())
	     {
	        case "customer":
	        	this.setShowCustomerInfo(true);	        	
	        	this.setShowCustomerHistoricInfo(true);
	        	this.setCustomerHistoricDataList(customerEJB.findDataByCustomerId(((VwCustomerInstance)event.getTreeNode().getData()).getCustomerId()));	 
	        	this.getCustomerDataList().clear();
	        	this.getCustomerDataList().add((VwCustomerInstance)event.getTreeNode().getData());
	        	break;
	        case "account":
	        	this.setShowCustomerInfo(true);
	        	this.setShowAccountInfo(true);
	        	this.setShowAccountHistoricInfo(true);
	        	this.setAccountHistoricDataList(accountEJB.findDataByAccountId(((VwAccountInstance)event.getTreeNode().getData()).getAccountId()));
	        	this.getAccountDataList().clear();
	        	this.getAccountDataList().add((VwAccountInstance)event.getTreeNode().getData());
	        	this.getCustomerDataList().clear();
	        	this.getCustomerDataList().add((VwCustomerInstance)event.getTreeNode().getParent().getData());
	        	break;
	        case "product":
	        	this.setShowCustomerInfo(true);
	        	this.setShowAccountInfo(true);	        	
	        	this.setShowProductInfo(true);
	        	this.setShowProductHistoricInfo(true);
	        	this.setProductHistoricDataList(productEJB.findDataByProductId(((VwProductInstance)event.getTreeNode().getData()).getProductId()));
	        	this.getProductDataList().clear();
	        	this.getProductDataList().add((VwProductInstance)event.getTreeNode().getData());
	        	this.getAccountDataList().clear();
	        	this.getAccountDataList().add((VwAccountInstance)event.getTreeNode().getParent().getData());
	        	this.getCustomerDataList().clear();
	        	this.getCustomerDataList().add((VwCustomerInstance)event.getTreeNode().getParent().getParent().getData());
	        	break;	        	
	        case "service":
	        	this.setShowCustomerInfo(true);
	        	this.setShowAccountInfo(true);
	        	this.setShowProductInfo(true);
	        	this.setShowServiceInfo(true);
	        	this.setShowServiceHistoricInfo(true);
	        	this.setServiceHistoricDataList(serviceEJB.findDataByServiceId(((VwServiceInstance)event.getTreeNode().getData()).getServiceId()));
	        	this.getServiceDataList().clear();
	        	this.getServiceDataList().add((VwServiceInstance)event.getTreeNode().getData());
	        	this.getProductDataList().clear();
	        	this.getProductDataList().add((VwProductInstance)event.getTreeNode().getParent().getData());
	        	this.getAccountDataList().clear();
	        	this.getAccountDataList().add((VwAccountInstance)event.getTreeNode().getParent().getParent().getData());
	        	this.getCustomerDataList().clear();
	        	this.getCustomerDataList().add((VwCustomerInstance)event.getTreeNode().getParent().getParent().getParent().getData());
	        	break;
	        case "feeProduct":
	        	this.setShowCustomerInfo(true);
	        	this.setShowAccountInfo(true);
	        	this.setShowProductInfo(true);
	        	this.setShowFeeHistoricInfo(true);
	        	this.setFeeHistoricDataList(feeEJB.findDataByFeeId(((VwFeeInstance)event.getTreeNode().getData()).getFeeId()));	        	
	        	this.getProductDataList().clear();
	        	this.getProductDataList().add((VwProductInstance)event.getTreeNode().getParent().getData());
	        	this.getAccountDataList().clear();
	        	this.getAccountDataList().add((VwAccountInstance)event.getTreeNode().getParent().getParent().getData());
	        	this.getCustomerDataList().clear();
	        	this.getCustomerDataList().add((VwCustomerInstance)event.getTreeNode().getParent().getParent().getParent().getData());
	        	break;
	        case "promotionProduct":
	        	this.setShowCustomerInfo(true);
	        	this.setShowAccountInfo(true);
	        	this.setShowProductInfo(true);
	        	this.setShowPromotionHistoricInfo(true);
	        	this.setPromotionHistoricDataList(promotionEJB.findDataByPromotionId(((VwPromotionInstance)event.getTreeNode().getData()).getPromotionId()));
	        	this.getProductDataList().clear();
	        	this.getProductDataList().add((VwProductInstance)event.getTreeNode().getParent().getData());
	        	this.getAccountDataList().clear();
	        	this.getAccountDataList().add((VwAccountInstance)event.getTreeNode().getParent().getParent().getData());
	        	this.getCustomerDataList().clear();
	        	this.getCustomerDataList().add((VwCustomerInstance)event.getTreeNode().getParent().getParent().getParent().getData());

	        	break;
	        case "feeService":
	        	this.setShowCustomerInfo(true);
	        	this.setShowAccountInfo(true);
	        	this.setShowProductInfo(true);
	        	this.setShowServiceInfo(true);	
	        	this.setShowFeeHistoricInfo(true);
	        	this.setFeeHistoricDataList(feeEJB.findDataByFeeId(((VwFeeInstance)event.getTreeNode().getData()).getFeeId()));
	        	this.getServiceDataList().clear();
	        	this.getServiceDataList().add((VwServiceInstance)event.getTreeNode().getParent().getData());
	        	this.getProductDataList().clear();
	        	this.getProductDataList().add((VwProductInstance)event.getTreeNode().getParent().getParent().getData());
	        	this.getAccountDataList().clear();
	        	this.getAccountDataList().add((VwAccountInstance)event.getTreeNode().getParent().getParent().getParent().getData());
	        	this.getCustomerDataList().clear();
	        	this.getCustomerDataList().add((VwCustomerInstance)event.getTreeNode().getParent().getParent().getParent().getParent().getData());
	        	break;
	        case "promotionService":
	        	this.setShowCustomerInfo(true);
	        	this.setShowAccountInfo(true);
	        	this.setShowProductInfo(true);
	        	this.setShowServiceInfo(true);
	        	this.setShowFeeHistoricInfo(true);
	        	this.setPromotionHistoricDataList(promotionEJB.findDataByPromotionId(((VwPromotionInstance)event.getTreeNode().getData()).getPromotionId()));
	        	this.getServiceDataList().clear();
	        	this.getServiceDataList().add((VwServiceInstance)event.getTreeNode().getParent().getData());
	        	this.getProductDataList().clear();
	        	this.getProductDataList().add((VwProductInstance)event.getTreeNode().getParent().getParent().getData());
	        	this.getAccountDataList().clear();
	        	this.getAccountDataList().add((VwAccountInstance)event.getTreeNode().getParent().getParent().getParent().getData());
	        	this.getCustomerDataList().clear();
	        	this.getCustomerDataList().add((VwCustomerInstance)event.getTreeNode().getParent().getParent().getParent().getParent().getData());
	        	break;
		    default:
		    	logger.fatal("Not a valid node selected" + event.getTreeNode().toString());
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, "SELECT NODE", "Not a valid node selected");
		    	
	      }
		
		//this.updateHiearchyInfo();
		

	}

	public void onNodeUnselect(NodeUnselectEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected",
				event.getTreeNode().toString());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void setInitVariablesToDefault() {
		this.setShowConditionsToDefault();
		this.setSearchDate(LocalDate.now().atStartOfDay());
		
	}
	
	public void setShowConditionsToDefault() {
		this.setShowCustomerInfo(false);
		this.setShowAccountInfo(false);
		this.setShowProductInfo(false);
		this.setShowServiceInfo(false);
		this.setShowCustomerHistoricInfo(false);
		this.setShowAccountHistoricInfo(false);
		this.setShowProductHistoricInfo(false);
		this.setShowServiceHistoricInfo(false);
		this.setShowFeeHistoricInfo(false);
		this.setShowPromotionHistoricInfo(false);
		
	}
	
	public void updateHiearchyInfo() {
		Ajax.update("form:customerTable");
		Ajax.update("form:historicCustomerTable");
	}

}
