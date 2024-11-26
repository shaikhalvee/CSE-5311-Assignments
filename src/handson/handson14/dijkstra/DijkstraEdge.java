package handson.handson14.dijkstra;

public class DijkstraEdge {
	public DijkstraNode target;
	public double weight;

	public DijkstraEdge(DijkstraNode target, double weight) {
		this.target = target;
		this.weight = weight;
	}
}

