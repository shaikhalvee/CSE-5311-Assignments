package handson.handson9;

import java.util.Arrays;

// The HashTable class
public class HashTable {
	private Bucket[] table;
	private int capacity;
	private int size;

	public HashTable(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.table = new Bucket[capacity];
		Arrays.fill(this.table, new Bucket());
	}

	// Customizable hash function
	private int hashFunction(int key) {
		// Combination of multiplication and division
		double A = (Math.sqrt(5) - 1) / 2;
		return (int) (capacity * ((key * A) % 1));
	}

	// Insert a key-value pair into the hash table
	public void insert(int key, int value) {
		int index = hashFunction(key);
		Node node = table[index].find(key);

		if (node == null) {
			table[index].addNode(key, value);
			size++;

			// Resize if load factor exceeds threshold (2/3 full)
			if (size >= (capacity * 2) / 3) {
				resize(capacity * 2);
			}
		} else {
			// Update value if key already exists
			node.value = value;
		}
	}

	// Get a value by key
	public Integer get(int key) {
		int index = hashFunction(key);
		Node node = table[index].find(key);
		if (node != null) {
			return node.value;
		}
		return null;
	}

	// Remove a key-value pair
	public void remove(int key) {
		int index = hashFunction(key);
		table[index].remove(key);
		size--;

		// Resize if load factor falls below threshold (1/4 full)
		if (size <= capacity / 4) {
			resize(capacity / 2);
		}
	}

	// Resize the hash table
	private void resize(int newCapacity) {
		Bucket[] oldTable = table;
		capacity = newCapacity;
		table = new Bucket[capacity];
		Arrays.fill(table, new Bucket());
		size = 0;

		// Re-hash all entries
		for (Bucket bucket : oldTable) {
			Node current = bucket.head;
			while (current != null) {
				insert(current.key, current.value);
				current = current.next;
			}
		}
	}

	// Get current size of the hash table
	public int size() {
		return size;
	}

	// Get the current capacity of the hash table
	public int capacity() {
		return capacity;
	}
}
