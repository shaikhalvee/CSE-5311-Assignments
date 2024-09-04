import algorithm.BubbleSort;
import algorithm.InsertionSort;
import algorithm.SelectionSort;
import algorithm.Sort;
import commons.Pair;

import java.util.ArrayList;
import java.util.HashMap;

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

	public static HashMap<String, HashMap<String, ArrayList<Pair<Integer, Long>>>>
	calculateRuntimes(int[][] inputArrays,
	                  String[] sortingAlgoList,
	                  Sort[] sortingAlgorithms) {
		// initialize strings
//		Sort[] sortingAlgorithms = {new InsertionSort(), new HeapSort(),
//				new MergeSort(), new ModifiedQuickSort(), new QuickSort()};
		// initialize runtime matrix
		HashMap<String, ArrayList<Pair<Integer, Long>>> runtimeMatrix = new HashMap<>();
		for (String sortingAlgo : sortingAlgoList) {
//			HashMap<String, ArrayList<Pair<Integer, Long>>> typeMatrix = new HashMap<>();
//			for (String inputType : inputTypes) {
//				typeMatrix.put(inputType, new ArrayList<>());
//			}
			runtimeMatrix.put(sortingAlgo, new ArrayList<>());
		}
		// do sorting & calculate runtime
		for (int i = 0; i < sortingAlgorithms.length; i++) {
			// input is copied for each algo so that same input is used for each algo
			int[][] arrayCopy = inputMatrixClone(inputArrays);
			// getting sorting type matrix before getting into it
			HashMap<String, ArrayList<Pair<Integer, Long>>> sortingTypeMap = runtimeMatrix.get(sortingAlgoList[i]);
			for (int j = 0; j < inputTypes.length; j++) {
				ArrayList<Pair<Integer, Long>> runtimeMap = sortingTypeMap.get(inputTypes[j]);
				for (int[] currentInputs : arrayCopy) {
					if (j == inputTypes.length - 1) {
						reverse(currentInputs);
					}
					Long startTime = System.nanoTime();
					sortingAlgorithms[i].doSort(currentInputs);
					Long endTime = System.nanoTime();
					Long totalTimeTaken = endTime - startTime;
					Pair<Integer, Long> value = new Pair<>(currentInputs.length, totalTimeTaken);
					runtimeMap.add(value);
				}
			}
		}
		return runtimeMatrix;
	}

	private static int[][] inputMatrixClone(int[][] inputArrays) {
		int[][] arrayCopy = new int[inputArrays.length][];
		for (int p = 0; p < inputArrays.length; p++) {
			arrayCopy[p] = new int[inputArrays[p].length];
			System.arraycopy(inputArrays[p], 0, arrayCopy[p], 0, inputArrays[p].length);
		}
		return arrayCopy;
	}
}
