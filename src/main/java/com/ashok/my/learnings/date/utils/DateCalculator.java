package com.ashok.my.learnings.date.utils;

import static com.ashok.my.learnings.date.constants.Constants.INVALID_DATE_ERROR;
import static com.ashok.my.learnings.date.constants.Constants.INVALID_INPUT_ERROR;
import static com.ashok.my.learnings.date.constants.Constants.NO_INPUT_ERROR;
import static com.ashok.my.learnings.date.constants.Constants.YEAR_OUT_OF_RANGE_ERROR;
import static com.ashok.my.learnings.date.validator.ValidDateValidator.isValid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.ashok.my.learnings.date.exception.InvalidDateException;

public class DateCalculator {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter two dates in the format DD MM YYYY, DD MM YYYY:");
		String input = scanner.nextLine();
		scanner.close();
		DateCalculator calculator = new DateCalculator();
		System.out.println("Output -> " + calculator.calculateDifference(input));
		System.out.println("");
		ArrayList<String> testData = new ArrayList<String>(Arrays.asList("01 01 2019,01 02 2020",
				"01 01 2020,01 02 2019", "01 01 2019,01 02 2020", "01 01 2019, 01 02 2020", "01/01/2019,01 02 2020",
				"01-01-2019,01 02 2020", "01 01 2019,01 02 2021", "01 01 2019,01 022020", "01 01 2019,  01 02 2020",
				"01 01 2019,31 02 2020", "01 01 2019", " , ", "            ", "1 01 2019,01 02 2020"));

		testData.forEach(line -> {
			System.out.println("Output -> " + calculator.calculateDifference(line));
			System.out.println("");
		});
	}

	public String calculateDifference(String input) {
		try {
			System.out.println("Input  -> " + input);

			isInputSupplied(input);

			String[] dateStrings = splitDates(input);

			Date date1 = createDate(dateStrings[0]);
			Date date2 = createDate(dateStrings[1]);

			long difference = Math.abs(date2.daysSince(date1));

			if (date1.compareTo(date2) > 0) {
				return date2 + ", " + date1 + ", Difference: " + difference + " days";
			}

			return date1 + ", " + date2 + ", Difference: " + difference + " days";
		} catch (InvalidDateException exception) {
			return exception.getMessage();
		} catch (Exception exception) {
			return exception.getMessage();
		}

	}

	private String[] splitDates(String input) {
		String[] dateStrings = input.split(",");
		if (dateStrings.length != 2) {
			throw new InvalidDateException(String.format(INVALID_INPUT_ERROR, input));
		}
		return dateStrings;
	}

	private void isInputSupplied(String input) {
		if (null == input || input.trim().isEmpty() || input.trim().isBlank()) {
			throw new InvalidDateException(NO_INPUT_ERROR);
		}
	}

	private static Date createDate(String dateString) {
		isValid(dateString);

		String[] parts = dateString.trim().split(" ");
		if (parts.length != 3) {
			throw new InvalidDateException(String.format(INVALID_DATE_ERROR, dateString));
		}

		try {
			int day = Integer.parseInt(parts[0]);
			int month = Integer.parseInt(parts[1]);
			int year = Integer.parseInt(parts[2]);

			if (year < 1900 || year > 2020) {
				throw new InvalidDateException(String.format(YEAR_OUT_OF_RANGE_ERROR, dateString));
			}

			return new Date(day, month, year);
		} catch (NumberFormatException e) {
			throw new InvalidDateException(String.format(INVALID_DATE_ERROR, dateString));
		}
	}

}
