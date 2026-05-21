package hw16_mst_algorithm.libs.mst;

import hw16_mst_algorithm.libs.graphs.WeightedEdge;
import hw16_mst_algorithm.libs.graphs.WeightedUndirectedGraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class PrimAlgorithm<V> {
    public List<WeightedEdge<V>> findMst(WeightedUndirectedGraph<V> graph) {
        if (graph.vertexCount() == 0) {
            return new ArrayList<>();
        }

        Set<V> visited = new HashSet<>();
        List<WeightedEdge<V>> result = new ArrayList<>();
        PriorityQueue<WeightedEdge<V>> queue =
                new PriorityQueue<>(Comparator.comparingInt(WeightedEdge::weight));

        V start = graph.vertices().iterator().next();
        visited.add(start);
        queue.addAll(graph.adjacent(start));

        while (!queue.isEmpty() && result.size() < graph.vertexCount() - 1) {
            WeightedEdge<V> edge = queue.poll();

            V next = null;

            if (visited.contains(edge.v1()) && !visited.contains(edge.v2())) {
                next = edge.v2();
            } else if (visited.contains(edge.v2()) && !visited.contains(edge.v1())) {
                next = edge.v1();
            }

            if (next == null) {
                continue;
            }

            visited.add(next);
            result.add(edge);

            for (WeightedEdge<V> adjacentEdge : graph.adjacent(next)) {
                if (!visited.contains(adjacentEdge.v1()) || !visited.contains(adjacentEdge.v2())) {
                    queue.add(adjacentEdge);
                }
            }
        }

        if (result.size() != graph.vertexCount() - 1) {
            throw new IllegalStateException("Graph is disconnected. MST does not exist.");
        }

        return result;
    }
}