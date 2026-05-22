package hw17_dijkstra_algorithm.libs.graphs;

import java.util.*;

public class AdjacencyVectorWeightedDirectedGraph<V> implements WeightedDirectedGraph<V> {
    private final Map<V, List<WeightedEdge<V>>> adjacency = new LinkedHashMap<>();
    private final List<WeightedEdge<V>> edges = new ArrayList<>();

    @Override
    public void addVertex(V vertex) {
        adjacency.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void addEdge(V from, V to, int weight) {
        if (!adjacency.containsKey(from) || !adjacency.containsKey(to)) {
            throw new IllegalArgumentException("Both vertices must exist before adding edge");
        }

        WeightedEdge<V> edge = new WeightedEdge<>(from, to, weight);
        adjacency.get(from).add(edge);
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