package handson.handson14.dijkstra;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
	public String name;
	public List<Edge> edges = new ArrayList<>();
	public double distance = Double.MAX_VALUE; // Initialize as infinity
	public Node previous = null;

	public Node(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Node other) {
		return Double.compare(this.distance, other.distance);
	}
}
