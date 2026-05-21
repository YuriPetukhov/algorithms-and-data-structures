package hw15_demukron_algorithm.libs.graphs;

public final class IntAdjacencyVectorGraphAdapter {
    private IntAdjacencyVectorGraphAdapter() {
    }

    public static DirectedGraph<Integer> from(int[][] adjacencyVector) {
        DirectedGraph<Integer> graph = new AdjacencyVectorDirectedGraph<>();

        for (int vertex = 0; vertex < adjacencyVector.length; vertex++) {
            graph.addVertex(vertex);
        }

        for (int from = 0; from < adjacencyVector.length; from++) {
            for (int to : adjacencyVector[from]) {
                if (to == -1) {
                    continue;
                }

                graph.addEdge(from, to);
            }
        }

        return graph;
    }
}