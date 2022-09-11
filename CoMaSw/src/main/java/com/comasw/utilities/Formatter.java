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

package com.comasw.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author catuxa
 *
 */
public class Formatter {

	public static final String DEFAULT_START_DATE = "01/01/1900";
	public static final String DEFAULT_END_DATE = "31/12/9999";

	private static final DateTimeFormatter simpleFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * Turns a local date into string (format dd/MM/yyyy)
	 * 
	 * @param date to convert
	 * @return string (format dd/MM/yyyy)
	 */
	public static String localDateToString(LocalDate date) {

		return simpleFormatter.format(date);

	}

	/**
	 * Turns a local date time into string (format dd/MM/yyyy)
	 * 
	 * @param date to convert
	 * @return string (format dd/MM/yyyy)
	 */

	public static String localDateTimeToString(LocalDateTime date) {
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return simpleFormatter.format(date);
	}

	/**
	 * Turns an string (format dd/MM/yyyy) into a local date
	 * 
	 * @param date string to convert (format dd/MM/yyyy)
	 * @return local date
	 */
	public static LocalDate stringToLocalDate(String date) {
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, simpleFormatter);

	}

	/**
	 * Turns an string (format dd/MM/yyyy) into a local date time
	 * 
	 * @param date string to convert (format dd/MM/yyyy)
	 * @return local date time
	 */

	public static LocalDateTime stringToLocalDateTime(String date) {
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, simpleFormatter).atStartOfDay();

	}

	/**
	 * Turns a local date into local date time
	 * 
	 * @param date
	 * @return local date time
	 */
	public static LocalDateTime localDateToLocalDateTime(LocalDate date) {

		return date.atStartOfDay();
	}

	/**
	 * Turns a local date time into local date
	 * 
	 * @param date
	 * @return local date
	 */
	public static LocalDate localDateTimeToLocalDate(LocalDateTime date) {
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.toLocalDate();
	}

}
