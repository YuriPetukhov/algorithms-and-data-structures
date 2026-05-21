package hw15_demukron_algorithm.libs.graphs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyVectorDirectedGraph<V> implements DirectedGraph<V> {
    private final Map<V, List<V>> adjacency = new LinkedHashMap<>();
    private int edgeCount;

    @Override
    public void addVertex(V vertex) {
        adjacency.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void addEdge(V from, V to) {
        if (!adjacency.containsKey(from) || !adjacency.containsKey(to)) {
            throw new IllegalArgumentException("Both vertices must exist before adding edge");
        }

        adjacency.get(from).add(to);
        edgeCount++;
    }

    @Override
    public Set<V> vertices() {
        return new LinkedHashSet<>(adjacency.keySet());
    }

    @Override
    public List<V> adjacent(V vertex) {
        if (!adjacency.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex does not exist: " + vertex);
        }

        return new ArrayList<>(adjacency.get(vertex));
    }

    @Override
    public int vertexCount() {
        return adjacency.size();
    }

    @Override
    public int edgeCount() {
        return edgeCount;
    }
}