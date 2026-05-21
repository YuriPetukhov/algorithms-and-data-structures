package hw16_mst_algorithm.libs.graphs;

import java.util.*;

public class AdjacencyVectorWeightedGraph<V> implements WeightedUndirectedGraph<V> {
    private final Map<V, List<WeightedEdge<V>>> adjacency = new LinkedHashMap<>();
    private final List<WeightedEdge<V>> edges = new ArrayList<>();

    @Override
    public void addVertex(V vertex) {
        adjacency.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void addEdge(V v1, V v2, int weight) {
        if (!adjacency.containsKey(v1) || !adjacency.containsKey(v2)) {
            throw new IllegalArgumentException("Both vertices must exist before adding edge");
        }

        WeightedEdge<V> edge = new WeightedEdge<>(v1, v2, weight);

        adjacency.get(v1).add(edge);
        adjacency.get(v2).add(edge);
        edges.add(edge);
    }

    @Override
    public Set<V> vertices() {
        return new LinkedHashSet<>(adjacency.keySet());
    }

    @Override
    public List<WeightedEdge<V>> edges() {
        return new ArrayList<>(edges);
    }

    @Override
    public List<WeightedEdge<V>> adjacent(V vertex) {
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
        return edges.size();
    }
}