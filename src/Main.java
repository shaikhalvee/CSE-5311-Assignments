import algorithm.BubbleSort;
import algorithm.InsertionSort;
import algorithm.SelectionSort;
import algorithm.Sort;
import commons.Pair;
import commons.SortingTechniqueComparison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

import static commons.CommonMethods.writeOutputInFile;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
	public static void main(String[] args) {
		String[] sortingAlgoList = {"Insertion Sort", "Selection Sort", "Bubble Sort"};
		Sort[] sortingAlgorithms = {new InsertionSort(), new SelectionSort(), new BubbleSort()};
//		String[] inputTypes = {"Normal", "Sorted"};
		int[] ranges = {5, 10, 100, 1000, 2000, 4000, 5000, 8000, 10000, 20000,
				30000, 40000, 50000, 80000, 100000};
		int[][] inputArrays = new int[ranges.length][];
		for (int i = 0; i < inputArrays.length; i++) {
			inputArrays[i] = new int[ranges[i]];
			inputArrays[i] = IntStream.generate(
					() -> new Random().nextInt()
			).limit(ranges[i]).toArray();
		}
		HashMap<String, ArrayList<Pair<Integer, Long>>> timeTaken = SortingTechniqueComparison
				.calculateRuntimes(inputArrays, sortingAlgoList, sortingAlgorithms);
		writeOutputInFile(timeTaken, sortingAlgoList);
	}
}
