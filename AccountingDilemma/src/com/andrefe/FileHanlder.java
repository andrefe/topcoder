package com.andrefe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHanlder {

    /** Processes the provided file, looking for a set of operations, so as to
     * find a valid subset that sums uo to the input balance.
     * 
     * This method also orders the results found in a file for a proper
     * processing in the AccountingDilemma class.
     * 
     * Moreover, the GCD is also evaluated.
     * 
     * 
     * @param operationsFilePath
     *        the path towards the file where to look for
     *        the operations.
     * @return the
     * @throws IOException */
    public static OperationBatch readOperations(String operationsFilePath)
	    throws IOException {
	// the target balance will be stored here
	int targetBalance = -1;
	// prepare the array where we will put the line-by-line parsed data
	Queue<Integer> operations = new PriorityQueue<Integer>();
	// the gcd will be stored here
	int gcd = 0;

	// parse the specified file
	Path aPath = Paths.get(operationsFilePath);
	try (BufferedReader reader = Files.newBufferedReader(aPath,
		Charset.defaultCharset())) {
	    // treat each line according to an integer and (optional) cents
	    // value pattern
	    Pattern valuePattern = Pattern.compile("(\\d+)(\\.[\\d]{0,2})");
	    String line = null;
	    while ((line = reader.readLine()) != null) {
		// convert the string(s) to a cent value
		Matcher valueMatcher = valuePattern.matcher(line);
		if(valueMatcher.find())
		{
		    int value = Integer.parseInt(valueMatcher.group(1)) * 100;
		    if (valueMatcher.groupCount() == 2) {
			// strip the initial dot from the cent part
			value += Integer.parseInt(valueMatcher.group(2)
				.substring(1));
		    }
		    // the first line refers to the target balance
		    if(targetBalance == -1){
			targetBalance = value;
		    }
		    else{
			operations.add(value);
		    }
		    // continuously evaluate the gcd
		    gcd = gcd > 0 ? Gcd.compute(gcd, value) : value;
		    
		    
		}
	    }
	}
	// just log the error, but let the caller deal with that.
	catch (IOException e) {
	    System.err.println("Error while reading operations "
		    + e.getMessage());
	    throw e;
	}

	// create an object for the AccountingDilemma's processing - note that
	// the priorityqueue containing the items will be converted to an
	// arraylist
	return new OperationBatch(targetBalance,operations,gcd);
    }

    /** Writes down to the provided file the results set.
     * 
     * @param resultsFilePath
     * @param results
     * @throws IOException */
    public static void writeResults(String resultsFilePath,
	    List<Integer> results) throws IOException {
	Path aPath = Paths.get(resultsFilePath);
	try (BufferedWriter writer = Files.newBufferedWriter(aPath,
		Charset.defaultCharset())) {

	    // if there are no results, write the default string
	    if(results.size() == 0)
	    {
		String noResult = "NO SOLUTION";
		writer.write(noResult, 0, noResult.length());
	    }

	    // write each value to disk
	    for (Integer value : results) {
		int integer = value / 100;
		int cents = value % 100;
		
		// convert values from cent-based to decimal dotted ones
		StringBuffer sb = new StringBuffer();
		sb.append(integer);
		sb.append('.');
		if(cents <= 10)
		{
		    sb.append("0");
		}
		sb.append(cents);
		String valueString = sb.toString();
		writer.write(valueString, 0, valueString.length());
		writer.newLine();
	    }
	}
	// just log the error, but let the caller deal with that.
	catch (IOException e) {
	    System.err.println("Error while writing results " + e.getMessage());
	    throw e;
	}
    }
}
