/**********************************************************
 * EECS2011ON: Fundamentals of Data Structures,  Winter 2020
 * Assignment 2, Problem 3: AugmentedStack.java
 * Student Name:   Jeff Kwan
 * Student EECS account:  jeffkw
 * Student ID number:  216396764
 **********************************************************/
package a2sol;

import java.util.Stack;

public class AugmentedStack<S extends Comparable<S>> {
	private Stack<S> smallest = new Stack<S>(); //a Stack to store in a current min value
	private Stack<S> stuff = new Stack<S>(); // putting in items into Stack
	static StringBuilder sb;

	/************* Constructor *************/
	public AugmentedStack() {
		sb = new StringBuilder();
	}

	/************* Push *************/
	/*
	 * @param any element to push into a Stack of stuff pushing elements into the
	 * Stack
	 */
	public void push(S item) {
		stuff.push(item); // pushing item into stuff Stack
		if (smallest.isEmpty()) {
			smallest.push(item); // if the min Stack is empty, push in the new item
		} else {
			if (smallest.peek().compareTo(item) >= 0) { // if peek of the min Stack is greater than the item
				smallest.push(item); // push the item into the min Stack
			}
		}
	}

	/************* Pop *************/
	/*
	 * removing the top of the Stack
	 * 
	 * @return null if element of Stack is empty or top unit of the Stack
	 */
	public S pop() {
		if (stuff.isEmpty()) {
			return null;
		} else {
			S min = stuff.pop();
			if (smallest.peek().equals(min)) { // if the peek of the min Stack is the popped Stack
				smallest.pop(); // remove the top of the min Stack as well
			}
			return min;
		}
	}

	/************* getMin *************/
	/*
	 * @return the smallest value of the Stack if empty, return null
	 */
	public S getMin() {
		if (smallest.isEmpty())
			return null;
		return smallest.peek(); //return the peek of the smallest which is the min value in stuff
	}

	/************* isEmpty *************/
	/*
	 * @return if Stack is empty true, else false
	 */
	public boolean isEmpty() {
		return stuff.isEmpty();
	}

	/************* top *************/
	/*
	 * @return the top of the Stack if empty, return null
	 */
	public S top() {
		if (stuff.isEmpty())
			return null;
		return stuff.peek(); 
	}

	/*---------------OUTPUT & Formating---------------*/

	/************* Formating *************/
	private static void deleteComma() {
		sb.deleteCharAt(sb.length() - 2);
	}

	/************* Clearing *************/
	private static void Clear() {
		sb.delete(0, sb.length());
	}

	/************* Printing for Strings *************/
	private static void Print(AugmentedStack<String> h) {
		if (h.isEmpty())
			return;
		String i = h.top();
		h.pop();
		Print(h);
		sb.append(i);
		sb.append(", ");
		h.push(i);
	}

	/************* Printing for Integer *************/
	private static void PrintV(AugmentedStack<Integer> a) {
		if (a.isEmpty())
			return;
		int i = a.top();
		a.pop();
		PrintV(a);
		sb.append(i);
		sb.append(", ");
		a.push(i);
	}

	/*---------------TESTING---------------*/

	/************* Main *************/
	public static void main(String[] args) {

		System.out.println("//TEST_1//\n");
		AugmentedStack<String> h = new AugmentedStack<String>();
//		String Hello = "Hello";
//		String num1 = "1";
//		String num2 = "2";
//		System.out.println(Hello.compareTo(num1));
//		System.out.println(Hello.compareTo(num2));
//		System.out.println(num1.compareTo(Hello));
//		System.out.println(num1.compareTo(num2));
//		System.out.println(num2.compareTo(Hello));
//		System.out.println(num2.compareTo(num1));
		h.push("Hello");
		System.out.println("Push: Hello");
		h.push("1");
		System.out.println("Push: 1");
		h.push("2");
		System.out.println("Push: 2");
		
		System.out.println("isEmpty? " + h.isEmpty());
		System.out.println("Top: " + h.top());

		sb.append("[");
		Print(h);
		sb.deleteCharAt(sb.length() - 2);
		sb.append("]");
		System.out.println("Stack: " + sb);

		Clear();
		System.out.println("Pop: " + h.pop());
		sb.append("[");
		Print(h);
		deleteComma();
		sb.append("]");
		System.out.println("New Stack: " + sb);

		System.out.println("getMin: " + h.getMin());
		System.out.println("\n");

		System.out.println("//TEST_2//\n");
		AugmentedStack<Integer> a = new AugmentedStack<Integer>();
		System.out.println("isEmpty? " + a.isEmpty());
		System.out.println("Top: " + a.top());
		System.out.println("getMin: " + a.getMin());
		a.push(500);
		System.out.println("Push: 500");
		a.push(356);
		System.out.println("Push: 356");
		a.push(1234567890);
		System.out.println("Push: 1234567890");
		sb.append("[");
		PrintV(a);
		deleteComma();
		sb.append("]");
		System.out.println("Stack: " + sb);
		System.out.println("isEmpty? " + a.isEmpty());
		System.out.println("Top: " + a.top());
		System.out.println("getMin: " + a.getMin());
		System.out.println("\n");

		System.out.println("//TEST_3//\n");
		AugmentedStack<String> b = new AugmentedStack<String>();
		System.out.println("isEmpty? " + b.isEmpty());
		System.out.println("Top: " + b.top());
		System.out.println("getMin: " + b.getMin());
//		String MEME = "MEME";
//		String JOKE = "JOKE";
//		String JEFF	= "JEFF";
//		String INTEGER = "INTERGER";
//		System.out.println(MEME.compareTo(JOKE));
//		System.out.println(MEME.compareTo(JEFF));
//		System.out.println(MEME.compareTo(INTEGER));
//		System.out.println(JOKE.compareTo(MEME));
//		System.out.println(JOKE.compareTo(JEFF));
//		System.out.println(JOKE.compareTo(INTEGER));
//		System.out.println(JEFF.compareTo(MEME));
//		System.out.println(JEFF.compareTo(JOKE));
//		System.out.println(JEFF.compareTo(INTEGER));
//		System.out.println(INTEGER.compareTo(MEME));
//		System.out.println(INTEGER.compareTo(JOKE));
//		System.out.println(INTEGER.compareTo(JEFF));
		b.push("MEME");
		System.out.println("Push: MEME");
		b.push("JOKE");
		System.out.println("Push: JOKE");
		b.push("JEFF");
		System.out.println("Push: JEFF");
		b.push("INTEGER");
		System.out.println("Push: INTEGER");
		sb.append("[");
		Print(b);
		deleteComma();
		sb.append("]");
		System.out.println("Stack: " + sb);
		System.out.println("isEmpty? " + b.isEmpty());
		System.out.println("Top: " + b.top());
		System.out.println("getMin: " + b.getMin());
		System.out.println("\n");

		System.out.println("//TEST_4//\n");
		AugmentedStack<String> n = new AugmentedStack<String>();
		System.out.println("isEmpty? " + n.isEmpty());
		System.out.println("Top: " + n.top());
		System.out.println("getMin: " + n.getMin());
		n.push("abc");
		System.out.println("Push: abc");
		n.push("bcd");
		System.out.println("Push: bcd");
		n.push("abcd");
		System.out.println("Push: abcd");
		sb.append("[");
		Print(n);
		deleteComma();
		sb.append("]");
		System.out.println("Stack: " + sb);
		System.out.println("isEmpty? " + n.isEmpty());
		System.out.println("Top: " + n.top());
		System.out.println("getMin: " + n.getMin());
		System.out.println("\n");

		System.out.println("//TEST_5//\n");
		Clear();
		System.out.println("Pop: " + n.pop());
		System.out.println("Pop: " + n.pop());
		System.out.println("Pop: " + n.pop());
		sb.append("[");
		Print(n);
		sb.append("]");
		System.out.println("New Stack: " + sb);
		System.out.println("isEmpty? " + n.isEmpty());
		System.out.println("Top: " + n.top());
		System.out.println("getMin: " + n.getMin());
		System.out.println("\n");
	}

}
