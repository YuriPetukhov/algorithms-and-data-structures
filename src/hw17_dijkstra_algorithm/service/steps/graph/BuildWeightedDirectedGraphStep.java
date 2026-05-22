package hw17_dijkstra_algorithm.service.steps.graph;

import hw17_dijkstra_algorithm.libs.graphs.IntWeightedAdjacencyVectorGraphAdapter;
import hw17_dijkstra_algorithm.service.ShortestPathContext;
import hw17_dijkstra_algorithm.service.steps.Step;

public class BuildWeightedDirectedGraphStep<R> implements Step<ShortestPathContext<R>> {
    @Override
    public void execute(ShortestPathContext<R> context) {
        context.setGraph(
                IntWeightedAdjacencyVectorGraphAdapter.from(
                        context.adjacencyVector(),
                        context.weightVector()
                )
        );
    }
}