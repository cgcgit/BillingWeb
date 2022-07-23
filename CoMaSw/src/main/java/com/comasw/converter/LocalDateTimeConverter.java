/**
 * 
 */
package com.comasw.converter;

import java.time.LocalDateTime;
import java.time.LocalDate;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.comasw.utilities.Formatter;

/**
 * @author catuxa
 *
 */
@FacesConverter("com.comasw.converter.localDateTimeConverter")
public class LocalDateTimeConverter implements Converter<Object> {

	Logger logger = (Logger) LogManager.getLogger(LocalDateTimeConverter.class);

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		String newValue = null;
		/*
		 * if (modelValue == null) { return ""; }
		 * 
		 * if (modelValue instanceof LocalDateTime) { return getFormatter(context,
		 * component).format(ZonedDateTime.of((LocalDateTime) modelValue,
		 * ZoneOffset.UTC)); } else { throw new ConverterException(new
		 * FacesMessage(modelValue + " is not a valid LocalDateTime")); }
		 */
		if (value == null) {
			return newValue;
		}

		if (value instanceof LocalDateTime) {
			newValue = Formatter.localDateTimeToString((LocalDateTime) value);
		} else if (value instanceof LocalDate) {
			newValue = Formatter.localDateToString((LocalDate) value);
		} else if (value instanceof String) {
			newValue = (String) value;
		} else {
			logger.error("ERROR conversion dates. The class value " + value.getClass().getName() + " was not expected");
		}

		return newValue;

	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		/*
		 * if (value == null || value.isEmpty()) { return null; }
		 * 
		 * try { return ZonedDateTime.parse(submittedValue, getFormatter(context,
		 * component)) .withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime(); } catch
		 * (DateTimeParseException e) { throw new ConverterException(new
		 * FacesMessage(submittedValue + " is not a valid local date time"), e); }
		 */

		if (value == null || value.isEmpty()) {
			return null;
		}

		// System.out.println("Component: " + component.getClientId() + " - old value: "
				// + value + " - new value" + timestamp.toString() );
		
		return Formatter.stringToLocalDateTime(value);

	}

	/*
	 * private DateTimeFormatter getFormatter(FacesContext context, UIComponent
	 * component) { DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern(getPattern(component), getLocale(context,
	 * component)); ZoneId zone = getZoneId(component); return (zone != null) ?
	 * formatter.withZone(zone) : formatter; }
	 * 
	 * private String getPattern(UIComponent component) { String pattern = (String)
	 * component.getAttributes().get("pattern");
	 * 
	 * if (pattern == null) { throw new
	 * IllegalArgumentException("pattern attribute is required"); }
	 * 
	 * return pattern; }
	 * 
	 * private Locale getLocale(FacesContext context, UIComponent component) {
	 * Object locale = component.getAttributes().get("locale"); return (locale
	 * instanceof Locale) ? (Locale) locale : (locale instanceof String) ? new
	 * Locale((String) locale) : context.getViewRoot().getLocale(); }
	 * 
	 * private ZoneId getZoneId(UIComponent component) { Object timeZone =
	 * component.getAttributes().get("timeZone"); return (timeZone instanceof
	 * TimeZone) ? ((TimeZone) timeZone).toZoneId() : (timeZone instanceof String) ?
	 * ZoneId.of((String) timeZone) : null; }
	 */
}