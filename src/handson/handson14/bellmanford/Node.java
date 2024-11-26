package handson.handson14.bellmanford;

public class Node {
	public String name;
	public double distance = Double.MAX_VALUE; // Initialize as infinity
	public Node previous = null;

	public Node(String name) {
		this.name = name;
	}
}

