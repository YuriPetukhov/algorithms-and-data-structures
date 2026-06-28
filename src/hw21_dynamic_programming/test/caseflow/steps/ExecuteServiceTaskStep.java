package hw21_dynamic_programming.test.caseflow.steps;

import hw21_dynamic_programming.test.bridge.MeasurableTask;
import hw21_dynamic_programming.test.caseflow.CaseContext;
import hw21_dynamic_programming.test.caseflow.CaseStep;
import hw21_dynamic_programming.test.model.TestResult;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class ExecuteServiceTaskStep implements CaseStep {

    @Override
    public void execute(CaseContext context) throws Exception {
        executeTyped(context, context.task());
    }

    private static <I, O> void executeTyped(
            CaseContext context,
            MeasurableTask<I, O> task
    ) throws Exception {
        I parsedInput = task.parse(context.inputRaw());
        long bestNanos = Long.MAX_VALUE;
        String bestOutput = null;

        for (int run = 0; run < context.benchmarkRuns(); run++) {
            TimedResult<O> timed = executeOnce(
                    () -> task.compute(parsedInput),
                    context.timeoutEnabled(),
                    context.timeoutMillis()
            );
            if (timed.timeout()) {
                continue;
            }
            if (timed.nanos() < bestNanos) {
                bestNanos = timed.nanos();
                bestOutput = task.format(timed.value());
            }
        }

        if (bestOutput == null) {
            long nanos = context.timeoutEnabled()
                    ? TimeUnit.MILLISECONDS.toNanos(context.timeoutMillis())
                    : -1L;
            context.result(TestResult.timeout(context.testCase().name(), nanos));
            return;
        }

        context.actualRaw(bestOutput);
        context.timeNanos(bestNanos);
    }

    private static <T> TimedResult<T> executeOnce(
            Callable<T> operation,
            boolean timeoutEnabled,
            long timeoutMillis
    ) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor(runnable -> {
            Thread thread = new Thread(runnable, "file-test-worker");
            thread.setDaemon(true);
            return thread;
        });
        long start = System.nanoTime();
        Future<T> future = executor.submit(operation);
        try {
            T value = timeoutEnabled
                    ? future.get(timeoutMillis, TimeUnit.MILLISECONDS)
                    : future.get();
            return new TimedResult<>(value, System.nanoTime() - start, false);
        } catch (TimeoutException exception) {
            future.cancel(true);
            return new TimedResult<>(null, -1L, true);
        } catch (ExecutionException exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof Exception checked) {
                throw checked;
            }
            throw new RuntimeException(cause);
        } finally {
            executor.shutdownNow();
        }
    }

    private record TimedResult<T>(T value, long nanos, boolean timeout) {
    }
}
