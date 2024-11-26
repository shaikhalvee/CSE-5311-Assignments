package handson.handson14.bellmanford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BellmanFordAlgorithm {
	public static boolean calculateShortestPath(BellmanGraph bellmanGraph, String startName) {
		BellmanNode startNode = bellmanGraph.getNode(startName);
		if (startNode == null) return false;

		startNode.distance = 0;

		// Relax all edges |V| - 1 times
		for (int i = 0; i < bellmanGraph.nodes.size() - 1; i++) {
			for (BellmanEdge bellmanEdge : bellmanGraph.bellmanEdges) {
				if (bellmanEdge.source.distance + bellmanEdge.weight < bellmanEdge.target.distance) {
					bellmanEdge.target.distance = bellmanEdge.source.distance + bellmanEdge.weight;
					bellmanEdge.target.previous = bellmanEdge.source;
				}
			}
		}

		// Check for negative-weight cycles
		for (BellmanEdge bellmanEdge : bellmanGraph.bellmanEdges) {
			if (bellmanEdge.source.distance + bellmanEdge.weight < bellmanEdge.target.distance) {
				System.out.println("Graph contains a negative-weight cycle.");
				return false;
			}
		}

		return true;
	}

	public static List<String> getShortestPathTo(BellmanNode target) {
		List<String> path = new ArrayList<>();
		for (BellmanNode bellmanNode = target; bellmanNode != null; bellmanNode = bellmanNode.previous) {
			path.add(bellmanNode.name);
		}
		Collections.reverse(path);
		return path;
	}
}

