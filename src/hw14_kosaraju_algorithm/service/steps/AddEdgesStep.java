package hw14_kosaraju_algorithm.service.steps;

import hw14_kosaraju_algorithm.libs.graphs.Edge;
import hw14_kosaraju_algorithm.service.GraphContext;

import java.util.List;

public class AddEdgesStep implements Step<GraphContext> {
    private final List<Edge<Integer>> edges;

    public AddEdgesStep(List<Edge<Integer>> edges) {
        this.edges = edges;
    }

    @Override
    public void execute(GraphContext context) {
        for (Edge<Integer> edge : edges) {
            context.graph().addEdge(edge.from(), edge.to());
        }
    }
}