package com.andrefe;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AccountingDilemmaTest {

    // -- Test special cases
    
    @Test
    public void testSpecialEmpty() {
	List<Integer> operations = new ArrayList<Integer>();
	int balance = 6600;
	List<Integer> expectedResults = new ArrayList<Integer>();
	int gcd = Gcd.compute(operations);
	gcd = Gcd.compute(gcd,balance);
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(operations, gcd, balance);
	assertEquals("Results differs!",expectedResults,results);
    }
    
    @Test
    public void testSpecialZero() {
	List<Integer> operations = new ArrayList<Integer>(Arrays.asList(1000,2000,4500,5600));
	int balance = 0;
	List<Integer> expectedResults = new ArrayList<Integer>();
	int gcd = Gcd.compute(operations);
	gcd = Gcd.compute(gcd,balance);
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(operations, gcd, balance);
	assertEquals("Results differs!",expectedResults,results);
    }
    
    @Test
    public void testSpecialMinimum() {
	List<Integer> operations = new ArrayList<Integer>(Arrays.asList(1000,2000,4500,5600));
	int balance = 1500;
	List<Integer> expectedResults = new ArrayList<Integer>(Arrays.asList(1000));
	int gcd = Gcd.compute(operations);
	gcd = Gcd.compute(gcd,balance);
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(operations, gcd, balance);
	assertEquals("Results differs!",expectedResults,results);
    }
    
    // -- Test directly accessing the array interface
    
    @Test
    public void testDirect0() {
	List<Integer> operations = new ArrayList<Integer>(Arrays.asList(1000,2000,4500,5600));
	int balance = 6600;
	List<Integer> expectedResults = new ArrayList<Integer>(Arrays.asList(5600,1000));
	int gcd = Gcd.compute(operations);
	gcd = Gcd.compute(gcd,balance);
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(operations, gcd, balance);
	assertEquals("Results differs!",expectedResults,results);
    }
    
    @Test
    public void testDirectAnySolution() {
	List<Integer> operations = new ArrayList<Integer>(Arrays.asList(1000,2000,4500,5600));
	int balance = 6500;
	List<Integer> expectedResults = new ArrayList<Integer>(Arrays.asList(4500,2000));
	int gcd = Gcd.compute(operations);
	gcd = Gcd.compute(gcd,balance);
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(operations, gcd, balance);
	assertEquals("Results differs!",expectedResults,results);
    }
    
    @Test
    public void testDirectNoSolution() {
	List<Integer> operations = new ArrayList<Integer>(Arrays.asList(1000,2000,4500,5600));
	int balance = 6501;
	List<Integer> expectedResults = new ArrayList<Integer>();
	int gcd = Gcd.compute(operations);
	gcd = Gcd.compute(gcd,balance);
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(operations, gcd, balance);
	assertEquals("Results differs!",expectedResults,results);
    }
    
    @Test
    public void testDirectX() {
	List<Integer> operations = new ArrayList<Integer>(Arrays.asList(1011,2011,4512,5613));
	int balance = 6523;
	List<Integer> expectedResults = new ArrayList<Integer>(Arrays.asList(4512,2011));
	int gcd = Gcd.compute(operations);
	gcd = Gcd.compute(gcd,balance);
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(operations, gcd, balance);
	assertEquals("Results differs!",expectedResults,results);
    }
    
    // -- Test via the file interface
    
    @Test
    public void testFile() throws IOException {
	String inputPath = "res/input.txt";
	String outputPath = "res/output.txt";
	
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(inputPath,outputPath);
	
	OperationBatch batch = FileHanlder.readOperations(inputPath);
	int balance = 0;
	for (Integer aResult : results) {
	    balance += aResult;
	}
	
	assertEquals("Results differs!",batch.getTargetBalance(),balance);
    }
    
    @Test
    public void testFile0() throws IOException {
	String inputPath = "res/input0.txt";
	String outputPath = "res/output0.txt";
	
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(inputPath,outputPath);
	
	OperationBatch batch = FileHanlder.readOperations(inputPath);
	int balance = 0;
	for (Integer aResult : results) {
	    balance += aResult;
	}
	
	assertEquals("Results differs!",batch.getTargetBalance(),balance);
    }
    
    @Test
    public void testFileAnySolution() throws IOException {
	String inputPath = "res/input1.txt";
	String outputPath = "res/output1.txt";
	
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(inputPath,outputPath);
	
	OperationBatch batch = FileHanlder.readOperations(inputPath);
	int balance = 0;
	for (Integer aResult : results) {
	    balance += aResult;
	}
	
	assertEquals("Results differs!",batch.getTargetBalance(),balance);
    }
    
    @Test
    public void testFile2() throws IOException {
	
	String inputPath = "res/input2.txt";
	String outputPath = "res/output2.txt";
	
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(inputPath,outputPath);
	
	OperationBatch batch = FileHanlder.readOperations(inputPath);
	int balance = 0;
	for (Integer aResult : results) {
	    balance += aResult;
	}
	
	assertEquals("Results differs!",batch.getTargetBalance(),balance);
    }
    
    @Test
    public void testFile3NoSolution() throws IOException {
	
	String inputPath = "res/input3.txt";
	String outputPath = "res/output3.txt";
	
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(inputPath,outputPath);
	
	assertEquals("Results differs!",results,new ArrayList<Integer>());
    }
    
    @Test
    public void testFile4WithInvalidOperations() throws IOException {
	String inputPath = "res/input4.txt";
	String outputPath = "res/output4.txt";
	
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(inputPath,outputPath);
	
	OperationBatch batch = FileHanlder.readOperations(inputPath);
	int balance = 0;
	for (Integer aResult : results) {
	    balance += aResult;
	}
	
	assertEquals("Results differs!",batch.getTargetBalance(),balance);
    }
    
    @Test
    public void testFile5() throws IOException {
	
	String inputPath = "res/input5.txt";
	String outputPath = "res/output5.txt";
	
	List<Integer> results = new AccountingDilemma().computeSequenceIterative(inputPath,outputPath);
	
	OperationBatch batch = FileHanlder.readOperations(inputPath);
	int balance = 0;
	for (Integer aResult : results) {
	    balance += aResult;
	}
	
	assertEquals("Results differs!",batch.getTargetBalance(),balance);
    }
}
