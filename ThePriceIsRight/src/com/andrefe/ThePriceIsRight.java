package com.andrefe;

/**
 * Problem Statement
 *     
 * The Price Is Right is a television game show. In the final round of
 * competition, the contestant is shown the prizes which he can win. To win
 * everything, he must make an educated guess and arrange the prizes in
 * increasing order of price.
 * Once the contestant has completed ordering prizes, the host begins to reveal
 * the price of each prize in any order that he wishes. To make the show more
 * interesting however, the host reveals prices in such a way that as many
 * prices are revealed as possible before an error in the order is revealed.
 * Each element in prices represents the price of a prize. The order of prices
 * represents what the contestant thought the order was when arranging prices.
 * Given a int[] of prices as ordered by the contestant, return a int[] with two
 * elements. The first element should be the maximum possible number of prices
 * revealed before the order of prices is broken, while the second element
 * should be the total number of ways of achieving that maximum number.
 * 
 * For example, prices = {30, 10, 20, 40, 50}
 * The host could reveal the following prices: 30 * * 40 50. The next price
 * revealed (either 10 or 20) will cause an error in the ordering to be
 * revealed. In this case, 3 prices were revealed. Alternatively, the host could
 * reveal the following prices: * 10 20 40 50. Once again, the next price
 * revealed will cause an error in the ordering to be revealed. However, in this
 * case, 4 prices were revealed and thus the host would prefer to reveal the
 * prices this way. Note that there is only 1 way of revealing 4 prices. The
 * method should return {4,1}.
 * Definition
 *     
 * Class:
 * ThePriceIsRight
 * Method:
 * howManyReveals
 * Parameters:
 * int[]
 * Returns:
 * int[]
 * Method signature:
 * int[] howManyReveals(int[] prices)
 * (be sure your method is public)
 * Limits
 *     
 * Time limit (s):
 * 2.000
 * Memory limit (MB):
 * 64
 * Notes
 * -
 * The host DOES NOT work out any intermediate deductions. He reveals prices
 * until the sequence order is broken. See examples 5 and 6.
 * Constraints
 * -
 * prices will have between 1 and 50 elements inclusive.
 * -
 * prices will not have any repeated elements.
 * -
 * each element in prices will be between 1 and 1000000 inclusive.
 * Examples
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
 * In theory, because there can be no price between 10 and 11, it is enough to
 * reveal both 10 and 11 to know that the sequence of prices is broken. However,
 * the host DOES NOT make these intermediate deductions and will reveal 10 * 11
 * 12.
 * This problem statement is the exclusive and proprietary property of TopCoder,
 * Inc. Any unauthorized use or reproduction of this information without the
 * prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003,
 * TopCoder, Inc. All rights reserved.
 */
public class ThePriceIsRight {

    // CHECK: https://github.com/alno/topcoder/blob/master/thepriceisright-java/ThePriceIsRight.java
    
    
    private int[] revealStep(
	    int[] prices,
	    int currentIndex,
	    int currentLen,
	    int currentMax,
	    int currentMaxLen,
	    int currentMaxLenAlternatives ) {
	// if we reach the end, return
	if( currentIndex == prices.length )
	{
	    int[] result = new int[2];
	    result[0] = currentMaxLen;
	    result[1] = currentMaxLenAlternatives;
	    return result;
	}
	// check if the current index is not applicable, then proceed
	if (prices[currentIndex] < currentMax)
	{
	    return revealStep(prices, currentIndex + 1, currentLen, currentMax,
		    currentMaxLen, currentMaxLenAlternatives);
	}
	
	// ASSERTION: the item is a good candidate for the progression. We can
	// either pick it up or not
	
	// System.out.println(currentIndex + "{" + currentMax + "," +
	// currentMaxLen + "}" + " Good candidate: " + prices[currentIndex]);
	
	// pick it
	int[] firstResult = revealStep(prices, currentIndex + 1, currentLen+1,
		prices[currentIndex], currentMaxLen+1, 1);
	// do not pick it
	int[] secondResult = revealStep(prices, currentIndex + 1, currentLen,
		currentMax, currentMaxLen, currentMaxLenAlternatives);
	
	// if the two estimations return the same maximum length, the number 
	// of solutions is the sum
	if ( firstResult[0] == secondResult[0] )
	{
	    firstResult[1] = firstResult[1] + secondResult[1];
	    return firstResult;
	}
	// otherwise, return the best one
	if ( firstResult[0] > secondResult[0] )
	{
	    return firstResult;
	}
	
	return secondResult;
    }

    public int[] howManyReveals(int[] prices){
	return revealStep(prices,0,0,0,0,0);
    }
    
}
