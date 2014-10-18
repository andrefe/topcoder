package com.andrefe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountingDilemma {

    public static List<Integer> computeSequenceRecursive(List<Integer> operations,
	    int targetBalance) {
	List<Integer> bestList = new ArrayList<Integer>();
	computeSequenceRecursive(operations, targetBalance, bestList);
	return bestList;
    }

    public static int computeSequenceRecursive(List<Integer> operations,
	    int remainingBalance,
	    List<Integer> bestList) {

	// check for termination
	if (operations.size() == 0) {
	    return remainingBalance;
	}
	if (remainingBalance == 0) {
	    return 0;
	} else if (remainingBalance < 0) {
	    return -1;
	}

	// TODO
	
	return 0;

    }

    public static int computeIterativeComplexityMaxExecutions(List<Integer> operations,
	    int gcd,
	    int targetBalance) {
	return (targetBalance / gcd + 1) * operations.size();

    };

    public static int computeIterativeComplexityMaxExecutions(List<Integer> operations,
	    int targetBalance) {
	return targetBalance * operations.size();

    };

    private static void logFinalStatistics(List<Integer> orderedOperations,
	    int gcd,
	    int targetBalance,
	    int numberOfExecutions,
	    List<Integer> resultingList) {
	System.out.println("Solution found in iteration "
		+ numberOfExecutions
		+ " out of "
		+ computeIterativeComplexityMaxExecutions(orderedOperations,
			gcd, targetBalance)
		+ " at a pace of "
		+ gcd
		+ " (without gcd "
		+ computeIterativeComplexityMaxExecutions(orderedOperations,
			targetBalance) + "): " + resultingList.toString());
    }

    /** Processes the provided operations ordered list so as to find a valid
     * subset that sums up to the input balance.
     * 
     * @param orderedOperations ordered list of values from which look for a
     *        proper solution;
     * @param targetBalance the targeted value to enforce on the returned
     *        subset;
     * @return the subset of values whose sum is the balance. */
    public static List<Integer> computeSequenceIterative(List<Integer> orderedOperations,
	    int gcd,
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
	 * - a step is an iteration on a list containing an additional element
	 * from the list of the operations.
	 * - a round is an iteration, within a given step, considering a
	 * balance lower of equal of the targeted one.
	 * 
	 * An important consideration is the one assessing the need of just
	 * step(i - 1) so as to evaluate step(i): therefore, we can reduce
	 * the memory footprint of our algorithm by keeping a smaller results
	 * set during each step. */
	int steps = orderedOperations.size();
	int rounds = targetBalance + 1;

	// allocates the needed steps
	int[] previousStep = new int[rounds]; // all elements set to 0
	int[] currentStep = new int[rounds]; // all elements set to 0

	/* So as to evaluate the final solution, we will mark in this array
	 * every time that, for a given balance, an operation is convenient
	 * to be considered.
	 * 
	 * As final step, we will perform a reverse-emission of the array, with
	 * a decreasing capacity (see further). */
	boolean[][] chosenItems = new boolean[steps][rounds]; // all elements
							      // set to false

	// OPTIMIZATION: we could have simply used ++
	// by using the greater common divisor, we will be able to skip cents
	// value that will never be used.
	int balanceIncrease = gcd;

	int balance = 0;
	int numberOfExecutions = 0;
	for (int step = 0; step < steps; ++step) {
	    int operation = orderedOperations.get(step);
	    for (balance = 0; balance <= targetBalance; balance += balanceIncrease) {
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

		++numberOfExecutions;

		// OPTIMIZATION: we could have proceeded till the end
		// there is no need to proceed further in this step
		// if we were lucky to find the target balance.
		currentStep[balance] = chosenBalance;
		if (chosenBalance == targetBalance) {
		    break;
		}

	    }

	    // OPTIMIZATION: we could have proceeded till the end
	    // there is no need to proceed further if we were lucky to find
	    // the target balance.
	    balance = balance > rounds ? targetBalance : balance;
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
	    // evaluate the proper iteration solution
	    int tempBalance = targetBalance;
	    for (int step = steps - 1; step >= 0; --step) {
		if (chosenItems[step][tempBalance] == true) {
		    tempBalance = tempBalance - orderedOperations.get(step);
		    resultingList.add(orderedOperations.get(step));
		}
	    }
	    
	    // log also any eventual gain!
	    logFinalStatistics(orderedOperations, gcd, targetBalance,
		    numberOfExecutions,resultingList);
	}

	return resultingList;
    }

    /** Processes the provided file, looking for a set of operations, so as to
     * find a valid subset that sums up to the input balance.
     * 
     * @param operationsFilePath the path where to look for the operations.
     * @return the subset of values whose sum is the balance.
     * @throws IOException read/write issues might occur. */
    public List<Integer> computeSequence(String operationsFilePath,
	    String resultsFilePath) throws IOException {
	List<Integer> results = new ArrayList<Integer>();
	try {
	    OperationBatch operationBatch = FileHanlder
		    .readOperations(operationsFilePath);
	    results = computeSequenceIterative(operationBatch.getOperations(),
		    operationBatch.getGcd(), operationBatch.getTargetBalance());
	    FileHanlder.writeResults(resultsFilePath, results);
	} catch (Exception e) {
	    results.clear();
	    e.printStackTrace();
	}
	return results;
    }
}
