/**
 * 
 */
package com.comasw.viewController.catalog.relationType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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

import com.comasw.model.tables.pojos.CtProductType;
import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.model.tables.pojos.CtProdFeeType;
import com.comasw.model.tables.pojos.VwProductFeeType;
import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.utilities.Formatter;
import com.comasw.ejb.catalog.relationType.ProductFeeTypeEJBLocal;
import com.comasw.ejb.catalog.type.FeeTypeEJBLocal;
import com.comasw.ejb.catalog.type.ProductTypeEJBLocal;
import com.comasw.ejb.parameterization.StatusEJBLocal;
import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.generalClass.SimpleHistoricRelationWithList;
import com.comasw.interfaces.ISimpleHistoricRelationsTable;

@Named
@ViewScoped
/**
 * @author catuxa
 *
 */
public class ProductFeeTypeController
		extends SimpleHistoricRelationWithList<CtProductType, VwProductFeeType, CtFeeType>
		implements Serializable, ISimpleHistoricRelationsTable {

	private static final long serialVersionUID = 2176506681704512988L;

	Logger logger = (Logger) LogManager.getLogger(ProductFeeTypeController.class);

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private ProductTypeEJBLocal productTypeEJB;

	@EJB
	private ProductFeeTypeEJBLocal productFeeTypeEJB;

	@EJB
	private FeeTypeEJBLocal feeTypeEJB;

	@EJB
	private StatusEJBLocal statusEJB;

	/**
	 * Selected main data
	 */
	@Inject
	private CtProductType injectSelectedData;

	/**
	 * Selected candidate data
	 */
	@Inject
	private CtFeeType injectSelectedCandidateData;

	/**
	 * Selected related data
	 */
	@Inject
	private VwProductFeeType injectSelectedRelatedData;


	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	/**
	 * @return the injectSelectedData
	 */
	public CtProductType getInjectSelectedData() {
		return injectSelectedData;
	}

	/**
	 * @param injectSelectedData the selectedParentData to set
	 */
	public void setInjectSelectedData(CtProductType injectSelectedData) {
		this.injectSelectedData = injectSelectedData;
	}

	/**
	 * @return the injectSelectedCandidateData
	 */
	public CtFeeType getInjectSelectedCandidateData() {
		return injectSelectedCandidateData;
	}

	/**
	 * @param injectSelectedCandidateData the selectedCandidateData to set
	 */
	public void setInjectSelectedCandidateData(CtFeeType injectSelectedCandidateData) {
		this.injectSelectedCandidateData = injectSelectedCandidateData;
	}

	/**
	 * @return the injectSelectedRelatedData
	 */
	public VwProductFeeType getInjectSelectedRelatedData() {
		return injectSelectedRelatedData;
	}

	/**
	 * @param selectedRelatedData the selectedRelatedData to set
	 */
	public void setInjectSelectedRelatedData(VwProductFeeType injectSelectedRelatedData) {
		this.injectSelectedRelatedData = injectSelectedRelatedData;
	}

	
	// -------------------
	// METHODS
	// -------------------

	
	/**
	 *
	 */
	public ProductFeeTypeController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		if (this.getSearchDate() == null) {
			this.setSearchDate(LocalDate.now().atStartOfDay());
		}

		if (this.getDataList() == null) {
			this.setDataList(new ArrayList<CtProductType>());
		}

		if (this.getFilteredDataList() == null) {
			this.setFilteredDataList(new ArrayList<CtProductType>());
		}

		if (this.getSelectedData() == null) {
			this.setSelectedData(new CtProductType());
		}

		if (this.getCandidateDataList() == null) {
			this.setCandidateDataList(new ArrayList<CtFeeType>());
		}

		if (this.getFilteredCandidateDataList() == null) {
			this.setFilteredCandidateDataList(new ArrayList<CtFeeType>());
		}

		if (this.getHistoricCandidateDataList() == null) {
			this.setHistoricCandidateDataList(new ArrayList<CtFeeType>());
		}

		if (this.getFilteredHistoricCandidateDataList() == null) {
			this.setFilteredHistoricCandidateDataList(new ArrayList<CtFeeType>());
		}

		if (this.getRelatedDataList() == null) {
			this.setRelatedDataList(new ArrayList<VwProductFeeType>());
		}

		if (this.getFilteredRelatedDataList() == null) {
			this.setFilteredRelatedDataList(new ArrayList<VwProductFeeType>());
		}

		if (this.getLoggedUser() == null) {
			this.setLoggedUser((VwUsers) externalContext.getSessionMap().get("applicationUser"));
		}

	}

	@Override
	public void loadDataList() {
		this.setDataList(productTypeEJB.findAllData());
		if (this.getDataList().isEmpty()) {
			logger.info("No parent data to show");

		} else {
			logger.info("Load parent data sucessful");
		}
	}
	
	@Override
	public void loadHistoricCandidateDataList() {
		String errorMessage;

		if ((this.getSelectedCandidateData() != null) && (this.getSelectedCandidateData().getFeeTypeId() != null)
				&& (this.getSelectedCandidateData().getFeeTypeId() != 0)) {
			this.setHistoricCandidateDataList(
					feeTypeEJB.findDataByFeeTypeId(this.getSelectedCandidateData().getFeeTypeId()));
			if (this.getHistoricCandidateDataList().isEmpty()) {
				logger.info("No candidate data to show");

			} else {
				logger.info("Load historic candidate data sucessful");
			}
		} else {
			errorMessage = "The selected product type is null";
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage);
		}

	}

	@Override
	public void loadCandidateDataList() {
		String errorMessage;

		if ((this.getSelectedData() != null) && (this.getSelectedData().getProductTypeId() != null)
				&& (this.getSelectedData().getProductTypeId() != 0)) {
			this.setCandidateDataList(
					productFeeTypeEJB.findEntityTypeCandidates(this.getSelectedData().getProductTypeId()));
			if (this.getCandidateDataList().isEmpty()) {
				logger.info("No candidate data to show");

			} else {
				logger.info("Load candidate data sucessful");
			}
		} else {
			errorMessage = "The selected product type is null";
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage);
		}

	}
	
	
	@Override
	public void loadRelatedDataList() {
		String errorMessage;

		if ((this.getSelectedData() != null) && (this.getSelectedData().getProductTypeId() != null)
				&& (this.getSelectedData().getProductTypeId() != 0)) {
			if (this.isHistoricRelatedDataCriteria()) {
				this.setRelatedDataList(productFeeTypeEJB
						.findHistoricRelatedEntityTypesView(this.getSelectedData().getProductTypeId()));
			} else {
				if (this.getSearchDate() == null) {
					logger.error("The search date is null");
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, "LOAD RELATED DATE",
							"The search date is null");
				} else {
					this.setRelatedDataList(productFeeTypeEJB.findRelatedEntityTypesByDateView(
							this.getSelectedData().getProductTypeId(), this.getSearchDate()));
				}
			}
			if (this.getRelatedDataList().isEmpty()) {
				logger.info("No related data to show");

			} else {
				logger.info("Load related data sucessful");
			}
		} else {
			errorMessage = "The selected product type is null";
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage);
		}

	}

	@Override
	public void pushSearchButton() {
		String message = "SEARCH DATA";
		String messageDetail = "";
		this.loadDataList();
		if (this.getDataList().isEmpty()) {
			messageDetail = "No data to show ";
			logger.info(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

		} else {
			this.resetFilterDataTable();
			PrimeFaces.current().executeScript("PF('searchListWidget').show();");
			// Ajax.update(SEARCH_DATA_TABLE_ID);
			messageDetail = "Shown data for the search date:" + Formatter.localDateTimeToString(searchDate);
			logger.info(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
		}

	}

	@Override
	public void pushSelectedButton() {
		String message = "SHOW DATA PRODUCT RELATION";
		String messageDetail = "";
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(DATA_TABLE_ID);

		// Gets the selected data
		this.setInjectSelectedData((CtProductType) dataTable.getRowData());

		if (this.getInjectSelectedData() == null | this.getInjectSelectedData().getProductTypeId() == null
				|| this.getInjectSelectedData().getProductTypeId() == 0) {
			messageDetail = "The product type is null";
			logger.error(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {
			this.setSelectedData(this.getInjectSelectedData());
			this.loadCandidateDataList();
			this.loadRelatedDataList();
			this.setShowDependentData(true);

			this.resetFilterCandidateDataTable();
			this.resetFilterRelatedDataTable();

			messageDetail = "Shown data for product: ";
			logger.info(message + " - " + messageDetail + this.getSelectedData().toString());
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
					messageDetail + this.getSelectedData().getCode());

			PrimeFaces.current().executeScript("PF('searchListWidget').hide();");
			PrimeFaces.current().executeScript("PF('multipleAccordionPanelWidget').selectAll();");

		}

	}
	
	@Override
	public void pushShowHistoricCandidateRowButton() {
		String message = "SHOW HISTORIC CANDIDATE DATA";
		String messageDetail = "";
		DataTable dataTable;
		CtFeeType dataObject;

		dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(CANDIDATE_DATA_TABLE_ID);

		dataObject = (CtFeeType) dataTable.getRowData();

		if (dataObject == null || dataObject.getFeeTypeId() == null) {
			messageDetail = "No selected fee type to shown";
			logger.error(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
					"The selected fee type to shown is null");
			facesContext.validationFailed();
		} else {
			// Gets selectedData
			this.setSelectedCandidateData(dataObject);
			this.loadHistoricCandidateDataList();
			this.resetFilterHistoricCandidateDataTable();

			messageDetail = "Shown historic data for the fee type:" + this.getSelectedCandidateData().getCode();
			logger.info(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
		}

	}

	@Override
	public void pushAddButtonCandidateToRelatedData() {
		String message = "ADD FEE TYPE TO PRODUCT";
		String messageDetail = "";
		DataTable mainDataTable;
		DataTable otherDataTable;

		if (this.getSelectedData() == null) {

			messageDetail = "The product type is null";
			logger.error(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {

			mainDataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(CANDIDATE_DATA_TABLE_ID);
			otherDataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(RELATED_DATA_TABLE_ID);

			if (mainDataTable.getSelection() == null
					|| ((CtFeeType) mainDataTable.getSelection()).getFeeTypeId() == null) {
				messageDetail = "No selected fee type to add for the product type " + this.getSelectedData().toString();
				logger.error(message + " - " + messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
						"The selected fee type to add is null");
				facesContext.validationFailed();
			} else {
				// Gets selectedData
				this.injectSelectedCandidateData = (CtFeeType) mainDataTable.getSelection();

				CtProdFeeType dataObject = new CtProdFeeType();
				dataObject.setProductTypeId(this.getSelectedData().getProductTypeId());
				dataObject.setFeeTypeId(this.getInjectSelectedCandidateData().getFeeTypeId());
				dataObject.setInputUser(this.loggedUser.getUserCode());
				dataObject.setInputDate(LocalDateTime.now());
				dataObject.setStatusId((Integer) statusEJB.findDataByCode(ACTIVE_STATUS_CODE).getStatusId());
				try {
					productFeeTypeEJB.insertData(dataObject);
					this.loadCandidateDataList();
					this.loadRelatedDataList();

					// Unselect selected rows
					mainDataTable.setRowIndex(-1);
					otherDataTable.setRowIndex(-1);

					this.resetFilterCandidateDataTable();
					this.resetFilterRelatedDataTable();

					messageDetail = "The product fee type relation was added";
					logger.info(message + " - " + messageDetail + dataObject.toString());
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);

				} catch (CoMaSwDataAccessException e) {
					messageDetail = "Error while adding the selected product fee type relation ";
					logger.error(message + " - " + messageDetail + dataObject.toString());
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
					facesContext.validationFailed();
				}

				this.injectSelectedCandidateData = null;

			}
		}

	}

	@Override
	public void pushRemoveButtonFromRelatedData() {
		String message = "REMOVE FEE TYPE FROM PRODUCT";
		String messageDetail = "";
		DataTable mainDataTable;
		DataTable otherDataTable;

		if (this.getSelectedData() == null) {

			messageDetail = "The product type is null";
			logger.error(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {

			mainDataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(RELATED_DATA_TABLE_ID);

			otherDataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(CANDIDATE_DATA_TABLE_ID);

			if (mainDataTable.getSelection() == null
					|| ((VwProductFeeType) mainDataTable.getSelection()).getProdFeeTypeId() == null) {
				messageDetail = "No selected fee type to remove for the selected product type ";
				logger.error(message + " - " + messageDetail + this.getSelectedData().toString());
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				facesContext.validationFailed();
			} else {
				// Gets selectedData
				this.setInjectSelectedRelatedData((VwProductFeeType) mainDataTable.getSelection());

				CtProdFeeType dataObject = productFeeTypeEJB
						.findEntityRelationType(this.injectSelectedRelatedData.getProdFeeTypeId());
				try {
					productFeeTypeEJB.deleteData(dataObject);
					this.loadCandidateDataList();
					this.loadRelatedDataList();

					// Unselect selected rows
					mainDataTable.setRowIndex(-1);
					otherDataTable.setRowIndex(-1);

					this.resetFilterCandidateDataTable();
					this.resetFilterRelatedDataTable();

					messageDetail = "The selected product fee type relation was removed";
					logger.info(message + " - " + messageDetail + dataObject.toString());
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							"The selected fee type was removed");

				} catch (CoMaSwDataAccessException e) {
					messageDetail = "Error while removing the selected product fee type relation ";
					logger.error(message + " - " + messageDetail + dataObject.toString());
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
					facesContext.validationFailed();
				}

				this.injectSelectedRelatedData = null;
			}
		}

	}

	@Override
	public void resetFilterDataTable() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('dataTableWidget').clearFilters()");
	}

	@Override
	public void refreshDataTable() {
		this.resetFilterDataTable();
		this.loadDataList();
		Ajax.update(DATA_TABLE_ID);
	}

	@Override
	public void resetFilterCandidateDataTable() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('candidateTableWidget').clearFilters()");
	}

	@Override
	public void refreshCandidateDataTable() {
		if (this.isShowDependentData()) {
			this.resetFilterCandidateDataTable();
			this.loadCandidateDataList();
			Ajax.update(CANDIDATE_DATA_TABLE_ID);
		}
	}

	@Override
	public void resetFilterRelatedDataTable() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('relatedTableWidget').clearFilters()");
	}

	@Override
	public void refreshRelatedDataTable() {
		if (this.isShowDependentData()) {
			this.resetFilterRelatedDataTable();
			this.loadRelatedDataList();
			Ajax.update(RELATED_DATA_TABLE_ID);
		}
	}

	

	

	@Override
	public void resetFilterHistoricCandidateDataTable() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('historicCandidateTableWidget').clearFilters()");

	}

	@Override
	public void setInitVariablesToDefault() {
		this.setShowDependentData(false);
		this.setHistoricRelatedDataCriteria(false);
		

	}

	@Override
	public void setControlVariablesToDefault() {
		this.setInitVariablesToDefault();

	}

	@Override
	public void onRelatedRowInit(RowEditEvent<?> event) {
		String message, messageDetail;
		VwProductFeeType dataObjectView;
		CtProdFeeType dataObject;

		message = "EDIT ROW";

		// Retrieved the data that was modified
		dataObjectView = (VwProductFeeType) event.getObject();
		dataObject = productFeeTypeEJB.findEntityRelationType(dataObjectView.getProdFeeTypeId());

		this.setSelectedRelatedData(dataObjectView);

		messageDetail = "Editing product_fee_type: ";
		logger.info(messageDetail + dataObject.toString());
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail
				+ " for product: " + dataObjectView.getProductTypeCode() + " and fee: " + dataObjectView.getFeeTypeCode());

	}

	@Override
	public void onRelatedRowEdit(RowEditEvent<?> event) throws ValidatorException {
		String message, messageDetail;
		VwProductFeeType dataObjectView;
		CtProdFeeType dataObject;
		boolean error = false;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObjectView = (VwProductFeeType) event.getObject();
		dataObject = productFeeTypeEJB.findEntityRelationType(dataObjectView.getProdFeeTypeId());
		dataObject.setStatusId(dataObjectView.getProdFeeTypeStatusId());
		dataObject.setModifDate(LocalDateTime.now());
		dataObject.setModifUser(this.getLoggedUser().getUserCode());

		try {

			productFeeTypeEJB.updateData(dataObject);
			messageDetail = "Data saves correctly";
			logger.info(
					"Update the status of the product_fee_type_id  " + dataObject.toString() + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

		} catch (EJBException e) {
			error = true;
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwDataAccessException")) {
				messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			} else if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
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
				this.refreshRelatedDataTable();
				this.loadRelatedDataList();
			}
		}

	}

	@Override
	public void onRelatedRowCancel(RowEditEvent<?> event) {
		String message, messageDetail;
		VwProductFeeType dataObjectView;
		message = "CANCEL UPDATE ROW";
		messageDetail = "Cancelled the edition for the product_fee_type ";

		dataObjectView = (VwProductFeeType) event.getObject();

		logger.info(messageDetail + "product_type: " + dataObjectView.getProductTypeCode() + " - fee_type: "
				+ dataObjectView.getFeeTypeCode());
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
				messageDetail + dataObjectView.toString());

	}
	
	
	@Override
	public void changeSearchDate(ValueChangeEvent e) {
		LocalDateTime newSearchDate = (LocalDateTime) e.getNewValue();
		String message, messageDetail;
		
		message="CHANGE SEARCH DATE";
		
		if (newSearchDate != null) {
			this.setSearchDate(newSearchDate);			
		} else {
			messageDetail = "ERROR - The search date can not be null";
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
		}
	}
	
}
