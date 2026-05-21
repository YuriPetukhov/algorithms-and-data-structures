package hw16_mst_algorithm.service.steps.mst;

import hw16_mst_algorithm.libs.mst.PrimAlgorithm;
import hw16_mst_algorithm.service.WeightedGraphContext;
import hw16_mst_algorithm.service.steps.Step;

public class PrimStep<R> implements Step<WeightedGraphContext<R>> {
    private final PrimAlgorithm<Integer> algorithm = new PrimAlgorithm<>();

    @Override
    public void execute(WeightedGraphContext<R> context) {
        if (context.graph() == null) {
            throw new IllegalStateException("Graph must be built before running Prim algorithm");
        }

        context.setMst(
                algorithm.findMst(context.graph())
        );
    }
}