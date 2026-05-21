package hw15_demukron_algorithm.service.steps.undirected;

import hw15_demukron_algorithm.libs.graphs.Edge;
import hw15_demukron_algorithm.libs.graphs.bridges.BridgeFinder;
import hw15_demukron_algorithm.service.UndirectedGraphContext;
import hw15_demukron_algorithm.service.steps.Step;

import java.util.List;

public class BridgeFinderStep implements Step<UndirectedGraphContext<List<Edge<Integer>>>> {
    private final BridgeFinder<Integer> finder = new BridgeFinder<>();

    @Override
    public void execute(UndirectedGraphContext<List<Edge<Integer>>> context) {
        if (context.graph() == null) {
            throw new IllegalStateException("Graph must be built before finding bridges");
        }

        context.setResult(
                finder.findBridges(context.graph())
        );
    }
}