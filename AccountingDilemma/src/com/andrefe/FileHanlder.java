package com.andrefe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHanlder {

    /** Processes the provided file, looking for a set of operations, so as to
     * find a valid subset that sums uo to the input balance.
     * 
     * 
     * @param operationsFilePath
     *        the path towards the file where to look for
     *        the operations.
     * @return the
     * @throws IOException */
    public static ArrayList<Integer> readOperations(String operationsFilePath)
	    throws IOException {
	// prepare the array where we will put the line-by-line parsed data
	ArrayList<Integer> operations = new ArrayList<Integer>();

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
		int value = Integer.parseInt(valueMatcher.group(1)) * 100;
		if (valueMatcher.groupCount() == 3) {
		    // strip the initial dot from the cent part
		    value += Integer.parseInt(valueMatcher.group(2)
			    .substring(1));
		}
		operations.add(value);
	    }
	}
	// just log the error, but let the caller deal with that.
	catch (IOException e) {
	    System.err.println("Error while reading operations "
		    + e.getMessage());
	    throw e;
	}
	return operations;
    }

    /** Writes down to the provided file the results set.
     * 
     * @param resultsFilePath
     * @param results
     * @throws IOException */
    public static void writeResults(String resultsFilePath,
	    ArrayList<Integer> results) throws IOException {
	Path aPath = Paths.get(resultsFilePath);
	try (BufferedWriter writer = Files.newBufferedWriter(aPath,
		Charset.defaultCharset())) {
	    // write each value to disk
	    for (Integer value : results) {
		// convert values from cent-based to decimal dotted ones
		StringBuffer sb = new StringBuffer();
		sb.append(value / 100).append('.').append(value % 100);
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
