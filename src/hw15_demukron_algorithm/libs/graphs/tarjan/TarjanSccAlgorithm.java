package hw15_demukron_algorithm.libs.graphs.tarjan;

import hw15_demukron_algorithm.libs.graphs.DirectedGraph;
import hw15_demukron_algorithm.libs.structures.FactorArrayStack;
import hw15_demukron_algorithm.libs.structures.Stack;

import java.util.*;


public class TarjanSccAlgorithm<V> {
    private int time;
    private final Map<V, Integer> index = new HashMap<>();
    private final Map<V, Integer> lowLink = new HashMap<>();
    private final Stack<V> stack = new FactorArrayStack<>();
    private final Set<V> onStack = new HashSet<>();
    private final List<List<V>> components = new ArrayList<>();

    public List<List<V>> findScc(DirectedGraph<V> graph) {
        time = 0;
        index.clear();
        lowLink.clear();
        stack.clear();
        onStack.clear();
        components.clear();

        for (V vertex : graph.vertices()) {
            if (!index.containsKey(vertex)) {
                dfs(graph, vertex);
            }
        }

        return components;
    }

    private void dfs(DirectedGraph<V> graph, V vertex) {
        index.put(vertex, time);
        lowLink.put(vertex, time);
        time++;

        stack.push(vertex);
        onStack.add(vertex);

        for (V next : graph.adjacent(vertex)) {
            if (!index.containsKey(next)) {
                dfs(graph, next);
                lowLink.put(
                        vertex,
                        Math.min(lowLink.get(vertex), lowLink.get(next))
                );
            } else if (onStack.contains(next)) {
                lowLink.put(
                        vertex,
                        Math.min(lowLink.get(vertex), index.get(next))
                );
            }
        }

        if (lowLink.get(vertex).equals(index.get(vertex))) {
            List<V> component = new ArrayList<>();
            V current;

            do {
                current = stack.pop();
                onStack.remove(current);
                component.add(current);
            } while (!current.equals(vertex));

            components.add(component);
        }
    }
}