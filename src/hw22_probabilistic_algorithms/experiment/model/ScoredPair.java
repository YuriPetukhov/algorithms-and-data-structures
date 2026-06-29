package hw22_probabilistic_algorithms.experiment.model;

public record ScoredPair(
        String pairId,
        boolean nearDuplicate,
        int hammingDistance
) {
}
