package hw15_demukron_algorithm.service.steps.demukron;

import hw15_demukron_algorithm.libs.graphs.demukron.IntLevelConverter;
import hw15_demukron_algorithm.service.DirectedGraphContext;
import hw15_demukron_algorithm.service.steps.Step;

public class GetLevelsStep implements Step<DirectedGraphContext<int[][]>> {
    @Override
    public void execute(DirectedGraphContext<int[][]> context) {
        if (context.levels() == null) {
            throw new IllegalStateException("Levels must be calculated before converting result");
        }

        context.setResult(
                IntLevelConverter.toIntArray(context.levels())
        );
    }
}