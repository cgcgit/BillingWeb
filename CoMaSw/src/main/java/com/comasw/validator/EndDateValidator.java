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

import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.utilities.Formatter;

/**
 * @author catuxa
 *
 */
@FacesValidator("endDateValidator")

public class EndDateValidator implements Validator<Object> {

	Logger logger = (Logger) LogManager.getLogger(EndDateValidator.class);

	@Override
	/**
	 * End Date Validation Validates if the EndDate is correct, attend to subsequent
	 * StartDate and EndDate. If the current EndDate is between subsequent StartDate
	 * and EndDate, it moves the subsequent StartDate to the current EndDate + 1 day
	 * so the EndDate for a record n is:
	 * <p>
	 * Greater or equal to Start Date of the record (SD(n) <= ED (n))
	 * <p>
	 * Equal to Start Date minus one day of the previous record (SD(n) = ED(n+1) - 1
	 * day)
	 */

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String errorMessage, errorMessageDetail;
		LocalDateTime currentStartDate, currentEndDate, subsequentStartDate, subsequentEndDate;
		int currentPos, lastPos, subsequentPos;
		FacesMessage facesMessage = new FacesMessage();
		String className;
		boolean validation = true;

		errorMessage = "END DATE ERROR";

		// The value can't be null
		if (value == null) {
			errorMessageDetail = "Error - The End Date can't be null";
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
		currentEndDate = (LocalDateTime) value;

		subsequentStartDate = null;
		subsequentEndDate = null;

		try {

			switch (className) {
			case "CtFeeType":
				currentStartDate = ((CtFeeType) dataTable.getRowData()).getStartDate();
				if (lastPos != 0) {
					// Not the unique data --> the change could be affected other rows
					if (currentPos != lastPos) {
						// Not the last position --> the subsequent end date must be modified
						subsequentPos = currentPos + 1;
						dataTable.setRowIndex(subsequentPos);
						subsequentStartDate = ((CtFeeType) dataTable.getRowData()).getStartDate();
						subsequentEndDate = ((CtFeeType) dataTable.getRowData()).getEndDate();
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

			if (currentEndDate.isAfter(Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE))) {
				errorMessageDetail = "Error - The current End Date can not be greather than maximum allowed date ("
						+ Formatter.DEFAULT_END_DATE + ").";
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesMessage.setSummary(errorMessage);
				facesMessage.setDetail(errorMessageDetail);
				context.addMessage(component.getClientId(context), facesMessage);
				errorMessageDetail = errorMessageDetail + "client id " + component.getClientId(context);
				logger.error(errorMessageDetail);
				// throw new ValidatorException(faces_message);
				validation = false;
			}

			if ((validation) && (currentStartDate == null)) {
				errorMessageDetail = "Error - The Start Date can not be null. Please fill the current Start Date field";
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesMessage.setSummary(errorMessage);
				facesMessage.setDetail(errorMessageDetail);
				context.addMessage(component.getClientId(context), facesMessage);
				errorMessageDetail = errorMessageDetail + "client id " + component.getClientId(context);
				logger.error(errorMessageDetail);
				// throw new ValidatorException(faces_message);
				validation = false;
			}
			if ((validation) && (currentStartDate.isAfter(currentEndDate))) {
				errorMessageDetail = "Error - The End Date must be greather or equal to Start Date";
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesMessage.setSummary(errorMessage);
				facesMessage.setDetail(errorMessageDetail);
				context.addMessage(component.getClientId(context), facesMessage);
				errorMessageDetail = errorMessageDetail + "client id " + component.getClientId(context);
				logger.error(errorMessageDetail);
				// throw new ValidatorException(faces_message);
				validation = false;
			}

			if ((validation) && (lastPos != 0)) {
				// Not the unique row ==> check if the start date must be validate respect of
				// previous row
				if (currentPos != lastPos) {
					// Not the last row --> the subsequent start date must be modify
					if (Duration.between(subsequentStartDate, subsequentEndDate).toDays() < 1) {
						// If the days between subsequent StartDate and EndDate is less than 1
						// the current StartDate not fit into
						errorMessageDetail = "Error - The current End Date must be one day before the subsequent Start Date, and the subsequent Start Date must be less or equal to the previous End Date";
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
						if (subsequentStartDate.isEqual(currentEndDate.plusDays(1))) {
							// do nothing
							logger.info(
									"The current End Date is the subsequent Start Date minus one day. It's not need to change anything");
						} else {
							// Set the subsequentStartDate to the new value
							// If the subsequentStartDate is less than the new value --> Reduced the
							// subsequent
							// interval of dates
							// If the subsequentStartDate is greater than the new value --> Extended the
							// subsequent
							// interval of dates

							validation = updateStartDate(className, dataTable, currentPos + 1,
									currentEndDate.plusDays(1));
							if (validation) {
								dataTable.setRowIndex(currentPos + 1);
								Ajax.updateRow(dataTable, currentPos + 1);
								logger.info(
										"Modifies the Start Date and the previous End Date (to Start Date value minus one day)");
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

	public boolean updateStartDate(String className, DataTable dataTable, int pos, LocalDateTime startDate) {

		boolean result = true;

		dataTable.setRowIndex(pos);

		switch (className) {
		case "CtFeeType":
			((CtFeeType) dataTable.getRowData()).setStartDate(startDate);
			break;

		default:
			result = false;
			FacesMessage facesMessage = new FacesMessage();
			String errorMessage = "START DATE ERROR - updating Start Date";
			String errorMessageDetail = "ERROR - The class object " + className + "is not expect";

			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesMessage.setSummary(errorMessage);
			facesMessage.setDetail(errorMessageDetail);
			logger.fatal(errorMessageDetail);
			// CoMaSwMessages.CoMaSwDialogMessageFatal(CURRENT_REQUEST_CONTEXT,
			// "DATABASE ERROR - " + ne.getMessage());
			throw new ValidatorException(facesMessage);
		}
		return result;
	}

}
