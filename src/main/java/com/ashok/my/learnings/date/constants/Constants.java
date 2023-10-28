package com.ashok.my.learnings.date.constants;

public class Constants {

	public static final String DATE_FORMAT = "dd MM yyyy";
	public static final String DATE_FORMAT_REGEX = "^(3[01]|[12][0-9]|0[1-9]) (1[0-2]|0[1-9]) [0-9]{4}$";
	public static final int YEAR_RANGE_MIN = 1900;
	public static final int YEAR_RANGE_MAX = 2020;

	public static final String INVALID_DATE_ERROR = "Invalid date or no date '%s' supplied. Date should be in '"
			+ DATE_FORMAT + "' format. "
			+ "And length of date should be 10 chars including space. And Year should be between " + YEAR_RANGE_MIN
			+ " and " + YEAR_RANGE_MAX + ". " + "Example : 25 03 2019.";

	public static final String INVALID_INPUT_ERROR = "Invalid input '%s' supplied. Please provide two dates separated by a comma. "
			+ "Example : '25 03 2019, 25 03 2020'";

	public static final String NO_INPUT_ERROR = "No input supplied. Please provide two dates separated by a comma. "
			+ "Example : '25 03 2019, 25 03 2020'";

	public static final String YEAR_OUT_OF_RANGE_ERROR = "Out of range date '%s' supplied. Year should be between between "
			+ YEAR_RANGE_MIN + " and " + YEAR_RANGE_MAX + ". ";

}
