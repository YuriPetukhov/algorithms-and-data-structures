package hw16_mst_algorithm.service.steps.mst;

import hw16_mst_algorithm.libs.mst.KruskalAlgorithm;
import hw16_mst_algorithm.service.WeightedGraphContext;
import hw16_mst_algorithm.service.steps.Step;

public class KruskalStep<R> implements Step<WeightedGraphContext<R>> {
    private final KruskalAlgorithm<Integer> algorithm = new KruskalAlgorithm<>();

    @Override
    public void execute(WeightedGraphContext<R> context) {
        if (context.graph() == null) {
            throw new IllegalStateException("Graph must be built before running Kruskal algorithm");
        }

        context.setMst(
                algorithm.findMst(context.graph())
        );
    }
}