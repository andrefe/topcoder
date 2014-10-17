package com.andrefe;

import java.io.IOException;
import java.util.ArrayList;

public class AccountingDilemma {

    /** Processes the provided operations array so as to find a valid subset
     * that sums up to the input balance.
     * 
     * @param operations set of values from which look for a proper subset;
     * @param balance the targeted value to enforce on the returned subset;
     * @return the subset of values whose sum is the balance.
     */
    public ArrayList<Integer> computeSequence(ArrayList<Integer> operations,
	    int balance) {
	// TODO
	return new ArrayList<Integer>();
    }

    /** Processes the provided file, looking for a set of operations, so as to
     * find a valid subset that sums up to the input balance.
     * 
     * @param operationsFilePath the path where to look for the operations.
     * @param balance the targeted value to enforce on the returned subset;
     * @return the subset of values whose sum is the balance.
     * @throws IOException read/write issues might occur.
     */
    public ArrayList<Integer> computeSequence(
	    String operationsFilePath,
	    int balance,
	    String resultsFilePath) throws IOException {
	ArrayList<Integer> results = new ArrayList<Integer>();
	try {
	    ArrayList<Integer> operations = FileHanlder
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
