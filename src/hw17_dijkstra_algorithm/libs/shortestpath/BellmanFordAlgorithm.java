package hw17_dijkstra_algorithm.libs.shortestpath;

import hw17_dijkstra_algorithm.libs.graphs.WeightedDirectedGraph;
import hw17_dijkstra_algorithm.libs.graphs.WeightedEdge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BellmanFordAlgorithm<V> {
    public List<WeightedEdge<V>> findShortestPath(
            WeightedDirectedGraph<V> graph,
            V start,
            V target
    ) {
        Map<V, Integer> distance = new HashMap<>();
        Map<V, WeightedEdge<V>> previousEdge = new HashMap<>();

        for (V vertex : graph.vertices()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }

        distance.put(start, 0);

        for (int i = 0; i < graph.vertexCount() - 1; i++) {
            boolean changed = false;

            for (WeightedEdge<V> edge : graph.edges()) {
                int fromDistance = distance.get(edge.from());

                if (fromDistance == Integer.MAX_VALUE) {
                    continue;
                }

                int nextDistance = fromDistance + edge.weight();

                if (nextDistance < distance.get(edge.to())) {
                    distance.put(edge.to(), nextDistance);
                    previousEdge.put(edge.to(), edge);
                    changed = true;
                }
            }

            if (!changed) {
                break;
            }
        }

        checkNegativeCycle(graph, distance);

        return restorePath(start, target, previousEdge);
    }

    private void checkNegativeCycle(
            WeightedDirectedGraph<V> graph,
            Map<V, Integer> distance
    ) {
        for (WeightedEdge<V> edge : graph.edges()) {
            int fromDistance = distance.get(edge.from());

            if (fromDistance == Integer.MAX_VALUE) {
                continue;
            }

            if (fromDistance + edge.weight() < distance.get(edge.to())) {
                throw new IllegalStateException("Graph contains a negative cycle");
            }
        }
    }

    private List<WeightedEdge<V>> restorePath(
            V start,
            V target,
            Map<V, WeightedEdge<V>> previousEdge
    ) {
        List<WeightedEdge<V>> path = new ArrayList<>();
        V current = target;

        while (!current.equals(start)) {
            WeightedEdge<V> edge = previousEdge.get(current);

            if (edge == null) {
                return new ArrayList<>();
            }

            path.add(edge);
            current = edge.from();
        }

        Collections.reverse(path);
        return path;
    }
}