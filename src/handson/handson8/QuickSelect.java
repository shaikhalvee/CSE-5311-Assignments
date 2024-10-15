package handson.handson8;

public class QuickSelect {

	// Partition function used in Quickselect (similar to Quicksort partition)
	private static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];  // Choosing the last element as pivot
		int i = low - 1;        // Index of the smaller element

		for (int j = low; j < high; j++) {
			if (arr[j] <= pivot) {
				i++;
				// Swap arr[i] and arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// Swap arr[i+1] and arr[high] (the pivot element)
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;  // Returning the partition index
	}

	// Quickselect function to find the k-th smallest element
	public static int quickSelect(int[] arr, int low, int high, int k) {
		if (low <= high) {
			// Partition the array and get the position of the pivot
			int partitionIndex = partition(arr, low, high);

			// If the pivot is at the k-1 position, we found the k-th smallest element
			if (partitionIndex == k - 1) {
				return arr[partitionIndex];
			}
			// If the pivot is too far right, search the left part
			else if (partitionIndex > k - 1) {
				return quickSelect(arr, low, partitionIndex - 1, k);
			}
			// If the pivot is too far left, search the right part
			else {
				return quickSelect(arr, partitionIndex + 1, high, k);
			}
		}

		// This condition should never be hit in a valid array
		return Integer.MAX_VALUE;
	}

	public static void main(String[] args) {
		// Example array
		int[] arr = {12, 3, 5, 7, 19, 26, 15};
		int n = arr.length;
		int k = 4;  // We want to find the 4th smallest element (k-th order statistic)

		// Calling the quickSelect method
		int result = quickSelect(arr, 0, n - 1, k);

		// Display the result
		System.out.println("The " + k + "th smallest element is " + result);
	}
}
