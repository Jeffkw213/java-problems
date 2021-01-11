/**********************************************************
 * EECS2011ON: Fundamentals of Data Structures,  Winter 2020
 * Assignment 3, Problem 3: PrioitySearchTree.java
 * Student Name:   Jeff Kwan
 * Student EECS account:  jeffkw
 * Student ID number:  216396764
 **********************************************************/
package A3sol;

public class PrioritySearchTree {
	public static class Point {
		int x; // the x-coordinate
		int y; // the y-coordinate
		String element; // name of the element
		/*
		 * Constructor
		 * 
		 * @param element, x, y 
		 * 		Initializing the element, x, y
		 */

		public Point(String element, int x, int y) {
			this.x = x;
			this.y = y;
			this.element = element;
		}

		/*
		 * @return int 
		 * return the x-coordinate
		 */
		public int get_X() {
			return this.y;
		}

		/*
		 * @return int 
		 * return the y-coordinate
		 */
		public int get_Y() {
			return this.y;
		}

		/*
		 * void type
		 * 
		 * @param x 
		 * 		setting the new x-coordinate
		 */
		public void set_X(int x) {
			this.x = x;
		}

		/*
		 * void type
		 * 
		 * @param y 
		 * 		setting the new
		 */
		public void set_Y(int y) {
			this.y = y;
		}
		/*
		 * @return element
		 * return the element
		 */
		public String getElement() {
			return this.element;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			sb.append(this.element);
			sb.append(" ,");
			sb.append(this.x);
			sb.append(" ,");
			sb.append(this.y);
			sb.append(")");
			return sb.toString();
		}
	}

	Point[] heap; //the external point given
	boolean[] used; //determine if the point has been used
	Point[] preorder; //the internal nodes in preorder
	int current = 0; //the current position of the preorder array

	/*
	 * Constructor
	 * @param tree
	 * 		the given External points
	 */
	public PrioritySearchTree(Point[] tree) {
		this.preorder = new Point[tree.length - 1]; //there will be one less of the array given in the binary tree when comparing
		this.heap = new Point[tree.length];
		this.used = new boolean[tree.length];
		for (int i = 0; i < tree.length; i++) {
			this.heap[i] = tree[i];
			this.used[i] = false;
		}
	}
	/*
	 * Recursion:
	 * Find the Largest y between the beginning and the end
	 * Recursively cut the array of external points in half and 
	 * find the left beginning, left end, and the right beginning, 
	 * right end
	 * Time Complexity: O(nlogn), since n x height of the tree O(logn)
	 */
	public void FindParent(int beginning, int end) {
		Point parent = null; //largest y between beginning and end
		int top = 0; //max y
		int leaf = -1; //determine if the largest is used
		if (end - beginning > 1) { // base case there are elements is at least 2 elements between beginning and end of array
			for (int i = beginning; i < end; i++) { // goes through the sub part of the array between beginning and end
				if (this.used[i]==false && (leaf == -1 || top < heap[i].get_Y())) { //if the top is smaller than the current internal point
					top = heap[i].get_Y(); //switch largest
					parent = heap[i]; 
					leaf = i; //get the current index of array 
				}
			}
			if (leaf != -1) { //if leaf was changed then there is an element that is a parent between beginning and end
				this.used[leaf] = true; //switch the current element to true to notify that its been used
				this.preorder[current] = parent; //put the point into the preorder array
				current++; //move to the next index of preorder
			} else {//if leaf was not changed then all the element between beginning and end has been used thus 'null' is the parent
				this.preorder[current] = parent; //put null in the current position of preorder
				current++;//move to the next index of preorder
			}

			// LeftSide: end would become the midpoint between beginning and end
			FindParent(beginning, (beginning + end) / 2);
			// RightSide: beginning would become the midpoint between beginning and end
			FindParent(((beginning + end) / 2), end);
		}
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < preorder.length; i++) {
			sb.append(preorder[i]);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}
/*
 *****************************- OUTPUTS -*****************************
 */
	public static void main(String[] args) {
		PrioritySearchTree tree;
		Point[] S1 = { new Point("p1", -8, 3), new Point("p2", -7, 1), new Point("p3", -1, 6), new Point("p4", 2, 4),
				new Point("p5", 4, 8), new Point("p6", 5, 9), new Point("p7", 7, 1), new Point("p8", 9, 7) };

		tree = new PrioritySearchTree(S1);
		tree.FindParent(0, S1.length);
		System.out.println("Internal Nodes of Tree");
		System.out.println("Tree pre-Order: ");
		System.out.println(tree.toString());
		System.out.println("*********************************************************************************\n");
		Point[] S2 = { new Point("p1", -8, 3), new Point("p2", -9, -6) };

		tree = new PrioritySearchTree(S2);
		tree.FindParent(0, S2.length);
		System.out.println("Internal Nodes of Tree");
		System.out.println("Tree pre-Order: ");
		System.out.println(tree.toString());
		System.out.println("*********************************************************************************\n");
		Point[] S3 = { new Point("p1", 1, 300), new Point("p2", 2, 1), new Point("p3", 3, 88), new Point("p4", 4, 4),
				new Point("p5", 5, -8), new Point("p6", 6, -79), new Point("p7", 7, 1), new Point("p8", 8, 7) };

		tree = new PrioritySearchTree(S3);
		tree.FindParent(0, S3.length);
		System.out.println("Internal Nodes of Tree");
		System.out.println("Tree pre-Order: ");
		System.out.println(tree.toString());
		System.out.println("*********************************************************************************\n");
	}
}
