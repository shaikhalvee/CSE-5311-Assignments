package algorithm;

public class SelectionSort implements Sort {

	@Override
	public void doSort(int[] array) {
		int len = array.length;

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < len - 1; i++) {
			// Find the minimum element in the unsorted array
			int minIndex = i;
			for (int j = i + 1; j < len; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}

			// Swap the found minimum element with the first element
			int temp = array[minIndex];
			array[minIndex] = array[i];
			array[i] = temp;
		}
	}
}
