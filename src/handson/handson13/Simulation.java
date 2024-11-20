package handson.handson13;

import java.util.ArrayList;
import java.util.List;

import static handson.handson13.DepthFirstSearch.dfs;
import static handson.handson13.Kruskal.*;
import static handson.handson13.TopologicalSort.topologicalSort;

public class Simulation {
	public static void main(String[] args) {
		topologicalSortSimulation();
		dfsSimulation();
		kruskalSimulation();
	}

	private static void kruskalSimulation() {
		int vertices = 4;
		List<Kruskal.Edge> edges = new ArrayList<>();
		edges.add(new Edge(0, 1, 10));
		edges.add(new Edge(0, 2, 6));
		edges.add(new Edge(0, 3, 5));
		edges.add(new Edge(1, 3, 15));
		edges.add(new Kruskal.Edge(2, 3, 4));

		kruskal(vertices, edges);
	}

	private static void dfsSimulation() {
		int vertices = 5;
		List<List<Integer>> adjacencyList = new ArrayList<>();
		for (int i = 0; i < vertices; i++) {
			adjacencyList.add(new ArrayList<>());
		}

		adjacencyList.get(0).add(1);
		adjacencyList.get(0).add(2);
		adjacencyList.get(1).add(3);
		adjacencyList.get(2).add(4);

		boolean[] visited = new boolean[vertices];
		System.out.println("DFS Traversal: ");
		dfs(0, adjacencyList, visited);
		System.out.println();
	}

	private static void topologicalSortSimulation() {
		int vertices = 6;
		List<List<Integer>> adjacencyList = new ArrayList<>();
		for (int i = 0; i < vertices; i++) {
			adjacencyList.add(new ArrayList<>());
		}

		adjacencyList.get(5).add(2);
		adjacencyList.get(5).add(0);
		adjacencyList.get(4).add(0);
		adjacencyList.get(4).add(1);
		adjacencyList.get(2).add(3);
		adjacencyList.get(3).add(1);

		topologicalSort(vertices, adjacencyList);
	}
}
