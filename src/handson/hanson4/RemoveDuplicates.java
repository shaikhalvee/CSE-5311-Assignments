package handson.hanson4;
import java.util.Arrays;

public class RemoveDuplicates {

	public static int[] removeDuplicates(int[] array) {
		if (array == null || array.length == 0) {
			return new int[0];  // Return empty array if input is null or empty
		}

		// Pointer for the last unique element
		int lastUnique = 0;

		for (int i = 1; i < array.length; i++) {
			if (array[i] != array[lastUnique]) {
				lastUnique++;
				array[lastUnique] = array[i];
			}
		}

		// Return a copy of the array containing only unique elements
		return Arrays.copyOf(array, lastUnique + 1);
	}

	public static void main(String[] args) {
		int[] array = {1, 2, 2, 3, 4, 4, 4, 5, 5};

		int[] result = removeDuplicates(array);
		System.out.println(Arrays.toString(result));  // Output: [1, 2, 3, 4, 5]
	}
}
