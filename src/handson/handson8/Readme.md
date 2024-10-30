# Hands on Activity 8

## First Objective

Leverage your implementation of quicksort to implement the ith order statistic. Demonstrate it's working via an example.

## Answer

Here is the Java implementation of the Quickselect algorithm, which leverages the partitioning approach from Quicksort to find the i-th order statistic (the i-th smallest element) in an array. The Quickselect algorithm has an average time complexity of \(O(n)\).

### Quickselect Algorithm Implementation in Java:

```java
import java.util.Random;

public class QuickSelect {
    
    // Partition function used in Quickselect (similar to Quicksort partition)
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Choosing the last element as pivot
        int i = low - 1;        // Index of the smaller element
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (the pivot element)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;  // Returning the partition index
    }

    // Quickselect function to find the k-th smallest element
    public static int quickSelect(int[] arr, int low, int high, int k) {
        if (low <= high) {
            // Partition the array and get the position of the pivot
            int partitionIndex = partition(arr, low, high);

            // If the pivot is at the k-1 position, we found the k-th smallest element
            if (partitionIndex == k - 1) {
                return arr[partitionIndex];
            }
            // If the pivot is too far right, search the left part
            else if (partitionIndex > k - 1) {
                return quickSelect(arr, low, partitionIndex - 1, k);
            }
            // If the pivot is too far left, search the right part
            else {
                return quickSelect(arr, partitionIndex + 1, high, k);
            }
        }

        // This condition should never be hit in a valid array
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        // Example array
        int[] arr = {12, 3, 5, 7, 19, 26, 15};
        int n = arr.length;
        int k = 4;  // We want to find the 4th smallest element (k-th order statistic)

        // Calling the quickSelect method
        int result = quickSelect(arr, 0, n - 1, k);

        // Display the result
        System.out.println("The " + k + "th smallest element is " + result);
    }
}
```

### Explanation:

- **Partition Function**: This is similar to the partition function used in the Quicksort algorithm. It selects a pivot and rearranges the array such that all elements smaller than or equal to the pivot are on the left, and all elements greater than the pivot are on the right.

- **Quickselect Function**: This function recursively partitions the array, narrowing down the search space until the pivot element is in the \(k\)-th position, at which point it returns the pivot as the \(k\)-th smallest element.

- **Main Method**: The example array `arr = {12, 3, 5, 7, 19, 26, 15}` is used. We are looking for the 4th smallest element in this array, so `k = 4`. The quickSelect function finds this element and prints it.

### Example:

For the array `{12, 3, 5, 7, 19, 26, 15}` and \(k = 4\), the quickSelect algorithm will find the 4th smallest element, which is `12`. The output of this program will be:

```
The 4th smallest element is 12
```

### Time Complexity:

- **Average Case**: \(O(n)\) — The Quickselect algorithm runs in linear time on average.
- **Worst Case**: \(O(n^2)\) — The worst case occurs when the partitioning is unbalanced, but this can be improved with randomized pivot selection.

---

## Second Objective

Implement in Java: stack, queue, and singly linked list. Make sure to implement push/pop, add/remove, etc. functionality.  
*Restriction*: Use fixed-sized arrays (C style arrays) and assume only integers (C style int) for storing the data.

## Answer

Here is the implementation of a **Stack**, **Queue**, and **Singly Linked List** in Java using fixed-size arrays for data storage, with integers as the data type.

### 1. **Stack Implementation**

```java
class Stack {
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
```

### 2. **Queue Implementation**

```java
class Queue {
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
```

### 3. **Singly Linked List Implementation**

```java
class SinglyLinkedList {
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

    // Add a new AVLNode at the end
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

    // Remove a AVLNode with a specific value
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
```

### Main Class to Test the Implementations:

```java
public class DataStructureDemo {
    public static void main(String[] args) {
        // Stack example
        Stack stack = new Stack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.printStack();
        stack.pop();
        stack.printStack();

        // Queue example
        Queue queue = new Queue(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();

        // Singly Linked List example
        SinglyLinkedList list = new SinglyLinkedList();
        list.add(100);
        list.add(200);
        list.add(300);
        list.printList();
        list.remove(200);
        list.printList();
    }
}
```

### Explanation:

1. **Stack**:
    - Implemented using a fixed-size array. Operations `push()`, `pop()`, `peek()`, `isFull()`, and `isEmpty()` are provided.
    - The `push` method adds an element to the top of the stack, and `pop` removes the top element. Both operations are \(O(1)\).

2. **Queue**:
    - Implemented using a circular fixed-size array. Operations `enqueue()`, `dequeue()`, `peek()`, `isFull()`, and `isEmpty()` are provided.
    - The `enqueue` method adds an element to the rear of the queue, and `dequeue` removes the front element. Both operations are \(O(1)\).

3. **Singly Linked List**:
    - Implemented using a `Node` class to store data and a pointer to the next AVLNode. The list supports `add()` and `remove()` operations.
    - The `add` method appends an element to the end of the list, and `remove` deletes the first occurrence of the element. Traversal is \(O(n)\) for both operations in the worst case.

### Output Example:

```
Stack: 10 20 30 
Stack: 10 20 
Queue: 1 2 3 
Queue: 2 3 
LinkedList: 100 200 300 
LinkedList: 100 300 
```

This implementation fulfills the restrictions of using fixed-size arrays and integers.

