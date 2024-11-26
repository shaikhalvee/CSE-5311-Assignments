package handson.handson14.floydwarshall;

public class FloydWarshallAlgorithm {
	private final int INF = Integer.MAX_VALUE;

	public int[][] floydWarshall(FloydWarshallGraph graph) {
		int[][] distances = new int[graph.numberOfNodes][graph.numberOfNodes];

		// Copy the adjacency matrix into the distances matrix
		for (int i = 0; i < graph.numberOfNodes; i++) {
			System.arraycopy(graph.adjacencyMatrix[i], 0, distances[i], 0, graph.numberOfNodes);
		}

		// Apply the Floyd-Warshall algorithm
		for (int k = 0; k < graph.numberOfNodes; k++) {
			for (int i = 0; i < graph.numberOfNodes; i++) {
				for (int j = 0; j < graph.numberOfNodes; j++) {
					if (distances[i][k] != INF && distances[k][j] != INF &&
							distances[i][k] + distances[k][j] < distances[i][j]) {
						distances[i][j] = distances[i][k] + distances[k][j];
					}
				}
			}
		}

		return distances;
	}

	public void printSolution(int[][] distances) {
		System.out.println("Shortest distances between every pair of nodes:");
		for (int[] distance : distances) {
			for (int i : distance) {
				if (i == INF) {
					System.out.print("INF ");
				} else {
					System.out.print(i + " ");
				}
			}
			System.out.println();
		}
	}
}

