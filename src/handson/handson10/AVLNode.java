package handson.handson10;

public class AVLNode {
	int data, height;
	AVLNode left, right;

	public AVLNode(int data) {
		this.data = data;
		height = 1;
	}
}
