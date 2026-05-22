package hw17_dijkstra_algorithm.libs.shortestpath;

import hw17_dijkstra_algorithm.libs.graphs.WeightedDirectedGraph;
import hw17_dijkstra_algorithm.libs.graphs.WeightedEdge;
import hw17_dijkstra_algorithm.libs.structures.BinaryHeapPriorityQueue;
import hw17_dijkstra_algorithm.libs.structures.PriorityQueue;

import java.util.*;

public class DijkstraAlgorithm<V> {
    public List<WeightedEdge<V>> findShortestPath(
            WeightedDirectedGraph<V> graph,
            V start,
            V target
    ) {
        Map<V, Integer> distance = new HashMap<>();
        Map<V, WeightedEdge<V>> previousEdge = new HashMap<>();
        PriorityQueue<PathNode<V>> queue = new BinaryHeapPriorityQueue<>();

        for (V vertex : graph.vertices()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }

        distance.put(start, 0);
        queue.enqueue(0, new PathNode<>(start, 0));

        while (!queue.isEmpty()) {
            PathNode<V> current = queue.dequeue();

            if (current.distance() != distance.get(current.vertex())) {
                continue;
            }

            if (current.vertex().equals(target)) {
                break;
            }

            for (WeightedEdge<V> edge : graph.adjacent(current.vertex())) {
                int currentDistance = distance.get(current.vertex());

                if (currentDistance == Integer.MAX_VALUE) {
                    continue;
                }

                int nextDistance = currentDistance + edge.weight();

                if (nextDistance < distance.get(edge.to())) {
                    distance.put(edge.to(), nextDistance);
                    previousEdge.put(edge.to(), edge);
                    queue.enqueue(nextDistance, new PathNode<>(edge.to(), nextDistance));
                }
            }
        }

        if (distance.get(target) == Integer.MAX_VALUE) {
            return new ArrayList<>();
        }

        return restorePath(start, target, previousEdge);
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
                throw new IllegalStateException("Cannot restore path");
            }

            path.add(edge);
            current = edge.from();
        }

        Collections.reverse(path);
        return path;
    }
}