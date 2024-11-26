package handson.handson14.floydwarshall;

public class FloydWarshallGraph {
	private final int INF = Integer.MAX_VALUE; // Represents infinity
	public int[][] adjacencyMatrix;
	public int numberOfNodes;

	public FloydWarshallGraph(int numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
		adjacencyMatrix = new int[numberOfNodes][numberOfNodes];

		// Initialize the adjacency matrix
		for (int i = 0; i < numberOfNodes; i++) {
			for (int j = 0; j < numberOfNodes; j++) {
				if (i == j) {
					adjacencyMatrix[i][j] = 0; // Distance to self is 0
				} else {
					adjacencyMatrix[i][j] = INF; // Default to infinity
				}
			}
		}
	}

	public void addEdge(int source, int target, int weight) {
		adjacencyMatrix[source][target] = weight;
	}
}

