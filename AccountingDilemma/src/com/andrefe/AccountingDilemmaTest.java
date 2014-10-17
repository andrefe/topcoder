package com.andrefe;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class AccountingDilemmaTest {

    @Test
    public void testDirect0() {
	ArrayList<Integer> operations = new ArrayList<Integer>(Arrays.asList(1000,2000,4500,5600));
	int balance = 6600;
	ArrayList<Integer> expectedResults = new ArrayList<Integer>(Arrays.asList(1000,5600));
	ArrayList<Integer> results = new AccountingDilemma().computeSequence(operations, balance);
	assertEquals("Results differs!",expectedResults,results);
    }
    
    @Test
    public void testDirect1() {
	ArrayList<Integer> operations = new ArrayList<Integer>(Arrays.asList(1000,2000,4500,5600));
	int balance = 6500;
	ArrayList<Integer> expectedResults = new ArrayList<Integer>(Arrays.asList(2000,4500));
	ArrayList<Integer> results = new AccountingDilemma().computeSequence(operations, balance);
	assertEquals("Results differs!",expectedResults,results);
    }

}
