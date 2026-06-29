package hw22_probabilistic_algorithms.experiment.model;

public record ExperimentResult(
        int maxDistance,
        long truePositive,
        long falsePositive,
        long trueNegative,
        long falseNegative
) {

    public long total() {
        return truePositive
                + falsePositive
                + trueNegative
                + falseNegative;
    }

    public double accuracy() {
        return divide(
                truePositive + trueNegative,
                total()
        );
    }

    public double precision() {
        return divide(
                truePositive,
                truePositive + falsePositive
        );
    }

    public double recall() {
        return divide(
                truePositive,
                truePositive + falseNegative
        );
    }

    public double f1() {
        double precision = precision();
        double recall = recall();

        if (precision + recall == 0.0) {
            return 0.0;
        }

        return 2.0
                * precision
                * recall
                / (precision + recall);
    }

    public double falsePositiveRate() {
        return divide(
                falsePositive,
                falsePositive + trueNegative
        );
    }

    public double falseNegativeRate() {
        return divide(
                falseNegative,
                falseNegative + truePositive
        );
    }

    private double divide(long numerator, long denominator) {
        if (denominator == 0L) {
            return 0.0;
        }

        return numerator / (double) denominator;
    }
}
