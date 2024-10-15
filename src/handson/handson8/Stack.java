package handson.handson8;

public class Stack {
	private int[] arr;
	private int top;
	private int maxSize;

	// Constructor to initialize stack
	public Stack(int size) {
		maxSize = size;
		arr = new int[maxSize];
		top = -1;
	}

	// Push an element onto the stack
	public void push(int value) {
		if (isFull()) {
			System.out.println("Stack Overflow");
			return;
		}
		arr[++top] = value;
	}

	// Pop an element from the stack
	public int pop() {
		if (isEmpty()) {
			System.out.println("Stack Underflow");
			return -1;
		}
		return arr[top--];
	}

	// Peek at the top element of the stack without removing it
	public int peek() {
		if (isEmpty()) {
			System.out.println("Stack is empty");
			return -1;
		}
		return arr[top];
	}

	// Check if the stack is full
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// Check if the stack is empty
	public boolean isEmpty() {
		return top == -1;
	}

	// Print all elements in the stack
	public void printStack() {
		if (isEmpty()) {
			System.out.println("Stack is empty");
			return;
		}
		System.out.print("Stack: ");
		for (int i = 0; i <= top; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
