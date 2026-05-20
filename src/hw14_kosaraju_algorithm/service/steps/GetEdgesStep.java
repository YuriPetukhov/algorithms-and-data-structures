package hw14_kosaraju_algorithm.service.steps;

import hw14_kosaraju_algorithm.service.GraphContext;

public class GetEdgesStep implements Step<GraphContext> {
    @Override
    public void execute(GraphContext context) {
        context.setResult(context.graph().edges());
    }
}