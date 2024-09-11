package algorithm;

public class MergeSort implements Sort {

	public void doSort(int[] array) {
		if (array.length < 2) return;
		int middle = array.length / 2;
		int[] leftSubArray = new int[middle];
		int[] rightSubArray = new int[array.length - middle];
		System.arraycopy(array, 0, leftSubArray, 0, middle);
		System.arraycopy(array, middle, rightSubArray, 0, array.length - middle);
		doSort(leftSubArray);
		doSort(rightSubArray);
		merge(array, leftSubArray, rightSubArray);
	}

	private void merge(int[] destination, int[] leftSubArray, int[] rightSubArray) {
		int leftIndex = 0;
		int rightIndex = 0;
		int destinationIndex = 0;
		while (leftIndex < leftSubArray.length && rightIndex < rightSubArray.length) {
			if (leftSubArray[leftIndex] <= rightSubArray[rightIndex]) {
				destination[destinationIndex++] = leftSubArray[leftIndex++];
			} else {
				destination[destinationIndex++] = rightSubArray[rightIndex++];
			}
		}
		while (leftIndex < leftSubArray.length) {
			destination[destinationIndex++] = leftSubArray[leftIndex++];
		}
		while (rightIndex < rightSubArray.length) {
			destination[destinationIndex++] = rightSubArray[rightIndex++];
		}
	}
}
