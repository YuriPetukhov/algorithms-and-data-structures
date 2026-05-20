package hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations;

import hw14_kosaraju_algorithm.libs.graphs.AbstractDirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.DirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.Edge;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyArrayGraph extends AbstractDirectedGraph {
    private final List<Edge<Integer>> edges = new ArrayList<>();

    private int[] offsets;
    private int[] adjacent;
    private boolean built;

    public AdjacencyArrayGraph(int vertexCount) {
        super(vertexCount);
    }

    @Override
    public void addEdge(int from, int to) {
        validateEdge(from, to);

        if (built) {
            throw new IllegalStateException("Cannot add edges after build()");
        }

        edges.add(new Edge<Integer>(from, to));
        increaseEdgeCount();
    }

    public void build() {
        offsets = new int[vertexCount() + 1];
        adjacent = new int[edgeCount()];

        for (Edge<Integer> edge : edges) {
            offsets[edge.from() + 1]++;
        }

        for (int i = 1; i < offsets.length; i++) {
            offsets[i] += offsets[i - 1];
        }

        int[] position = offsets.clone();

        for (Edge<Integer> edge : edges) {
            adjacent[position[edge.from()]++] = edge.to();
        }

        built = true;
    }

    @Override
    public List<Integer> adjacent(int vertex) {
        validateVertex(vertex);
        ensureBuilt();

        List<Integer> result = new ArrayList<>();

        for (int i = offsets[vertex]; i < offsets[vertex + 1]; i++) {
            result.add(adjacent[i]);
        }

        return result;
    }

    @Override
    public List<Edge<Integer>> edges() {
        return new ArrayList<>(edges);
    }

    @Override
    public DirectedGraph reversed() {
        AdjacencyArrayGraph reversed = new AdjacencyArrayGraph(vertexCount());

        for (Edge<Integer> edge : edges) {
            reversed.addEdge(edge.to(), edge.from());
        }

        reversed.build();
        return reversed;
    }

    public int[] offsets() {
        ensureBuilt();
        return offsets.clone();
    }

    public int[] adjacentArray() {
        ensureBuilt();
        return adjacent.clone();
    }

    private void ensureBuilt() {
        if (!built) {
            build();
        }
    }
}
