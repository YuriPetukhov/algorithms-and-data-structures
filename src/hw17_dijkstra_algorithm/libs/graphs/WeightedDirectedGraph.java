package hw17_dijkstra_algorithm.libs.graphs;

import java.util.List;
import java.util.Set;

public interface WeightedDirectedGraph<V> {
    void addVertex(V vertex);

    void addEdge(V from, V to, int weight);

    Set<V> vertices();

    List<WeightedEdge<V>> edges();

    List<WeightedEdge<V>> adjacent(V vertex);

    int vertexCount();

    int edgeCount();
}