package commons;

import java.util.Random;

public class CommonMethods {
	public static void swapValues(Integer a, Integer b) {
		Integer temp = a;
		a = b;
		b = temp;
	}

	public static int[] generateRandomArray(int size) {
		Random random = new Random();
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = random.nextInt(10000); // random numbers between 0 and 9999
		}
		return array;
	}
}


