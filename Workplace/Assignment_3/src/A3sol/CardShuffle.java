/**********************************************************
 * EECS2011ON: Fundamentals of Data Structures,  Winter 2020
 * Assignment 3, Problem 1: CardShuffle.java
 * Student Name:   Jeff Kwan
 * Student EECS account:  jeffkw
 * Student ID number:  216396764
 **********************************************************/
package A3sol;

import java.util.*;

public class CardShuffle {
	// Node Class
	public static class Node<T> {
		private T data; // element being stored into the linked list
		private Node<T> next; // element next to 'data'
		/*
		 * Constructor
		 * initializing the element
		 */
		public Node(T data) {
			this.data = data;
		}
		/*
		 * @param next
		 * 		the next element of 'data'
		 * setting 'next'
		 */
		public void setNext(Node<T> next) {
			this.next = next;
		}
		/*
		 * @return Node<T>
		 * return 'next'
		 */
		public Node<T> getNext() {
			return this.next;
		}
		/*
		 * @return toString
		 * return the elements into strings
		 */
		public String toString() {
			return this.data.toString();
		}
	}

	// LinkedList Class
	public static class LinkedList<T> {
		// pointers
		public Node<T> head;
		public Node<T> tail;

		/*
		 * Constructor Initialize variables
		 */
		public LinkedList() {
			this.head = null;
			this.tail = null;
		}

		/*
		 * void type
		 * 
		 * @param data Element to enter to the linked list Method add in an element into
		 * the linked list
		 */
		public void add(T data) {
			if (this.tail == null) {
				// if the linked list starts of empty and the add method is called
				this.head = (this.tail = new Node<T>(data)); // have a head pointer point to the element that is added
			} else {
				// have the tail pointer point to the next element each time an element is being
				// added to linked list which will always be the last element of the linked list
				this.tail = (this.tail.next = new Node<T>(data));
			}
		}

		/*
		 * void type
		 * 
		 * @param middle The middle of the linked list Method shuffles the linked list
		 * in a specific order: Before: [A, B, C, D, E, F] After: [A, D, B, E, C, F]
		 * 
		 */
		public void CardShuffle(Node<T> middle) {
			// have two pointers pointing to the beginning of the linked list and the middle
			// of the linked list
			// This would cut the linked list into two sections
			Node<T> first_pointer = head;
			Node<T> second_pointer = middle.getNext();
			// cutting the first half of the linked list so its separated into 2 parts
			middle.setNext(null);
			// run when the the second pointer is not 'null'
			while (second_pointer != null) {
				// temps for the switch
				Node<T> first_pointer_temp = first_pointer.getNext();
				Node<T> second_pointer_temp = second_pointer.getNext();

				// Swap			
				first_pointer.setNext(second_pointer);
				//System.out.println("First pointer " + first_pointer_temp.toString());
				first_pointer = first_pointer_temp;
				
				second_pointer.setNext(first_pointer);
				//System.out.println("second pointer " + second_pointer_temp.toString());
				second_pointer = second_pointer_temp;
				
			}
		}
		/*
		 * @return Node<T>
		 * return the last value of the linked list
		 */
		public Node<T> getLast() {
			return this.tail;
		}

		/*
		 * print out the linked list
		 * 
		 * @return String return the list into a string.
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			if (this.head != null) {
				Node<T> current = this.head;
				while (current != null) {
					sb.append(current.data.toString());
					sb.append(",");
					current = current.next;
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("]");
			return sb.toString();
		}

	}

	int amount;

	/*
	 * Constructor
	 * 
	 * @param k The amount of elements being inputed
	 */
	public CardShuffle(int k) {
		this.amount = k;
	}

	/*
	 * @return boolean 
	 * Check if the amount inputed is valid
	 */
	public boolean Check() {
		return (this.amount > 0);
	}
	/*
	 * void type
	 * @param amount
	 * 		number of elements being inputed to linked list
	 * inputting elements into linked list
	 */
	public void Enter_Elements(int amount) {
		Scanner input = new Scanner(System.in);
		if (Check()) {
			int n = amount / 2;
			LinkedList<String> elements = new LinkedList<String>();
			System.out.println("Enter the element: ");
			//entering values of the first half
			for (int i = 0; i < n; i++) {
				elements.add(input.nextLine());
			}
			//set the middle value
			Node<String> middle = elements.getLast();
			//finish entering values
			for (int i = 0; i < n; i++) {
				elements.add(input.nextLine());
			}
			System.out.println("Card: \n" + elements.toString());
			elements.CardShuffle(middle);
			System.out.println("CardShuffle: \n" + elements.toString());
		} else {
			System.out.println("Enter a positive even number");
		}
		input.close();
	}
	/*
	 * Main Method
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int k;
		System.out.println("Please enter the total amount k (where k is even)");
		k = input.nextInt();
		if (k % 2 != 0) {
			k *= 2;
			System.out.println("Total amount of elements: " + k);
		}
		CardShuffle test = new CardShuffle(k);
		test.Enter_Elements(k);
		input.close();
	}
}
