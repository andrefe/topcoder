package com.andrefe;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/** This class contains the three basic items that are needed for an
 *  (efficient) calculation of our AccountDilemma.
 * 
 *  The operations list and the target balance are also provided with their
 *  GCD, so as to have an efficient pace during the iterative algorithm.
 * 
 * @author andrefe
 *
 */
public class OperationBatch {

    private int _targetBalance;
    private List<Integer> _operations;

    public OperationBatch(
	    int targetBalance,
	    Queue<Integer> operations) {
	super();
	_targetBalance = targetBalance;
	_operations = new ArrayList<Integer>(operations);
    }

    public int getTargetBalance() {
	return _targetBalance;
    }

    public List<Integer> getOperations() {
	return _operations;
    }

}
