package handson.handson10;

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
