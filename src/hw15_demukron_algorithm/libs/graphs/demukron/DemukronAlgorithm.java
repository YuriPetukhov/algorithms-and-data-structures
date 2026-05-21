package hw15_demukron_algorithm.libs.graphs.demukron;

import hw15_demukron_algorithm.libs.graphs.DirectedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemukronAlgorithm<V> {
    public List<List<V>> sortByLevels(DirectedGraph<V> graph) {
        Map<V, Integer> incomingDegree = calculateIncomingDegrees(graph);
        List<List<V>> levels = new ArrayList<>();
        int processedCount = 0;

        while (processedCount < graph.vertexCount()) {
            List<V> currentLevel = findZeroIncomingVertices(
                    graph,
                    incomingDegree
            );

            if (currentLevel.isEmpty()) {
                throw new IllegalStateException(
                        "Graph contains a cycle. Demukron algorithm requires a directed acyclic graph."
                );
            }

            levels.add(currentLevel);
            processedCount += currentLevel.size();

            for (V vertex : currentLevel) {
                incomingDegree.put(vertex, -1);

                for (V adjacent : graph.adjacent(vertex)) {
                    incomingDegree.put(
                            adjacent,
                            incomingDegree.get(adjacent) - 1
                    );
                }
            }
        }

        return levels;
    }

    private Map<V, Integer> calculateIncomingDegrees(DirectedGraph<V> graph) {
        Map<V, Integer> incomingDegree = new HashMap<>();

        for (V vertex : graph.vertices()) {
            incomingDegree.put(vertex, 0);
        }

        for (V vertex : graph.vertices()) {
            for (V adjacent : graph.adjacent(vertex)) {
                incomingDegree.put(
                        adjacent,
                        incomingDegree.get(adjacent) + 1
                );
            }
        }

        return incomingDegree;
    }

    private List<V> findZeroIncomingVertices(
            DirectedGraph<V> graph,
            Map<V, Integer> incomingDegree
    ) {
        List<V> result = new ArrayList<>();

        for (V vertex : graph.vertices()) {
            if (incomingDegree.get(vertex) == 0) {
                result.add(vertex);
            }
        }

        return result;
    }
}