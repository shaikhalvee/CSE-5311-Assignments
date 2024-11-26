package handson.handson14.bellmanford;

public class Edge {
	public Node source;
	public Node target;
	public double weight;

	public Edge(Node source, Node target, double weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}
}

