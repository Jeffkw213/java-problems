/**********************************************************
 * EECS2011ON: Fundamentals of Data Structures,  Winter 2020
 * Assignment 2, Problem 1: Coins.java
 * Student Name:   Jeff Kwan
 * Student EECS account:  jeffkw
 * Student ID number:  216396764
 **********************************************************/
package a2sol;

import java.util.*;

public class Coins {
	public static class InvalidMoneyException extends RuntimeException { //throw runtime Exception
		public InvalidMoneyException() {

		}

		public InvalidMoneyException(String msg) {
			super(msg);
		}
	}
	/************* Attributes *************/
	/*Fixed variables 
	 * */
	final int CoinCents = 4; // the number of cent coins
	final int[] coin = new int[] { 1, 5, 10, 25 }; // the value of the cents
	final int[] count = new int[CoinCents]; //storing the number of cents that could be broken down
	final String[] Plural = new String[] { "Pennies", "Nickels", "Dimes", "Quaters" };// Plural names of Coins
	final String[] Singular = new String[] { "Penny", "Nickel", "Dime", "Quater" };//Singular names of Coins
	int list; //number of possibilities to break down cents into coins
	StringBuilder sb; //buiding the output
	
	/************* PrintOut *************/
	/* Reinitializing counter variables.
	 * */
	public Coins() {
		this.list = 1; //list always start at 1
		for (int i = 0; i < CoinCents; i++) {
			this.count[i] = 0; //reset the counter of each value to 0.
		}
		
	}
	/************* PrintOut *************/
	/* This prints out the results of the coins.
	 * @param list
	 * 		the number of recursion it is on
	 * @return String
	 * 
	 */ 
	public String PrintOut(int list) {
		sb = new StringBuilder(); //creating a new StringBuilder everytime its called.
		System.out.print("	" + this.list + ") "); //indenting and getting the which recursion number its on
		for (int i = CoinCents - 1; i >= 0; i--) { //for loop descending so that it outputs the larger unit of coin first
			if (count[i] > 0) //if the count of coin is greater than 0, then output
				if (count[i] == 1) { //if the count of coin is 1, then output the singular word
					sb.append(this.count[i]);
					sb.append(" ");
					sb.append(Singular[i]); 
					sb.append(", ");
				}
				else { //otherwise output the plural.
					sb.append(this.count[i]);
					sb.append(" ");
					sb.append(Plural[i]); 
					sb.append(", ");
				}
		}
		sb.deleteCharAt(sb.length()-2); //Removing the comma of the last element in the string builder
		return sb.toString(); //returning the string of StringBuilder
	}
	
	/*************Recursion*************/
	/*
	 * Recursive method that calls a helper method
	 * 
	 * @param money
	 * 		cents inputed by user
	 * 
	 * Pre-Condition: money has to be an integer
	 * Post-Condition: money is positive
	 */
	public void ways(int money) {
		//if the money inputed is negative, throw an Exception.
		if (money <= 0) {
			throw new InvalidMoneyException("money needs to be positive");
		}
		System.out.println("This amount can be changed in the following ways:");
		Helperways(money, this.coin.length - 1, this.list);
	}
	
	/*************HelperMethod*************/
	/*
	 * Helper Recursive call
	 * Base case: when currentunit == 0
	 * Pre-Condition: Money has to be an integer and positive
	 * Post-Condition: organize multiple ways to group the number of cents into coins
	 * @param currentbalance
	 * 		the balance of money when subtracted by the value of a coin
	 * @param currentunit
	 * 		the current value of the coin
	 * @param list
	 * 		The counter to determine how many times the recursion finds a possible solution.
	 */
	private void Helperways(int currentbalance, int currentunit, int list) {
		//return statement if the current unit reaches the smallest unit
		if (currentunit >= 0) {
			//print the possibility when the current balance is 0
			if (currentbalance == 0) {
				System.out.println(PrintOut(this.list)); //print out result
				this.list++;
			} else {
				if (currentbalance >= coin[currentunit]) { //if the current balance is greater than the current unit coin
					this.count[currentunit]++; //increment that coin
					Helperways(currentbalance - coin[currentunit], currentunit, this.list); //recursively call the method with 
																							//the difference of the current value and the current coin
					this.count[currentunit]--; //move back the count of the coin when finish the recursive call
				}
				//recursive call when the current balance is less than the current unit coin
				Helperways(currentbalance, currentunit - 1, this.list);
			}
		}
	}
	/************* Main *************/
	public static void main(String[] args) {
		int money;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter an amount in cents:");
		money = input.nextInt();
		Coins c = new Coins();
		c.ways(money);
		input.close();
	}
}
