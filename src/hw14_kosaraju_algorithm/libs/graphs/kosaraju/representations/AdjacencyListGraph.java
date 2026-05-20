package hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations;

import hw14_kosaraju_algorithm.libs.graphs.AbstractDirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.DirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.Edge;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyListGraph extends AbstractDirectedGraph {
    private final List<List<Integer>> adjacency;

    public AdjacencyListGraph(int vertexCount) {
        super(vertexCount);

        adjacency = new ArrayList<>();

        for (int i = 0; i < vertexCount; i++) {
            adjacency.add(new ArrayList<>());
        }
    }

    @Override
    public void addEdge(int from, int to) {
        validateEdge(from, to);
        adjacency.get(from).add(to);
        increaseEdgeCount();
    }

    @Override
    public List<Integer> adjacent(int vertex) {
        validateVertex(vertex);
        return new ArrayList<>(adjacency.get(vertex));
    }

    @Override
    public List<Edge<Integer>> edges() {
        List<Edge<Integer>> result = new ArrayList<>();

        for (int from = 0; from < vertexCount(); from++) {
            for (int to : adjacency.get(from)) {
                result.add(new Edge<Integer>(from, to));
            }
        }

        return result;
    }

    @Override
    public DirectedGraph reversed() {
        AdjacencyListGraph reversed = new AdjacencyListGraph(vertexCount());

        for (Edge<Integer> edge : edges()) {
            reversed.addEdge(edge.to(), edge.from());
        }

        return reversed;
    }

    public List<List<Integer>> adjacency() {
        List<List<Integer>> copy = new ArrayList<>();

        for (List<Integer> list : adjacency) {
            copy.add(new ArrayList<>(list));
        }

        return copy;
    }
}
