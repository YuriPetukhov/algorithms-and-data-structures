package hw15_demukron_algorithm.libs.articulation;

import hw15_demukron_algorithm.libs.graphs.UndirectedGraph;

import java.util.*;

public class ArticulationPointFinder<V> {
    private int time;
    private final Map<V, Integer> tin = new HashMap<>();
    private final Map<V, Integer> low = new HashMap<>();
    private final Set<V> visited = new HashSet<>();
    private final Set<V> points = new LinkedHashSet<>();

    public Set<V> findArticulationPoints(UndirectedGraph<V> graph) {
        time = 0;
        tin.clear();
        low.clear();
        visited.clear();
        points.clear();

        for (V vertex : graph.vertices()) {
            if (!visited.contains(vertex)) {
                dfs(graph, vertex, null);
            }
        }

        return points;
    }

    private void dfs(UndirectedGraph<V> graph, V vertex, V parent) {
        visited.add(vertex);
        tin.put(vertex, time);
        low.put(vertex, time);
        time++;

        int children = 0;

        for (V next : graph.adjacent(vertex)) {
            if (next.equals(parent)) {
                continue;
            }

            if (visited.contains(next)) {
                low.put(vertex, Math.min(low.get(vertex), tin.get(next)));
            } else {
                dfs(graph, next, vertex);

                low.put(vertex, Math.min(low.get(vertex), low.get(next)));

                if (parent != null && low.get(next) >= tin.get(vertex)) {
                    points.add(vertex);
                }

                children++;
            }
        }

        if (parent == null && children > 1) {
            points.add(vertex);
        }
    }
}