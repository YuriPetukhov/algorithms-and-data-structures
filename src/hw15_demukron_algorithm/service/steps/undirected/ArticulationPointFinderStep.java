package hw15_demukron_algorithm.service.steps.undirected;

import hw15_demukron_algorithm.libs.articulation.ArticulationPointFinder;
import hw15_demukron_algorithm.service.UndirectedGraphContext;
import hw15_demukron_algorithm.service.steps.Step;

import java.util.Set;

public class ArticulationPointFinderStep implements Step<UndirectedGraphContext<Set<Integer>>> {
    private final ArticulationPointFinder<Integer> finder = new ArticulationPointFinder<>();

    @Override
    public void execute(UndirectedGraphContext<Set<Integer>> context) {
        if (context.graph() == null) {
            throw new IllegalStateException("Graph must be built before finding articulation points");
        }

        context.setResult(
                finder.findArticulationPoints(context.graph())
        );
    }
}