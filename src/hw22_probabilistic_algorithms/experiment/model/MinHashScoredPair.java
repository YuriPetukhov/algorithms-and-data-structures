package hw22_probabilistic_algorithms.experiment.model;

public record MinHashScoredPair(
        String pairId,
        boolean nearDuplicate,
        double estimatedSimilarity,
        double exactJaccardSimilarity
) {

    public double absoluteError() {
        return Math.abs(
                exactJaccardSimilarity
                        - estimatedSimilarity
        );
    }
}
