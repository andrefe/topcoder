package com.andrefe;
import static org.junit.Assert.*;

import org.junit.Test;


/**
 * 0)
 * 
 * {"08:00:00","13:00:00","19:27:32"}
 * {"12:00:00","17:00:00","20:48:10"}
 * 1000
 * Returns: 10015
 * 
 * 1)
 * 
 *     
 * {"01:05:47","16:48:12"}
 * {"09:27:30","21:17:59"}
 * 2000
 * Returns: 33920
 * 
 * 2)
 * 
 *     
 * {"00:00:00"}
 * {"23:59:59"}
 * 10000
 * Returns: 299995
 * 
 * 3)
 * 
 *     
 * {"10:00:00"}
 * {"18:00:00"}
 * 10000
 * Returns: 80000
 * Notice that 18:00:00 is exclusive, so the last second was not overtime.
 * 
 * 4)
 * 
 *     
 * {"22:19:46"}
 * {"23:12:46"}
 * 5320
 * Returns: 7049
 */
public class SalaryTest {

    @Test
    public void test0() {
	String[] arrival = {"08:00:00","13:00:00","19:27:32"};
	String[] departure = {"12:00:00","17:00:00","20:48:10"};
	int wage = 1000;
	int salary = new Salary().howMuch(arrival, departure, wage);
	System.out.println("test0: " + salary);
	assertTrue(salary == 10015);
    }
    
    @Test
    public void test1() {
	String[] arrival = {"01:05:47","16:48:12"};
	String[] departure = {"09:27:30","21:17:59"};
	int wage = 2000;
	int salary = new Salary().howMuch(arrival, departure, wage);
	System.out.println("test1: " + salary);
	assertTrue(salary == 33920);
    }
    
    @Test
    public void test2() {
	String[] arrival = {"00:00:00"};
	String[] departure = {"23:59:59"};
	int wage = 10000;
	int salary = new Salary().howMuch(arrival, departure, wage);
	System.out.println("test2: " + salary);
	assertTrue(salary == 299995);
    }
    
    @Test
    public void test3() {
	String[] arrival = {"10:00:00"};
	String[] departure = {"18:00:00"};
	int wage = 10000;
	int salary = new Salary().howMuch(arrival, departure, wage);
	System.out.println("test3: " + salary);
	assertTrue(salary == 80000);
    }  
    
    @Test
    public void test4() {
	String[] arrival = {"22:19:46"};
	String[] departure = {"23:12:46"};
	int wage = 5320;
	int salary = new Salary().howMuch(arrival, departure, wage);
	System.out.println("test4: " + salary);
	assertTrue(salary == 7049);
    }  
}
