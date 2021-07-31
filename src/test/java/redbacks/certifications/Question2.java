package redbacks.certifications;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Question2 {
	/*
	 * Modify this function to return an array of integers from 1 to 10
	 */
	int[] numbers() {
		return null;
	}

	/*
	 * Create a function 'printEvens' which accepts an array of integers as input and prints all even values.
	 * e.g. if the array was [3, 1, 4, 1], it would just print 4.
	 */
	// Write your code here

	/*
	 * Create a function 'sum' which accepts an array of doubles as input and returns the sum of all values
	 */
	// Write your code here

	/*
	 * Create a function 'average' which takes an array of doubles as input, and returns the average.
	 * You should call your 'sum' function as part of this answer.
	 * 
	 * You can assume that the array will contain at least one value.
	 */
	// Write your code here

	/*
	 * Optional challenge: Create a function 'atIndexes' which takes an array of doubles and an array of integers as inputs.
	 * The function should return a new array of doubles, where each double is the value at the position in the first array specified by each value in the second.
	 * 
	 * e.g. For inputs [3.1, 4.1, -5.9, 2.6, -5.3] and [3, 1, 4, 1], the result should be [2.6, 4.1, -5.3, 4.1].
	 * 
	 * You can assume the array of integers will be valid positions in the first array.
	 */
	// Write your code here

	/*
	 * Testing code, do not modify
	 */
	ByteArrayOutputStream bytes;
	PrintStream stdOut, newOut;

	@BeforeEach
	void beforeEach() {
		bytes = new ByteArrayOutputStream();
		newOut = new PrintStream(bytes);
		stdOut = System.out;

		System.setOut(newOut);
	}

	@AfterEach
	void afterEach() {
		newOut.close();
		System.setOut(stdOut);
	}

	@Test
	void testNumbers() {
		int[] numbers = numbers();

		for(int i = 0; i < numbers.length; i++) {
			assertEquals(i + 1, numbers[i]);
		}
	}

	@Test
	void testPrintEvens() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = getClass().getDeclaredMethod("printEvens", int[].class);
		assertEquals(Void.TYPE, method.getReturnType());

		method.invoke(this, new int[] {2, 7, 1, 8, 2, 8, 4, 6, 1, 3});
		assertEquals(String.format("2%n8%n2%n8%n4%n6%n"), bytes.toString());
		bytes.reset();

		method.invoke(this, new int[] {1});
		assertEquals(String.format(""), bytes.toString());
		bytes.reset();

		method.invoke(this, new int[0]);
		assertEquals(String.format(""), bytes.toString());
		bytes.reset();
	}

	@Test
	void testSum() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = getClass().getDeclaredMethod("sum", double[].class);
		assertEquals(Double.TYPE, method.getReturnType());

		assertEquals(4.6 + 1.3 - 1.7 - 7.2, (double)method.invoke(this, new double[] {4.6, 1.3, -1.7, -7.2}), 1e-6);
		assertEquals(0d, method.invoke(this, new double[0]));
	}

	@Test
	void testAverage() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = getClass().getDeclaredMethod("average", double[].class);
		assertEquals(Double.TYPE, method.getReturnType());

		assertEquals((4.6 + 1.3 - 1.7 - 7.2) / 4, (double)method.invoke(this, new double[] {4.6, 1.3, -1.7, -7.2}), 1e-6);
		assertEquals(-4613d, method.invoke(this, new double[] {-4613}));
	}

	@Test
	void testAtIndexes() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = getClass().getDeclaredMethod("atIndexes", double[].class, int[].class);
		assertEquals(double[].class, method.getReturnType());

		assertArrayEquals(new double[] {2.6, 4.1, -5.3, 4.1}, (double[])method.invoke(this, new double[] {3.1, 4.1, -5.9, 2.6, -5.3}, new int[] {3, 1, 4, 1}));
		assertArrayEquals(new double[0], (double[])method.invoke(this, new double[] {3.1, 4.1, -5.9, 2.6, -5.3}, new int[0]));
	}
}
