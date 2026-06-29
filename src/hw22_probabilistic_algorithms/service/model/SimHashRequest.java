package hw22_probabilistic_algorithms.service.model;

public record SimHashRequest(
        String firstText,
        String secondText,
        int maxDistance
) {
}
