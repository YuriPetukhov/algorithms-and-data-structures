package hw17_dijkstra_algorithm.libs.graphs;

public final class IntWeightedAdjacencyVectorGraphAdapter {
    private IntWeightedAdjacencyVectorGraphAdapter() {
    }

    public static WeightedDirectedGraph<Integer> from(
            int[][] adjacencyVector,
            int[][] weightVector
    ) {
        WeightedDirectedGraph<Integer> graph =
                new AdjacencyVectorWeightedDirectedGraph<>();

        for (int vertex = 0; vertex < adjacencyVector.length; vertex++) {
            graph.addVertex(vertex);
        }

        for (int from = 0; from < adjacencyVector.length; from++) {
            for (int i = 0; i < adjacencyVector[from].length; i++) {
                int to = adjacencyVector[from][i];

                if (to == -1) {
                    continue;
                }

                graph.addEdge(from, to, weightVector[from][i]);
            }
        }

        return graph;
    }
}