package hw15_demukron_algorithm.libs.graphs.bridges;

import hw15_demukron_algorithm.libs.graphs.Edge;
import hw15_demukron_algorithm.libs.graphs.UndirectedGraph;

import java.util.*;

public class BridgeFinder<V> {
    private int time;
    private final Map<V, Integer> tin = new HashMap<>();
    private final Map<V, Integer> low = new HashMap<>();
    private final Set<V> visited = new HashSet<>();
    private final List<Edge<V>> bridges = new ArrayList<>();

    public List<Edge<V>> findBridges(UndirectedGraph<V> graph) {
        time = 0;
        tin.clear();
        low.clear();
        visited.clear();
        bridges.clear();

        for (V vertex : graph.vertices()) {
            if (!visited.contains(vertex)) {
                dfs(graph, vertex, null);
            }
        }

        return bridges;
    }

    private void dfs(UndirectedGraph<V> graph, V vertex, V parent) {
        visited.add(vertex);
        tin.put(vertex, time);
        low.put(vertex, time);
        time++;

        for (V next : graph.adjacent(vertex)) {
            if (next.equals(parent)) {
                continue;
            }

            if (visited.contains(next)) {
                low.put(vertex, Math.min(low.get(vertex), tin.get(next)));
            } else {
                dfs(graph, next, vertex);

                low.put(vertex, Math.min(low.get(vertex), low.get(next)));

                if (low.get(next) > tin.get(vertex)) {
                    bridges.add(new Edge<>(vertex, next));
                }
            }
        }
    }
}