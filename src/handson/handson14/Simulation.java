package handson.handson14;

import handson.handson14.bellmanford.BellmanFordAlgorithm;
import handson.handson14.bellmanford.BellmanGraph;
import handson.handson14.bellmanford.BellmanNode;
import handson.handson14.dijkstra.DijkstraAlgorithm;
import handson.handson14.dijkstra.DijkstraGraph;
import handson.handson14.dijkstra.DijkstraNode;
import handson.handson14.floydwarshall.FloydWarshallAlgorithm;
import handson.handson14.floydwarshall.FloydWarshallGraph;

public class Simulation {

	public static void main(String[] args) {
		dijkstraSimulation();
		bellmanFordSimulation();
		floydWarshallSimulation();
	}

	private static void dijkstraSimulation() {
		System.out.println("Dijkstra Algorithm Simulation");
		// Create the graph
		DijkstraGraph dijkstraGraph = new DijkstraGraph();
		dijkstraGraph.addNode("s");
		dijkstraGraph.addNode("t");
		dijkstraGraph.addNode("x");
		dijkstraGraph.addNode("y");
		dijkstraGraph.addNode("z");

		// Add edges with weights
		dijkstraGraph.addEdge("s", "t", 10);
		dijkstraGraph.addEdge("s", "y", 5);
		dijkstraGraph.addEdge("t", "x", 1);
		dijkstraGraph.addEdge("t", "y", 2);
		dijkstraGraph.addEdge("y", "t", 3);
		dijkstraGraph.addEdge("y", "z", 2);
		dijkstraGraph.addEdge("y", "x", 9);
		dijkstraGraph.addEdge("x", "z", 4);
		dijkstraGraph.addEdge("z", "x", 6);

		// Run Dijkstra's algorithm from node "s"
		DijkstraAlgorithm.calculateShortestPath(dijkstraGraph, "s");

		// Output the shortest path to each node
		for (DijkstraNode dijkstraNode : dijkstraGraph.nodes.values()) {
			System.out.println("Shortest path to " + dijkstraNode.name + ": " +
					DijkstraAlgorithm.getShortestPathTo(dijkstraNode) + " (Distance: " + dijkstraNode.distance + ")");
		}
	}

	private static void bellmanFordSimulation() {
		System.out.println("Bellman Ford Algorithm Simulation");
		// Create the graph
		BellmanGraph graph = new BellmanGraph();
		graph.addNode("s");
		graph.addNode("t");
		graph.addNode("x");
		graph.addNode("y");
		graph.addNode("z");

		// Add edges with weights (from the provided image)
		graph.addEdge("s", "t", 6);
		graph.addEdge("s", "y", 7);
		graph.addEdge("t", "x", 5);
		graph.addEdge("t", "y", 8);
		graph.addEdge("t", "z", -4);
		graph.addEdge("y", "t", -2);
		graph.addEdge("y", "x", -3);
		graph.addEdge("y", "z", 9);
		graph.addEdge("x", "z", 7);

		// Run Bellman-Ford algorithm from node "s"
		boolean noNegativeCycle = BellmanFordAlgorithm.calculateShortestPath(graph, "s");

		if (noNegativeCycle) {
			// Output the shortest path to each node
			for (BellmanNode node : graph.nodes.values()) {
				System.out.println("Shortest path to " + node.name + ": " +
						BellmanFordAlgorithm.getShortestPathTo(node) + " (Distance: " + node.distance + ")");
			}
		}
	}

	private static void floydWarshallSimulation() {
		System.out.println("Floyd Warshall Algorithm Simulation");
		// Number of nodes in the graph
		int numberOfNodes = 5;

		// Create the graph
		FloydWarshallGraph graph = new FloydWarshallGraph(numberOfNodes);

		// Add edges (using 0-based indexing for nodes: s=0, t=1, x=2, y=3, z=4)
		graph.addEdge(0, 1, 6);  // s -> t
		graph.addEdge(0, 3, 7);  // s -> y
		graph.addEdge(1, 2, 5);  // t -> x
		graph.addEdge(1, 3, 8);  // t -> y
		graph.addEdge(1, 4, -4); // t -> z
		graph.addEdge(3, 1, -2); // y -> t
		graph.addEdge(3, 2, -3); // y -> x
		graph.addEdge(3, 4, 9);  // y -> z
		graph.addEdge(2, 4, 7);  // x -> z

		// Run the Floyd-Warshall algorithm
		FloydWarshallAlgorithm algorithm = new FloydWarshallAlgorithm();
		int[][] distances = algorithm.floydWarshall(graph);

		// Print the shortest path distances
		algorithm.printSolution(distances);
	}
}
