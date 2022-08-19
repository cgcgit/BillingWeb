/**
 * 
 */
package com.comasw.validator;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.omnifaces.util.Ajax;
import org.omnifaces.util.Components;
import org.primefaces.component.api.UIData;
import org.primefaces.component.datatable.DataTable;
import com.comasw.model.tables.pojos.*;
import com.comasw.utilities.Formatter;

/**
 * @author catuxa
 *
 */
@FacesValidator("startDateValidator")
public class StartDateValidator implements Validator<Object> {

	Logger logger = (Logger) LogManager.getLogger(StartDateValidator.class);

	@Override
	/**
	 * Start Date Validation - Validates if the StartDate is correct, attend to
	 * previous StartDate and EndDate.
	 * <p>
	 * If the current StartDate is between previous StartDate and EndDate, it moves
	 * the previous EndDate to the current StartDate - 1 day so the StartDate for a
	 * record n is:
	 * <p>
	 * Less or equal to End Date of the record (SD(n) <= ED (n))
	 * <p>
	 * Equal to End Date plus one day of the previous record (SD(n) = ED(n-1) + 1
	 * day)
	 */
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String errorMessage, errorMessageDetail;
		LocalDateTime currentStartDate, currentEndDate, previousStartDate, previousEndDate;
		int currentPos, lastPos, prevPos;
		FacesMessage facesMessage = new FacesMessage();
		String className;
		boolean validation = true;

		errorMessage = "START DATE ERROR";

		// The value can't be null
		if (value == null) {
			errorMessageDetail = "Error - The current Start Date can't be null";
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesMessage.setSummary(errorMessage);
			facesMessage.setDetail(errorMessageDetail);
			errorMessageDetail = errorMessageDetail + "client id " + component.getClientId(context);
			logger.error(errorMessageDetail);
			throw new ValidatorException(facesMessage);
		}

		// Gets the data from table
		DataTable dataTable = (DataTable) Components.getClosestParent(component, UIData.class);
		currentPos = dataTable.getRowIndex(); // position of the current dat
		lastPos = dataTable.getRowCount() - 1; // last position of the table
		className = dataTable.getRowData().getClass().getSimpleName(); // ClassName of the data
		currentStartDate = (LocalDateTime) value;

		previousStartDate = null;
		previousEndDate = null;

		try {

			switch (className) {
			case "CtFeeType":
				currentEndDate = ((CtFeeType) dataTable.getRowData()).getEndDate();
				if (currentPos != 0) {
					// It os not the first row --> the change could be affected other rows
					if (currentPos != lastPos) {
						// Not the unique data --> the previous end date must be modified
						prevPos = currentPos - 1;
						dataTable.setRowIndex(prevPos);
						previousStartDate = ((CtFeeType) dataTable.getRowData()).getStartDate();
						previousEndDate = ((CtFeeType) dataTable.getRowData()).getEndDate();
					}
				}
				break;

			case "CtPromotionType":
				currentEndDate = ((CtPromotionType) dataTable.getRowData()).getEndDate();
				if (currentPos != 0) {
					// It os not the first row --> the change could be affected other rows
					if (currentPos != lastPos) {
						// Not the unique data --> the previous end date must be modified
						prevPos = currentPos - 1;
						dataTable.setRowIndex(prevPos);
						previousStartDate = ((CtPromotionType) dataTable.getRowData()).getStartDate();
						previousEndDate = ((CtPromotionType) dataTable.getRowData()).getEndDate();
					}
				}
				break;

			case "ItCustomer":
				currentEndDate = ((ItCustomer) dataTable.getRowData()).getEndDate();
				if (currentPos != 0) {
					// It os not the first row --> the change could be affected other rows
					if (currentPos != lastPos) {
						// Not the unique data --> the previous end date must be modified
						prevPos = currentPos - 1;
						dataTable.setRowIndex(prevPos);
						previousStartDate = ((ItCustomer) dataTable.getRowData()).getStartDate();
						previousEndDate = ((ItCustomer) dataTable.getRowData()).getEndDate();
					}
				}
				break;

			case "ItAccount":
				currentEndDate = ((ItAccount) dataTable.getRowData()).getEndDate();
				if (currentPos != 0) {
					// It os not the first row --> the change could be affected other rows
					if (currentPos != lastPos) {
						// Not the unique data --> the previous end date must be modified
						prevPos = currentPos - 1;
						dataTable.setRowIndex(prevPos);
						previousStartDate = ((ItAccount) dataTable.getRowData()).getStartDate();
						previousEndDate = ((ItAccount) dataTable.getRowData()).getEndDate();
					}
				}
				break;

			case "ItProduct":
				currentEndDate = ((ItProduct) dataTable.getRowData()).getEndDate();
				if (currentPos != 0) {
					// It os not the first row --> the change could be affected other rows
					if (currentPos != lastPos) {
						// Not the unique data --> the previous end date must be modified
						prevPos = currentPos - 1;
						dataTable.setRowIndex(prevPos);
						previousStartDate = ((ItProduct) dataTable.getRowData()).getStartDate();
						previousEndDate = ((ItProduct) dataTable.getRowData()).getEndDate();
					}
				}
				break;

			case "ItService":
				currentEndDate = ((ItService) dataTable.getRowData()).getEndDate();
				if (currentPos != 0) {
					// It os not the first row --> the change could be affected other rows
					if (currentPos != lastPos) {
						// Not the unique data --> the previous end date must be modified
						prevPos = currentPos - 1;
						dataTable.setRowIndex(prevPos);
						previousStartDate = ((ItService) dataTable.getRowData()).getStartDate();
						previousEndDate = ((ItService) dataTable.getRowData()).getEndDate();
					}
				}
				break;

			case "ItFee":
				currentEndDate = ((ItFee) dataTable.getRowData()).getEndDate();
				if (currentPos != 0) {
					// It os not the first row --> the change could be affected other rows
					if (currentPos != lastPos) {
						// Not the unique data --> the previous end date must be modified
						prevPos = currentPos - 1;
						dataTable.setRowIndex(prevPos);
						previousStartDate = ((ItFee) dataTable.getRowData()).getStartDate();
						previousEndDate = ((ItFee) dataTable.getRowData()).getEndDate();
					}
				}
				break;

			case "ItPromotion":
				currentEndDate = ((ItPromotion) dataTable.getRowData()).getEndDate();
				if (currentPos != 0) {
					// It os not the first row --> the change could be affected other rows
					if (currentPos != lastPos) {
						// Not the unique data --> the previous end date must be modified
						prevPos = currentPos - 1;
						dataTable.setRowIndex(prevPos);
						previousStartDate = ((ItPromotion) dataTable.getRowData()).getStartDate();
						previousEndDate = ((ItPromotion) dataTable.getRowData()).getEndDate();
					}
				}
				break;
			default:
				errorMessageDetail = "ERROR - The class object " + className + "is not expect";
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesMessage.setSummary(errorMessage);
				facesMessage.setDetail(errorMessageDetail);
				logger.fatal(errorMessageDetail);
				throw new ValidatorException(facesMessage);
			}

			if (currentStartDate.isBefore(Formatter.stringToLocalDateTime(Formatter.DEFAULT_START_DATE))) {
				errorMessageDetail = "Error - The current Start Date can not less than minimum allowed date ("
						+ Formatter.DEFAULT_START_DATE + ").";
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesMessage.setSummary(errorMessage);
				facesMessage.setDetail(errorMessageDetail);
				context.addMessage(component.getClientId(context), facesMessage);
				errorMessageDetail = errorMessageDetail + "client id " + component.getClientId(context);
				logger.error(errorMessageDetail);
				// throw new ValidatorException(faces_message);
				validation = false;
			}

			if ((validation) && currentEndDate == null) {
				errorMessageDetail = "Error - The current End Date can not be null. Please fill the current End Date field";
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesMessage.setSummary(errorMessage);
				facesMessage.setDetail(errorMessageDetail);
				context.addMessage(component.getClientId(context), facesMessage);
				errorMessageDetail = errorMessageDetail + "client id " + component.getClientId(context);
				logger.error(errorMessageDetail);
				// throw new ValidatorException(faces_message);
				validation = false;
			}

			if ((validation) && currentStartDate.isAfter(currentEndDate)) {
				errorMessageDetail = "Error - The current Start Date must be less or equal to current End Date";
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesMessage.setSummary(errorMessage);
				facesMessage.setDetail(errorMessageDetail);
				context.addMessage(component.getClientId(context), facesMessage);
				errorMessageDetail = errorMessageDetail + "client id " + component.getClientId(context);
				logger.error(errorMessageDetail);
				// throw new ValidatorException(faces_message);
				validation = false;
			}

			if ((validation) && (currentPos != lastPos)) {
				// Not the unique row ==> check if the start date must be validate respect of
				// previous row
				if (currentPos != 0) {
					// Not the first row --> the previous end date must be modify
					if (Duration.between(previousStartDate, previousEndDate).toDays() < 1) {
						// If the days between previous StartDate and EndDate is less than 1
						// the current StartDate not fit into
						errorMessageDetail = "Error - The current Start Date must be one day after the previous End Date, and the previous End Date must be greater or equal to the previous Start Date";
						facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
						facesMessage.setSummary(errorMessage);
						facesMessage.setDetail(errorMessageDetail);
						context.addMessage(component.getClientId(context), facesMessage);
						errorMessageDetail = errorMessageDetail + "client id " + component.getClientId(context);
						logger.error(errorMessageDetail);
						// throw new ValidatorException(faces_message);
						validation = false;
					}

					if (validation) {
						if (previousEndDate.isEqual(currentStartDate.minusDays(1))) {
							// do nothing
							logger.info(
									"The current Start Date is the previous End Date plus one day. It's not need to change anything");
						} else {
							// Set the prevEndDate to the new value
							// If the prevEndDate is less than the new value --> Reduced the previous
							// interval of dates
							// If the prevEndDate is greater than the new value --> Extended the previous
							// interval of dates

							validation = updateEndDate(className, dataTable, currentPos - 1,
									currentStartDate.minusDays(1));
							if (validation) {
								dataTable.setRowIndex(currentPos - 1);
								Ajax.updateRow(dataTable, currentPos - 1);
								logger.info(
										"Modifies the current Start Date and the previous End Date (to current Start Date value minus one day)");
							}
						}
					}
				}
			}

		} catch (EJBException e) {
			Exception ne = (Exception) e.getCause();
			switch (ne.getClass().getSimpleName()) {
			case "CoMaSwDataAccessException":
				errorMessageDetail = "DATABASE ERROR - " + ne.getMessage();
				// faces_message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error_message,
				// error_message_detail);
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesMessage.setSummary(errorMessage);
				facesMessage.setDetail(errorMessageDetail);
				logger.fatal(errorMessageDetail);
				// CoMaSwMessages.CoMaSwDialogMessageFatal(CURRENT_REQUEST_CONTEXT,
				// "DATABASE ERROR - " + ne.getMessage());
				throw new ValidatorException(facesMessage);
			case "CoMaSwParseException":
				// showDialogMessageFatal("DATABASE ERROR - " + ne.getMessage());
				errorMessageDetail = "PARSE ERROR - " + ne.getMessage();
				// faces_message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error_message,
				// error_message_detail);
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesMessage.setSummary(errorMessage);
				facesMessage.setDetail(errorMessageDetail);
				logger.fatal(errorMessageDetail);
				// CoMaSwMessages.CoMaSwDialogMessageFatal(CURRENT_REQUEST_CONTEXT,
				// "DATABASE ERROR - " + ne.getMessage());
				throw new ValidatorException(facesMessage);
			default:
				// showDialogMessageFatal("DATABASE ERROR - " + ne.getMessage());
				errorMessageDetail = "ERROR - " + ne.getMessage();
				// faces_message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error_message,
				// error_message_detail);
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesMessage.setSummary(errorMessage);
				facesMessage.setDetail(errorMessageDetail);
				logger.fatal(errorMessageDetail);
				// CoMaSwMessages.CoMaSwDialogMessageFatal(CURRENT_REQUEST_CONTEXT,
				// "DATABASE ERROR - " + ne.getMessage());
				throw new ValidatorException(facesMessage);
			}
		} catch (Exception e) {
			// showDialogMessageFatal("ERROR - " + e.getCause().toString());
			if (!e.getClass().getName().equals("javax.faces.validator.ValidatorException")) {
				errorMessageDetail = "ERROR - " + e.getCause().toString();
				// faces_message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error_message,
				// error_message_detail);
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesMessage.setSummary(errorMessage);
				facesMessage.setDetail(errorMessageDetail);
				logger.fatal(errorMessageDetail);
				// CoMaSwMessages.CoMaSwDialogMessageFatal(CURRENT_REQUEST_CONTEXT,
				// "ERROR - " + e.getCause().toString());
				throw new ValidatorException(facesMessage);
			}

		} finally {
			dataTable.setRowIndex(currentPos);

		}

		if (!validation) {
			if (facesMessage.getDetail() == null) {
				logger.info("Valid Start Date");
			} else {
				throw new ValidatorException(facesMessage);
			}
		}

	}

	private boolean updateEndDate(String className, DataTable dataTable, int pos, LocalDateTime endDate) {

		boolean result = true;

		dataTable.setRowIndex(pos);

		switch (className) {
		case "CtFeeType":
			((CtFeeType) dataTable.getRowData()).setEndDate(endDate);
			break;
		default:
			result = false;
			FacesMessage facesMesage = new FacesMessage();
			String errorMessage = "START DATE ERROR - updating previous End Date";
			String errorMessageDetail = "ERROR - The class object " + className + "is not expect";

			facesMesage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesMesage.setSummary(errorMessage);
			facesMesage.setDetail(errorMessageDetail);
			logger.fatal(errorMessageDetail);
			// CoMaSwMessages.CoMaSwDialogMessageFatal(CURRENT_REQUEST_CONTEXT,
			// "DATABASE ERROR - " + ne.getMessage());
			throw new ValidatorException(facesMesage);
		}
		return result;

	}

}
