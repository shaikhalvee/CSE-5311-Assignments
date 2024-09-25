package handson.hanson5;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<T>> {
	private ArrayList<T> heap;

	public MinHeap() {
		this.heap = new ArrayList<>();
	}

	private int parent(int index) {
		// Using bit manipulation to get the parent index: (i - 1) / 2
		return (index - 1) >> 1;
	}

	private int leftChild(int index) {
		// Using bit manipulation to get the left child index: 2 * i + 1
		return (index << 1) + 1;
	}

	private int rightChild(int index) {
		// Using bit manipulation to get the right child index: 2 * i + 2
		return (index << 1) + 2;
	}

	private void heapifyDown(int index) {
		int smallest = index;
		int left = leftChild(index);
		int right = rightChild(index);

		// Check if left child exists and is smaller than the current node
		if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
			smallest = left;
		}

		// Check if right child exists and is smaller than the current node
		if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
			smallest = right;
		}

		// Swap and continue heapifying if root is not the smallest
		if (smallest != index) {
			swap(index, smallest);
			heapifyDown(smallest);
		}
	}

	private void heapifyUp(int index) {
		int parentIndex = parent(index);

		// Compare the current node with its parent and swap if necessary
		if (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
			swap(index, parentIndex);
			heapifyUp(parentIndex);
		}
	}

	public void buildMinHeap(ArrayList<T> elements) {
		this.heap = new ArrayList<>(elements);
		// Build the heap by heapifying each non-leaf node
		for (int i = heap.size() / 2 - 1; i >= 0; i--) {
			heapifyDown(i);
		}
	}

	private void swap(int i, int j) {
		T temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}


	public void push(T element) {
		// Add the new element at the end of the heap
		heap.add(element);
		// Heapify up to maintain the min-heap property
		heapifyUp(heap.size() - 1);
	}
	public T pop() {
		if (heap.isEmpty()) {
			throw new NoSuchElementException("Pop from an empty heap");
		}

		// Swap the root with the last element
		T root = heap.get(0);
		heap.set(0, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);

		// Heapify down to maintain the min-heap property
		if (!heap.isEmpty()) {
			heapifyDown(0);
		}

		return root;
	}

	public T peek() {
		if (heap.isEmpty()) {
			throw new NoSuchElementException("Peek from an empty heap");
		}
		return heap.get(0);
	}

	public void printHeap() {
		System.out.println(heap);
	}
}
