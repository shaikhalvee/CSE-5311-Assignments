package handson.handson14.dijkstra;

import java.util.ArrayList;
import java.util.List;

public class DijkstraNode implements Comparable<DijkstraNode> {
	public String name;
	public List<DijkstraEdge> dijkstraEdges = new ArrayList<>();
	public double distance = Double.MAX_VALUE; // Initialize as infinity
	public DijkstraNode previous = null;

	public DijkstraNode(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(DijkstraNode other) {
		return Double.compare(this.distance, other.distance);
	}
}
