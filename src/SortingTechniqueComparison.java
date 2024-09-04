import algorithm.BubbleSort;
import algorithm.InsertionSort;
import algorithm.SelectionSort;
import algorithm.Sort;
import commons.Pair;

public class SortingTechniqueComparison {
	public static long benchmarkSort(int[] array, Sort sort) {
		long startTime = System.nanoTime();

		if (sort instanceof InsertionSort) {
			sort.doSort(array);
		} else if (sort instanceof SelectionSort) {
			sort.doSort(array);
		} else if (sort instanceof BubbleSort) {
			sort.doSort(array);
		}

		long endTime = System.nanoTime();
		// convert to milliseconds
		return (endTime - startTime) / 1000000;
	}

	Pair<Integer, Integer> pair = new Pair<Integer, Integer>(-1, -1);
}
