package handson.handson9;

public class HashTableSimulation {
	public static void main(String[] args) {
		HashTable hashTable = new HashTable(4);

		// Insert values
		hashTable.insert(10, 100);
		hashTable.insert(22, 200);
		hashTable.insert(31, 300);
		hashTable.insert(4, 400);
		hashTable.insert(15, 500);
		hashTable.insert(28, 600);
		hashTable.insert(17, 700);

		// Retrieve values
		System.out.println("Value for key 22: " + hashTable.get(22));  // Output: 200
		System.out.println("Value for key 15: " + hashTable.get(15));  // Output: 500

		// Remove a key
		hashTable.remove(31);

		// Check size and capacity
		System.out.println("Current size: " + hashTable.size());        // Output: 6
		System.out.println("Current capacity: " + hashTable.capacity()); // Output: 8 (doubled capacity)
	}
}
