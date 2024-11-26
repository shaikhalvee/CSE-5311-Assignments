package handson.handson14.dijkstra;

import java.util.HashMap;
import java.util.Map;

public class DijkstraGraph {
	public Map<String, DijkstraNode> nodes = new HashMap<>();

	public void addNode(String name) {
		nodes.putIfAbsent(name, new DijkstraNode(name));
	}

	public void addEdge(String source, String target, double weight) {
		DijkstraNode sourceNode = nodes.get(source);
		DijkstraNode targetNode = nodes.get(target);

		if (sourceNode != null && targetNode != null) {
			sourceNode.dijkstraEdges.add(new DijkstraEdge(targetNode, weight));
		}
	}

	public DijkstraNode getNode(String name) {
		return nodes.get(name);
	}
}

