package handson.handson13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

		for (Edge edge : edges) {
			if (uf.union(edge.src, edge.dest)) {
				mst.add(edge);
				mstCost += edge.weight;
			}
		}

		System.out.println("Minimum Spanning Tree:");
		for (Edge edge : mst) {
			System.out.println(edge.src + " - " + edge.dest + ": " + edge.weight);
		}
		System.out.println("Total Cost: " + mstCost);
	}
}
