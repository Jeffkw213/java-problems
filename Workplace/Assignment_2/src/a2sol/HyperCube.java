/**********************************************************
 * EECS2011ON: Fundamentals of Data Structures,  Winter 2020
 * Assignment 2, Problem 2: HyperCube.java
 * Student Name:   Jeff Kwan
 * Student EECS account:  jeffkw
 * Student ID number:  216396764
 **********************************************************/
package a2sol;

import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class HyperCube {
	/*
	 * static class Corner
	 */
	public static class Corner {
		/*
		 * Variables for the Corner class
		 * coordinate for boolean to determine 0 or 1 (true = 1 & false = 0)
		 * dimension is the dimension of the hypercube
		 * visit is to determine if the corner has been read yet.
		 */
		boolean[] coordinate;
		int dimension;
		static ArrayList<String> visit;

		/*
		 * Constructor
		 * 
		 * @param int dimension 
		 * 		The unit of dimension
		 * 
		 * Initialize Variables
		 */
		public Corner(int dimension) {
			this.coordinate = new boolean[dimension];
			for (int i = 0; i < dimension; i++) {
				coordinate[i] = false;
			}
			this.dimension = dimension;
			Corner.visit = new ArrayList<String>();
		}

		/*
		 * Moving Method
		 * 
		 * @param int direct 
		 * 		which coordinate needs to be moved
		 * 
		 * Moving a coordinate of a corner to either true or false
		 */
		protected void move(int direct) {
			this.coordinate[direct] = !this.coordinate[direct];
		}

		/*
		 * Getting coordinates
		 * 
		 * @return String 
		 * 		StringBuilder to string
		 * 
		 * Output the coordinate of the corner
		 */
		protected String Point() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < this.dimension; i++) {
				if (this.coordinate[i] == true)
					sb.append(1);
				else
					sb.append(0);
			}
			return sb.toString();
		}

		/*
		 * Making Deep Copy of corner
		 * 
		 * @param Corner corner 
		 * Creating a clone of corner
		 * 
		 * @return Object 
		 * 		return the Deep copy of the corner Overriding method clone
		 * 		into static class Corner
		 */
		protected Object clone(Corner corner) {
			Corner corner_copy = new Corner(corner.dimension);
			for (int i = 0; i < corner.coordinate.length; i++) {
				corner_copy.coordinate[i] = corner.coordinate[i];
			}
			return corner_copy;
		}

		/*
		 * Comparing corners
		 * 
		 * @param Corner corner 
		 * 		comparing corners
		 * 
		 * @return boolean 
		 * 		Overriding method equals into static class Corner
		 */
		protected boolean equals(Corner corner) {
			for (int i = 0; i < this.dimension; i++) {
				if (this.coordinate[i] != corner.coordinate[i]) {
					return false;
				}
			}
			return true;
		}
	}

	/* RuntimeException Class */
	public static class InvalidDimension extends RuntimeException {
		public InvalidDimension() {
		}

		public InvalidDimension(String msg) {
			super(msg);
		}
	}

	/*
	 * HyperCube Class Variables
	 * What dimension the user want the hypercube to be
	 * origin is the starting point of the hypercube the coordinates are all false
	 * visited is to check if the corner has been checked yet. 
	 */
	int dimension;
	Corner origin;
	static ArrayList<String> visited;

	/*
	 * HyperCube Constructor
	 * 
	 * Pre-Condition: dimension is Integer
	 * Post-Condition: dimension is greater than 0
	 * 
	 * @param int dimension 
	 * inputed dimension Initialize variables and to check if
	 * dimension is valid throw Exception when dimension is < 0
	 */
	public HyperCube(int dimension) {
		if (dimension < 0)
			throw new InvalidDimension("Dimension needs to be positive.");
		if (dimension == 0)
			throw new InvalidDimension("Dimension cannot be 0");
		this.dimension = dimension;
		this.origin = new Corner(this.dimension);
		visited = new ArrayList<String>();
	}

	/*
	 * RecursiveWalk Method
	 * 
	 * initialize the starting corner pass the starting corner into helper method
	 */
	private void recursiveWalk() {
		Corner copy = (Corner) this.origin.clone(this.origin);
		this.HelperrecursiveWalk(copy);
	}

	/*
	 * HelperRecursiveWalk Method
	 * 
	 * @param Corner start 
	 * 		start is the variable that would be changing in this recursion is a parent
	 * The method would move one of the coordinates of a corner and is a
	 * child 
	 * A global ArrayList to keep track of which corner has been read
	 * Recursively change the child node to a parent and move one of the coordinate
	 * of the coordinate.
	 * 
	 * This method is Depth First Search.
	 */
	private void HelperrecursiveWalk(Corner start) {
		if (!visited.contains(start.Point())) { // if the parent is in the ArrayList then recursion is done
			visited.add(start.Point()); // adding the parent to the ArrayList to make sure the method doesn't
										// StackOverFlow
			for (int i = 0; i < this.dimension; i++) { // for loop to go every neighbouring corner.
				Corner copy_start = (Corner) start.clone(start); // make a deep copy so the position of the parent
																	// corner doesn't change
				copy_start.move(i); // move the parent corner to a neighbouring corner/ to a child corner
				if (!visited.contains(copy_start.Point())) { // if the child is not in the ArrayList, Recursively call
																// the method again.
					HelperrecursiveWalk(copy_start);
				}
			}
		}
	}

	/* 
	 * Printing RecursiveWalk Method 
	 * @param ArrayList<String> visited
	 * 		output the ArrayList with system.out.println
	 * Formating
	 */
	private void printWalk(ArrayList<String> visited) {
		System.out.println("recursiveWalk:");
		System.out.println("A Walk:");
		for (String s : visited) {
			System.out.println(" " + s);
		}
	}

	/* 
	 * iterativeWalk Method
	 * @return ArrayDeque<String>
	 * 		return the ArrayDeque which is the pathway to go through every
	 * 		single point in the Cube.
	 * 
	 * This method is a iterative method to the recursive method
	 * Instead of a recursive call, it has a while loop with the same condition 
	 * in the recursive method. However in the for loop, the loop needs to break
	 * so that the parent is able to change everytime the ArrayDeque doesn't contain
	 * the child coordinate.
	 * 
	 * 
	 */
	private ArrayDeque<String> iterativeWalk() {
		Corner start = (Corner) this.origin.clone(this.origin); //starting point
		ArrayDeque<String> visited = new ArrayDeque<String>(); //storing elements that the program didn't read yet
		while (!visited.contains(start.Point())) { //keep looping until the parent starting is repeated
			visited.add(start.Point()); //push the parent into the ArrayDeque
			for (int i = 0; i < this.dimension; i++) { //for loop to go through every neighbour
				Corner copy_start = (Corner) start.clone(start); //keeping track where was parent corner
				copy_start.move(i); //moving making a child corner
				if (!visited.contains(copy_start.Point())) { //continue the for loop if the child is not in
															//in the ArrayDeque
					start = (Corner) copy_start.clone(copy_start); //changing Child to Parent
					break; //break for loop
				}
			}
		}
		return visited;
	}

	/* 
	 * Printing iterativeWalk Method 
	 * 
	 * @param ArrayDeque<String> visited
	 * 		Printing out the ArrayDeque with system.out.println
	 * Formating
	 */
	private void printWalk(ArrayDeque<String> visited) {
		System.out.println("iterativeWalk");
		System.out.println("A Walk:");
		for (String s : visited) {
			System.out.println(" " + s);
		}
	}

	/* ---------- Output ---------- */
	public static void main(String[] args) {
		int dimension;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a dimension: ");
		dimension = input.nextInt();
		input.close();
		HyperCube h = new HyperCube(dimension);
		h.recursiveWalk();
		h.printWalk(visited);
		System.out.println();
		h.printWalk(h.iterativeWalk());
	}
}
