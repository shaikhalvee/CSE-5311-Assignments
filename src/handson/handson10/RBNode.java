package handson.handson10;

import static handson.handson10.RedBlack.RED;

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
