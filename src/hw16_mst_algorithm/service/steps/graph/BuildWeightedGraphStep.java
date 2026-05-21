package hw16_mst_algorithm.service.steps.graph;

import hw16_mst_algorithm.libs.graphs.IntWeightedAdjacencyVectorGraphAdapter;
import hw16_mst_algorithm.service.WeightedGraphContext;
import hw16_mst_algorithm.service.steps.Step;

public class BuildWeightedGraphStep<R> implements Step<WeightedGraphContext<R>> {
    @Override
    public void execute(WeightedGraphContext<R> context) {
        context.setGraph(
                IntWeightedAdjacencyVectorGraphAdapter.from(
                        context.adjacencyVector(),
                        context.weightVector()
                )
        );
    }
}