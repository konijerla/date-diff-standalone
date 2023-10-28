package com.ashok.my.learnings.date.validator;

import static com.ashok.my.learnings.date.constants.Constants.DATE_FORMAT;
import static com.ashok.my.learnings.date.constants.Constants.DATE_FORMAT_REGEX;
import static com.ashok.my.learnings.date.constants.Constants.YEAR_RANGE_MAX;
import static com.ashok.my.learnings.date.constants.Constants.YEAR_RANGE_MIN;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ashok.my.learnings.date.exception.InvalidDateException;

public class ValidDateValidator {

	Logger logger = LoggerFactory.getLogger(ValidDateValidator.class);

	public static boolean isValid(String dateStr) {
		// Check if the string matches the expected format "dd MM yyyy"
		if (!dateStr.matches(DATE_FORMAT_REGEX)) {
			throw new InvalidDateException(
					"Invalid date '" + dateStr + "'supplied. Date should be in '" + DATE_FORMAT + "' format. "
							+ "And length of date should be 10 chars inclusing space. " + "And Year should be between "
							+ YEAR_RANGE_MIN + " and " + YEAR_RANGE_MAX + ". " + "Example : 25 03 2019.");
		}
		// Split the string into day, month, and year
		String[] parts = dateStr.split(" ");
		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);

		// Basic checks for day, month, and year
		if (day < 1 || day > 31 || month < 1 || month > 12 || year < YEAR_RANGE_MIN || year > YEAR_RANGE_MAX) {
			throw new InvalidDateException("Invalid date supplied. Date should be in '" + DATE_FORMAT + "' format. "
					+ "And length of date should be 10 chars inclusing space. " + "And Year should be between "
					+ YEAR_RANGE_MIN + " and " + YEAR_RANGE_MAX + ". " + "Example : 25 03 2019.");
		}

		// Additional checks for specific months (e.g., February)
		if (month == 2) {
			if ((day > 29) || (day == 29 && !isLeapYear(year))) {
				throw new InvalidDateException("Invalid date supplied. Date should be in '" + DATE_FORMAT + "' format. "
						+ "And length of date should be 10 chars inclusing space. " + "And Year should be between "
						+ YEAR_RANGE_MIN + " and " + YEAR_RANGE_MAX + ". " + "Example : 25 03 2019.");
			}

		}

		// Additional checks for months with 30 days
		if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
			throw new InvalidDateException("Invalid date supplied. Date should be in '" + DATE_FORMAT + "' format. "
					+ "And length of date should be 10 chars inclusing space. " + "And Year should be between "
					+ YEAR_RANGE_MIN + " and " + YEAR_RANGE_MAX + ". " + "Example : 25 03 2019.");
		}

		return true;
	}

	private static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
}
