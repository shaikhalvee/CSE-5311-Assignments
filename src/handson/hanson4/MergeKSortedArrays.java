package handson.hanson4;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

	// Custom class to store elements along with the array index and element index
	static class ArrayEntry implements Comparable<ArrayEntry> {
		int value;
		int arrayIndex;
		int elementIndex;

		public ArrayEntry(int value, int arrayIndex, int elementIndex) {
			this.value = value;
			this.arrayIndex = arrayIndex;
			this.elementIndex = elementIndex;
		}

		// Override compareTo to make the priority queue a min-heap
		@Override
		public int compareTo(ArrayEntry other) {
			return Integer.compare(this.value, other.value);
		}
	}

	public static int[] mergeKSortedArrays(int[][] arrays) {
		PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>();
		int totalSize = 0;

		// Push the first element of each array along with array index and element index
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].length > 0) {
				minHeap.offer(new ArrayEntry(arrays[i][0], i, 0));
				totalSize += arrays[i].length;  // Calculate total size of the merged array
			}
		}

		int[] mergedArray = new int[totalSize];
		int index = 0;

		// Continue extracting minimum elements and pushing the next elements of that array
		while (!minHeap.isEmpty()) {
			ArrayEntry entry = minHeap.poll();
			mergedArray[index++] = entry.value;

			// Push the next element of the same array into the heap
			if (entry.elementIndex + 1 < arrays[entry.arrayIndex].length) {
				int nextValue = arrays[entry.arrayIndex][entry.elementIndex + 1];
				minHeap.offer(new ArrayEntry(nextValue, entry.arrayIndex, entry.elementIndex + 1));
			}
		}

		return mergedArray;
	}

	public static void main(String[] args) {
		int[][] arrays = {
				{1, 3, 5, 7},
				{2, 4, 6, 8},
				{0, 9, 10, 11}
		};

		int[] result = mergeKSortedArrays(arrays);
		for (int val : result) {
			System.out.print(val + " ");
		}
	}
}

