package hw14_kosaraju_algorithm.service.steps;

import hw14_kosaraju_algorithm.libs.graphs.kosaraju.KosarajuScc;
import hw14_kosaraju_algorithm.service.GraphContext;

public class KosarajuStep implements Step<GraphContext> {
    private final KosarajuScc kosarajuScc = new KosarajuScc();

    @Override
    public void execute(GraphContext context) {
        context.setResult(kosarajuScc.findScc(context.graph()));
    }
}