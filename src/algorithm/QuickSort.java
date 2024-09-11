package algorithm;

public class QuickSort implements Sort {

	public void doSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	private void quickSort(int[] array, int left, int right) {
		int pivotIndex = left / 2 + right / 2;
		int pivot = array[pivotIndex];
		int leftIndex = left;
		int rightIndex = right;
		int swap;
		while (leftIndex <= rightIndex) {
			while (leftIndex < array.length && array[leftIndex] < pivot) {
				leftIndex++;
			}
			while (rightIndex > 0 && array[rightIndex] > pivot) {
				rightIndex--;
			}
			if (leftIndex <= rightIndex) {
				swap = array[leftIndex];
				array[leftIndex] = array[rightIndex];
				array[rightIndex] = swap;
				leftIndex++;
				rightIndex--;
			}
		}
		if (left < rightIndex) {
			quickSort(array, left, rightIndex);
		}
		if (leftIndex < right) {
			quickSort(array, leftIndex, right);
		}
	}
}
