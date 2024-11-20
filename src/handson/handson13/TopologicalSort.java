package handson.handson13;

import java.util.List;
import java.util.Stack;

public class TopologicalSort {
	public static void topologicalSort(int vertices, List<List<Integer>> adjacencyList) {
		boolean[] visited = new boolean[vertices];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < vertices; i++) {
			if (!visited[i]) {
				dfs(i, adjacencyList, visited, stack);
			}
		}

		System.out.println("Topological Order: ");
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
		System.out.println();
	}

	private static void dfs(int node, List<List<Integer>> adjacencyList, boolean[] visited, Stack<Integer> stack) {
		visited[node] = true;
		for (int neighbor : adjacencyList.get(node)) {
			if (!visited[neighbor]) {
				dfs(neighbor, adjacencyList, visited, stack);
			}
		}
		stack.push(node);
	}
}
