package hw02_dynamic_programming_and_testing.test.caseflow.steps;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw02_dynamic_programming_and_testing.app.core.Task;
import hw02_dynamic_programming_and_testing.test.caseflow.CaseContext;
import hw02_dynamic_programming_and_testing.test.caseflow.CaseStep;

public class ExecuteTaskStep implements CaseStep {

    @Override
    public void execute(CaseContext ctx) throws Exception {

        int runs = Math.max(1, ctx.benchmarkRuns());

        Task task = ctx.task();
        String input = ctx.inputRaw();

        long best = Long.MAX_VALUE;
        String actual = null;

        if (task instanceof MeasurableTask<?, ?> mt) {

            @SuppressWarnings("unchecked")
            MeasurableTask<Object, Object> measurable =
                    (MeasurableTask<Object, Object>) mt;

            Object parsed = measurable.parse(input);

            for (int i = 0; i < runs; i++) {
                long start = System.nanoTime();

                Object result = measurable.compute(parsed);

                long time = System.nanoTime() - start;

                if (time < best) {
                    best = time;
                    actual = measurable.format(result);
                }
            }

        } else {
            for (int i = 0; i < runs; i++) {
                long start = System.nanoTime();

                String result = task.run(input);

                long time = System.nanoTime() - start;

                if (time < best) {
                    best = time;
                    actual = result;
                }
            }
        }

        ctx.setActualRaw(actual);
        ctx.setTimeNanos(best);
    }
}
