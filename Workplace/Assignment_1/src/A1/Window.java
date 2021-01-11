/**********************************************************
 * EECS2011ON: Fundamentals of Data Structures,  Winter 2020
 * Assignment 1, Problem 3: Window.java
 * Student Name:   Jeff Kwan
 * Student EECS account:  jeffkw
 * Student ID number:  216396764
 **********************************************************/
package A1;

public class Window {
	// static class if there is an invalid exception in the coordinates in the sides
	// of the windows
	public static class InvalidWindowException extends RuntimeException {
		public InvalidWindowException() {
		}

		public InvalidWindowException(String msg) {
			super(msg);
		}
	}

	protected double left, right, bottom, top;

	public Window() {

	}

	public Window(double left, double right, double bottom, double top) {
		if (left >= right || bottom >= top) { // conditions for a window
			throw new InvalidWindowException("invalid Window");
		}
		// set values
		this.left = left;
		this.right = right;
		this.bottom = bottom;
		this.top = top;
	}

	// Getter methods
	public double getLeft() {
		return this.left;
	}

	public double getRight() {
		return this.right;
	}

	public double getBottom() {
		return this.bottom;
	}

	public double getTop() {
		return this.top;
	}

	// Setter method
	public void set_x(double left, double right) {
		if (left < right) { // left side has to be less than the right side
			this.left = left;
			this.right = right;
		} else {
			throw new InvalidWindowException("Left is greater than right");
		}

	}

	public void set_y(double bottom, double top) {
		if (bottom < top) { // bottom side has to be less than the top side
			this.bottom = bottom;
			this.top = top;
		} else {
			throw new InvalidWindowException("Bottom is greater than top");
		}
	}

	// enclose && overlaps
	public boolean encloses(Window w) {
		if (this.left <= w.left && this.right >= w.right && this.top >= w.top && this.bottom <= w.bottom) { // comparing
																											// the
																											// current
																											// window
																											// with the
																											// argument
																											// window
			return true; // if the argument window is in the current window then its true
		}
		return false;
	}

	public boolean overlaps(Window w) {
		if (this.right <= w.left || this.left >= w.right || this.bottom >= w.top || this.top <= w.bottom) { // comparing
																											// the
																											// current
																											// window
																											// with the
																											// argument
																											// window
			return false; // if the argument window is not in the current window then its false
		}
		return true;
	}

	// counting
	public static int overlapCount(Window[] windows) {
		int count = 0; // counting how many times has overlapped
		for (int i = 0; i < windows.length; i++) { // go through every single element in the array
			for (int j = i + 1; j < windows.length; j++) { // comparing every element with each other without any order
				if (windows[i].overlaps(windows[j])) { // if the overlaps method gets true, counter increments
					count++;
				}
			}
		}
		return count;
	}

	public static int encloseCount(Window[] windows) {
		int count = 0; // counting how many times has enclosed
		for (int i = 0; i < windows.length; i++) { // go through every single element in the array
			for (int j = 0; j < windows.length; j++) { // comparing every element with each other in order
				if (i != j && windows[i].encloses(windows[j])) {// if the encloses method gets true, counter increments
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		boolean b;
		int result;
		System.out.println("-TEST_1- \n");
		Window a = new Window(1, 2, 3, 4);
		b = a.getLeft() == 1;
		System.out.println("Left Value is: " + a.getLeft());
		TestHelper.verify(b, "incorrect");
		b = a.getRight() == 2;
		System.out.println("Right Value is: " + a.getRight());
		TestHelper.verify(b, "incorrect");
		b = a.getBottom() == 3;
		System.out.println("Bottom Value is: " + a.getBottom());
		TestHelper.verify(b, "incorrect");
		b = a.getTop() == 4;
		System.out.println("Top Value is: " + a.getTop() + "\n");
		TestHelper.verify(b, "incorrect");

		System.out.println("-TEST_2- \n");
		a.set_x(2, 4);
		a.set_y(3, 11);

		b = a.getLeft() == 2;
		System.out.println("Left Value is: " + a.getLeft());
		TestHelper.verify(b, "incorrect");
		b = a.getRight() == 4;
		System.out.println("Right Value is: " + a.getRight());
		TestHelper.verify(b, "incorrect");
		b = a.getBottom() == 3;
		System.out.println("Bottom Value is: " + a.getBottom());
		TestHelper.verify(b, "incorrect");
		b = a.getTop() == 11;
		System.out.println("Top Value is: " + a.getTop() + "\n");
		TestHelper.verify(b, "incorrect");

		System.out.println("-TEST_3- \n");

		System.out.println("WINDOW 1");
		System.out.println("Left Value is: " + a.getLeft());
		System.out.println("Right Value is: " + a.getRight());
		System.out.println("Bottom Value is: " + a.getBottom());
		System.out.println("Top Value is: " + a.getTop() + "\n");

		Window l = new Window(3.01, 9.11, 3.14, 12);
		System.out.println("WINDOW 2");
		b = l.getLeft() == 3.01;
		System.out.println("Left Value is: " + l.getLeft());
		TestHelper.verify(b, "incorrect");
		b = l.getRight() == 9.11;
		System.out.println("Right Value is: " + l.getRight());
		TestHelper.verify(b, "incorrect");
		b = l.getBottom() == 3.14;
		System.out.println("Bottom Value is: " + l.getBottom());
		TestHelper.verify(b, "incorrect");
		b = l.getTop() == 12;
		System.out.println("Top Value is: " + l.getTop() + "\n");
		TestHelper.verify(b, "incorrect");

		System.out.println("Window 1 encloses onto Window 2: " + a.encloses(l));
		TestHelper.verify(a.encloses(l) == false, "incorrect");
		System.out.println("Window 2 encloses onto Window 1: " + l.encloses(a) + "\n");
		TestHelper.verify(l.encloses(a) == false, "incorrect");

		System.out.println("Window 1 overlaps onto Window 2: " + a.overlaps(l));
		TestHelper.verify(a.overlaps(l) == true, "incorrect");
		System.out.println("Window 2 overlaps onto Window 1: " + l.overlaps(a) + "\n");
		TestHelper.verify(l.overlaps(a) == true, "incorrect");

		System.out.println("-TEST_4- \n");
		System.out.println("WINDOW 1");
		System.out.println("Left Value is: " + a.getLeft());
		System.out.println("Right Value is: " + a.getRight());
		System.out.println("Bottom Value is: " + a.getBottom());
		System.out.println("Top Value is: " + a.getTop() + "\n");

		System.out.println("WINDOW 2");
		System.out.println("Left Value is: " + l.getLeft());
		TestHelper.verify(b, "incorrect");
		System.out.println("Right Value is: " + l.getRight());
		TestHelper.verify(b, "incorrect");
		System.out.println("Bottom Value is: " + l.getBottom());
		TestHelper.verify(b, "incorrect");
		System.out.println("Top Value is: " + l.getTop() + "\n");

		Window m = new Window(10, 11, -10, 7);
		System.out.println("WINDOW 3");
		b = m.getLeft() == 10;
		System.out.println("Left Value is: " + m.getLeft());
		TestHelper.verify(b, "incorrect");
		b = m.getRight() == 11;
		System.out.println("Right Value is: " + m.getRight());
		TestHelper.verify(b, "incorrect");
		b = m.getBottom() == -10;
		System.out.println("Bottom Value is: " + m.getBottom());
		TestHelper.verify(b, "incorrect");
		b = m.getTop() == 7;
		System.out.println("Top Value is: " + m.getTop() + "\n");
		TestHelper.verify(b, "incorrect");

		Window n = new Window(10.1, 10.5, 1, 6);
		System.out.println("WINDOW 4");
		b = n.getLeft() == 10.1;
		System.out.println("Left Value is: " + n.getLeft());
		TestHelper.verify(b, "incorrect");
		b = n.getRight() == 10.5;
		System.out.println("Right Value is: " + n.getRight());
		TestHelper.verify(b, "incorrect");
		b = n.getBottom() == 1;
		System.out.println("Bottom Value is: " + n.getBottom());
		TestHelper.verify(b, "incorrect");
		b = n.getTop() == 6;
		System.out.println("Top Value is: " + n.getTop() + "\n");
		TestHelper.verify(b, "incorrect");

		System.out.println("Window 3 encloses onto Window 4: " + m.encloses(n));
		TestHelper.verify(m.encloses(n) == true, "incorrect");
		System.out.println("Window 4 encloses onto Window 3: " + n.encloses(m) + "\n");
		TestHelper.verify(n.encloses(m) == false, "incorrect");

		System.out.println("Window 3 overlaps onto Window 4: " + m.overlaps(n));
		TestHelper.verify(m.overlaps(n) == true, "incorrect");
		System.out.println("Window 4 overlaps onto Window 3: " + n.overlaps(m) + "\n");
		TestHelper.verify(n.overlaps(m) == true, "incorrect");

		System.out.println("-TEST_5- \n");
		Window[] y = new Window[] { a, l, m, n };
		System.out.println("Array: WINDOW 1, WINDOW 2, WINDOW 3, WINDOW 4");
		System.out.println("Number of encloses is " + encloseCount(y) + "\n");
		result = encloseCount(y);
		TestHelper.verify(result == 1, "incorrect");
		System.out.println("Number of overlaps is " + overlapCount(y) + "\n");
		result = overlapCount(y);
		TestHelper.verify(result == 2, "incorrect");

		System.out.println("-TEST_6- \n");

		Window a1 = new Window(12, 29, 1, 15);

		System.out.println("WINDOW 1");
		b = a1.getLeft() == 12;
		System.out.println("Left Value is " + a1.getLeft());
		TestHelper.verify(b, "incorrect");
		b = a1.getRight() == 29;
		System.out.println("Right Value is " + a1.getRight());
		TestHelper.verify(b, "incorrect");
		b = a1.getBottom() == 1;
		System.out.println("Bottom Value is " + a1.getBottom());
		TestHelper.verify(b, "incorrect");
		b = a1.getTop() == 15;
		System.out.println("Top Value is " + a1.getTop() + "\n");
		TestHelper.verify(b, "incorrect \n");

		try {
			a1 = new Window(15, 12, 30, 40);
		} catch (InvalidWindowException e) {
			b = true;
			System.out.println(e + "\n");
		}
		TestHelper.verify(b, "Should throw exception");

		System.out.println("-TEST_7- \n");

		Window a2 = new Window(12, 15, 30, 40);
		try {
			a2.set_x(30, 12);
			a2.set_y(1, 3);
		} catch (InvalidWindowException e) {
			b = true;
			System.out.println(e + "\n");
		}
		TestHelper.verify(b, "Should throw exception");

		try {
			a2.set_x(12, 30);
			a2.set_y(3, 1);
		} catch (InvalidWindowException e) {
			b = true;
			System.out.println(e + "\n");
		}
		TestHelper.verify(b, "Should throw exception");

		a2.set_x(12, 30);
		a2.set_y(1, 3);

		System.out.println("WINDOW 2");
		b = a2.getLeft() == 12;
		System.out.println("Left Value is " + a2.getLeft());
		TestHelper.verify(b, "incorrect");
		b = a2.getRight() == 30;
		System.out.println("Right Value is " + a2.getRight());
		TestHelper.verify(b, "incorrect");
		b = a2.getBottom() == 1;
		System.out.println("Bottom Value is " + a2.getBottom());
		TestHelper.verify(b, "incorrect");
		b = a2.getTop() == 3;
		System.out.println("Top Value is " + a2.getTop() + "\n");
		TestHelper.verify(b, "incorrect \n");

		System.out.println("-TEST_8- \n");

		Window a3 = new Window(-100, 3, -10, -1);
		System.out.println("WINDOW 3");
		b = a3.getLeft() == -100;
		System.out.println("Left Value is " + a3.getLeft());
		TestHelper.verify(b, "incorrect");
		b = a3.getRight() == 3;
		System.out.println("Right Value is " + a3.getRight());
		TestHelper.verify(b, "incorrect");
		b = a3.getBottom() == -10;
		System.out.println("Bottom Value is " + a3.getBottom());
		TestHelper.verify(b, "incorrect");
		b = a3.getTop() == -1;
		System.out.println("Top Value is " + a3.getTop() + "\n");
		TestHelper.verify(b, "incorrect \n");

		try {
			a3 = new Window(13, 21, 31, 1);
		} catch (InvalidWindowException e) {
			b = true;
			System.out.println(e + "\n");
		}
		Window a4 = new Window(13, 21, 1, 31);
		TestHelper.verify(b, "Should throw exception");
		System.out.println("WINDOW 4");
		b = a4.getLeft() == 13;
		System.out.println("Left Value is " + a4.getLeft());
		TestHelper.verify(b, "incorrect");
		b = a4.getRight() == 21;
		System.out.println("Right Value is " + a4.getRight());
		TestHelper.verify(b, "incorrect");
		b = a4.getBottom() == 1;
		System.out.println("Bottom Value is " + a4.getBottom());
		TestHelper.verify(b, "incorrect");
		b = a4.getTop() == 31;
		System.out.println("Top Value is " + a4.getTop() + "\n");
		TestHelper.verify(b, "incorrect \n");

		System.out.println("-TEST_9- \n");
		System.out.println("ENCLOSES WITH WINDOW 1 AND WINDOW 2");
		System.out.print("WINDOW 1 LEFT: " + a1.getLeft());
		System.out.print(" || WINDOW 2 LEFT: " + a2.getLeft() + "\n");
		System.out.print("WINDOW 1 RIGHT: " + a1.getRight());
		System.out.print(" || WINDOW 2 RIGHT: " + a2.getRight() + "\n");
		System.out.print("WINDOW 1 BOTTOM: " + a1.getBottom());
		System.out.print(" || WINDOW 2 BOTTOM: " + a2.getBottom() + "\n");
		System.out.print("WINDOW 1 TOP: " + a1.getTop());
		System.out.print(" || WINDOW 2 TOP: " + a2.getTop() + "\n");
		b = a1.encloses(a2);
		if (b) {
			TestHelper.verify(b == false, "incorrect");
		} else {
			System.out.println("\nWINDOW 2 DOES NOT ENCLOSE WITH WINDOW 1");
		}
		b = a1.overlaps(a2);
		if (b) {
			System.out.println("WINDOW 2 DOES OVERLAP WITH WINDOW 1 \n");
		} else {
			TestHelper.verify(b == true, "incorrect");
		}

		System.out.println("-TEST_10- \n");
		System.out.println("ENCLOSES WITH WINDOW 1 AND WINDOW 3");
		System.out.print("WINDOW 1 LEFT: " + a1.getLeft());
		System.out.print(" || WINDOW 3 LEFT: " + a3.getLeft() + "\n");
		System.out.print("WINDOW 1 RIGHT: " + a1.getRight());
		System.out.print(" || WINDOW 3 RIGHT: " + a3.getRight() + "\n");
		System.out.print("WINDOW 1 BOTTOM: " + a1.getBottom());
		System.out.print(" || WINDOW 3 BOTTOM: " + a3.getBottom() + "\n");
		System.out.print("WINDOW 1 TOP: " + a1.getTop());
		System.out.print(" || WINDOW 3 TOP: " + a3.getTop() + "\n");
		b = a1.encloses(a3);
		if (b) {
			TestHelper.verify(b == false, "incorrect");
		} else {
			System.out.println("\nWINDOW 3 DOES NOT ENCLOSE WITH WINDOW 1");
		}
		b = a1.overlaps(a3);
		if (b) {
			TestHelper.verify(b == false, "incorrect");
		} else {
			System.out.println("WINDOW 3 DOES NOT OVERLAP WITH WINDOW 1 \n");
		}

		System.out.println("-TEST_11- \n");
		System.out.println("ENCLOSES WITH WINDOW 1 AND WINDOW 4");
		System.out.print("WINDOW 1 LEFT: " + a1.getLeft());
		System.out.print(" || WINDOW 4 LEFT: " + a4.getLeft() + "\n");
		System.out.print("WINDOW 1 RIGHT: " + a1.getRight());
		System.out.print(" || WINDOW 4 RIGHT: " + a4.getRight() + "\n");
		System.out.print("WINDOW 1 BOTTOM: " + a1.getBottom());
		System.out.print(" || WINDOW 4 BOTTOM: " + a4.getBottom() + "\n");
		System.out.print("WINDOW 1 TOP: " + a1.getTop());
		System.out.print(" || WINDOW 4 TOP: " + a4.getTop() + "\n");
		b = a1.encloses(a4);
		if (b) {
			TestHelper.verify(b == false, "incorrect");
		} else {
			System.out.println("\nWINDOW 4 DOES NOT ENCLOSE WITH WINDOW 1");
		}
		b = a1.overlaps(a4);
		if (b) {
			System.out.println("WINDOW 4 DOES OVERLAP WITH WINDOW 1 \n");
		} else {
			TestHelper.verify(b == true, "incorrect");
		}

		System.out.println("-TEST_12- \n");
		System.out.println("ENCLOSES WITH WINDOW 2 AND WINDOW 3");
		System.out.print("WINDOW 2 LEFT: " + a2.getLeft());
		System.out.print(" || WINDOW 3 LEFT: " + a3.getLeft() + "\n");
		System.out.print("WINDOW 2 RIGHT: " + a2.getRight());
		System.out.print(" || WINDOW 3 RIGHT: " + a3.getRight() + "\n");
		System.out.print("WINDOW 2 BOTTOM: " + a2.getBottom());
		System.out.print(" || WINDOW 3 BOTTOM: " + a3.getBottom() + "\n");
		System.out.print("WINDOW 2 TOP: " + a2.getTop());
		System.out.print(" || WINDOW 3 TOP: " + a3.getTop() + "\n");
		b = a2.encloses(a3);
		if (b) {
			TestHelper.verify(b == false, "incorrect");
		} else {
			System.out.println("\nWINDOW 3 DOES NOT ENCLOSE WITH WINDOW 2 \n");
		}
		b = a2.overlaps(a3);
		if (b) {
			TestHelper.verify(b == false, "incorrect");
		} else {
			System.out.println("WINDOW 3 DOES NOT OVERLAP WITH WINDOW 2 \n");
		}

		System.out.println("-TEST_13- \n");
		System.out.println("ENCLOSES WITH WINDOW 2 AND WINDOW 4");
		System.out.print("WINDOW 2 LEFT: " + a2.getLeft());
		System.out.print(" || WINDOW 4 LEFT: " + a4.getLeft() + "\n");
		System.out.print("WINDOW 2 RIGHT: " + a2.getRight());
		System.out.print(" || WINDOW 4 RIGHT: " + a4.getRight() + "\n");
		System.out.print("WINDOW 2 BOTTOM: " + a2.getBottom());
		System.out.print(" || WINDOW 4 BOTTOM: " + a4.getBottom() + "\n");
		System.out.print("WINDOW 2 TOP: " + a2.getTop());
		System.out.print(" || WINDOW 4 TOP: " + a4.getTop() + "\n");
		b = a2.encloses(a4);
		if (b) {
			TestHelper.verify(b == false, "incorrect");
		} else {
			System.out.println("\nWINDOW 4 DOES NOT ENCLOSE WITH WINDOW 2 \n");
		}
		b = a2.overlaps(a4);
		if (b) {
			System.out.println("WINDOW 4 DOES OVERLAP WITH WINDOW 2 \n");
		} else {
			TestHelper.verify(b == true, "incorrect");
		}

		System.out.println("-TEST_14- \n");
		System.out.println("ENCLOSES WITH WINDOW 3 AND WINDOW 4");
		System.out.print("WINDOW 3 LEFT: " + a3.getLeft());
		System.out.print(" || WINDOW 4 LEFT: " + a4.getLeft() + "\n");
		System.out.print("WINDOW 3 RIGHT: " + a3.getRight());
		System.out.print(" || WINDOW 4 RIGHT: " + a4.getRight() + "\n");
		System.out.print("WINDOW 3 BOTTOM: " + a3.getBottom());
		System.out.print(" || WINDOW 4 BOTTOM: " + a4.getBottom() + "\n");
		System.out.print("WINDOW 3 TOP: " + a3.getTop());
		System.out.print(" || WINDOW 4 TOP: " + a4.getTop() + "\n");
		b = a3.encloses(a4);
		if (b) {
			TestHelper.verify(b == false, "incorrect");
		} else {
			System.out.println("\nWINDOW 4 DOES NOT ENCLOSE WITH WINDOW 3 \n");
		}
		b = a3.overlaps(a4);
		if (b) {
			TestHelper.verify(b == false, "incorrect");
		} else {
			System.out.println("WINDOW 4 DOES OVERLAP WITH WINDOW 3 \n");
		}

		System.out.println("-TEST_15- \n");
		Window[] c = new Window[] { a1, a2, a3, a4 };
		result = encloseCount(c);
		System.out.println("Number of Encloses is " + result);
		TestHelper.verify(result == 0, "incorrect");
		result = overlapCount(c);
		System.out.println("Number of Overlaps is " + result);
		TestHelper.verify(result == 3, "incorrect");
	}

}
