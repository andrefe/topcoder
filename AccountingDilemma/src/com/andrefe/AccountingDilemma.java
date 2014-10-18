package com.andrefe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountingDilemma {

    public List<Integer> computeSequenceRecursive(List<Integer> operations,
	    int targetBalance) {
	List<Integer> bestList = new ArrayList<Integer>();
	computeSequenceRecursive(operations, targetBalance, bestList);
	return bestList;
    }

    public int computeSequenceRecursive(List<Integer> operations,
	    int targetBalance,
	    List<Integer> bestList) {

	// check for termination
	if (operations.size() == 0) {
	    return targetBalance;
	}
	if (targetBalance == 0) {
	    return 0;
	} else if (targetBalance < 0) {
	    return -1;
	}
	
	return 0;

    }

    private int gcd(int a, int b) {
	while(b > 0)
	{
	    int c = a % b;
	    a = b;
	    b = c;
	}
	return a;
    }

    private int gcd(List<Integer> values) {
	int result = values.get(0);
	for (int i = 1; i < values.size(); ++i) {
	    int second = values.get(i);
	    result = gcd(result, second);
	}
	return result;
    }

    /** Processes the provided operations array so as to find a valid subset
     * that sums up to the input balance.
     * 
     * @param operations set of values from which look for a proper subset;
     * @param targetBalance the targeted value to enforce on the returned
     *        subset;
     * @return the subset of values whose sum is the balance. */
    public List<Integer> computeSequence(List<Integer> operations,
	    int targetBalance) {

	/* The proper change for:
	 * - a set of operations X, whose first element is x
	 * - a balance b
	 * Will be either
	 * a) the proper change for set X' (X without x) for balance b -x
	 * b) the proper change for set X' (X without x) for balance b
	 * 
	 * So as to solve this problem, we can put in place a dynamic
	 * programming algorithm that evaluates the best solution for a
	 * balance progressively increasing to the target one on a set of
	 * operations progressively increasing to the provided one.
	 * 
	 * As naming convention,
	 * - the action of adding an item to the operations set is
	 * performed during a step.
	 * 
	 * An important consideration is the one assessing the need of just
	 * step(i - 1) so as to evaluate step(i): therefore, we can reduce
	 * the memory footprint of our algorithm by keeping a smaller results
	 * set during each step. */
	int steps = operations.size();
	int rounds = targetBalance + 1;

	// allocates the needed steps
	int[] previousStep = new int[rounds]; // all elements set to 0
	int[] currentStep = new int[rounds]; // all elements set to 0

	// TODO
	boolean[][] chosenItems = new boolean[steps][rounds]; // all elements
	// set to false

	int step = 0;
	int balance = 0;
	for (step = 0; step < steps; ++step) {
	    int operation = operations.get(step);
	    for (balance = 0; balance < rounds; ++balance) {
		int chosenBalance = previousStep[balance];
		// check if there is enough balance to consider this operation
		if (balance >= operation) {

		    /* Check which value is the best fit: the balance
		     * with or without the current operation. In both cases,
		     * the balance evaluated during the previous step is needed.
		     * 
		     * If this step was a recursive method call, it would have
		     * been:
		     * 
		     * balance(X,b) = MAX{balance(X-x,b-x),balance(X-x,b)} */
		    int balanceWithout = previousStep[balance];
		    int balanceWith = previousStep[balance - operation]
			    + operation;

		    if (balanceWith > balanceWithout) {
			chosenBalance = balanceWith;
			chosenItems[step][balance] = true;
		    }
		}

		// there is no need to proceed further in this step
		// if we were lucky to find the target balance.
		currentStep[balance] = chosenBalance;
		if (chosenBalance == targetBalance) {
		    break;
		}

	    }

	    // there is no need to proceed further if we were lucky to find
	    // the target balance.
	    balance = balance == rounds ? balance - 1 : balance;
	    if (currentStep[balance] == targetBalance) {
		break;
	    }

	    // copy the current step over the previous step
	    System.arraycopy(currentStep, 0, previousStep, 0, rounds);

	}

	// check what is the maximized balance
	int finalBalance = currentStep[balance];
	ArrayList<Integer> resultingList = new ArrayList<Integer>();
	if (finalBalance != targetBalance) {
	    System.out.println("No operations found for the provided balance: "
		    + finalBalance + " vs " + targetBalance);
	} else {
	    System.out.println("Solutions found in iteration "
		    + Integer.toString(step * targetBalance + finalBalance)
		    + " out of "
		    + Integer.toString((rounds + 1) * targetBalance));
	    int tempBalance = targetBalance;
	    for (step = steps - 1; step >= 0; --step) {
		if (chosenItems[step][tempBalance] == true) {
		    tempBalance = tempBalance - operations.get(step);
		    resultingList.add(operations.get(step));
		}
	    }
	}

	return resultingList;
    }

    /** Processes the provided file, looking for a set of operations, so as to
     * find a valid subset that sums up to the input balance.
     * 
     * @param operationsFilePath the path where to look for the operations.
     * @param balance the targeted value to enforce on the returned subset;
     * @return the subset of values whose sum is the balance.
     * @throws IOException read/write issues might occur. */
    public List<Integer> computeSequence(String operationsFilePath,
	    int balance,
	    String resultsFilePath) throws IOException {
	List<Integer> results = new ArrayList<Integer>();
	try {
	    List<Integer> operations = FileHanlder
		    .readOperations(operationsFilePath);
	    results = computeSequence(operations, balance);
	    FileHanlder.writeResults(resultsFilePath, results);
	} catch (Exception e) {
	    results.clear();
	    e.printStackTrace();
	}
	return results;
    }
}
