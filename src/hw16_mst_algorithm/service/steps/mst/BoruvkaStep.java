package hw16_mst_algorithm.service.steps.mst;

import hw16_mst_algorithm.libs.mst.BoruvkaAlgorithm;
import hw16_mst_algorithm.service.WeightedGraphContext;
import hw16_mst_algorithm.service.steps.Step;

public class BoruvkaStep<R> implements Step<WeightedGraphContext<R>> {
    private final BoruvkaAlgorithm<Integer> algorithm = new BoruvkaAlgorithm<>();

    @Override
    public void execute(WeightedGraphContext<R> context) {
        if (context.graph() == null) {
            throw new IllegalStateException("Graph must be built before running Boruvka algorithm");
        }

        context.setMst(
                algorithm.findMst(context.graph())
        );
    }
}