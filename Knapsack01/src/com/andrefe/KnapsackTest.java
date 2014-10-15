package com.andrefe;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.andrefe.Knapsack.KnapsackResult;

public class KnapsackTest {

    @Test
    public void test()
    {
	int[] weights = {2,5,6,7};
	int[] values = {4,3,5,8};
	int capacity = 11;
	// SOLUTION:
	// x = {3,0}
	// v = 12
	KnapsackResult result = new Knapsack().knapsackItems(weights, values, capacity);
	int value = result.value;
	ArrayList<Integer> items = result.items;
	System.out.println(value);
	System.out.println(items);
	assertTrue(new String("Failed, result is " + value +  " and not " + 12),
		(value == 12) );
    }
    
    
    @Test
    public void test1()
    {
	int[] weights = {2,5,6,7,9,12,15,19};
	int[] values =  {4,3,5,8,2,13,2,18};
	int capacity = 50;
	// SOLUTION:
	// x = {7,5,3,2,0}
	// v = 48
	KnapsackResult result = new Knapsack().knapsackItems(weights, values, capacity);
	int value = result.value;
	ArrayList<Integer> items = result.items;
	System.out.println(value);
	System.out.println(items);
	assertTrue(new String("Failed, result is " + value +  " and not " + 12),
		(value == 48) );
    }

    
    @Test
    public void test2()
    {
	int[] weights = {2,5,6,7,9,12,13,15,19,33,40};
	int[] values =  {4,3,5,8,2,13,2,18,23,1,60};
	int capacity = 100;
	// SOLUTION:
	// x = {10, 8, 7, 5, 3, 1, 0}
	// v = 129
	KnapsackResult result = new Knapsack().knapsackItems(weights, values, capacity);
	int value = result.value;
	ArrayList<Integer> items = result.items;
	System.out.println(value);
	System.out.println(items);
	assertTrue(new String("Failed, result is " + value +  " and not " + 129),
		(value == 129) );
    }
}
