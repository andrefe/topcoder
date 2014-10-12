package com.andrefe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem Statement     
 * 
 * 
 * Often employees at a company time stamp their arrivals
 * and departures, so when the month is over the boss can check how much each
 * employee has worked. Given the time stamps for a single employee during a
 * single day as well as his (or her) hourly wage, calculate how much the
 * employee has earned that day.
 * 
 * The time stamps are given in the format
 * "hh:mm:ss" (quotes for clarity only) where hh is the hour (between 00 and 23
 * inclusive), mm is the minute (between 00 and 59 inclusive) and ss is the
 * second (between 00 and 59 inclusive). All these numbers have exactly two
 * digits. The arrival time stamps are inclusive, and the departure time stamps
 * are exclusive, so an employee arriving at 09:00:00 one day and departing
 * 17:30:00 the same day has worked exactly 8 hours 30 minutes 0 seconds during
 * that interval.
 * 
 * An employee working during evenings (between 18:00:00 and
 * 23:59:59, inclusive) or nights (between 00:00:00 and 05:59:59, inclusive)
 * gets paid one and a half times as much during that period.
 * 
 * 
 * Create a class
 * Salary containing the method howMuch which takes a String[], arrival, and a
 * String[], departure, the arrival and departures times of an employee,
 * respectively, as well an int wage, the hourly wage (in cents). Your method
 * should return an int representing the total amount (in cents) the employee
 * earned during the time he or she worked. The amount should be rounded down to
 * the largest integer less than or equal to the actual amount. Element i in
 * arrival corresponds to element i in departure.
 * 
 * Definition
 *     
 * Class:
 * Salary
 * Method:
 * howMuch
 * Parameters:
 * String[], String[], int
 * Returns:
 * int
 * Method signature:
 * int howMuch(String[] arrival, String[] departure, int wage)
 * (be sure your method is public)
 * Limits
 *     
 * Time limit (s):
 * 2.000
 * Memory limit (MB):
 * 64
 * Constraints
 * - arrival will contain between 1 and 10 elements, inclusive.
 * - departure will contain between 1 and 10 elements, inclusive.
 * - arrival will contain the same number of elements as departure.
 * - All elements in arrival and departure will be in the form "hh:mm:ss"
 * (quotes
 * for clarity only) satisfying the constraints given above.
 * - All time stamps will be strictly increasing; that is,
 * arrival[0]<departure[0]<arrival[1]<departure[1] and so on.
 * -
 * wage will be between 100 and 10000, inclusive.
 */
public class Salary {

    /**
     * Inner class for monitoring standard and extra working hours.
     * 
     * @author andrefe
     *
     */
    private static class SalaryItem {
	/** Working hours in the 06:00:00 -> 18:00:00 period. */
	public int _standardSeconds = 0;
	/** Working hours outside the 06:00:00 -> 18:00:00 period. */
	public int _extraSeconds = 0;

	void addStandardSeconds(int seconds) {
	    _standardSeconds += seconds;
	}

	void addExtraSeconds(int seconds) {
	    _extraSeconds += seconds;
	}

	int computeSalary(int wage) {
	    // by keeping the two amounts at once, approximation error is
	    // limited
	    return _standardSeconds * wage / 3600 + _extraSeconds * wage / 2400; // *(3/2)/(3600)
	}
    };

    // -- Static start and stop times, string format

    private static final String EVENING_START = "18:00:00";
    private static final String EVENING_END = "24:00:00";
    // private static final String NIGHT_START = "00:00:00";
    private static final String NIGHT_END = "06:00:00";

    // -- Static start and stop times, integer format

    private static final int EVENING_START_TIME = stringToIntTime(EVENING_START);
    private static final int EVENING_END_TIME = stringToIntTime(EVENING_END);
    // private static final int NIGHT_START_TIME = stringToIntTime(NIGHT_START);
    private static final int NIGHT_END_TIME = stringToIntTime(NIGHT_END);

    /**
     * Converts HH:MM:SS strings into integers, considering the number
     * of seconds
     * 
     * @param strTime
     * @return
     */
    private static int stringToIntTime(String strTime) {
	int intTime = 0;
	// look for three integers colon separated
	String pattern = "(\\d{2}):(\\d{2}):(\\d{2})";
	Pattern r = Pattern.compile(pattern);
	Matcher m = r.matcher(strTime);
	if (m.find()) {
	    intTime += Integer.parseInt(m.group(3));
	    intTime += Integer.parseInt(m.group(2)) * 60;
	    intTime += Integer.parseInt(m.group(1)) * 3600;

	}

	return intTime;
    }

//    private static int stringToIntTime(String strTime) {
//	int intTime = 0;
//	// look for three integers colon separated
//	String[] elements = strTime.split(":");
//	intTime += Integer.parseInt(elements[2]);
//	intTime += Integer.parseInt(elements[1]) * 60;
//	intTime += Integer.parseInt(elements[0]) * 3600;
//	return intTime;
//    }

    private void evaluateHoursForEarlyShift(SalaryItem salary,
	    int arrivalTime,
	    int departureTime) {
	// END before 06:00
	if (departureTime < NIGHT_END_TIME) {
	    salary.addExtraSeconds(departureTime - arrivalTime);
	}
	// END before 18:00
	else if (departureTime < EVENING_START_TIME) {
	    salary.addExtraSeconds(NIGHT_END_TIME - arrivalTime);
	    salary.addStandardSeconds(departureTime - NIGHT_END_TIME);
	}
	// END before 24:00
	else // if(departureTime < EVENING_END_TIME)
	{
	    salary.addExtraSeconds(NIGHT_END_TIME - arrivalTime);
	    salary.addStandardSeconds(EVENING_START_TIME - NIGHT_END_TIME);
	    salary.addExtraSeconds(EVENING_END_TIME - departureTime);
	}
    }

    private void evaluateHoursForInnerShift(SalaryItem salary,
	    int arrivalTime,
	    int departureTime) {
	// END before 18:00
	if (departureTime < EVENING_START_TIME) {
	    salary.addStandardSeconds(departureTime - arrivalTime);
	}
	// END before 24:00
	else // if(departureTime < EVENING_END_TIME)
	{
	    salary.addStandardSeconds(EVENING_START_TIME - arrivalTime);
	    salary.addExtraSeconds(EVENING_END_TIME - departureTime);
	}
    }

    public void evaluateHoursForShift(SalaryItem salary,
	    int arrivalTime,
	    int departureTime) {
	// START before 06:00
	if (arrivalTime < NIGHT_END_TIME) {
	    evaluateHoursForEarlyShift(salary, arrivalTime, departureTime);
	}
	// START after 18:00
	else if (arrivalTime > EVENING_START_TIME) {
	    salary.addExtraSeconds(departureTime - arrivalTime);
	}
	// START between 06:00 and 18:00
	else {
	    evaluateHoursForInnerShift(salary, arrivalTime, departureTime);
	}
    };

    
    
    
    
    public int howMuch(String[] arrival, String[] departure, int wage) {
	// the salary item
	SalaryItem salary = new SalaryItem();

	// iterate on the shifts
	for (int i = 0; i < arrival.length; ++i) {
	    // cast to int arrival and departure and eventually generates
	    // intermediate times for night shifts (18:00 --> 06:00)
	    //
	    // e.g. {17:00,23:00} --> {17:00,18:00}{18:00,19:00}
	    // for each item, evaluate the salary

	    int arrivalTime = stringToIntTime(arrival[i]);
	    int departureTime = stringToIntTime(departure[i]);

	    // evaluates standard and extra hours for the shift
	    evaluateHoursForShift(salary, arrivalTime, departureTime);
	}

	return salary.computeSalary(wage);

    }

}
