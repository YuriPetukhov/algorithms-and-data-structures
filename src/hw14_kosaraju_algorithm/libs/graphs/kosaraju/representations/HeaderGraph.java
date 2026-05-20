package hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations;

import hw14_kosaraju_algorithm.libs.graphs.AbstractDirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.DirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.Edge;

import java.util.ArrayList;
import java.util.List;

public class HeaderGraph extends AbstractDirectedGraph {
    private final int[] head;
    private final List<Integer> to = new ArrayList<>();
    private final List<Integer> next = new ArrayList<>();

    public HeaderGraph(int vertexCount) {
        super(vertexCount);

        head = new int[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            head[i] = -1;
        }
    }

    @Override
    public void addEdge(int from, int toVertex) {
        validateEdge(from, toVertex);

        to.add(toVertex);
        next.add(head[from]);
        head[from] = edgeCount();

        increaseEdgeCount();
    }

    @Override
    public List<Integer> adjacent(int vertex) {
        validateVertex(vertex);

        List<Integer> result = new ArrayList<>();

        for (int edgeIndex = head[vertex]; edgeIndex != -1; edgeIndex = next.get(edgeIndex)) {
            result.add(to.get(edgeIndex));
        }

        return result;
    }

    @Override
    public List<Edge<Integer>> edges() {
        List<Edge<Integer>> result = new ArrayList<>();

        for (int from = 0; from < vertexCount(); from++) {
            for (int edgeIndex = head[from]; edgeIndex != -1; edgeIndex = next.get(edgeIndex)) {
                result.add(new Edge<Integer>(from, to.get(edgeIndex)));
            }
        }

        return result;
    }

    @Override
    public DirectedGraph reversed() {
        HeaderGraph reversed = new HeaderGraph(vertexCount());

        for (Edge<Integer> edge : edges()) {
            reversed.addEdge(edge.to(), edge.from());
        }

        return reversed;
    }

    public int[] head() {
        return head.clone();
    }

    public List<Integer> to() {
        return new ArrayList<>(to);
    }

    public List<Integer> next() {
        return new ArrayList<>(next);
    }
}
