package hw15_demukron_algorithm.service.steps.undirected;

import hw15_demukron_algorithm.service.UndirectedGraphContext;
import hw15_demukron_algorithm.service.steps.Step;

import java.util.HashSet;
import java.util.Set;

public class ValidateUndirectedEdgesStep<R> implements Step<UndirectedGraphContext<R>> {
    @Override
    public void execute(UndirectedGraphContext<R> context) {
        if (context.vertexCount() < 0) {
            throw new IllegalArgumentException("Vertex count must not be negative");
        }

        if (context.edges() == null) {
            throw new IllegalArgumentException("Edges must not be null");
        }

        Set<String> uniqueEdges = new HashSet<>();

        for (int i = 0; i < context.edges().length; i++) {
            int[] edge = context.edges()[i];

            if (edge == null || edge.length != 2) {
                throw new IllegalArgumentException("Edge must contain exactly two vertices: " + i);
            }

            int first = edge[0];
            int second = edge[1];

            if (first < 0 || first >= context.vertexCount()) {
                throw new IllegalArgumentException("Invalid vertex: " + first);
            }

            if (second < 0 || second >= context.vertexCount()) {
                throw new IllegalArgumentException("Invalid vertex: " + second);
            }

            if (first == second) {
                throw new IllegalArgumentException("Self-loop is not allowed: " + first);
            }

            int min = Math.min(first, second);
            int max = Math.max(first, second);
            String key = min + ":" + max;

            if (!uniqueEdges.add(key)) {
                throw new IllegalArgumentException(
                        "Duplicate undirected edge: " + first + " - " + second
                );
            }
        }
    }
}