package handson.handson6;

import java.util.Random;

public class QuickSortBenchmark {
	public static void main(String[] args) {
		QuickSort quickSort = new QuickSort();
		int[] inputSizes = {1000, 2000, 4000, 8000, 16000}; // Example sizes

		// Benchmarking for best, worst, and average cases
		for (int n : inputSizes) {

			// Worst case: reverse sorted array
			int[] worstCase = generateReverseSortedArray(n);
			long worstStartTime = System.nanoTime();
			quickSort.quickSortNonRandom(worstCase, 0, worstCase.length - 1);
			long worstEndTime = System.nanoTime();
			System.out.println("Worst case time for n=" + n + ": " + (worstEndTime - worstStartTime) + " ns");

			// Best case: taking last index as pivot
			int[] bestCase = generateRandomArray(n);
			long bestStartTime = System.nanoTime();
			quickSort.quickSortNonRandom(bestCase, 0, bestCase.length - 1);
			long bestEndTime = System.nanoTime();
			System.out.println("Best case time for n=" + n + ": " + (bestEndTime - bestStartTime) + " ns");

			// Average case: random array and random pivot
			int[] averageCase = generateRandomArray(n);
			long avgStartTime = System.nanoTime();
			quickSort.quickSortRandom(averageCase, 0, averageCase.length - 1);
			long avgEndTime = System.nanoTime();
			System.out.println("Average case time for n=" + n + ": " + (avgEndTime - avgStartTime) + " ns");
		}
	}

	private static int[] generateReverseSortedArray(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = size - i;
		}
		return arr;
	}

	private static int[] generateRandomArray(int size) {
		Random rand = new Random();
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = rand.nextInt(size);
		}
		return arr;
	}
}

