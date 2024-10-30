package handson.handson10;

public class Simulation {
	public static void main(String[] args) {
		AVLTree avlTree = new AVLTree();
		avlTree.insert(10);
		avlTree.insert(20);
		avlTree.insert(30);
		avlTree.insert(25);

		System.out.print("Inorder traversal for AVL Tree: ");
		avlTree.inorder();

		RedBlackTree redBlackTree = new RedBlackTree();
		redBlackTree.insert(10);
		redBlackTree.insert(20);
		redBlackTree.insert(30);
		redBlackTree.insert(15);

		System.out.print("Inorder traversal for RedBlack Tree: ");
		redBlackTree.inorder();

		BST bstTree = new BST();
		bstTree.insert(10);
		bstTree.insert(5);
		bstTree.insert(15);
		bstTree.insert(2);
		bstTree.insert(7);

		System.out.print("Inorder traversal: ");
		bstTree.inorder();

		System.out.println("Search 5: " + bstTree.search(5));
		System.out.println("Search 12: " + bstTree.search(12));

		bstTree.delete(5);
		System.out.print("Inorder after deleting 5: ");
		bstTree.inorder();
	}
}
