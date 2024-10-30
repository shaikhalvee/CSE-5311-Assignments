## Question

Implement a hash table  in Java with following properties and sub-properties:

- Use the multiplication AND division method for your hash function
  - Note your code should be generic enough to allow for ANY hash function
- For simplicity assume your keys are integers and the values (data) are integers
- Use collision resolution by chaining
  - Use a doubly linked list, and you must write your own (so for example you can't use "list" in C++)
- You are only allowed to use C-style array's for this implementation (so for example no C++ vectors)
- Your Hash table should grow and shrink
- When it's full double the array size and re-hash everything
- When it's becoming empty e.g. 1/4 empty, then half the size of the array and re-hash everything.

## Answer

Here is a complete implementation of a hash table in Java that follows the specified requirements. 

### Node.java
Abstract structure of Node of the hash table implementation in [Node.java](Node.java)

```java
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
```

### Bucket.java
Abstract implementation for holding the bucket (for chaining) implemented in [Bucket.java](Bucket.java)
```java
// A bucket that contains a doubly linked list
public class Bucket {
	Node head;
	Node tail;

	Bucket() {
		this.head = null;
		this.tail = null;
	}

	// Add a AVLNode at the end of the doubly linked list (chaining)
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

	// Find AVLNode with the given key
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

	// Remove a AVLNode with the given key
	public void remove(int key) {
		Node AVLNode = find(key);
		if (AVLNode != null) {
			if (AVLNode == head) {
				head = head.next;
			}
			if (AVLNode == tail) {
				tail = tail.prev;
			}
			if (AVLNode.prev != null) {
				AVLNode.prev.next = AVLNode.next;
			}
			if (AVLNode.next != null) {
				AVLNode.next.prev = AVLNode.prev;
			}
		}
	}
}

```

### HashTable.java
Implemented in [HashTable.java](HashTable.java)
```java
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
		Node AVLNode = table[index].find(key);

		if (AVLNode == null) {
			table[index].addNode(key, value);
			size++;

			// Resize if load factor exceeds threshold (2/3 full)
			if (size >= (capacity * 2) / 3) {
				resize(capacity * 2);
			}
		} else {
			// Update value if key already exists
			AVLNode.value = value;
		}
	}

	// Get a value by key
	public Integer get(int key) {
		int index = hashFunction(key);
		Node AVLNode = table[index].find(key);
		if (AVLNode != null) {
			return AVLNode.value;
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
```



### Key Components:

1. **Node Class**: Represents a AVLNode in a doubly linked list, which stores a key-value pair.

2. **Bucket Class**: Represents a bucket in the hash table. Each bucket is a doubly linked list where collisions are handled by chaining.

3. **HashTable Class**:
    - **hashFunction()**: Implements the multiplication and division method. It uses the fractional part of multiplying the key by a constant derived from the golden ratio.
    - **insert()**: Inserts a key-value pair, resolves collisions by chaining, and resizes the table when it becomes full.
    - **get()**: Retrieves the value for a given key.
    - **remove()**: Removes a key-value pair, and resizes the table if it becomes sparse.
    - **resize()**: Resizes the hash table when needed, either growing (doubling) or shrinking (halving) the capacity and rehashing the elements.

### Example:

1. Insert the following keys and values:
   ```
   (10, 100), (22, 200), (31, 300), (4, 400), (15, 500), (28, 600), (17, 700)
   ```

2. Retrieve values:
   ```
   get(22) -> 200
   get(15) -> 500
   ```

3. Remove key:
   ```
   remove(31)
   ```

4. Resize automatically:
    - The hash table doubles in size when it exceeds 2/3 capacity.
    - It shrinks when the load factor drops below 1/4.

This implementation follows the requirements to avoid built-in Java collections and uses manual array-based management of the hash table and linked lists.
