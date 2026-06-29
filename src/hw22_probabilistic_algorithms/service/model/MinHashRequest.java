package hw22_probabilistic_algorithms.service.model;

public record MinHashRequest(
        String firstText,
        String secondText,
        double similarityThreshold
) {
}
