package hw15_demukron_algorithm.service.steps.demukron;

import hw15_demukron_algorithm.libs.graphs.demukron.DemukronAlgorithm;
import hw15_demukron_algorithm.service.DirectedGraphContext;
import hw15_demukron_algorithm.service.steps.Step;

public class DemukronStep implements Step<DirectedGraphContext<int[][]>> {
    private final DemukronAlgorithm<Integer> algorithm = new DemukronAlgorithm<>();

    @Override
    public void execute(DirectedGraphContext<int[][]> context) {
        if (context.graph() == null) {
            throw new IllegalStateException("Graph must be built before running Demukron algorithm");
        }

        context.setLevels(
                algorithm.sortByLevels(context.graph())
        );
    }
}