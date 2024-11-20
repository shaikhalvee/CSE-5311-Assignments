package handson.handson13;

import java.util.List;

public class DepthFirstSearch {
	public static void dfs(int start, List<List<Integer>> adjacencyList, boolean[] visited) {
		visited[start] = true;
		System.out.print(start + " ");

		for (int neighbor : adjacencyList.get(start)) {
			if (!visited[neighbor]) {
				dfs(neighbor, adjacencyList, visited);
			}
		}
	}
}
