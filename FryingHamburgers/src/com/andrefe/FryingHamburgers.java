package com.andrefe;

/**
 * Problem Statement
 *     
 * In order to cook hamburgers, a chef must fry them 5 minutes on each side.
 * However, the two 5 minute intervals do not have to happen consecutively.
 * panSize indicates how many hamburgers can fit into the given pan, while
 * hamburgers represents the number of hamburgers that need to be fried. Given
 * panSize and hamburgers return the minimum amount of time (in minutes)
 * required to fry all the hamburgers.
 * Definition
 *     
 * Class:
 * FryingHamburgers
 * Method:
 * howLong
 * Parameters:
 * int, int
 * Returns:
 * int
 * Method signature:
 * int howLong(int panSize, int hamburgers)
 * (be sure your method is public)
 * Limits
 *     
 * Time limit (s):
 * 2.000
 * Memory limit (MB):
 * 64
 * Constraints
 * -
 * panSize is between 1 and 1000 inclusive.
 * -
 * hamburgers is between 0 and 1000 inclusive.
 * Examples
 * 0)
 * 
 *     
 * 2
 * 3
 * Returns: 15
 * We will assume that the three hamburgers given are A, B and C. The chef could
 * fry A and B on one side, taking up 5 minutes. Then remove B and fry A on its
 * second side together with C on its first side. After 5 minutes, remove A and
 * fry B and C on their second sides for another 5 minutes. The total time used
 * is 15 minutes.
 * 1)
 * 
 *     
 * 3
 * 4
 * Returns: 15
 * We will assume that the four hamburgers given are A, B, C and D. The chef
 * could fry A, B and C on one side, taking up 5 minutes. Then remove C and fry
 * A and B on their second side together with D on its first side. So after a
 * total of 10 minutes A and B are completely fried, while C and D are fried on
 * one side only. Finally, the chef fries C and D on their second side, taking
 * another 5 minutes. The total time used is 15 minutes.
 * 2)
 * 
 *     
 * 3
 * 2
 * Returns: 10
 * 
 * 3)
 * 
 *     
 * 100
 * 0
 * Returns: 0
 * There are no hamburgers, so we do not have to fry anything.
 * 4)
 * 
 *     
 * 303
 * 919
 * Returns: 35
 * 
 * This problem statement is the exclusive and proprietary property of TopCoder,
 * Inc. Any unauthorized use or reproduction of this information without the
 * prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003,
 * TopCoder, Inc. All rights reserved.
 */
public class FryingHamburgers {

    public static final int ROUND_DURATION = 5;

    public int howLong(int panSize, int hamburgers) {
	int rounds = 0;

	if (hamburgers == 0) {
	    rounds = 0;
	} else if (hamburgers <= panSize) {
	    rounds = 2;
	} else if (((hamburgers * 2) % panSize) == 0) {
	    rounds = (hamburgers * 2) / panSize;
	} else {
	    rounds = (hamburgers * 2) / panSize + 1;
	}

	return rounds * ROUND_DURATION;
    }

}
