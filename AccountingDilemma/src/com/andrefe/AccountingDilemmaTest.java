package com.andrefe;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AccountingDilemmaTest {

    @Test
    public void testDirect0() {
	List<Integer> operations = new ArrayList<Integer>(Arrays.asList(1000,2000,4500,5600));
	int balance = 6600;
	List<Integer> expectedResults = new ArrayList<Integer>(Arrays.asList(5600,1000));
	List<Integer> results = new AccountingDilemma().computeSequence(operations, balance);
	assertEquals("Results differs!",expectedResults,results);
    }
    
    @Test
    public void testDirectRecursive0() {
	List<Integer> operations = new ArrayList<Integer>(Arrays.asList(1000,2000,4500,5600));
	int balance = 6600;
	List<Integer> expectedResults = new ArrayList<Integer>(Arrays.asList(5600,1000));
	List<Integer> results = new AccountingDilemma().computeSequenceRecursive(operations, balance);
	assertEquals("Results differs!",expectedResults,results);
    }
    
    @Test
    public void testDirect1() {
	List<Integer> operations = new ArrayList<Integer>(Arrays.asList(1000,2000,4500,5600));
	int balance = 6500;
	List<Integer> expectedResults = new ArrayList<Integer>(Arrays.asList(4500,2000));
	List<Integer> results = new AccountingDilemma().computeSequence(operations, balance);
	assertEquals("Results differs!",expectedResults,results);
    }

}
