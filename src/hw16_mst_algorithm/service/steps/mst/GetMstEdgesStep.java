package hw16_mst_algorithm.service.steps.mst;

import hw16_mst_algorithm.libs.graphs.Edge;
import hw16_mst_algorithm.libs.mst.MstEdgeConverter;
import hw16_mst_algorithm.service.WeightedGraphContext;
import hw16_mst_algorithm.service.steps.Step;

public class GetMstEdgesStep implements Step<WeightedGraphContext<Edge[]>> {
    @Override
    public void execute(WeightedGraphContext<Edge[]> context) {
        if (context.mst() == null) {
            throw new IllegalStateException("MST must be calculated before converting result");
        }

        context.setResult(
                MstEdgeConverter.toEdgeArray(context.mst())
        );
    }
}