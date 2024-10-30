package handson.handson10;

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
