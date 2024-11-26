package handson.handson14.bellmanford;

public class BellmanEdge {
	public BellmanNode source;
	public BellmanNode target;
	public double weight;

	public BellmanEdge(BellmanNode source, BellmanNode target, double weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}
}

