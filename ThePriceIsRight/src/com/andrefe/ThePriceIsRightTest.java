package com.andrefe;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 0)
 * 
 *     
 * {30,10,20,40,50}
 * Returns: { 4, 1 }
 * See above.
 * 1)
 * 
 *     
 * {39,88,67,5,69,87,82,64,58,61}
 * Returns: { 4, 2 }
 * The maximum number of prices that can be revealed is 4, and there are 2 ways
 * of achieving it. The host could either reveal 39 * 67 * 69 87 * * * * or 39 *
 * 67 * 69 * 82 * * *. The method should return {4,2}.
 * 2)
 * 
 *     
 * {1,2,3,4,5,6,7,8,9,10}
 * Returns: { 10, 1 }
 * 
 * 3)
 * 
 *     
 * {10,9,8,7,6,5,4,3,2,1}
 * Returns: { 1, 10 }
 * 
 * 4)
 * 
 *     
 * {29,31,73,70,14,5,6,34,53,30,15,86}
 * Returns: { 5, 2 }
 * The host could either reveal 29 31 * * * * * 34 53 * * 86 or * * * * * 5 6 34
 * 53 * * 86. The method should return {5,2}
 * 5)
 * 
 *     
 * {100,99,1,2,3}
 * Returns: { 3, 1 }
 * In theory, because elements in prices are at least 1 (due to constraints), it
 * is enough to reveal any of 1, 2 or 3 to know that the sequence of prices is
 * broken. However, the host DOES NOT make these intermediate deductions and
 * will reveal * * 1 2 3.
 * 6)
 * 
 *     
 * {10,20,11,12}
 * Returns: { 3, 1 }
 */
public class ThePriceIsRightTest {

    @Test
    public void test0() {
	int[] prices = {30,10,20,40,50};
	int[] result = new ThePriceIsRight().howManyReveals(prices);
	System.out.println(result[0] + "," + result[1]);
	int[] expectedResult = { 4, 1 };
	System.out.println(expectedResult[0] + "," + expectedResult[1]);
	assertArrayEquals(result,expectedResult);
    }
    
    @Test
    public void test1() {
	int[] prices = {39,88,67,5,69,87,82,64,58,61};
	int[] result = new ThePriceIsRight().howManyReveals(prices);
	System.out.println(result[0] + "," + result[1]);
	int[] expectedResult = { 4, 2 };
	assertArrayEquals(result,expectedResult);
    }
    
    @Test
    public void test2() {
	int[] prices = {1,2,3,4,5,6,7,8,9,10};
	int[] result = new ThePriceIsRight().howManyReveals(prices);
	System.out.println(result[0] + "," + result[1]);
	int[] expectedResult = { 10, 1 };
	assertArrayEquals(result,expectedResult);
    }
    
    @Test
    public void test3() {
	int[] prices = {10,9,8,7,6,5,4,3,2,1};
	int[] result = new ThePriceIsRight().howManyReveals(prices);
	System.out.println(result[0] + "," + result[1]);
	int[] expectedResult = { 1, 10 };
	assertArrayEquals(result,expectedResult);
    }
    
    @Test
    public void test4() {
	int[] prices = {29,31,73,70,14,5,6,34,53,30,15,86};
	int[] result = new ThePriceIsRight().howManyReveals(prices);
	System.out.println(result[0] + "," + result[1]);
	int[] expectedResult = {5,2};
	assertArrayEquals(result,expectedResult);
    }
    
    @Test
    public void test5() {
	int[] prices = {100,99,1,2,3};
	int[] result = new ThePriceIsRight().howManyReveals(prices);
	System.out.println(result[0] + "," + result[1]);
	int[] expectedResult = { 3, 1 };
	assertArrayEquals(result,expectedResult);
    }
}
