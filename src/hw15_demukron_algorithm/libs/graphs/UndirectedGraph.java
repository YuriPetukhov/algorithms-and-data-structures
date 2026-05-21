package hw15_demukron_algorithm.libs.graphs;

import java.util.List;
import java.util.Set;

public interface UndirectedGraph<V> {
    void addVertex(V vertex);

    void addEdge(V first, V second);

    Set<V> vertices();

    List<V> adjacent(V vertex);

    int vertexCount();

    int edgeCount();
}