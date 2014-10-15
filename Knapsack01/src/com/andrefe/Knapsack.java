package com.andrefe;

import java.util.ArrayList;

public class Knapsack {
    
    /**	Utility class involved in the item list and max value return.
     * 
     */
    public static class KnapsackResult {
	int value = 0;
	ArrayList<Integer> items = new ArrayList<Integer>();
	
	public KnapsackResult() {
	    super();
	    this.value = 0;
	    this.items = new ArrayList<Integer>();   
	}
	
	public KnapsackResult(int value, ArrayList<Integer> bestElements) {
	    super();
	    this.value = value;
	    this.items = bestElements;
	}
	
    }
    
    
    /**	Returns the item list and the max value that solve the provided
     * 	binary knapsack problem.
     * 
     * @param weights the item weights;
     * @param values the item values;
     * @param capacity the item capacity;
     * @return the item solution and max value.
     */
    public KnapsackResult knapsackItems(int[] weights, int[] values, int capacity)
    {
	// check for bogus input
	if(values.length != weights.length)
	{
	    return null;
	}
	// check if the capacity is enough
	if(capacity == 0)
	{
	    return new KnapsackResult();
	}
	
	// TODO pre-sort depending on weight
	
	// those two arrays will contain the solution for an item and the
	// previous one
	int[] previousStepValue = new int[capacity+1];
	int[] stepValue = new int[capacity+1];
	// this matrix will contain whether an item is to be considered at a
	// given capacity.
	// NOTE: you should do an emit operation to find the proper sequence
	boolean [][] stepItems = new boolean[capacity+1][weights.length];

	// no need to initialize: by default java initializes int to 0 and
	// boolean to false
	
	// for each item
	for ( int currentItem = 0; currentItem < weights.length; ++currentItem)
	{
	    // for each incremental capacity
	    for ( int currentCapacity = 0; currentCapacity <= capacity; ++currentCapacity)
	    {
		// by default, let's assume we do not add the item: the
		// value is the same as before
		int valueIfNotIncluded = previousStepValue[currentCapacity];
		stepValue[currentCapacity] = valueIfNotIncluded;
		
		// let's see now if it is convenient to add the item instead:
		// check if there would be at least enough space
		if ( currentCapacity >= weights[currentItem] ){	  
		    // check if it would be convenient to include the item
		    // by comparing the value we (pessimistically) stored
		    // and the if included one.
		    int valueIfIncluded = previousStepValue[currentCapacity - weights[currentItem]] + values[currentItem];
		    if( valueIfNotIncluded < valueIfIncluded ){
			// include
			stepValue[currentCapacity] = valueIfIncluded;
			stepItems[currentCapacity][currentItem] = true;
		    }
		}
	    }
	    
	    //System.out.println(Arrays.toString(stepValue));
	    
	    // store the array of this step for the next one 
	    for(int i = 0; i <= capacity; ++i) {
		previousStepValue[i] = stepValue[i];
	    }
	    
	}
	
	// get the value
	int value = stepValue[capacity];
	
	// get the list of items by emitting values
	int temporaryCapacity = capacity;
	ArrayList<Integer> items = new ArrayList<Integer>();
	for(int i = weights.length -1; i >=0 ; --i){
	    if(stepItems[temporaryCapacity][i] == true)
	    {
		temporaryCapacity = temporaryCapacity - weights[i];
		items.add(i);
	    }
	}
	
	// return
	return new KnapsackResult(value, items);
    }
    
}
