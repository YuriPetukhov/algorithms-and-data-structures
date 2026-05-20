package hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations;

import hw14_kosaraju_algorithm.libs.graphs.AbstractDirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.DirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.Edge;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyVectorGraph extends AbstractDirectedGraph {
    private final List<Integer>[] vectors;

    @SuppressWarnings("unchecked")
    public AdjacencyVectorGraph(int vertexCount) {
        super(vertexCount);

        vectors = new List[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            vectors[i] = new ArrayList<>();
        }
    }

    @Override
    public void addEdge(int from, int to) {
        validateEdge(from, to);
        vectors[from].add(to);
        increaseEdgeCount();
    }

    @Override
    public List<Integer> adjacent(int vertex) {
        validateVertex(vertex);
        return new ArrayList<>(vectors[vertex]);
    }

    @Override
    public List<Edge<Integer>> edges() {
        List<Edge<Integer>> result = new ArrayList<>();

        for (int from = 0; from < vertexCount(); from++) {
            for (int to : vectors[from]) {
                result.add(new Edge<Integer>(from, to));
            }
        }

        return result;
    }

    @Override
    public DirectedGraph reversed() {
        AdjacencyVectorGraph reversed = new AdjacencyVectorGraph(vertexCount());

        for (Edge<Integer> edge : edges()) {
            reversed.addEdge(edge.to(), edge.from());
        }

        return reversed;
    }
}