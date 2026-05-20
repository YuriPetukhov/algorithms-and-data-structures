package hw14_kosaraju_algorithm.libs.graphs.kosaraju;

import hw14_kosaraju_algorithm.libs.graphs.DirectedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KosarajuScc {
    public List<List<Integer>> findScc(DirectedGraph graph) {
        boolean[] visited = new boolean[graph.vertexCount()];
        List<Integer> order = new ArrayList<>();

        for (int vertex = 0; vertex < graph.vertexCount(); vertex++) {
            if (!visited[vertex]) {
                dfsOrder(graph, vertex, visited, order);
            }
        }

        DirectedGraph reversed = graph.reversed();
        boolean[] reversedVisited = new boolean[graph.vertexCount()];
        List<List<Integer>> components = new ArrayList<>();

        Collections.reverse(order);

        for (int vertex : order) {
            if (!reversedVisited[vertex]) {
                List<Integer> component = new ArrayList<>();
                dfsComponent(reversed, vertex, reversedVisited, component);
                components.add(component);
            }
        }

        return components;
    }

    private void dfsOrder(
            DirectedGraph graph,
            int vertex,
            boolean[] visited,
            List<Integer> order
    ) {
        visited[vertex] = true;

        for (int next : graph.adjacent(vertex)) {
            if (!visited[next]) {
                dfsOrder(graph, next, visited, order);
            }
        }

        order.add(vertex);
    }

    private void dfsComponent(
            DirectedGraph graph,
            int vertex,
            boolean[] visited,
            List<Integer> component
    ) {
        visited[vertex] = true;
        component.add(vertex);

        for (int next : graph.adjacent(vertex)) {
            if (!visited[next]) {
                dfsComponent(graph, next, visited, component);
            }
        }
    }
}