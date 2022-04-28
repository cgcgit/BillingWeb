/**
 * 
 */
package com.billingweb.viewController.catalog.relationType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.omnifaces.util.Ajax;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.billingweb.ejb.catalog.relationType.ProductPromotionTypeEJBLocal;
import com.billingweb.ejb.catalog.type.ProductTypeEJBLocal;
import com.billingweb.ejb.parameterization.StatusEJBLocal;
import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.generalClass.SimpleRelatedTypeClass;
import com.billingweb.interfaces.IRelatedCatalogType;
import com.billingweb.model.tables.pojos.CtProductType;
import com.billingweb.model.tables.pojos.CtPromotionType;
import com.billingweb.model.tables.pojos.CtPromoProdType;
import com.billingweb.model.tables.pojos.VwPromotionProductType;
import com.billingweb.model.tables.pojos.VwUsers;

@Named
@ViewScoped
/**
 * @author catuxa
 *
 */
public class ProductPromotionTypeController extends SimpleRelatedTypeClass implements Serializable, IRelatedCatalogType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2872381274603389370L;
	

	Logger logger = (Logger) LogManager.getLogger(ProductServiceTypeController.class);

	protected static String ACTIVE_STATUS_CODE = dbDefinitions.getString("STATUS_CODE_ACT");

	String SEARCH_DATA_TABLE_ID = "form:accordionPanel:searchTable";

	String SELECTED_DATA_TABLE_ID = "form:accordionPanel:selectedTable";

	String RELATED_DATA_TABLE_ID = "form:accordionPanel:relatedTable";

	String CANDIDATE_DATA_TABLE_ID = "form:accordionPanel:candidateTable";

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private ProductTypeEJBLocal productTypeEJB;

	@EJB
	private ProductPromotionTypeEJBLocal productPromotionTypeEJB;

	@EJB
	private StatusEJBLocal statusEJB;

	/**
	 * MAIN DATA - Product data
	 */

	/**
	 * Main data list
	 */
	private List<CtProductType> searchDataList;

	/**
	 * Filtered data list for main data
	 */
	private List<CtProductType> filteredSearchDataList;

	/**
	 * Selected main data
	 */
	@Inject
	private CtProductType selectedMainData;

	/**
	 * Main data
	 */
	private CtProductType mainData;

	/**
	 * Candidate data list
	 */
	private List<CtPromotionType> candidateDataList;

	/**
	 * Filtered data list for candidate data
	 */
	private List<CtPromotionType> filteredCandidateDataList;

	/**
	 * Related data list
	 */
	private List<VwPromotionProductType> relatedDataList;

	/**
	 * Filtered data list for related data
	 */
	private List<VwPromotionProductType> filteredRelatedDataList;

	/**
	 * Selected candidate data
	 */
	@Inject
	private CtPromotionType selectedCandidateData;

	/**
	 * Selected related data
	 */
	@Inject
	private VwPromotionProductType selectedRelatedData;

	/**
	 * Application user
	 **/

	private VwUsers loginUser;

	private Integer activeStatusId;

	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	/**
	 * @return the searchDataList
	 */
	public List<CtProductType> getSearchDataList() {
		return searchDataList;
	}

	/**
	 * @param mainDataList the searchDataList to set
	 */
	public void setSearchDataList(List<CtProductType> searchDataList) {
		this.searchDataList = searchDataList;
	}

	/**
	 * @return the filteredSearchDataList
	 */
	public List<CtProductType> getFilteredSearchDataList() {
		return filteredSearchDataList;
	}

	/**
	 * @param filteredMainDataList the filteredMainDataList to set
	 */
	public void setFilteredSearchDataList(List<CtProductType> filteredSearchDataList) {
		this.filteredSearchDataList = filteredSearchDataList;
	}

	/**
	 * @return the candidateDataList
	 */
	public List<CtPromotionType> getCandidateDataList() {
		return candidateDataList;
	}

	/**
	 * @param candidateDataList the candidateDataList to set
	 */
	public void setCandidateDataList(List<CtPromotionType> candidateDataList) {
		this.candidateDataList = candidateDataList;
	}

	/**
	 * @return the filteredCandidateDataList
	 */
	public List<CtPromotionType> getFilteredCandidateDataList() {
		return filteredCandidateDataList;
	}

	/**
	 * @param filteredCandidateDataList the filteredCandidateDataList to set
	 */
	public void setFilteredCandidateDataList(List<CtPromotionType> filteredCandidateDataList) {
		this.filteredCandidateDataList = filteredCandidateDataList;
	}

	/**
	 * @return the relatedDataList
	 */
	public List<VwPromotionProductType> getRelatedDataList() {
		return relatedDataList;
	}

	/**
	 * @param relatedDataList the relatedDataList to set
	 */
	public void setRelatedDataList(List<VwPromotionProductType> relatedDataList) {
		this.relatedDataList = relatedDataList;
	}

	/**
	 * @return the filteredRelatedDataList
	 */
	public List<VwPromotionProductType> getFilteredRelatedDataList() {
		return filteredRelatedDataList;
	}

	/**
	 * @param filteredRelatedDataList the filteredRelatedDataList to set
	 */
	public void setFilteredRelatedDataList(List<VwPromotionProductType> filteredRelatedDataList) {
		this.filteredRelatedDataList = filteredRelatedDataList;
	}

	/**
	 * @return the selectedMainData
	 */
	public CtProductType getSelectedMainData() {
		return selectedMainData;
	}

	/**
	 * @param selectedMainData the selectedParentData to set
	 */
	public void setSelectedMainData(CtProductType selectedMainData) {
		this.selectedMainData = selectedMainData;
	}

	/**
	 * @return the mainData
	 */
	public CtProductType getMainData() {
		return mainData;
	}

	/**
	 * @param mainData the mainData to set
	 */
	public void setMainData(CtProductType mainData) {
		this.mainData = mainData;
	}

	/**
	 * @return the selectedCandidateData
	 */
	public CtPromotionType getSelectedCandidateData() {
		return selectedCandidateData;
	}

	/**
	 * @param selectedCandidateData the selectedCandidateData to set
	 */
	public void setSelectedCandidateData(CtPromotionType selectedCandidateData) {
		this.selectedCandidateData = selectedCandidateData;
	}

	/**
	 * @return the selectedRelatedData
	 */
	public VwPromotionProductType getSelectedRelatedData() {
		return selectedRelatedData;
	}

	/**
	 * @param selectedRelatedData the selectedRelatedData to set
	 */
	public void setSelectedRelatedData(VwPromotionProductType selectedRelatedData) {
		this.selectedRelatedData = selectedRelatedData;
	}

	/**
	 * @return the loginUser
	 */
	public VwUsers getLoginUser() {
		return loginUser;
	}

	/**
	 * @param loginUser the loginUser to set
	 */
	public void setLoginUser(VwUsers loginUser) {
		this.loginUser = loginUser;
	}

	/**
	 * @return the activeStatusId
	 */
	public Integer getActiveStatusId() {
		return activeStatusId;
	}

	/**
	 * @param activeStatusId the activeStatusId to set
	 */
	public void setActiveStatusId(Integer activeStatusId) {
		this.activeStatusId = activeStatusId;
	}

	// -------------------
	// METHODS
	// -------------------

	/**
	 * 
	 */
	public ProductPromotionTypeController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		if (this.selectedMainData == null) {
			this.selectedMainData = new CtProductType();
		}

		if (this.selectedCandidateData == null) {
			this.selectedCandidateData = new CtPromotionType();
		}

		if (this.selectedRelatedData == null) {
			this.selectedRelatedData = new VwPromotionProductType();
		}

		if (this.loginUser == null) {
			this.loginUser = (VwUsers) externalContext.getSessionMap().get("applicationUser");
		}

		this.activeStatusId = (Integer) statusEJB.findDataByCode(ACTIVE_STATUS_CODE).getStatusId();

	}

	@Override
	public void loadSearchData() {
		this.searchDataList = productTypeEJB.findAllData();
		if (this.searchDataList.isEmpty()) {
			logger.info("No parent data to show");

		} else {
			logger.info("Load parent data sucessful");
		}

	}

	@Override
	public void loadCandidateData() {
		String errorMessage;

		if ((this.mainData == null) || (this.mainData.getProductTypeId() != null)) {
			this.candidateDataList = productPromotionTypeEJB.findEntityTypeCandidates(this.mainData.getProductTypeId());
			if (this.candidateDataList.isEmpty()) {
				logger.info("No candidate data to show");

			} else {
				logger.info("Load candidate data sucessful");
			}
		} else {
			errorMessage = "The selected product type is null";
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage);
		}

	}

	@Override
	public void loadRelatedData() {
		String errorMessage;

		if ((this.mainData == null) || (this.mainData.getProductTypeId() != null)) {
			this.relatedDataList = productPromotionTypeEJB.findRelatedEntityTypesView(this.mainData.getProductTypeId());
			if (this.relatedDataList.isEmpty()) {
				logger.info("No related data to show");

			} else {
				logger.info("Load related data sucessful");
			}
		} else {
			errorMessage = "The selected product type is null";
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage);
		}

	}

	@Override
	public void pushSearchButton() {
		if (this.selectedMainData == null) {
			this.selectedMainData = new CtProductType();
		}
		this.loadSearchData();
		PrimeFaces.current().executeScript("PF('searchList').show();");
		Ajax.update(SEARCH_DATA_TABLE_ID);

	}

	@Override
	public void pushSelectedButton() {
		String message = "SHOW DATA PRODUCT RELATION";
		String messageDetail = "";
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(SEARCH_DATA_TABLE_ID);

		// Gets the selected data
		this.selectedMainData = (CtProductType) dataTable.getRowData();

		if (this.selectedMainData == null) {
			messageDetail = "The product type is null";
			logger.error(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {
			this.mainData = this.selectedMainData;
			/*
			 * this.selectedDataList.clear();
			 * this.selectedDataList.add(this.selectedMainData);
			 */
			this.loadCandidateData();
			this.loadRelatedData();
			this.showDependentData = true;
		
			this.refreshCandidateDataTable();
			this.refreshRelatedDataTable();

			messageDetail = "Shown data for product: ";
			logger.info(message + " - " + messageDetail + this.mainData.toString());
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
					messageDetail + this.getSelectedMainData().getCode());

			PrimeFaces.current().executeScript("PF('searchList').hide();");
			//Ajax.update(SELECTED_DATA_TABLE_ID);
			
			this.refreshRelatedDataTable();
			this.refreshCandidateDataTable();
		}

	}

	@Override
	public void pushAddButtonCandidateToRelatedData() {
		String message = "ADD PROMOTION TYPE TO PRODUCT";
		String messageDetail = "";
		DataTable mainDataTable;
		DataTable otherDataTable;

		if (this.mainData == null) {

			messageDetail = "The product type is null";
			logger.error(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {

			mainDataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(CANDIDATE_DATA_TABLE_ID);
			
			otherDataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(RELATED_DATA_TABLE_ID);			


			if (mainDataTable.getSelection() == null || ((CtPromotionType) mainDataTable.getSelection()).getPromotionTypeId() == null) {
				messageDetail = "No selected promotion type to add for the product type " + this.mainData.toString();
				logger.error(message + " - " + messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
						"The selected fee type to add is null");
				facesContext.validationFailed();
			} else {
				// Gets selectedData
				this.selectedCandidateData = (CtPromotionType) mainDataTable.getSelection();

				CtPromoProdType dataObject = new CtPromoProdType();
				dataObject.setProductTypeId(this.mainData.getProductTypeId());
				dataObject.setPromotionTypeId(this.selectedCandidateData.getPromotionTypeId());
				dataObject.setInputUser(this.loginUser.getUserCode());
				dataObject.setInputDate(LocalDateTime.now());
				dataObject.setStatusId(this.activeStatusId);
				try {

					productPromotionTypeEJB.insertData(dataObject);
					
					this.loadCandidateData();
					this.loadRelatedData();			
					
					//Unselect selected rows
					mainDataTable.setRowIndex(-1);
					otherDataTable.setRowIndex(-1);
					
				
					this.refreshCandidateDataTable();
					this.refreshRelatedDataTable();
					messageDetail = "The product promotion type relation was added";
					logger.info(message + " - " + messageDetail + dataObject.toString());
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);

				} catch (BillingWebDataAccessException e) {
					messageDetail = "Error while adding the selected product promotion type relation ";
					logger.error(message + " - " + messageDetail + dataObject.toString());
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
					facesContext.validationFailed();
				}

				this.selectedCandidateData = null;

			}
		}

	}

	@Override
	public void pushRemoveButtonFromRelatedData() {
		String message = "REMOVE PROMOTION TYPE FROM PRODUCT";
		String messageDetail = "";
		DataTable mainDataTable;
		DataTable otherDataTable;

		if (this.mainData == null) {

			messageDetail = "The product type is null";
			logger.error(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {		
			
			mainDataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(RELATED_DATA_TABLE_ID);
			
			otherDataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(CANDIDATE_DATA_TABLE_ID);

			if (mainDataTable.getSelection() == null
					|| ((VwPromotionProductType) mainDataTable.getSelection()).getPromoProdTypeId() == null) {
				messageDetail = "No selected promotion type to remove for the selected product type ";
				logger.error(message + " - " + messageDetail + this.mainData.toString());
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				facesContext.validationFailed();
			} else {
				// Gets selectedData
				this.selectedRelatedData = (VwPromotionProductType) mainDataTable.getSelection();

				CtPromoProdType dataObject = productPromotionTypeEJB
						.findEntityRelationType(this.selectedRelatedData.getPromoProdTypeId());
				try {
					productPromotionTypeEJB.deleteData(dataObject);
					
					this.loadCandidateData();
					this.loadRelatedData();					

					//Unselect selected rows
					mainDataTable.setRowIndex(-1);
					otherDataTable.setRowIndex(-1);					
					
					this.refreshCandidateDataTable();
					this.refreshRelatedDataTable();

					messageDetail = "The selected product promotion type relation was removed";
					logger.info(message + " - " + messageDetail + dataObject.toString());
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							"The selected promotion type was removed");

				} catch (BillingWebDataAccessException e) {
					messageDetail = "Error while removing the selected product promotion type relation ";
					logger.error(message + " - " + messageDetail + dataObject.toString());
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
					facesContext.validationFailed();
				}

				this.selectedRelatedData = null;
			}
		}

	}

	@Override
	public void onRelatedRowSelect(SelectEvent<?> event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRelatedRowInit(RowEditEvent<?> event) {
		String message, messageDetail;
		VwPromotionProductType dataObjectView;
		CtPromoProdType dataObject;

		message = "EDIT ROW";

		// Retrieved the data that was modified
		dataObjectView = (VwPromotionProductType) event.getObject();
		dataObject = productPromotionTypeEJB.findEntityRelationType(dataObjectView.getPromoProdTypeId());				

		messageDetail = "Editing product_promotion_type: ";
		logger.info(messageDetail + dataObject.toString());
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
				messageDetail + " for product: " + dataObjectView.getProductTypeCode() + " and fee: "
						+ dataObjectView.getPromotionTypeCode());

	}

	@Override
	public void onRelatedRowEdit(RowEditEvent<?> event) throws ValidatorException {
		String message, messageDetail;
		VwPromotionProductType dataObjectView;
		CtPromoProdType dataObject;
		boolean error = false;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObjectView = (VwPromotionProductType) event.getObject();
		dataObject = productPromotionTypeEJB.findEntityRelationType(dataObjectView.getPromoProdTypeId());
		dataObject.setStatusId(dataObjectView.getPromoProdTypeId());

		try {

			productPromotionTypeEJB.updateData(dataObject);
			messageDetail = "Data saves correctly";
			logger.info(
					"Update the status of the product_service_type_id  " + dataObject.toString() + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

		} catch (EJBException e) {
			error = true;
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.billingweb.exception.BillingWebDataAccessException")) {
				messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			} else if (ne.getClass().getName().equals("es.billingweb.exception.BillingWebParseException")) {
				messageDetail = "PARSE ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			} else {
				messageDetail = "ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			}

		} catch (Exception e) {
			messageDetail = "ERROR - " + e.getMessage();
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
		} finally {
			if (error) {
				facesContext.validationFailed();
			} else {
				this.loadRelatedData();
			}
		}

	}

	@Override
	public void onRelatedRowCancel(RowEditEvent<?> event) {
		String message, messageDetail;
		message = "CANCEL UPDATE ROW";

		messageDetail = "Cancelled the edition for the product_service_type";
		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

	}

	@Override
	public void onCandidateRowSelect(SelectEvent<?> event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setInitVariablesToDefault() {
		this.showDependentData = false;

	}

	@Override
	public void refreshRelatedDataTable() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('relatedTableWidget').clearFilters()");
		//Ajax.update(RELATED_DATA_TABLE_ID);
		
	}

	@Override
	public void refreshCandidateDataTable() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('candidateTableWidget').clearFilters()");
		//Ajax.update(CANDIDATE_DATA_TABLE_ID);
		
	}


}
