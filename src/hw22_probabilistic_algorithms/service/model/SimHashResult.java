package hw22_probabilistic_algorithms.service.model;

public record SimHashResult(
        long firstFingerprint,
        long secondFingerprint,
        int hammingDistance,
        double similarity,
        boolean similar
) {

    public double similarityPercent() {
        return similarity * 100.0;
    }

    public String firstFingerprintHex() {
        return String.format("%016x", firstFingerprint);
    }

    public String secondFingerprintHex() {
        return String.format("%016x", secondFingerprint);
    }
}
