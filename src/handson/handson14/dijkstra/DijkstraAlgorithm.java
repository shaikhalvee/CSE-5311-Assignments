package handson.handson14.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
	public static void calculateShortestPath(Graph graph, String startName) {
		Node startNode = graph.getNode(startName);
		if (startNode == null) return;

		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
		startNode.distance = 0;
		priorityQueue.add(startNode);

		while (!priorityQueue.isEmpty()) {
			Node currentNode = priorityQueue.poll();

			for (Edge edge : currentNode.edges) {
				Node neighbor = edge.target;
				double newDist = currentNode.distance + edge.weight;

				if (newDist < neighbor.distance) {
					neighbor.distance = newDist;
					neighbor.previous = currentNode;
					priorityQueue.add(neighbor);
				}
			}
		}
	}

	public static List<String> getShortestPathTo(Node target) {
		List<String> path = new ArrayList<>();
		for (Node node = target; node != null; node = node.previous) {
			path.add(node.name);
		}
		Collections.reverse(path);
		return path;
	}
}

