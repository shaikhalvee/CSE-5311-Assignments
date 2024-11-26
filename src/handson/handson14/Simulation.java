package handson.handson14;

import handson.handson14.dijkstra.DijkstraAlgorithm;
import handson.handson14.dijkstra.Graph;
import handson.handson14.dijkstra.Node;

public class Simulation {

	public static void main(String[] args) {
		dijkstraSimulation();
	}

	private static void dijkstraSimulation() {
		// Create the graph
		Graph graph = new Graph();
		graph.addNode("s");
		graph.addNode("t");
		graph.addNode("x");
		graph.addNode("y");
		graph.addNode("z");

		// Add edges with weights
		graph.addEdge("s", "t", 10);
		graph.addEdge("s", "y", 5);
		graph.addEdge("t", "x", 1);
		graph.addEdge("t", "y", 2);
		graph.addEdge("y", "t", 3);
		graph.addEdge("y", "z", 2);
		graph.addEdge("y", "x", 9);
		graph.addEdge("x", "z", 4);
		graph.addEdge("z", "x", 6);

		// Run Dijkstra's algorithm from node "s"
		DijkstraAlgorithm.calculateShortestPath(graph, "s");

		// Output the shortest path to each node
		for (Node node : graph.nodes.values()) {
			System.out.println("Shortest path to " + node.name + ": " +
					DijkstraAlgorithm.getShortestPathTo(node) + " (Distance: " + node.distance + ")");
		}
	}
}
