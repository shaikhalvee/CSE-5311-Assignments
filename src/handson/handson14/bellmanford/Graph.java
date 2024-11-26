package handson.handson14.bellmanford;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	public Map<String, Node> nodes = new HashMap<>();
	public List<Edge> edges = new ArrayList<>();

	public void addNode(String name) {
		nodes.putIfAbsent(name, new Node(name));
	}

	public void addEdge(String source, String target, double weight) {
		Node sourceNode = nodes.get(source);
		Node targetNode = nodes.get(target);

		if (sourceNode != null && targetNode != null) {
			edges.add(new Edge(sourceNode, targetNode, weight));
		}
	}

	public Node getNode(String name) {
		return nodes.get(name);
	}
}

