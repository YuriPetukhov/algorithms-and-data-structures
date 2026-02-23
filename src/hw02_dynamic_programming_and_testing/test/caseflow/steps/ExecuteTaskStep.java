package hw02_dynamic_programming_and_testing.test.caseflow.steps;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw02_dynamic_programming_and_testing.app.core.Task;
import hw02_dynamic_programming_and_testing.test.caseflow.CaseContext;
import hw02_dynamic_programming_and_testing.test.caseflow.CaseStep;

import java.util.concurrent.*;

import static hw02_dynamic_programming_and_testing.test.model.TestStatus.TIMEOUT;

public class ExecuteTaskStep implements CaseStep {

    private long timeoutLimitMillis;
    private boolean timeoutEnabled;

    @Override
    public void execute(CaseContext ctx) throws Exception {
        int runs = Math.max(1, ctx.benchmarkRuns());
        Task task = ctx.task();
        String input = ctx.inputRaw();

        timeoutLimitMillis = ctx.timeoutMillis();
        timeoutEnabled = ctx.timeoutEnabled();

        long best = Long.MAX_VALUE;
        String bestActual = null;

        if (task instanceof MeasurableTask<?, ?> mt) {
            @SuppressWarnings("unchecked")
            MeasurableTask<Object, Object> measurable = (MeasurableTask<Object, Object>) mt;

            Object parsed = measurable.parse(input);

            for (int i = 0; i < runs; i++) {
                TimedResult<Object> tr = runWithTimeoutPerRun(() -> measurable.compute(parsed));
                if (tr.timeout) {
                    continue;
                }
                if (tr.nanos < best) {
                    best = tr.nanos;
                    bestActual = measurable.format(tr.value);
                }
            }

        } else {
            for (int i = 0; i < runs; i++) {
                TimedResult<String> tr = runWithTimeoutPerRun(() -> task.run(input));
                if (tr.timeout) {
                    continue;
                }
                if (tr.nanos < best) {
                    best = tr.nanos;
                    bestActual = tr.value;
                }
            }
        }

        if (bestActual == null) {
            ctx.setActualRaw(TIMEOUT.name());
            ctx.setTimeNanos(timeoutEnabled ? TimeUnit.MILLISECONDS.toNanos(timeoutLimitMillis) : -1L);
        } else {
            ctx.setActualRaw(bestActual);
            ctx.setTimeNanos(best);
        }

    }

    private static final class TimedResult<T> {
        final T value;
        final long nanos;
        final boolean timeout;

        private TimedResult(T value, long nanos, boolean timeout) {
            this.value = value;
            this.nanos = nanos;
            this.timeout = timeout;
        }

        static <T> TimedResult<T> ok(T value, long nanos) { return new TimedResult<>(value, nanos, false); }
        static <T> TimedResult<T> timeout() { return new TimedResult<>(null, -1L, true); }
    }

    private <T> TimedResult<T> runWithTimeoutPerRun(Callable<T> call) throws Exception {
        ExecutorService exec = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r, "task-run-worker");
            t.setDaemon(true);
            return t;
        });

        long start = System.nanoTime();
        Future<T> f = exec.submit(call);

        try {
            T value;
            if (!timeoutEnabled) {
                value = f.get();
            } else {
                value = f.get(timeoutLimitMillis, TimeUnit.MILLISECONDS);
            }
            long nanos = System.nanoTime() - start;
            return TimedResult.ok(value, nanos);

        } catch (TimeoutException e) {
            f.cancel(true);
            return TimedResult.timeout();

        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof Exception ex) throw ex;
            throw new RuntimeException(cause);

        } finally {
            exec.shutdownNow();
        }
    }
}