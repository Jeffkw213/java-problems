/**********************************************************
 * EECS2011ON: Fundamentals of Data Structures,  Winter 2020
 * Assignment 1, Problem 1: ArraySqueeze.java
 * Student Name:   Jeff KWan
 * Student EECS account:  jeffkw
 * Student ID number:  216396764 
 **********************************************************/

package A1;

/**
 * The purpose of this class is to squeeze an array of ints.
 * 
 * The main method runs some tests.
 * 
 * @author andy
 * 
 */

public class ArraySqueeze {

	/**
	 * squeeze() takes an array of ints. On completion the array contains the same
	 * numbers, but wherever the array had two or more consecutive duplicate
	 * numbers, they are replaced by one copy of the number. Hence, after squeeze()
	 * is done, no two consecutive numbers in the array are the same.
	 * 
	 * Any unused elements at the end of the array are set to -1.
	 * 
	 * For example, if the input array is [ 4 , 1 , 1 , 3 , 3 , 3 , 1 , 1 ], it
	 * reads [ 4 , 1 , 3 , 1 , -1 , -1 , -1 , -1 ] after squeeze() completes.
	 * 
	 * @param ints the input array.
	 */

	public static void squeeze(int[] ints) {

		// TODO: Fill in your solution here. Ours takes linear time and is less than 10
		// lines long,
		// not counting blank/comment lines or lines already present in this file.

		// create a sub array consisting of only unique elements beside each other. (sub
		// array begins at index 0 and ends at the variable end)
		int index = ints[0], end = 1; // the get the comparable variable for no consistent duplicate, and the ending
										// of a sub array
		for (int i = 1; i < ints.length; i++) { // loop to go through every element of the array
			if (ints[i] != index) { // if the element on the right of the array is not the same as the current
									// element
				index = ints[i]; // changing the start of the array to the element on the right
				ints[end++] = ints[i]; // move the element on the right in to the end part of the sub array
				// increment the end of the sub array by 1
			}
		}
		// inputting -1 to the remaining parts of the array
		while (end < ints.length) {
			ints[end++] = -1;
		}
	}

	/**
	 * main() runs test cases on your squeeze() method. Prints summary information
	 * on basic operations and halts with an error (and a stack trace) if any of the
	 * tests fail.
	 */

	public static void main(String[] args) {
		String result;

		System.out.println("Let's squeeze arrays!\n");

		int[] test1 = { 3, 7, 7, 7, 4, 5, 5, 2, 0, 8, 8, 8, 8, 5 };
		System.out.println("squeezing " + TestHelper.stringInts(test1) + ":");
		squeeze(test1);
		result = TestHelper.stringInts(test1);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 3 , 7 , 4 , 5 , 2 , 0 , 8 , 5 , -1 , -1 , -1 , -1 , -1 , -1 ]"),
				"BAD SQEEZE!!!  No cookie.");

		int[] test2 = { 6, 6, 6, 6, 6, 3, 6, 3, 6, 3, 3, 3, 3, 3, 3 };
		System.out.println("squeezing " + TestHelper.stringInts(test2) + ":");
		squeeze(test2);
		result = TestHelper.stringInts(test2);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 6 , 3 , 6 , 3 , 6 , 3 , -1 , -1 , -1 , -1 , -1 , -1 , -1 , -1 , -1 ]"),
				"BAD SQEEZE!!!  No cookie.");

		int[] test3 = { 4, 4, 4, 4, 4 };
		System.out.println("squeezing " + TestHelper.stringInts(test3) + ":");
		squeeze(test3);
		result = TestHelper.stringInts(test3);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 4 , -1 , -1 , -1 , -1 ]"), "BAD SQEEZE!!!  No cookie.");

		int[] test4 = { 0, 1, 2, 3, 4, 5, 6 };
		System.out.println("squeezing " + TestHelper.stringInts(test4) + ":");
		squeeze(test4);
		result = TestHelper.stringInts(test4);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 0 , 1 , 2 , 3 , 4 , 5 , 6 ]"), "BAD SQEEZE!!!  No cookie.");

		System.out.println("\nAdditional tests done by the student or TA:\n");

		int[] test5 = { 1, 3, 3, 4, 4, 4, 4, 9 };
		System.out.println("squeezing " + TestHelper.stringInts(test5) + ":");
		squeeze(test5);
		result = TestHelper.stringInts(test5);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 1 , 3 , 4 , 9 , -1 , -1 , -1 , -1 ]"), "BAD SQEEZE!!!  No cookie.");

		int[] test6 = { 1 };
		System.out.println("squeezing " + TestHelper.stringInts(test6) + ":");
		squeeze(test6);
		result = TestHelper.stringInts(test6);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 1 ]"), "BAD SQEEZE!!!  No cookie.");

		int[] test7 = { 9, 9, 9, 10, 1, 1, 0, 9 };
		System.out.println("squeezing " + TestHelper.stringInts(test7) + ":");
		squeeze(test7);
		result = TestHelper.stringInts(test7);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 9 , 10 , 1 , 0 , 9 , -1 , -1 , -1 ]"), "BAD SQEEZE!!!  No cookie.");

		int[] test8 = { 9, 9 };
		System.out.println("squeezing " + TestHelper.stringInts(test8) + ":");
		squeeze(test8);
		result = TestHelper.stringInts(test8);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 9 , -1 ]"), "BAD SQEEZE!!!  No cookie.");

		int[] test9 = { 9, 10 };
		System.out.println("squeezing " + TestHelper.stringInts(test9) + ":");
		squeeze(test9);
		result = TestHelper.stringInts(test9);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 9 , 10 ]"), "BAD SQEEZE!!!  No cookie.");

		int[] test10 = { 1, 3, 3 };
		System.out.println("squeezing " + TestHelper.stringInts(test10) + ":");
		squeeze(test10);
		result = TestHelper.stringInts(test10);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 1 , 3 , -1 ]"), "BAD SQEEZE!!!  No cookie.");

		int[] test11 = { 1, 1, 0, 3, 3 };
		System.out.println("squeezing " + TestHelper.stringInts(test11) + ":");
		squeeze(test11);
		result = TestHelper.stringInts(test11);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 1 , 0 , 3 , -1 , -1 ]"), "BAD SQEEZE!!!  No cookie.");

		// Insert your additional test cases here.
	}
}