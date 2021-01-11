/**********************************************************
 * EECS2011ON: Fundamentals of Data Structures,  Winter 2020
 * Assignment 3, Problem 2: Binary_Tree_Node_Balance_Factor.java
 * Student Name:   Jeff Kwan
 * Student EECS account:  jeffkw
 * Student ID number:  216396764
 **********************************************************/
package A3sol;

import java.util.Scanner;
//Balance_Factor = Height(Left Node) - Height(Right Node)
public class Binary_Tree_Node_Balance_Factors {
	public static class Node<T> {
		T element; //element of the node
		Node<T> left; //left of the current node
		Node<T> right; //right of the current node
		/*
		 * Constructor
		 * @param element
		 * 		The element of the node
		 * Initialize Variable
		 */
		public Node(T element) {
			this.element = element;
			this.left = null;
			this.right = null;
		}
		/*
		 * @return T
		 * return the element
		 */
		public T getElement() {
			return this.element;
		}
		/*
		 * @return Node<T>
		 * return the left of the current node
		 */
		public Node<T> getLeft() {
			return this.left;
		}
		/*
		 * @return Node<T>
		 * return the right of the current node
		 */
		public Node<T> getRight() {
			return this.right;
		}
		/*
		 * @param element
		 * set a new element
		 */
		public void setElement(T element) {
			this.element = element;
		}
		/*
		 * @param Node<T>
		 * set a new left node 
		 */
		public void setLeft(Node<T> left) {
			this.left = left;
		}
		/*
		 * @param Node<T>
		 * set a new right node
		 */
		public void setRight(Node<T> right) {
			this.right = right;
		}
	}

	public static class BinaryTree<T extends Comparable<T>> {
		Node<T> root;
		/*
		 * @param element
		 * 		adding the element to the root of the tree
		 */
		public void addNode(T element) {
			root = Tree(root, element);
		}
		/*
		 * @param current, element
		 * 		current node and the element of the current node
		 * 
		 * @return Node<T>
		 * Recursive call to sketch out the binary tree.
		 * If the element is less than the current node, then it goes to the left of the current node
		 * If the element is greater than the current node, then it goes to the right of the current node		
		 */
		private Node<T> Tree(Node<T> current, T element) {
			if (current == null) { // if the current node is empty 
				return new Node<T>(element); //make new node with the element
			}
			if (element.compareTo(current.element) <= 0) { //if the current node's element is less or equal than the new element then move the element down the left branch of the tree
				current.left = Tree(current.left, element);
				System.out.println("Left");
			} else if (element.compareTo(current.element) > 0) {//if the current node's element is greater than the new element then move the element down the right branch of the tree
				current.right = Tree(current.right, element);
				System.out.println("Right");
			}
			return current;
		}
		/*
		 * Balance factor
		 * printing out the balance factor of each node
		 */
		public void printBalanceFactor() {
			this.BalanceFactor(this.root);
		}
		/*
		 * @param current
		 * 		the current node
		 * @return int
		 * return the height of the node.
		 */
		private int BalanceFactor(Node<T> current) {
			if (current == null) { //base case when the current node is null
				return 0;
			}
			int left_height = this.BalanceFactor(current.getLeft()); //recursive call to run down left of node
			int right_height = this.BalanceFactor(current.getRight());//recursive call to run down the right of node
			int balance_factor = Math.abs(right_height - left_height);//calculate the balance factor
			System.out.println("(" + current.getElement() + ", " + balance_factor + ")");
			return left_height > right_height ? left_height + 1 : right_height + 1; //incrementing the height of the node
		}

	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		System.out.println("Put in the number of nodes in the binary tree");
		int number = input.nextInt();
		System.out.println("Enter a Root number");
		tree.addNode(input.nextInt());
		for (int i = 0; i < number - 1; i++) {
			System.out.println("Enter a number");
			tree.addNode(input.nextInt());
		}
		System.out.println("\nBalance Factor (Element, BalanceFactor):");
		tree.printBalanceFactor();
		input.close();
	}
}
