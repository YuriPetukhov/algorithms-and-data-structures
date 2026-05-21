package hw16_mst_algorithm.libs.graphs;

import java.util.HashSet;
import java.util.Set;

public final class IntWeightedAdjacencyVectorGraphAdapter {
    private IntWeightedAdjacencyVectorGraphAdapter() {
    }

    public static WeightedUndirectedGraph<Integer> from(
            int[][] adjacencyVector,
            int[][] weightVector
    ) {
        WeightedUndirectedGraph<Integer> graph = new AdjacencyVectorWeightedGraph<>();

        for (int vertex = 0; vertex < adjacencyVector.length; vertex++) {
            graph.addVertex(vertex);
        }

        Set<String> addedEdges = new HashSet<>();

        for (int from = 0; from < adjacencyVector.length; from++) {
            for (int i = 0; i < adjacencyVector[from].length; i++) {
                int to = adjacencyVector[from][i];

                if (to == -1) {
                    continue;
                }

                int min = Math.min(from, to);
                int max = Math.max(from, to);
                String key = min + ":" + max;

                if (addedEdges.add(key)) {
                    graph.addEdge(from, to, weightVector[from][i]);
                }
            }
        }

        return graph;
    }
}