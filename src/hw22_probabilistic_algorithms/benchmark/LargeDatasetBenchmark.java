package hw22_probabilistic_algorithms.benchmark;

import hw22_probabilistic_algorithms.dataset.CsvDocumentPairLoader;
import hw22_probabilistic_algorithms.experiment.MetricsAccumulator;
import hw22_probabilistic_algorithms.experiment.model.ExperimentResult;
import hw22_probabilistic_algorithms.service.SimHashService;
import hw22_probabilistic_algorithms.service.model.SimHashRequest;
import hw22_probabilistic_algorithms.service.model.SimHashResult;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public final class LargeDatasetBenchmark {

    private final SimHashService service;
    private final CsvDocumentPairLoader loader;

    public LargeDatasetBenchmark(
            SimHashService service,
            CsvDocumentPairLoader loader
    ) {
        this.service = Objects.requireNonNull(
                service,
                "Service must not be null."
        );

        this.loader = Objects.requireNonNull(
                loader,
                "Loader must not be null."
        );
    }

    public BenchmarkResult run(
            Path datasetPath,
            int shingleSize,
            int maxDistance
    ) {
        Objects.requireNonNull(
                datasetPath,
                "Dataset path must not be null."
        );

        if (maxDistance < 0
                || maxDistance > Long.SIZE) {

            throw new IllegalArgumentException(
                    "Max distance must be between 0 and 64."
            );
        }

        MetricsAccumulator accumulator =
                new MetricsAccumulator();

        long fileSize = fileSize(datasetPath);
        long started = System.nanoTime();

        long processed = loader.forEachFromFile(
                datasetPath,
                pair -> {
                    SimHashResult result =
                            service.compare(
                                    new SimHashRequest(
                                            pair.firstText(),
                                            pair.secondText(),
                                            maxDistance
                                    )
                            );

                    accumulator.add(
                            pair.nearDuplicate(),
                            result.similar()
                    );
                }
        );

        long elapsed = System.nanoTime() - started;

        if (processed != accumulator.total()) {
            throw new IllegalStateException(
                    "Processed row count does not match "
                            + "the accumulated metrics."
            );
        }

        ExperimentResult metrics =
                accumulator.toResult(maxDistance);

        return new BenchmarkResult(
                datasetPath.toAbsolutePath(),
                fileSize,
                shingleSize,
                maxDistance,
                processed,
                elapsed,
                metrics
        );
    }

    private long fileSize(Path path) {
        try {
            return Files.size(path);
        } catch (IOException exception) {
            throw new UncheckedIOException(
                    "Failed to obtain dataset size: "
                            + path.toAbsolutePath(),
                    exception
            );
        }
    }
}
