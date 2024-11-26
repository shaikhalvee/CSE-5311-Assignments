package handson.handson14.bellmanford;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BellmanGraph {
	public Map<String, BellmanNode> nodes = new HashMap<>();
	public List<BellmanEdge> bellmanEdges = new ArrayList<>();

	public void addNode(String name) {
		nodes.putIfAbsent(name, new BellmanNode(name));
	}

	public void addEdge(String source, String target, double weight) {
		BellmanNode sourceNode = nodes.get(source);
		BellmanNode targetNode = nodes.get(target);

		if (sourceNode != null && targetNode != null) {
			bellmanEdges.add(new BellmanEdge(sourceNode, targetNode, weight));
		}
	}

	public BellmanNode getNode(String name) {
		return nodes.get(name);
	}
}

