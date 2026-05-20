package hw14_kosaraju_algorithm.service.steps;

import hw14_kosaraju_algorithm.service.GraphContext;

public class GetAdjacencyStep implements Step<GraphContext> {
    @Override
    public void execute(GraphContext context) {
        if (context.vertex() == null) {
            throw new IllegalArgumentException("Vertex is required for adjacency step");
        }

        context.setResult(context.graph().adjacent(context.vertex()));
    }
}