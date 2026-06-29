package hw22_probabilistic_algorithms.benchmark;

import hw22_probabilistic_algorithms.experiment.MetricsAccumulator;
import hw22_probabilistic_algorithms.experiment.model.ExperimentResult;

public final class MinHashMetricsAccumulator {

    private final MetricsAccumulator classification =
            new MetricsAccumulator();

    private long count;
    private double absoluteErrorSum;
    private double squaredErrorSum;
    private double maximumAbsoluteError;

    public void add(
            boolean actualNearDuplicate,
            boolean predictedNearDuplicate,
            double exactSimilarity,
            double estimatedSimilarity
    ) {
        classification.add(
                actualNearDuplicate,
                predictedNearDuplicate
        );

        double error = Math.abs(
                exactSimilarity - estimatedSimilarity
        );

        count++;
        absoluteErrorSum += error;
        squaredErrorSum += error * error;
        maximumAbsoluteError = Math.max(
                maximumAbsoluteError,
                error
        );
    }

    public long total() {
        return count;
    }

    public ExperimentResult classification() {
        return classification.toResult(0);
    }

    public double meanAbsoluteError() {
        return count == 0L
                ? 0.0
                : absoluteErrorSum / count;
    }

    public double rootMeanSquaredError() {
        return count == 0L
                ? 0.0
                : Math.sqrt(squaredErrorSum / count);
    }

    public double maximumAbsoluteError() {
        return maximumAbsoluteError;
    }
}
