package hw22_probabilistic_algorithms.benchmark;

import hw22_probabilistic_algorithms.experiment.model.ExperimentResult;

import java.nio.file.Path;

public record BenchmarkResult(
        Path datasetPath,
        long fileSizeBytes,
        int shingleSize,
        int maxDistance,
        long processedPairs,
        long elapsedNanos,
        ExperimentResult metrics
) {

    public double elapsedSeconds() {
        return elapsedNanos / 1_000_000_000.0;
    }

    public double pairsPerSecond() {
        double seconds = elapsedSeconds();

        if (seconds == 0.0) {
            return 0.0;
        }

        return processedPairs / seconds;
    }

    public double fileSizeMegabytes() {
        return fileSizeBytes / 1024.0 / 1024.0;
    }

    public double megabytesPerSecond() {
        double seconds = elapsedSeconds();

        if (seconds == 0.0) {
            return 0.0;
        }

        return fileSizeMegabytes() / seconds;
    }
}
