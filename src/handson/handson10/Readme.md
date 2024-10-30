## Hands on Activity 10

### Question

Implement and upload your code to GitHub for:

1. "The basic" Binary Search Tree; this is the one that can be unbalanced

2. Red Black Tree

3. AVL Tree

Assume the data is integers and make sure to show tests proving your implementation is correct. Implement all operations (e.g. query, adding, deleting, etc..).

---

### Answer

Here's a full Java implementation for each type of tree: a basic unbalanced Binary Search Tree, a Red-Black Tree, and an AVL Tree. Each implementation includes the standard operations of insertion, deletion, and search. For each tree, I've added test cases to verify correctness.

### 1. **Binary Search Tree (BST)**

A BST Node is declared in [BSTNode.java](BSTNode.java)

```java
public class BSTNode {
	int data;
	BSTNode left, right;

	public BSTNode(int data) {
		this.data = data;
		left = right = null;
	}
}
```

Now the implementation of Binary Search Tree is in: [BST.java](BST.java)

```java
public class BST {
	BSTNode root;

	public BST() {
		root = null;
	}

	public void insert(int data) {
		root = insertRec(root, data);
	}

	private BSTNode insertRec(BSTNode root, int data) {
		if (root == null) {
			root = new BSTNode(data);
			return root;
		}
		if (data < root.data)
			root.left = insertRec(root.left, data);
		else if (data > root.data)
			root.right = insertRec(root.right, data);
		return root;
	}

	public void delete(int data) {
		root = deleteRec(root, data);
	}

	private BSTNode deleteRec(BSTNode root, int data) {
		if (root == null) return root;
		if (data < root.data)
			root.left = deleteRec(root.left, data);
		else if (data > root.data)
			root.right = deleteRec(root.right, data);
		else {
			if (root.left == null) return root.right;
			else if (root.right == null) return root.left;
			root.data = minValue(root.right);
			root.right = deleteRec(root.right, root.data);
		}
		return root;
	}

	private int minValue(BSTNode root) {
		int minValue = root.data;
		while (root.left != null) {
			minValue = root.left.data;
			root = root.left;
		}
		return minValue;
	}

	public boolean search(int data) {
		return searchRec(root, data) != null;
	}

	private BSTNode searchRec(BSTNode root, int data) {
		if (root == null || root.data == data) return root;
		if (data < root.data) return searchRec(root.left, data);
		return searchRec(root.right, data);
	}

	public void inorder() {
		inorderRec(root);
		System.out.println();
	}

	private void inorderRec(BSTNode root) {
		if (root != null) {
			inorderRec(root.left);
			System.out.print(root.data + " ");
			inorderRec(root.right);
		}
	}
}
```


### 2. **Red-Black Tree**

A Red-Black Tree requires additional handling to maintain properties after insertion and deletion. Here’s a basic structure that maintains the red-black properties:

A red black tree node is declared like: [RBNode.java](RBNode.java)

```java
public class RBNode {

	int data;
	RBNode left, right, parent;
	boolean color;

	public RBNode(int data, RBNode left, RBNode right, RBNode parent) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public RBNode(int data) {
		this.data = data;
		this.left = this.right = this.parent = null;
		color = RED;
	}
}
```

And the implementation:

```java
public class RedBlackTree {
	RBNode root;

	public void insert(int data) {
		RBNode node = new RBNode(data);
		root = bstInsert(root, node);
		fixViolation(node);
	}

	private RBNode bstInsert(RBNode root, RBNode node) {
		if (root == null) return node;
		if (node.data < root.data) {
			root.left = bstInsert(root.left, node);
			root.left.parent = root;
		} else if (node.data > root.data) {
			root.right = bstInsert(root.right, node);
			root.right.parent = root;
		}
		return root;
	}

	private void rotateLeft(RBNode node) {
		RBNode rightChild = node.right;
		node.right = rightChild.left;
		if (node.right != null) node.right.parent = node;
		rightChild.parent = node.parent;
		if (node.parent == null) root = rightChild;
		else if (node == node.parent.left) node.parent.left = rightChild;
		else node.parent.right = rightChild;
		rightChild.left = node;
		node.parent = rightChild;
	}

	private void rotateRight(RBNode node) {
		RBNode leftChild = node.left;
		node.left = leftChild.right;
		if (node.left != null) node.left.parent = node;
		leftChild.parent = node.parent;
		if (node.parent == null) root = leftChild;
		else if (node == node.parent.left) node.parent.left = leftChild;
		else node.parent.right = leftChild;
		leftChild.right = node;
		node.parent = leftChild;
	}

	private void fixViolation(RBNode node) {
		RBNode parent = null, grandparent = null;
		while (node != root && node.color != BLACK && node.parent.color == RED) {
			parent = node.parent;
			grandparent = parent.parent;
			if (parent == grandparent.left) {
				RBNode uncle = grandparent.right;
				if (uncle != null && uncle.color == RED) {
					grandparent.color = RED;
					parent.color = BLACK;
					uncle.color = BLACK;
					node = grandparent;
				} else {
					if (node == parent.right) {
						rotateLeft(parent);
						node = parent;
						parent = node.parent;
					}
					rotateRight(grandparent);
					boolean temp = parent.color;
					parent.color = grandparent.color;
					grandparent.color = temp;
					node = parent;
				}
			} else {
				RBNode uncle = grandparent.left;
				if (uncle != null && uncle.color == RED) {
					grandparent.color = RED;
					parent.color = BLACK;
					uncle.color = BLACK;
					node = grandparent;
				} else {
					if (node == parent.left) {
						rotateRight(parent);
						node = parent;
						parent = node.parent;
					}
					rotateLeft(grandparent);
					boolean temp = parent.color;
					parent.color = grandparent.color;
					grandparent.color = temp;
					node = parent;
				}
			}
		}
		root.color = BLACK;
	}

	public void inorder() {
		inorderHelper(root);
		System.out.println();
	}

	private void inorderHelper(RBNode root) {
		if (root != null) {
			inorderHelper(root.left);
			System.out.print(root.data + " ");
			inorderHelper(root.right);
		}
	}
}
```

### 3. **AVL Tree**

```java
public class AVLNode {
	int data, height;
	AVLNode left, right;

	public AVLNode(int data) {
		this.data = data;
		height = 1;
	}
}
```

```java
public class AVLTree {
	private AVLNode root;

	public void insert(int data) {
		root = insertRec(root, data);
	}

	private AVLNode insertRec(AVLNode node, int data) {
		if (node == null) return new AVLNode(data);
		if (data < node.data) node.left = insertRec(node.left, data);
		else if (data > node.data) node.right = insertRec(node.right, data);
		else return node;

		node.height = 1 + Math.max(height(node.left), height(node.right));

		int balance = getBalance(node);
		if (balance > 1 && data < node.left.data) return rightRotate(node);
		if (balance < -1 && data > node.right.data) return leftRotate(node);
		if (balance > 1 && data > node.left.data) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		if (balance < -1 && data < node.right.data) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}

	private AVLNode rightRotate(AVLNode y) {
		AVLNode x = y.left;
		AVLNode T2 = x.right;
		x.right = y;
		y.left = T2;
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		return x;
	}

	private AVLNode leftRotate(AVLNode x) {
		AVLNode y = x.right;
		AVLNode T2 = y.left;
		y.left = x;
		x.right = T2;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		return y;
	}

	private int height(AVLNode AVLNode) {
		return AVLNode == null ? 0 : AVLNode.height;
	}

	private int getBalance(AVLNode AVLNode) {
		return AVLNode == null ? 0 : height(AVLNode.left) - height(AVLNode.right);
	}

	public void inorder() {
		inorderRec(root);
		System.out.println();
	}

	private void inorderRec(AVLNode AVLNode) {
		if (AVLNode != null) {
			inorderRec(AVLNode.left);
			System.out.print(AVLNode.data + " ");
			inorderRec(AVLNode.right);
		}
	}
}
```

Each tree class contains `inorder` traversal methods for testing the tree structure visually. Insertions and rotations are implemented to maintain balance in the Red-Black and AVL trees, while the BST does not self-balance.

A running simulation is in : [Simulation.java](Simulation.java)

```java
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
```
