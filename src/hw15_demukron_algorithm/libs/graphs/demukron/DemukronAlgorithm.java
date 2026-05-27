package hw15_demukron_algorithm.libs.graphs.demukron;

import hw15_demukron_algorithm.libs.graphs.DirectedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemukronAlgorithm<V> {
    public List<List<V>> sortByLevels(DirectedGraph<V> graph) {
        Map<V, Integer> incomingDegree = calculateIncomingDegrees(graph);
        List<V> currentLevel = findInitialZeroIncomingVertices(incomingDegree);
        List<List<V>> levels = new ArrayList<>();

        int processedCount = 0;

        while (!currentLevel.isEmpty()) {
            levels.add(currentLevel);
            processedCount += currentLevel.size();

            List<V> nextLevel = new ArrayList<>();

            for (V vertex : currentLevel) {
                incomingDegree.put(vertex, -1);

                for (V adjacent : graph.adjacent(vertex)) {
                    int updatedDegree = incomingDegree.get(adjacent) - 1;
                    incomingDegree.put(adjacent, updatedDegree);

                    if (updatedDegree == 0) {
                        nextLevel.add(adjacent);
                    }
                }
            }

            currentLevel = nextLevel;
        }

        if (processedCount != graph.vertexCount()) {
            throw new IllegalStateException(
                    "Graph contains a cycle. Demukron algorithm requires a directed acyclic graph."
            );
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
                incomingDegree.put(adjacent, incomingDegree.get(adjacent) + 1);
            }
        }

        return incomingDegree;
    }

    private List<V> findInitialZeroIncomingVertices(Map<V, Integer> incomingDegree) {
        List<V> result = new ArrayList<>();

        for (Map.Entry<V, Integer> entry : incomingDegree.entrySet()) {
            if (entry.getValue() == 0) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}