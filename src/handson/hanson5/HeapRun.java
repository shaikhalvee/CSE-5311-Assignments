package handson.hanson5;

import java.util.ArrayList;

public class HeapRun {
	public static void main(String[] args) {
		// Create a MinHeap instance
		MinHeap<Integer> minHeap = new MinHeap<>();

		// Build the heap with an array of elements
		ArrayList<Integer> elements = new ArrayList<>();
		elements.add(10);
		elements.add(3);
		elements.add(2);
		elements.add(7);
		elements.add(6);
		elements.add(5);
		elements.add(8);

		System.out.println("Building Min Heap from: " + elements);
		minHeap.buildMinHeap(elements);
		System.out.print("Heap after building: ");
		minHeap.printHeap();

		// Push new elements into the heap
		System.out.println("\nPushing 1 into the heap");
		minHeap.push(1);
		System.out.print("Heap after pushing: ");
		minHeap.printHeap();

		System.out.println("\nPushing 4 into the heap");
		minHeap.push(4);
		System.out.print("Heap after pushing: ");
		minHeap.printHeap();

		// Peek at the minimum element
		System.out.println("\nThe minimum element is: " + minHeap.peek());

		// Pop the root element from the heap
		System.out.println("\nPopping the minimum element: " + minHeap.pop());
		System.out.print("Heap after popping: ");
		minHeap.printHeap();

		System.out.println("\nPopping the minimum element: " + minHeap.pop());
		System.out.print("Heap after popping: ");
		minHeap.printHeap();
	}
}
