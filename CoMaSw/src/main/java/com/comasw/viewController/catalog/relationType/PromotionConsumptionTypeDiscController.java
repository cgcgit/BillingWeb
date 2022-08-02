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

import com.comasw.model.tables.pojos.CtConsumptionType;
import com.comasw.model.tables.pojos.CtPromoConsumTypeDiscount;
import com.comasw.model.tables.pojos.CtPromotionType;
import com.comasw.model.tables.pojos.VwPromoConsumTypeDiscount;
import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.utilities.Formatter;
import com.comasw.ejb.catalog.relationType.PromotionConsumptionTypeDiscountEJBLocal;
import com.comasw.ejb.catalog.type.ConsumptionTypeEJBLocal;
import com.comasw.ejb.catalog.type.PromotionTypeEJBLocal;
import com.comasw.ejb.parameterization.StatusEJBLocal;
import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.generalClass.SimpleHistoricRelationWithList;
import com.comasw.interfaces.ISimpleHistoricRelationsTable;

@Named
@ViewScoped
public class PromotionConsumptionTypeDiscController
		extends SimpleHistoricRelationWithList<CtPromotionType, VwPromoConsumTypeDiscount, CtConsumptionType>
		implements Serializable, ISimpleHistoricRelationsTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3651939438629827251L;

	Logger logger = (Logger) LogManager.getLogger(PromotionConsumptionTypeDiscController.class);

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private PromotionTypeEJBLocal promotionTypeEJB;

	@EJB
	private PromotionConsumptionTypeDiscountEJBLocal promotionConsumptionTypeEJB;

	@EJB
	private ConsumptionTypeEJBLocal consumptionTypeEJB;

	@EJB
	private StatusEJBLocal statusEJB;

	/**
	 * Selected main data
	 */
	
	private CtPromotionType injectSelectedData;

	/**
	 * Selected candidate data
	 */
	
	private CtConsumptionType injectSelectedCandidateData;

	/**
	 * Selected related data
	 */
	
	private VwPromoConsumTypeDiscount injectSelectedRelatedData;

	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	/**
	 * @return the injectSelectedData
	 */
	public CtPromotionType getInjectSelectedData() {
		return injectSelectedData;
	}

	/**
	 * @param injectSelectedData the selectedParentData to set
	 */
	public void setInjectSelectedData(CtPromotionType injectSelectedData) {
		this.injectSelectedData = injectSelectedData;
	}

	/**
	 * @return the injectSelectedCandidateData
	 */
	public CtConsumptionType getInjectSelectedCandidateData() {
		return injectSelectedCandidateData;
	}

	/**
	 * @param injectSelectedCandidateData the selectedCandidateData to set
	 */
	public void setInjectSelectedCandidateData(CtConsumptionType injectSelectedCandidateData) {
		this.injectSelectedCandidateData = injectSelectedCandidateData;
	}

	/**
	 * @return the injectSelectedRelatedData
	 */
	public VwPromoConsumTypeDiscount getInjectSelectedRelatedData() {
		return injectSelectedRelatedData;
	}

	/**
	 * @param selectedRelatedData the selectedRelatedData to set
	 */
	public void setInjectSelectedRelatedData(VwPromoConsumTypeDiscount injectSelectedRelatedData) {
		this.injectSelectedRelatedData = injectSelectedRelatedData;
	}

	// -------------------
	// METHODS
	// -------------------

	public PromotionConsumptionTypeDiscController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		if (this.getSearchDate() == null) {
			this.setSearchDate(LocalDate.now().atStartOfDay());
		}

		if (this.getDataList() == null) {
			this.setDataList(new ArrayList<CtPromotionType>());
		}

		if (this.getFilteredDataList() == null) {
			this.setFilteredDataList(new ArrayList<CtPromotionType>());
		}

		if (this.getSelectedData() == null) {
			this.setSelectedData(new CtPromotionType());
		}

		if (this.getCandidateDataList() == null) {
			this.setCandidateDataList(new ArrayList<CtConsumptionType>());
		}

		if (this.getFilteredCandidateDataList() == null) {
			this.setFilteredCandidateDataList(new ArrayList<CtConsumptionType>());
		}

		if (this.getHistoricCandidateDataList() == null) {
			this.setHistoricCandidateDataList(new ArrayList<CtConsumptionType>());
		}

		if (this.getFilteredHistoricCandidateDataList() == null) {
			this.setFilteredHistoricCandidateDataList(new ArrayList<CtConsumptionType>());
		}

		if (this.getRelatedDataList() == null) {
			this.setRelatedDataList(new ArrayList<VwPromoConsumTypeDiscount>());
		}

		if (this.getFilteredRelatedDataList() == null) {
			this.setFilteredRelatedDataList(new ArrayList<VwPromoConsumTypeDiscount>());
		}

		if (this.getLoggedUser() == null) {
			this.setLoggedUser((VwUsers) externalContext.getSessionMap().get("applicationUser"));
		}

	}

	@Override
	public void loadDataList() {
		String message = "LOAD DATA";
		String messageDetail = "";
		if (this.isHistoricSearchDataCriteria()) {
			this.setDataList(promotionTypeEJB.findAllData());
			if (this.getDataList().isEmpty()) {
				logger.info("No data to show");

			} else {
				logger.info("Load data sucessful");
			}
		} else {
			if (this.getSearchDate() == null) {
				messageDetail = "The date value to search is null. Please fill the search date field";
				logger.error(message + " - " + messageDetail);
				FacesContext.getCurrentInstance().validationFailed();
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL, message, messageDetail));
			} else {
				this.setDataList(promotionTypeEJB.findDataBySearchDate(searchDate));
				if (this.getDataList().isEmpty()) {
					logger.info("No data to show");

				} else {
					logger.info("Load data sucessful");
				}
			}
		}
	}

	@Override
	public void loadHistoricCandidateDataList() {

	}

	@Override
	public void loadCandidateDataList() {
		String errorMessage;

		if ((this.getSelectedData() != null) && (this.getSelectedData().getPromotionTypeId() != null)
				&& (this.getSelectedData().getPromotionTypeId() != 0)) {
			this.setCandidateDataList(
					promotionConsumptionTypeEJB.findEntityTypeCandidates(this.getSelectedData().getPromotionTypeId()));
			if (this.getCandidateDataList().isEmpty()) {
				logger.info("No candidate data to show");

			} else {
				logger.info("Load candidate data sucessful");
			}
		} else {
			errorMessage = "The selected promotion type is null";
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage);
		}

	}

	@Override
	public void loadRelatedDataList() {
		String errorMessage;

		if ((this.getSelectedData() != null) && (this.getSelectedData().getPromotionTypeId() != null)
				&& (this.getSelectedData().getPromotionTypeId() != 0)) {
			this.setRelatedDataList(promotionConsumptionTypeEJB
					.findRelatedEntityTypesView(this.getSelectedData().getPromotionTypeId()));
			if (this.getRelatedDataList().isEmpty()) {
				logger.info("No related data to show");

			} else {
				logger.info("Load related data sucessful");
			}
		} else {
			errorMessage = "The selected promotion type is null";
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
			messageDetail = "No data to show for the specific search criteria";
			logger.info(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

		} else {
			this.resetFilterDataTable();
			this.changeSearchDataTableTitle();

			PrimeFaces.current().executeScript("PF('searchListWidget').show();");
			
		}

	}

	@Override
	public void pushSelectedButton() {
		String message = "SHOW DATA PROMOTION RELATION";
		String messageDetail = "";
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(DATA_TABLE_ID);

		// Gets the selected data
		this.setInjectSelectedData((CtPromotionType) dataTable.getRowData());

		if (this.getInjectSelectedData() == null | this.getInjectSelectedData().getPromotionTypeId() == null
				|| this.getInjectSelectedData().getPromotionTypeId() == 0) {
			messageDetail = "The promotion type is null";
			logger.error(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {
			this.setSelectedData(this.getInjectSelectedData());
			this.loadCandidateDataList();
			this.loadRelatedDataList();
			this.setShowDependentData(true);

			this.resetFilterCandidateDataTable();
			this.resetFilterRelatedDataTable();

			messageDetail = "Shown data for promotion: ";
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
		CtConsumptionType dataObject;

		dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(CANDIDATE_DATA_TABLE_ID);

		dataObject = (CtConsumptionType) dataTable.getRowData();

		if (dataObject == null || dataObject.getConsumptionTypeId() == null) {
			messageDetail = "No selected consumption type to shown";
			logger.error(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
					"The selected consumption type to shown is null");
			facesContext.validationFailed();
		} else {
			// Gets selectedData
			this.setSelectedCandidateData(dataObject);
			this.loadHistoricCandidateDataList();
			this.resetFilterHistoricCandidateDataTable();

			messageDetail = "Shown historic data for the consumption type:" + this.getSelectedCandidateData().getCode();
			logger.info(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
		}

	}

	@Override
	public void pushAddButtonCandidateToRelatedData() {
		String message = "ADD CONSUMPTION TYPE TO PROMOTION";
		String messageDetail = "";
		DataTable mainDataTable;
		DataTable otherDataTable;

		if (this.getSelectedData() == null) {

			messageDetail = "The promotion type is null";
			logger.error(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {

			mainDataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(CANDIDATE_DATA_TABLE_ID);
			otherDataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(RELATED_DATA_TABLE_ID);

			if (mainDataTable.getSelection() == null
					|| ((CtConsumptionType) mainDataTable.getSelection()).getConsumptionTypeId() == null) {
				messageDetail = "No selected consumption type to add for the promotion type "
						+ this.getSelectedData().toString();
				logger.error(message + " - " + messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
						"The selected consumption type to add is null");
				facesContext.validationFailed();
			} else {
				// Gets selectedData
				this.injectSelectedCandidateData = (CtConsumptionType) mainDataTable.getSelection();

				CtPromoConsumTypeDiscount dataObject = new CtPromoConsumTypeDiscount();
				dataObject.setPromotionTypeId(this.getSelectedData().getPromotionTypeId());
				dataObject.setConsumptionTypeId(this.getInjectSelectedCandidateData().getConsumptionTypeId());
				dataObject.setInputUser(this.loggedUser.getUserCode());
				dataObject.setInputDate(LocalDateTime.now());
				dataObject.setStatusId((Integer) statusEJB.findDataByCode(ACTIVE_STATUS_CODE).getStatusId());
				try {
					promotionConsumptionTypeEJB.insertData(dataObject);
					this.loadCandidateDataList();
					this.loadRelatedDataList();

					// Unselect selected rows
					mainDataTable.setRowIndex(-1);
					otherDataTable.setRowIndex(-1);

					this.resetFilterCandidateDataTable();
					this.resetFilterRelatedDataTable();

					messageDetail = "The promotion consumption type relation was added";
					logger.info(message + " - " + messageDetail + dataObject.toString());
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);

				} catch (CoMaSwDataAccessException e) {
					messageDetail = "Error while adding the selected promotion consumption type relation ";
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
		String message = "REMOVE CONSUMPTION TYPE FROM PROMOTION";
		String messageDetail = "";
		DataTable mainDataTable;
		DataTable otherDataTable;

		if (this.getSelectedData() == null) {

			messageDetail = "The promotion type is null";
			logger.error(message + " - " + messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {

			mainDataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(RELATED_DATA_TABLE_ID);

			otherDataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(CANDIDATE_DATA_TABLE_ID);

			if (mainDataTable.getSelection() == null || ((VwPromoConsumTypeDiscount) mainDataTable.getSelection())
					.getPromoConsumTypeDiscountId() == null) {
				messageDetail = "No selected consumption type to remove for the selected promotion type ";
				logger.error(message + " - " + messageDetail + this.getSelectedData().toString());
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				facesContext.validationFailed();
			} else {
				// Gets selectedData
				this.setInjectSelectedRelatedData((VwPromoConsumTypeDiscount) mainDataTable.getSelection());

				CtPromoConsumTypeDiscount dataObject = promotionConsumptionTypeEJB
						.findEntityRelationType(this.injectSelectedRelatedData.getPromoConsumTypeDiscountId());
				try {
					promotionConsumptionTypeEJB.deleteData(dataObject);
					this.loadCandidateDataList();
					this.loadRelatedDataList();

					// Unselect selected rows
					mainDataTable.setRowIndex(-1);
					otherDataTable.setRowIndex(-1);

					this.resetFilterCandidateDataTable();
					this.resetFilterRelatedDataTable();

					messageDetail = "The selected promotion consumption type relation was removed";
					logger.info(message + " - " + messageDetail + dataObject.toString());
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							"The selected consumption type was removed");

				} catch (CoMaSwDataAccessException e) {
					messageDetail = "Error while removing the selected promotion consumption type relation ";
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
			if (this.getSelectedData() == null) {
				// recover the selected data from the selected table
				if (this.getSelectedDataList().get(0) != null) {
					this.setSelectedData(this.getSelectedDataList().get(0));
				}
			}
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
			if (this.getSelectedData() == null) {
				// recover the selected data from the selected table
				if (this.getSelectedDataList().get(0) != null) {
					this.setSelectedData(this.getSelectedDataList().get(0));
				}
			}
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
		this.setEditingMode(false);
		this.setSearchDate(LocalDate.now().atStartOfDay());
		this.setShowDependentData(false);
		this.setHistoricRelatedDataCriteria(false);
		this.setHistoricSearchDataCriteria(false);

	}

	@Override
	public void setControlVariablesToDefault() {
		this.setInitVariablesToDefault();

	}

	@Override
	public void onRelatedRowInit(RowEditEvent<?> event) {
		String message, messageDetail;
		VwPromoConsumTypeDiscount dataObjectView;
		CtPromoConsumTypeDiscount dataObject;

		message = "EDIT ROW";

		// Retrieved the data that was modified
		dataObjectView = (VwPromoConsumTypeDiscount) event.getObject();
		dataObject = promotionConsumptionTypeEJB.findEntityRelationType(dataObjectView.getPromoConsumTypeDiscountId());

		this.setSelectedRelatedData(dataObjectView);

		messageDetail = "Editing promotion_consumption_type: ";
		logger.info(messageDetail + dataObject.toString());
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
				messageDetail + " for promotion: " + dataObjectView.getPromotionTypeCode() + " and consumption: "
						+ dataObjectView.getConsumptionTypeCode());

	}

	@Override
	public void onRelatedRowEdit(RowEditEvent<?> event) throws ValidatorException {
		String message, messageDetail;
		VwPromoConsumTypeDiscount dataObjectView;
		CtPromoConsumTypeDiscount dataObject;
		boolean error = false;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObjectView = (VwPromoConsumTypeDiscount) event.getObject();
		dataObject = promotionConsumptionTypeEJB.findEntityRelationType(dataObjectView.getPromoConsumTypeDiscountId());
		dataObject.setStatusId(dataObjectView.getPromoConsumTypeDiscStatusId());
		dataObject.setModifDate(LocalDateTime.now());
		dataObject.setModifUser(this.getLoggedUser().getUserCode());

		try {

			promotionConsumptionTypeEJB.updateData(dataObject);
			messageDetail = "Data saves correctly";
			logger.info("Update the status of the promotion_consumption_type_id  " + dataObject.toString() + " - "
					+ messageDetail);
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
		VwPromoConsumTypeDiscount dataObjectView;
		message = "CANCEL UPDATE ROW";
		messageDetail = "Cancelled the edition for the promotion_consumption_type ";

		dataObjectView = (VwPromoConsumTypeDiscount) event.getObject();

		logger.info(messageDetail + "promotion_type: " + dataObjectView.getPromotionTypeCode() + " - consumption_type: "
				+ dataObjectView.getConsumptionTypeCode());
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
				messageDetail + dataObjectView.toString());

	}

	@Override
	public void changeSearchDate(ValueChangeEvent e) {
		LocalDateTime newSearchDate = (LocalDateTime) e.getNewValue();
		String message, messageDetail;

		message = "CHANGE SEARCH DATE";

		if (newSearchDate != null) {
			this.setSearchDate(newSearchDate);
		} else {
			messageDetail = "ERROR - The search date can not be null";
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
		}
	}

	@Override
	public void changeSearchDataTableTitle() {
		if (this.isHistoricSearchDataCriteria()) {
			this.setSearchDataTableTitle(
					"RESULT DATA FOR HISTORIC SEARCH - Click on row button to manage the historic records of the promotion type");
		} else {
			this.setSearchDataTableTitle("RESULT DATA FOR SEARCH DATE: " + Formatter.localDateTimeToString(this.getSearchDate())
					+ " - Click on row button to manage the historic records of the promotion type");
		}
	}

}
