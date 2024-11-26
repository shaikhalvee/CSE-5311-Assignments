# Hands-on Activity 13

## Tasks

Implement the following algorithms:

1. Topological sort

2. Depth-First Search

3. Kruskal algorithm

## Response

### Topological Sort
The topological sort is implemented in the [TopologicalSort.java](TopologicalSort.java) class.

```java
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

	private static void dfs(int dijkstraNode, List<List<Integer>> adjacencyList, boolean[] visited, Stack<Integer> stack) {
		visited[dijkstraNode] = true;
		for (int neighbor : adjacencyList.get(dijkstraNode)) {
			if (!visited[neighbor]) {
				dfs(neighbor, adjacencyList, visited, stack);
			}
		}
		stack.push(dijkstraNode);
	}
}
```

### Depth First Search
The Depth First Search or DFS is implemented in the [DepthFirstSearch.java](DepthFirstSearch.java) class.

```java
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
```

### Kruskal's Algorithm
Here, kruskal's algorithm is implemented with the help of classical Union Find algorithm.

The Union Find's implementation is in [UnionFind.java](UnionFind.java) class.
```java
class UnionFind { 
	private int[] parent, rank;

	UnionFind(int size) {
		parent = new int[size];
		rank = new int[size];
		for (int i = 0; i < size; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public int find(int dijkstraNode) {
		if (parent[dijkstraNode] != dijkstraNode) {
			parent[dijkstraNode] = find(parent[dijkstraNode]); // Path compression
		}
		return parent[dijkstraNode];
	}

	public boolean union(int u, int v) {
		int rootU = find(u);
		int rootV = find(v);

		if (rootU == rootV) return false;

		if (rank[rootU] < rank[rootV]) {
			parent[rootU] = rootV;
		} else if (rank[rootU] > rank[rootV]) {
			parent[rootV] = rootU;
		} else {
			parent[rootV] = rootU;
			rank[rootU]++;
		}

		return true;
	}
}
```
And the Kruskal's implementation is in [Kruskal.java](Kruskal.java) class.

```java
public class Kruskal {
	static class Edge implements Comparable<Edge> {
		int src, dest, weight;

		Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge other) {
			return this.weight - other.weight;
		}
	}

	public static void kruskal(int vertices, List<Edge> edges) {
		Collections.sort(edges);
		UnionFind uf = new UnionFind(vertices);

		List<Edge> mst = new ArrayList<>();
		int mstCost = 0;

		for (Edge dijkstraEdge : edges) {
			if (uf.union(dijkstraEdge.src, dijkstraEdge.dest)) {
				mst.add(dijkstraEdge);
				mstCost += dijkstraEdge.weight;
			}
		}

		System.out.println("Minimum Spanning Tree:");
		for (Edge dijkstraEdge : mst) {
			System.out.println(dijkstraEdge.src + " - " + dijkstraEdge.dest + ": " + dijkstraEdge.weight);
		}
		System.out.println("Total Cost: " + mstCost);
	}
}
```

### Testing
The testing was done in the [Simulation.java](Simulation.java) class.
If the code is run, it implements all the three algorithms with their test cases.
