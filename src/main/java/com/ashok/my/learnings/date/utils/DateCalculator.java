package com.ashok.my.learnings.date.utils;

import static com.ashok.my.learnings.date.constants.Constants.DATE_FORMAT;
import static com.ashok.my.learnings.date.constants.Constants.YEAR_RANGE_MAX;
import static com.ashok.my.learnings.date.constants.Constants.YEAR_RANGE_MIN;
import static com.ashok.my.learnings.date.validator.ValidDateValidator.isValid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.ashok.my.learnings.date.exception.InvalidDateException;

public class DateCalculator {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter two dates in the format DD MM YYYY, DD MM YYYY:");
		String input = scanner.nextLine();
		scanner.close();
		DateCalculator calculator = new DateCalculator();
		System.out.println(calculator.calculateDifference(input));

		ArrayList<String> testData = new ArrayList<String>(
				Arrays.asList("01 01 2019,01 02 2020", 
						"01 01 2020,01 02 2019", 
						"01 01 2019,01 02 2020",
						"01 01 2019, 01 02 2020", 
						"01/01/2019,01 02 2020", 
						"01-01-2019,01 02 2020",
						"01 01 2019,01 02 2021"));
		
		testData.forEach(lines -> {calculator.calculateDifference(lines));
	}

	private String calculateDifference(String input) {
		try {
			isInputSupplied(input);

			String[] dateStrings = splitDates(input);

			isValid(dateStrings[0]);
			isValid(dateStrings[1]);

			Date date1 = createDate(dateStrings[0]);
			Date date2 = createDate(dateStrings[1]);

			long difference = Math.abs(date2.daysSince(date1));

			if (date1.compareTo(date2) > 0) {
				return date2 + ", " + date1 + ", Difference: " + difference + " days";
			}

			return date1 + ", " + date2 + ", Difference: " + difference + " days";
		} catch (

		InvalidDateException exception) {
			return exception.getMessage();

		}

	}

	private String[] splitDates(String input) {
		String[] dateStrings = input.split(",");
		if (dateStrings.length != 2) {
			throw new InvalidDateException("Invalid input format. Please provide two dates separated by a comma."
					+ "Invalid date supplied. Each Date should be in '" + DATE_FORMAT + "' format. "
					+ "And length of date should be 10 chars inclusing space. " + "And Year should be between "
					+ YEAR_RANGE_MIN + " and " + YEAR_RANGE_MAX + ". " + "Example : 25 03 2019.");
		}
		return dateStrings;
	}

	private void isInputSupplied(String input) {
		if (null == input || input.trim().isEmpty() || input.trim().isBlank()) {
			throw new InvalidDateException("Invalid input format. Please provide two dates separated by a comma."
					+ "Invalid date supplied. Each Date should be in '" + DATE_FORMAT + "' format. "
					+ "And length of date should be 10 chars inclusing space. " + "And Year should be between "
					+ YEAR_RANGE_MIN + " and " + YEAR_RANGE_MAX + ". " + "Example : 25 03 2019.");
		}
	}

	private static Date createDate(String dateString) {
		isValid(dateString);

		String[] parts = dateString.trim().split(" ");
		if (parts.length != 3) {
			return null;
		}

		try {
			int day = Integer.parseInt(parts[0]);
			int month = Integer.parseInt(parts[1]);
			int year = Integer.parseInt(parts[2]);

			if (year < 1900 || year > 2020) {
				return null;
			}

			return new Date(day, month, year);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
