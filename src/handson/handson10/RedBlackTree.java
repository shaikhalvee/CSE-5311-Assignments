package handson.handson10;

import static handson.handson10.RedBlack.BLACK;
import static handson.handson10.RedBlack.RED;

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
