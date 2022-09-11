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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jooq.Record;
import org.jooq.Result;

/**
 * @author catuxa
 *
 */
public class Utilities {

	public static <R extends Record, T> void resultToList(Result<R> source, List<T> destination) {

		destination.clear();
		for (R obj : source) {

			destination.add((T) obj);
		}
	}

	public static <T, R extends Record> void listToResult(List<T> source, Result<R> destination) {

		destination.clear();

		for (T obj : source) {
			R r = (R) new Object();
			r.from(obj);
			destination.add(r);
		}

	}

	public static String MD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean emailValidation(String email) {
		// source: https://www.tutorialspoint.com/validate-email-address-in-java)
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		Pattern pattern = Pattern.compile(regex);
		Matcher match;

		match = pattern.matcher(email);

		return (match.find() && match.group().equals(email));

	}

	public static boolean phoneNumberValidation(String phoneNumber) {
		// validates phone number. Source:
		// https://es.stackoverflow.com/questions/119879/validar-n%C3%BAmero-de-m%C3%B3vil-y-n%C3%BAmero-de-tel%C3%A9fono-fija-de-n%C3%BAmero-espa%C3%B1oles-en-android
		// https://java2blog.com/validate-phone-number-java/)
		String landLineRegex = "^(\\+34|0034|34)?[89]\\d{8}$";
		String mobilLineRegex = "^(\\+34|0034|34)?[67]\\d{8}$";
		String internationalRegex = "^(\\+|00)(?:[0-9] ?){6,14}[0-9]$";
		String regex = landLineRegex + "|" + mobilLineRegex + "|" + internationalRegex;
		Pattern pattern = Pattern.compile(regex);
		Matcher match;

		match = pattern.matcher(phoneNumber);

		return (match.find() && match.group().equals(phoneNumber));
	}

	public static Object deepClone(Object object) throws Exception {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw e;
		}
	}

	public static <T> void copyGenericList(List<T> source, List<T> destination) throws Exception {
		T aux;
		destination.clear();

		for (T obj : source) {
			aux = (T) deepClone(obj);
			destination.add(aux);
		}

	}

}
