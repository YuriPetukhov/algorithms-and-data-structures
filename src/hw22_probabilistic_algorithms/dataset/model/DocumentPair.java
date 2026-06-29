package hw22_probabilistic_algorithms.dataset.model;

public record DocumentPair(
        String pairId,
        boolean nearDuplicate,
        String pairType,
        String mutation,
        String firstText,
        String secondText
) {
}
