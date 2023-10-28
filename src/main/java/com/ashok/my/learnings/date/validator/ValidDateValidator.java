package com.ashok.my.learnings.date.validator;

import static com.ashok.my.learnings.date.constants.Constants.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ashok.my.learnings.date.exception.InvalidDateException;

public class ValidDateValidator {

	Logger logger = LoggerFactory.getLogger(ValidDateValidator.class);

	public static boolean isValid(String dateStr) {

		// Check if the string matches the expected format "dd MM yyyy"
		if (null == dateStr || dateStr.trim().isEmpty() || dateStr.trim().isBlank()
				|| !dateStr.trim().matches(DATE_FORMAT_REGEX)) {

			throw new InvalidDateException(String.format(INVALID_DATE_ERROR, dateStr));
		}
		// Split the string into day, month, and year
		String[] parts = dateStr.trim().split(" ");
		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);

		// Basic checks for year
		if ( year < YEAR_RANGE_MIN || year > YEAR_RANGE_MAX) {
			throw new InvalidDateException(String.format(YEAR_OUT_OF_RANGE_ERROR, dateStr));
		}
		
		// Basic checks for day, month
		if (day < 1 || day > 31 || month < 1 || month > 12 || year < YEAR_RANGE_MIN || year > YEAR_RANGE_MAX) {
			throw new InvalidDateException(String.format(INVALID_DATE_ERROR, dateStr));
		}

		// Additional checks for specific months (e.g., February)
		if (month == 2) {
			if ((day > 29) || (day == 29 && !isLeapYear(year))) {
				throw new InvalidDateException(String.format(INVALID_DATE_ERROR, dateStr));
			}

		}

		// Additional checks for months with 30 days
		if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
			throw new InvalidDateException(String.format(INVALID_DATE_ERROR, dateStr));
		}

		return true;
	}

	private static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
}
