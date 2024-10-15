package handson.handson8;

public class Queue {
	private int[] arr;
	private int front, rear, maxSize, currentSize;

	// Constructor to initialize queue
	public Queue(int size) {
		maxSize = size;
		arr = new int[maxSize];
		front = 0;
		rear = -1;
		currentSize = 0;
	}

	// Add an element to the queue
	public void enqueue(int value) {
		if (isFull()) {
			System.out.println("Queue Overflow");
			return;
		}
		rear = (rear + 1) % maxSize;
		arr[rear] = value;
		currentSize++;
	}

	// Remove and return the front element from the queue
	public int dequeue() {
		if (isEmpty()) {
			System.out.println("Queue Underflow");
			return -1;
		}
		int value = arr[front];
		front = (front + 1) % maxSize;
		currentSize--;
		return value;
	}

	// Peek at the front element without removing it
	public int peek() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return -1;
		}
		return arr[front];
	}

	// Check if the queue is full
	public boolean isFull() {
		return currentSize == maxSize;
	}

	// Check if the queue is empty
	public boolean isEmpty() {
		return currentSize == 0;
	}

	// Print all elements in the queue
	public void printQueue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return;
		}
		System.out.print("Queue: ");
		for (int i = 0; i < currentSize; i++) {
			System.out.print(arr[(front + i) % maxSize] + " ");
		}
		System.out.println();
	}
}
