package handson.handson8;

public class SinglyLinkedList {
	private class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	private Node head;

	// Constructor
	public SinglyLinkedList() {
		head = null;
	}

	// Add a new node at the end
	public void add(int value) {
		Node newNode = new Node(value);
		if (head == null) {
			head = newNode;
		} else {
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
		}
	}

	// Remove a node with a specific value
	public void remove(int value) {
		if (head == null) {
			System.out.println("List is empty");
			return;
		}

		if (head.data == value) {
			head = head.next;
			return;
		}

		Node current = head;
		while (current.next != null && current.next.data != value) {
			current = current.next;
		}

		if (current.next == null) {
			System.out.println("Value not found in the list");
			return;
		}

		current.next = current.next.next;
	}

	// Print all elements in the list
	public void printList() {
		if (head == null) {
			System.out.println("List is empty");
			return;
		}

		Node current = head;
		System.out.print("LinkedList: ");
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println();
	}
}
