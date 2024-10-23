package handson.handson9;

// A bucket that contains a doubly linked list
public class Bucket {
	Node head;
	Node tail;

	Bucket() {
		this.head = null;
		this.tail = null;
	}

	// Add a node at the end of the doubly linked list (chaining)
	public void addNode(int key, int value) {
		Node newNode = new Node(key, value);
		if (head == null) {
			head = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
		}
		tail = newNode;
	}

	// Find node with the given key
	public Node find(int key) {
		Node current = head;
		while (current != null) {
			if (current.key == key) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	// Remove a node with the given key
	public void remove(int key) {
		Node node = find(key);
		if (node != null) {
			if (node == head) {
				head = head.next;
			}
			if (node == tail) {
				tail = tail.prev;
			}
			if (node.prev != null) {
				node.prev.next = node.next;
			}
			if (node.next != null) {
				node.next.prev = node.prev;
			}
		}
	}
}
