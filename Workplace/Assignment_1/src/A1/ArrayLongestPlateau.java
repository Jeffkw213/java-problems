/**********************************************************
 * EECS2011ON: Fundamentals of Data Structures,  Winter 2020
 * Assignment 1, Problem 2: ArrayLongestPlateau.java
 * Student Name:   Jeff Kwan
 * Student EECS account:  jeffkw
 * Student ID number:  216396764
 **********************************************************/

package A1;

/**
 * The purpose of this class is to find the longest plateau of an array of ints.
 * 
 * The main method runs some tests.
 * 
 * @author andy
 * 
 */

public class ArrayLongestPlateau {

	/**
	 * longestPlateau() returns the longest plateau of an array of ints.
	 * 
	 * @return an array int[3] of the form {value, start, len} representing the
	 *         longest plateau of ints[] as a length len contiguous subarray
	 *         starting at index start with common element values value.
	 * 
	 *         For example, on the input array [2, 3, 3, 3, 3, 6, 6, 1, 1, 1], it
	 *         returns [6, 5, 2], indicating the longest plateau of this array is
	 *         the subarray [6, 6]; it starts at index 5 and has length 2.
	 * 
	 * @param ints the input array.
	 */

	public static int[] longestPlateau(int[] ints) {

		// TODO: Replace the following one line stub with your solution. Ours takes
		// linear time and is
		// 24 lines long, not counting blank/comment lines or lines already present in
		// this file.
		int value = ints[0], start = 0, len = 1, fin_value = ints[0], fin_start = 0, fin_len = 0;
		for (int i = 0; i < ints.length; i++) { // loop to go through every single element in the array
			// checking the left of the element
			if (i - 1 > 0 && ints[i - 1] > ints[i]) { // comparing the previous element with the current element
				start = -1; // if this case passes then it is not the correct plateau
				len = 1; // reset the counter len
			} else if (i - 1 >= 0 && ints[i - 1] < ints[i]) { // if the previous element is smaller than the current a
																// possible plateau
				value = ints[i]; // this is a possible plateau
				start = i; // set the starting to where it starts
			}
			// checking the right of the element
			if (i + 1 < ints.length && ints[i] == ints[i + 1] && start != -1) { // if the current element and the next
																				// element is the same
				len++;// if the element is a possible plateau then increment the len
			} else if (i + 1 < ints.length && ints[i] < ints[i + 1]) { // comparing the current with the next element
				start = -1; // if the current element is less than the next element then its not a plateau
				len = 1; // reset the counter
			} else if (i + 1 >= ints.length || ints[i] > ints[i + 1]) { // if the next element is less than the current,
																		// then there is a possibility to be a plateau
				if (fin_len < len) { // comparing the previous plateau with the current, if the current plateau
										// length is greater than the previous switch values, starting position and
										// length
					fin_value = value;
					fin_start = start;
					fin_len = len;
				} // else the current plateau is not the longest plateau
				start = -1; // not the longest plateau
				len = 1; // reset the len
			}
		}
		return new int[] { fin_value, fin_start, fin_len }; // output the fin_value, fin_start, fin_len
	}

	/**
	 * main() runs test cases on your longestPlateau() method. Prints summary
	 * information on basic operations and halts with an error (and a stack trace)
	 * if any of the tests fail.
	 */

	public static void main(String[] args) {
		String result;

		System.out.println("Let's find longest plateaus of arrays!\n");

		int[] test1 = { 4, 1, 1, 6, 6, 6, 6, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test1) + ":");
		result = TestHelper.stringInts(longestPlateau(test1));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 3 , 4 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test2 = { 3, 3, 1, 2, 4, 2, 1, 1, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test2) + ":");
		result = TestHelper.stringInts(longestPlateau(test2));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 3 , 0 , 2 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test3 = { 3, 3, 1, 2, 4, 0, 1, 1, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test3) + ":");
		result = TestHelper.stringInts(longestPlateau(test3));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 1 , 6 , 4 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test4 = { 3, 3, 3, 4, 1, 2, 4, 4, 0, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test4) + ":");
		result = TestHelper.stringInts(longestPlateau(test4));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 4 , 6 , 2 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test5 = { 7, 7, 7, 7, 9, 8, 2, 5, 5, 5, 0, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test5) + ":");
		result = TestHelper.stringInts(longestPlateau(test5));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 5 , 7 , 3 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test6 = { 4 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test6) + ":");
		result = TestHelper.stringInts(longestPlateau(test6));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 4 , 0 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test7 = { 4, 4, 4, 5, 5, 5, 6, 6 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test7) + ":");
		result = TestHelper.stringInts(longestPlateau(test7));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 6 , 2 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		System.out.println("\nAdditional tests done by the student or TA:\n");

		int[] test8 = { 1, 5, 5, 5, 5, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test8) + ":");
		result = TestHelper.stringInts(longestPlateau(test8));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 5 , 1 , 4 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test9 = { 1, 2, 3 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test9) + ":");
		result = TestHelper.stringInts(longestPlateau(test9));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 3 , 2 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test10 = { 10, 9 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test10) + ":");
		result = TestHelper.stringInts(longestPlateau(test10));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 10 , 0 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test11 = { 5, 9, 5, 5 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test11) + ":");
		result = TestHelper.stringInts(longestPlateau(test11));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 9 , 1 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test12 = { 5, 9, 8, 5 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test12) + ":");
		result = TestHelper.stringInts(longestPlateau(test12));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 9 , 1 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test13 = { 5, 3, 3, 5 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test13) + ":");
		result = TestHelper.stringInts(longestPlateau(test13));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 5 , 0 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test14 = { 1, 1, 1, 1, 5 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test14) + ":");
		result = TestHelper.stringInts(longestPlateau(test14));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 5 , 4 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		// Insert your additional test cases here.
	}
}