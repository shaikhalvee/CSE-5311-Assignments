package handson.handson14.bellmanford;

public class BellmanNode {
	public String name;
	public double distance = Double.MAX_VALUE; // Initialize as infinity
	public BellmanNode previous = null;

	public BellmanNode(String name) {
		this.name = name;
	}
}

