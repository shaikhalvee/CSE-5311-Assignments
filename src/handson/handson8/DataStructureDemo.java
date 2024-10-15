package handson.handson8;

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
