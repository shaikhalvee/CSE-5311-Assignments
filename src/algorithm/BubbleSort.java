package algorithm;

public class BubbleSort implements Sort {
	@Override
	public void doSort(int[] array) {
		int length = array.length;
		boolean swapped;

		// Outer loop to traverse through all array elements
		for (int i = 0; i < length - 1; i++) {
			swapped = false;

			// Inner loop to compare adjacent elements
			for (int j = 0; j < length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					// Swap if the element found is greater than the next element
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;

					swapped = true;
				}
			}

			// If no two elements were swapped in the inner loop, the array is already sorted
			if (!swapped) break;
		}
	}
}
