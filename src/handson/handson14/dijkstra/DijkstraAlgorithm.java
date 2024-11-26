package handson.handson14.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
	public static void calculateShortestPath(DijkstraGraph dijkstraGraph, String startName) {
		DijkstraNode startNode = dijkstraGraph.getNode(startName);
		if (startNode == null) return;

		PriorityQueue<DijkstraNode> priorityQueue = new PriorityQueue<>();
		startNode.distance = 0;
		priorityQueue.add(startNode);

		while (!priorityQueue.isEmpty()) {
			DijkstraNode currentNode = priorityQueue.poll();

			for (DijkstraEdge dijkstraEdge : currentNode.dijkstraEdges) {
				DijkstraNode neighbor = dijkstraEdge.target;
				double newDist = currentNode.distance + dijkstraEdge.weight;

				if (newDist < neighbor.distance) {
					neighbor.distance = newDist;
					neighbor.previous = currentNode;
					priorityQueue.add(neighbor);
				}
			}
		}
	}

	public static List<String> getShortestPathTo(DijkstraNode target) {
		List<String> path = new ArrayList<>();
		for (DijkstraNode dijkstraNode = target; dijkstraNode != null; dijkstraNode = dijkstraNode.previous) {
			path.add(dijkstraNode.name);
		}
		Collections.reverse(path);
		return path;
	}
}

