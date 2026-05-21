package hw15_demukron_algorithm.service.steps.tarjan;

import hw15_demukron_algorithm.libs.graphs.tarjan.TarjanSccAlgorithm;
import hw15_demukron_algorithm.service.DirectedGraphContext;
import hw15_demukron_algorithm.service.steps.Step;

import java.util.List;

public class TarjanStep implements Step<DirectedGraphContext<List<List<Integer>>>> {
    private final TarjanSccAlgorithm<Integer> algorithm = new TarjanSccAlgorithm<>();

    @Override
    public void execute(DirectedGraphContext<List<List<Integer>>> context) {
        if (context.graph() == null) {
            throw new IllegalStateException("Graph must be built before running Tarjan algorithm");
        }

        context.setResult(
                algorithm.findScc(context.graph())
        );
    }
}