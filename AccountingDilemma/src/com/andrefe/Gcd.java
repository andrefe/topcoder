package com.andrefe;

import java.util.List;


public class Gcd {

    public static int compute(int a, int b) {
	while(b > 0)
	{
	    int c = a % b;
	    a = b;
	    b = c;
	}
	return a;
    }
    
    public static int compute(List<Integer> values) {
	int result = values.get(0);
	for (int i = 1; i < values.size(); ++i) {
	    int second = values.get(i);
	    result = compute(result, second);
	}
	return result;
    }

}
