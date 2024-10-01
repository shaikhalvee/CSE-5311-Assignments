package handson.handson6;

import java.util.Random;

public class QuickSort {

	// Non-Random Pivot Quicksort
	public void quickSortNonRandom(int[] arr, int low, int high) {
		if (low < high) {
			int pivotIndex = partition(arr, low, high);
			quickSortNonRandom(arr, low, pivotIndex - 1);
			quickSortNonRandom(arr, pivotIndex + 1, high);
		}
	}

	// Random Pivot Quicksort
	public void quickSortRandom(int[] arr, int low, int high) {
		if (low < high) {
			int pivotIndex = partitionRandom(arr, low, high);
			quickSortRandom(arr, low, pivotIndex - 1);
			quickSortRandom(arr, pivotIndex + 1, high);
		}
	}

	// Partition function for non-random pivot (choosing last element as pivot)
	private int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (arr[j] <= pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return i + 1;
	}

	// Partition function for random pivot
	private int partitionRandom(int[] arr, int low, int high) {
		int pivotIndex = new Random().nextInt(high - low + 1) + low;
		swap(arr, pivotIndex, high);
		return partition(arr, low, high);
	}

	// Utility function to swap elements
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}

