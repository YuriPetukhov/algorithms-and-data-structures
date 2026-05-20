package hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations;

import hw14_kosaraju_algorithm.libs.graphs.AbstractDirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.DirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.Edge;

import java.util.ArrayList;
import java.util.List;

public class IncidenceMatrixGraph extends AbstractDirectedGraph {
    private final List<Edge<Integer>> edges = new ArrayList<>();

    public IncidenceMatrixGraph(int vertexCount) {
        super(vertexCount);
    }

    @Override
    public void addEdge(int from, int to) {
        validateEdge(from, to);
        edges.add(new Edge<Integer>(from, to));
        increaseEdgeCount();
    }

    @Override
    public List<Integer> adjacent(int vertex) {
        validateVertex(vertex);

        List<Integer> result = new ArrayList<>();

        for (Edge<Integer> edge : edges) {
            if (edge.from() == vertex) {
                result.add(edge.to());
            }
        }

        return result;
    }

    @Override
    public List<Edge<Integer>> edges() {
        return new ArrayList<>(edges);
    }

    @Override
    public DirectedGraph reversed() {
        IncidenceMatrixGraph reversed = new IncidenceMatrixGraph(vertexCount());

        for (Edge<Integer> edge : edges) {
            reversed.addEdge(edge.to(), edge.from());
        }

        return reversed;
    }

    public int[][] matrix() {
        int[][] matrix = new int[vertexCount()][edges.size()];

        for (int edgeIndex = 0; edgeIndex < edges.size(); edgeIndex++) {
            Edge<Integer> edge = edges.get(edgeIndex);

            matrix[edge.from()][edgeIndex] = -1;
            matrix[edge.to()][edgeIndex] = 1;
        }

        return matrix;
    }
}