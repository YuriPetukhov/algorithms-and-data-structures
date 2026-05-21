package hw16_mst_algorithm.libs.graphs;

import java.util.List;
import java.util.Set;

public interface WeightedUndirectedGraph<V> {
    void addVertex(V vertex);

    void addEdge(V v1, V v2, int weight);

    Set<V> vertices();

    List<WeightedEdge<V>> edges();

    List<WeightedEdge<V>> adjacent(V vertex);

    int vertexCount();

    int edgeCount();
}