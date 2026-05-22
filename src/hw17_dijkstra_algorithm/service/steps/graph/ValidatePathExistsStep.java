package hw17_dijkstra_algorithm.service.steps.graph;

import hw17_dijkstra_algorithm.service.ShortestPathContext;
import hw17_dijkstra_algorithm.service.steps.Step;

public class ValidatePathExistsStep<R> implements Step<ShortestPathContext<R>> {
    @Override
    public void execute(ShortestPathContext<R> context) {
        if (context.path() == null) {
            throw new IllegalStateException("Path must be calculated before validation");
        }

        if (context.start() != context.target() && context.path().isEmpty()) {
            throw new IllegalStateException(
                    "Path does not exist from " + context.start() + " to " + context.target()
            );
        }
    }
}