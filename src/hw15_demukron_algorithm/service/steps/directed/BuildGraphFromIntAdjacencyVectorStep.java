package hw15_demukron_algorithm.service.steps.directed;

import hw15_demukron_algorithm.libs.graphs.IntAdjacencyVectorGraphAdapter;
import hw15_demukron_algorithm.service.DirectedGraphContext;
import hw15_demukron_algorithm.service.steps.Step;

public class BuildGraphFromIntAdjacencyVectorStep<R> implements Step<DirectedGraphContext<R>> {
    @Override
    public void execute(DirectedGraphContext<R> context) {
        context.setGraph(
                IntAdjacencyVectorGraphAdapter.from(context.adjacencyVector())
        );
    }
}