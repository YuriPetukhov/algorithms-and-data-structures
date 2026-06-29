package hw22_probabilistic_algorithms.service.model;

public record MinHashResult(
        int[] firstSignature,
        int[] secondSignature,
        double estimatedSimilarity,
        double exactJaccardSimilarity,
        double absoluteError,
        boolean similar
) {

    public MinHashResult {
        firstSignature = firstSignature.clone();
        secondSignature = secondSignature.clone();
    }

    @Override
    public int[] firstSignature() {
        return firstSignature.clone();
    }

    @Override
    public int[] secondSignature() {
        return secondSignature.clone();
    }
}
