package commons;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CommonMethods {
	public static <T> void swapValues(Class<T> a, Class<T> b) {
		Class<T> temp = a;
		a = b;
		b = temp;
	}

	public static int[] generateRandomArray(int size) {
		Random random = new Random();
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = random.nextInt(10000); // random numbers between 0 and 9999
		}
		return array;
	}

	public static void writeOutputInFile(HashMap<String, ArrayList<Pair<Integer, Long>>> timeTakenMatrix,
	                                     String[] sortingAlgoList) {
		File outputFile = new File("output_runtime.csv");
		try {
			boolean newFile = outputFile.createNewFile();
			if (!newFile) {
				outputFile.delete();
				outputFile.createNewFile();
			}
			PrintWriter printWriter = new PrintWriter(outputFile);

			// write column name
			String[] dataToWrite = {"Sorting Algorithm", "Array Size", "Time Taken in NanoSecond"};
			for (int i = 0; i < dataToWrite.length; i++) {
				printWriter.print(dataToWrite[i]);
				if (i != dataToWrite.length - 1) {
					printWriter.print(",");
				}
			}
			printWriter.println();

			// write the data
			for (String algorithm : sortingAlgoList) {
				ArrayList<Pair<Integer, Long>> dataMatrixForEachAlgorithm = timeTakenMatrix.get(algorithm);
				for (Pair<Integer, Long> dataForCurrentAlgorithm : dataMatrixForEachAlgorithm) {
					printWriter.println(algorithm
							+ ","
							+ dataForCurrentAlgorithm.getKey()
							+ ","
							+ dataForCurrentAlgorithm.getValue()
					);
				}
			}
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


