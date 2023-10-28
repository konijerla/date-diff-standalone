package unit.com.ashok.my.learning.date;

import static com.ashok.my.learnings.date.constants.Constants.*;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ashok.my.learnings.date.utils.DateCalculator;

public class TestDateCalculator {

	DateCalculator calculator;
	String input = null;
	String actualResult = null;
	String expectedResult = null;

//	Input  -> 01 01 2019,01 02 2020
//	Output -> 01 01 2019, 01 02 2020, Difference: 396 days
//
//	Input  -> 01 01 2019,01 02 2020
//	Output -> 01 01 2019, 01 02 2020, Difference: 396 days
//
//	Input  -> 01 01 2020,01 02 2019
//	Output -> 01 02 2019, 01 01 2020, Difference: 31 days
//
//	Input  -> 01 01 2019,01 02 2020
//	Output -> 01 01 2019, 01 02 2020, Difference: 396 days
//
//	Input  -> 01 01 2019, 01 02 2020
//	Output -> 01 01 2019, 01 02 2020, Difference: 396 days
//
//	Input  -> 01/01/2019,01 02 2020
//	Output -> Invalid date or no date '01/01/2019' supplied. Date should be in 'dd MM yyyy' format. And length of date should be 10 chars including space. And Year should be between 1900 and 2020. Example : 25 03 2019.
//
//	Input  -> 01-01-2019,01 02 2020
//	Output -> Invalid date or no date '01-01-2019' supplied. Date should be in 'dd MM yyyy' format. And length of date should be 10 chars including space. And Year should be between 1900 and 2020. Example : 25 03 2019.
//
//	Input  -> 01 01 2019,01 02 2021
//	Output -> Out of range date '01 02 2021' supplied. Year should be between between 1900 and 2020. 
//
//	Input  -> 01 01 2019,01 022020
//	Output -> Invalid date or no date '01 022020' supplied. Date should be in 'dd MM yyyy' format. And length of date should be 10 chars including space. And Year should be between 1900 and 2020. Example : 25 03 2019.
//
//	Input  -> 01 01 2019,  01 02 2020
//	Output -> 01 01 2019, 01 02 2020, Difference: 396 days
//
//	Input  -> 01 01 2019,31 02 2020
//	Output -> Invalid date or no date '31 02 2020' supplied. Date should be in 'dd MM yyyy' format. And length of date should be 10 chars including space. And Year should be between 1900 and 2020. Example : 25 03 2019.
//
//	Input  -> 01 01 2019
//	Output -> Invalid input '01 01 2019' supplied. Please provide two dates separated by a comma. Example : '25 03 2019, 25 03 2020'
//
//	Input  ->  , 
//	Output -> Invalid date or no date ' ' supplied. Date should be in 'dd MM yyyy' format. And length of date should be 10 chars including space. And Year should be between 1900 and 2020. Example : 25 03 2019.
//
//	Input  ->             
//	Output -> No input supplied. Please provide two dates separated by a comma. Example : '25 03 2019, 25 03 2020'
//
//	Input  -> 1 01 2019,01 02 2020
//	Output -> Invalid date or no date '1 01 2019' supplied. Date should be in 'dd MM yyyy' format. And length of date should be 10 chars including space. And Year should be between 1900 and 2020. Example : 25 03 2019.
//
//	

	@Before
	public void setUp() {
		calculator = new DateCalculator();
		input = null;
		actualResult = null;
		expectedResult = null;
	}

	@After
	public void tearDown() {
		calculator = null;
		input = null;
		actualResult = null;
		expectedResult = null;
	}

	@Test
	public void testSupplyInvalidDateFormatAndCalculateDifference() {
		input = "01/01/2019,01 02 2020";
		expectedResult = String.format(INVALID_DATE_ERROR, "01/01/2019");
		actualResult = calculator.calculateDifference(input);
		assertTrue(expectedResult.equals(actualResult));
		
	}

	@Test
	public void testSupplyEmptyInputAndCalculateDifference() {
		input = "";
		expectedResult = NO_INPUT_ERROR;
		actualResult = calculator.calculateDifference(input);
		assertTrue(expectedResult.equals(actualResult));
	}
	
	@Test
	public void testSupplyOutofRangeYearAndCalculateDifference() {
		input = "01 02 2022, 01 02 2020";
		expectedResult = String.format(YEAR_OUT_OF_RANGE_ERROR, "01 02 2022");
		actualResult = calculator.calculateDifference(input);
		assertTrue(expectedResult.equals(actualResult));
	}
	
	@Test
	public void testSupplyEmptyDatesAndCalculateDifference() {
		input = ",";
		expectedResult = String.format(INVALID_INPUT_ERROR, input);
		actualResult = calculator.calculateDifference(input);
		assertTrue(expectedResult.equals(actualResult));
	}
	
	@Test
	public void testSupplyValidDates1AndCalculateDifference() {
		input = "01 01 2020,01 02 2019";
		expectedResult = "01 02 2019, 01 01 2020, Difference: 31 days";
		actualResult = calculator.calculateDifference(input);
		assertTrue(expectedResult.equals(actualResult));
	}
	
	@Test
	public void testSupplyValidDates2AndCalculateDifference() {
		input = "01 01 2018,01 02 2019";
		expectedResult = "01 01 2018, 01 02 2019, Difference: 396 days";
		actualResult = calculator.calculateDifference(input);
		assertTrue(expectedResult.equals(actualResult));
	}

	@Test
	public void testSupplyInValidDateFormat1AndCalculateDifference() {
		input = "01 012020,01 02 2019";
		expectedResult = String.format(INVALID_DATE_ERROR, "01 012020");
		actualResult = calculator.calculateDifference(input);
		assertTrue(expectedResult.equals(actualResult));
	}
	
	@Test
	public void testSupplyOneDateAndCalculateDifference() {
		input = "01 01 2020";
		expectedResult = String.format(INVALID_INPUT_ERROR, "01 01 2020");
		actualResult = calculator.calculateDifference(input);
		assertTrue(expectedResult.equals(actualResult));
	}
	
	@Test
	public void testSupplyInValidDate2Format1AndCalculateDifference() {
		input = "1 01 2020,01 02 2019";
		expectedResult = String.format(INVALID_DATE_ERROR, "1 01 2020");
		actualResult = calculator.calculateDifference(input);
		assertTrue(expectedResult.equals(actualResult));
	}
	
	
}
