package hw15_demukron_algorithm.libs.graphs;

import java.util.*;

public class AdjacencyVectorUndirectedGraph<V> implements UndirectedGraph<V> {
    private final Map<V, List<V>> adjacency = new LinkedHashMap<>();
    private int edgeCount;

    @Override
    public void addVertex(V vertex) {
        adjacency.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void addEdge(V first, V second) {
        if (!adjacency.containsKey(first) || !adjacency.containsKey(second)) {
            throw new IllegalArgumentException("Both vertices must exist before adding edge");
        }

        adjacency.get(first).add(second);
        adjacency.get(second).add(first);
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