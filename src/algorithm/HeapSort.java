package algorithm;

import java.util.Collections;
import java.util.Vector;

public class HeapSort implements Sort {

	public Vector<Integer> heapArray;

	HeapSort() {
		this.heapArray = new Vector<>();
	}

	private void heapInsert(int num) {
		heapArray.add(num);
		int i = heapArray.size() - 1;
		while (i > 0 && heapArray.get(i / 2) < heapArray.get(i)) {
			Collections.swap(heapArray, i, i / 2);
			i /= 2;
		}
		// smallest value will always be at the root.
	}

	public void doSort(int[] array) {
		for (int num : array) {
			heapInsert(num);
		}
		for (int i = heapArray.size() - 1; i >= 0; i--) {
			Collections.swap(heapArray, 0, i);
			buildMinHeap(i, 0);
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = heapArray.get(i);
		}
		// clearing heapArray so that for the next sort, this DS has no data.
		heapArray.clear();
	}

	private void buildMinHeap(int heapLength, int index) {
		int rootIndex = index;
		int leftIndex = (index << 1) + 1;
		int rightIndex = (index << 1) + 2;
		if (leftIndex < heapLength && heapArray.get(leftIndex) > heapArray.get(rootIndex)) {
			rootIndex = leftIndex;
		}
		if (rightIndex < heapLength && heapArray.get(rightIndex) > heapArray.get(rootIndex)) {
			rootIndex = rightIndex;
		}
		if (rootIndex != index) {
			Collections.swap(heapArray, rootIndex, index);
			buildMinHeap(heapLength, rootIndex);
		}
	}
}
