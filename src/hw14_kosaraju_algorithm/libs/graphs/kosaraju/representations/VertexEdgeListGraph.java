package hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations;

import hw14_kosaraju_algorithm.libs.graphs.AbstractDirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.DirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.Edge;

import java.util.ArrayList;
import java.util.List;

public class VertexEdgeListGraph extends AbstractDirectedGraph {
    private final List<Integer> vertices = new ArrayList<>();
    private final List<Edge<Integer>> edges = new ArrayList<>();

    public VertexEdgeListGraph(int vertexCount) {
        super(vertexCount);

        for (int i = 0; i < vertexCount; i++) {
            vertices.add(i);
        }
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
        VertexEdgeListGraph reversed = new VertexEdgeListGraph(vertexCount());

        for (Edge<Integer> edge : edges) {
            reversed.addEdge(edge.to(), edge.from());
        }

        return reversed;
    }

    public List<Integer> vertices() {
        return new ArrayList<>(vertices);
    }
}
