package hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations;

import hw14_kosaraju_algorithm.libs.graphs.AbstractDirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.DirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.Edge;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrixGraph extends AbstractDirectedGraph {
    private final boolean[][] matrix;

    public AdjacencyMatrixGraph(int vertexCount) {
        super(vertexCount);
        this.matrix = new boolean[vertexCount][vertexCount];
    }

    @Override
    public void addEdge(int from, int to) {
        validateEdge(from, to);

        if (!matrix[from][to]) {
            matrix[from][to] = true;
            increaseEdgeCount();
        }
    }

    @Override
    public List<Integer> adjacent(int vertex) {
        validateVertex(vertex);

        List<Integer> result = new ArrayList<>();

        for (int to = 0; to < vertexCount(); to++) {
            if (matrix[vertex][to]) {
                result.add(to);
            }
        }

        return result;
    }

    @Override
    public List<Edge<Integer>> edges() {
        List<Edge<Integer>> result = new ArrayList<>();

        for (int from = 0; from < vertexCount(); from++) {
            for (int to = 0; to < vertexCount(); to++) {
                if (matrix[from][to]) {
                    result.add(new Edge<Integer>(from, to));
                }
            }
        }

        return result;
    }

    @Override
    public DirectedGraph reversed() {
        AdjacencyMatrixGraph reversed = new AdjacencyMatrixGraph(vertexCount());

        for (Edge<Integer> edge : edges()) {
            reversed.addEdge(edge.to(), edge.from());
        }

        return reversed;
    }

    public boolean[][] matrix() {
        boolean[][] copy = new boolean[vertexCount()][vertexCount()];

        for (int i = 0; i < vertexCount(); i++) {
            System.arraycopy(matrix[i], 0, copy[i], 0, vertexCount());
        }

        return copy;
    }
}
