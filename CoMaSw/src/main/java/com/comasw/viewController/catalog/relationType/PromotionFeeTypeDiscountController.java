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

import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.model.tables.pojos.CtPromoFeeTypeDiscount;
import com.comasw.model.tables.pojos.CtPromotionType;
import com.comasw.model.tables.pojos.VwPromotionFeeTypeDiscount;
import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.utilities.Formatter;
import com.comasw.ejb.catalog.relationType.PromotionFeeTypeDiscountEJBLocal;
import com.comasw.ejb.catalog.type.FeeTypeEJBLocal;
import com.comasw.ejb.catalog.type.PromotionTypeEJBLocal;
import com.comasw.ejb.parameterization.StatusEJBLocal;
import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.generalClass.DoubleHistoricRelation;
import com.comasw.interfaces.IDoubleHistoricRelationsTable;

@Named
@ViewScoped
/**
 * @author catuxa
 *
 */
public class PromotionFeeTypeDiscountController
		extends DoubleHistoricRelation<CtPromotionType, VwPromotionFeeTypeDiscount, CtFeeType>
		implements Serializable, IDoubleHistoricRelationsTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4682982909640361796L;

	Logger logger = (Logger) LogManager.getLogger(PromotionFeeTypeDiscountController.class);

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private PromotionTypeEJBLocal promotionTypeEJB;

	@EJB
	private PromotionFeeTypeDiscountEJBLocal promotionFeeTypeEJB;

	@EJB
	private FeeTypeEJBLocal feeTypeEJB;

	@EJB
	private StatusEJBLocal statusEJB;

	/**
	 * Selected main data
	 */
	
	private CtPromotionType injectSelectedData;

	/**
	 * Selected candidate data
	 */
	
	private CtFeeType injectSelectedCandidateData;

	/**
	 * Selected related data
	 */
	
	private VwPromotionFeeTypeDiscount injectSelectedRelatedData;

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
	public VwPromotionFeeTypeDiscount getInjectSelectedRelatedData() {
		return injectSelectedRelatedData;
	}

	/**
	 * @param selectedRelatedData the selectedRelatedData to set
	 */
	public void setInjectSelectedRelatedData(VwPromotionFeeTypeDiscount injectSelectedRelatedData) {
		this.injectSelectedRelatedData = injectSelectedRelatedData;
	}

	// -------------------
	// METHODS
	// -------------------

	/**
	 * 
	 */
	public PromotionFeeTypeDiscountController() {
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
			this.setRelatedDataList(new ArrayList<VwPromotionFeeTypeDiscount>());
		}

		if (this.getFilteredRelatedDataList() == null) {
			this.setFilteredRelatedDataList(new ArrayList<VwPromotionFeeTypeDiscount>());
		}

		if (this.getLoggedUser() == null) {
			this.setLoggedUser((VwUsers) externalContext.getSessionMap().get("applicationUser"));
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
			errorMessage = "The selected promotion type is null";
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage);
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
	public void resetFilterHistoricCandidateDataTable() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('historicCandidateTableWidget').clearFilters()");

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
	public void loadRelatedDataList() {
		String errorMessage;

		if ((this.getSelectedData() != null) && (this.getSelectedData().getPromotionTypeId() != null)
				&& (this.getSelectedData().getPromotionTypeId() != 0)) {
			if (this.isHistoricRelatedDataCriteria()) {
				this.setRelatedDataList(promotionFeeTypeEJB
						.findHistoricRelatedEntityTypesView(this.getSelectedData().getPromotionTypeId()));
			} else {
				if (this.getSearchDate() == null) {
					logger.error("The search date is null");
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, "LOAD RELATED DATE",
							"The search date is null");
				} else {
					this.setRelatedDataList(promotionFeeTypeEJB.findRelatedEntityTypesByDateView(
							this.getSelectedData().getPromotionTypeId(), this.getSecondSearchDate()));
				}
			}
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
	public void loadCandidateDataList() {
		String errorMessage;

		if ((this.getSelectedData() != null) && (this.getSelectedData().getPromotionTypeId() != null)
				&& (this.getSelectedData().getPromotionTypeId() != 0)) {
			this.setCandidateDataList(
					promotionFeeTypeEJB.findEntityTypeCandidates(this.getSelectedData().getPromotionTypeId()));
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
	public void pushAddButtonCandidateToRelatedData() {
		String message = "ADD FEE TYPE TO PROMOTION";
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
					|| ((CtFeeType) mainDataTable.getSelection()).getFeeTypeId() == null) {
				messageDetail = "No selected fee type to add for the promotion type "
						+ this.getSelectedData().toString();
				logger.error(message + " - " + messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
						"The selected fee type to add is null");
				facesContext.validationFailed();
			} else {
				// Gets selectedData
				this.injectSelectedCandidateData = (CtFeeType) mainDataTable.getSelection();

				CtPromoFeeTypeDiscount dataObject = new CtPromoFeeTypeDiscount();
				dataObject.setPromotionTypeId(this.getSelectedData().getPromotionTypeId());
				dataObject.setFeeTypeId(this.getInjectSelectedCandidateData().getFeeTypeId());
				dataObject.setApplicationLevelId(this.getInjectSelectedCandidateData().getApplicationLevelId());
				dataObject.setInputUser(this.loggedUser.getUserCode());
				dataObject.setInputDate(LocalDateTime.now());
				dataObject.setStatusId((Integer) statusEJB.findDataByCode(ACTIVE_STATUS_CODE).getStatusId());
				try {
					promotionFeeTypeEJB.insertData(dataObject);
					this.loadCandidateDataList();
					this.loadRelatedDataList();

					// Unselect selected rows
					mainDataTable.setRowIndex(-1);
					otherDataTable.setRowIndex(-1);

					this.resetFilterCandidateDataTable();
					this.resetFilterRelatedDataTable();

					messageDetail = "The promotion fee type relation was added";
					logger.info(message + " - " + messageDetail + dataObject.toString());
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);

				} catch (CoMaSwDataAccessException e) {
					messageDetail = "Error while adding the selected promotion fee type relation ";
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
		String message = "REMOVE FEE TYPE FROM PROMOTION";
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

			if (mainDataTable.getSelection() == null || ((VwPromotionFeeTypeDiscount) mainDataTable.getSelection())
					.getPromoFeeTypeDiscountId() == null) {
				messageDetail = "No selected fee type to remove for the selected promotion type ";
				logger.error(message + " - " + messageDetail + this.getSelectedData().toString());
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				facesContext.validationFailed();
			} else {
				// Gets selectedData
				this.setInjectSelectedRelatedData((VwPromotionFeeTypeDiscount) mainDataTable.getSelection());

				CtPromoFeeTypeDiscount dataObject = promotionFeeTypeEJB
						.findEntityRelationType(this.injectSelectedRelatedData.getPromoFeeTypeDiscountId());
				try {
					promotionFeeTypeEJB.deleteData(dataObject);
					this.loadCandidateDataList();
					this.loadRelatedDataList();

					// Unselect selected rows
					mainDataTable.setRowIndex(-1);
					otherDataTable.setRowIndex(-1);

					this.resetFilterCandidateDataTable();
					this.resetFilterRelatedDataTable();

					messageDetail = "The selected promotion fee type relation was removed";
					logger.info(message + " - " + messageDetail + dataObject.toString());
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							"The selected fee type was removed");

				} catch (CoMaSwDataAccessException e) {
					messageDetail = "Error while removing the selected promotion fee type relation ";
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
	public void onRelatedRowInit(RowEditEvent<?> event) {
		String message, messageDetail;
		VwPromotionFeeTypeDiscount dataObjectView;
		CtPromoFeeTypeDiscount dataObject;

		message = "EDIT ROW";

		// Retrieved the data that was modified
		dataObjectView = (VwPromotionFeeTypeDiscount) event.getObject();
		dataObject = promotionFeeTypeEJB.findEntityRelationType(dataObjectView.getPromoFeeTypeDiscountId());

		this.setSelectedRelatedData(dataObjectView);

		messageDetail = "Editing promotion_fee_type: ";
		logger.info(messageDetail + dataObject.toString());
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
				messageDetail + " for promotion: " + dataObjectView.getPromotionTypeCode() + " and fee: "
						+ dataObjectView.getFeeTypeCode());

	}

	@Override
	public void onRelatedRowEdit(RowEditEvent<?> event) throws ValidatorException {
		String message, messageDetail;
		VwPromotionFeeTypeDiscount dataObjectView;
		CtPromoFeeTypeDiscount dataObject;
		boolean error = false;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObjectView = (VwPromotionFeeTypeDiscount) event.getObject();
		dataObject = promotionFeeTypeEJB.findEntityRelationType(dataObjectView.getPromoFeeTypeDiscountId());
		dataObject.setStatusId(dataObjectView.getPromoFeeTypeDiscStatusId());
		dataObject.setModifDate(LocalDateTime.now());
		dataObject.setModifUser(this.getLoggedUser().getUserCode());

		try {

			promotionFeeTypeEJB.updateData(dataObject);
			messageDetail = "Data saves correctly";
			logger.info(
					"Update the status of the promotion_fee_type_id  " + dataObject.toString() + " - " + messageDetail);
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
		VwPromotionFeeTypeDiscount dataObjectView;
		message = "CANCEL UPDATE ROW";
		messageDetail = "Cancelled the edition for the promotion_fee_type ";

		dataObjectView = (VwPromotionFeeTypeDiscount) event.getObject();

		logger.info(messageDetail + "promotion_type: " + dataObjectView.getPromotionTypeCode() + " - fee_type: "
				+ dataObjectView.getFeeTypeCode());
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
				messageDetail + dataObjectView.toString());

	}

	@Override
	public void setInitVariablesToDefault() {
		this.setEditingMode(false);
		this.setSearchDate(LocalDate.now().atStartOfDay());
		this.setSecondSearchDate(LocalDateTime.now());
		this.setShowDependentData(false);
		this.setHistoricRelatedDataCriteria(false);

	}

	@Override
	public void setControlVariablesToDefault() {
		this.setInitVariablesToDefault();

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
