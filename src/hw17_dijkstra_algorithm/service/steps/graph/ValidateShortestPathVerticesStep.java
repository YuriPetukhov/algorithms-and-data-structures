package hw17_dijkstra_algorithm.service.steps.graph;

import hw17_dijkstra_algorithm.service.ShortestPathContext;
import hw17_dijkstra_algorithm.service.steps.Step;

public class ValidateShortestPathVerticesStep<R> implements Step<ShortestPathContext<R>> {
    @Override
    public void execute(ShortestPathContext<R> context) {
        int vertexCount = context.adjacencyVector().length;

        if (context.start() < 0 || context.start() >= vertexCount) {
            throw new IllegalArgumentException("Invalid start vertex: " + context.start());
        }

        if (context.target() < 0 || context.target() >= vertexCount) {
            throw new IllegalArgumentException("Invalid target vertex: " + context.target());
        }
    }
}