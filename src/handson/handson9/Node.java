package handson.handson9;

// Node for doubly linked list (for chaining)
public class Node {
	int key;
	int value;
	Node next;
	Node prev;

	Node(int key, int value) {
		this.key = key;
		this.value = value;
		this.next = null;
		this.prev = null;
	}
}
