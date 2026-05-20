package hw14_kosaraju_algorithm.libs.graphs;

import java.util.List;

public interface DirectedGraph {
    int vertexCount();

    int edgeCount();

    void addEdge(int from, int to);

    List<Integer> adjacent(int vertex);

    List<Edge<Integer>> edges();

    DirectedGraph reversed();
}