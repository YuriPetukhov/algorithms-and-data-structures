package hw22_probabilistic_algorithms.benchmark;

import hw22_probabilistic_algorithms.experiment.model.ExperimentResult;

import java.nio.file.Path;

public record MinHashBenchmarkResult(
        Path datasetPath,
        long fileSizeBytes,
        int shingleSize,
        int signatureSize,
        double similarityThreshold,
        long processedPairs,
        long elapsedNanos,
        ExperimentResult classification,
        double meanAbsoluteError,
        double rootMeanSquaredError,
        double maximumAbsoluteError
) {

    public double elapsedSeconds() {
        return elapsedNanos / 1_000_000_000.0;
    }

    public double pairsPerSecond() {
        double seconds = elapsedSeconds();
        return seconds == 0.0
                ? 0.0
                : processedPairs / seconds;
    }

    public double fileSizeMegabytes() {
        return fileSizeBytes / 1024.0 / 1024.0;
    }

    public double megabytesPerSecond() {
        double seconds = elapsedSeconds();
        return seconds == 0.0
                ? 0.0
                : fileSizeMegabytes() / seconds;
    }
}
