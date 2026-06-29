package hw22_probabilistic_algorithms.experiment;

import hw22_probabilistic_algorithms.experiment.model.ExperimentResult;

public final class MetricsAccumulator {

    private long truePositive;
    private long falsePositive;
    private long trueNegative;
    private long falseNegative;

    public void add(
            boolean actualNearDuplicate,
            boolean predictedNearDuplicate
    ) {
        if (actualNearDuplicate
                && predictedNearDuplicate) {

            truePositive++;
        } else if (!actualNearDuplicate
                && predictedNearDuplicate) {

            falsePositive++;
        } else if (!actualNearDuplicate) {
            trueNegative++;
        } else {
            falseNegative++;
        }
    }

    public long total() {
        return truePositive
                + falsePositive
                + trueNegative
                + falseNegative;
    }

    public ExperimentResult toResult(
            int maxDistance
    ) {
        return new ExperimentResult(
                maxDistance,
                truePositive,
                falsePositive,
                trueNegative,
                falseNegative
        );
    }
}
