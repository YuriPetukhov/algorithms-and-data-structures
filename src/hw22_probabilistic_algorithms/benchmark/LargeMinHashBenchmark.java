package hw22_probabilistic_algorithms.benchmark;

import hw22_probabilistic_algorithms.dataset.CsvDocumentPairLoader;
import hw22_probabilistic_algorithms.service.MinHashService;
import hw22_probabilistic_algorithms.service.model.MinHashRequest;
import hw22_probabilistic_algorithms.service.model.MinHashResult;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public final class LargeMinHashBenchmark {

    private final MinHashService service;
    private final CsvDocumentPairLoader loader;

    public LargeMinHashBenchmark(
            MinHashService service,
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

    public MinHashBenchmarkResult run(
            Path datasetPath,
            int shingleSize,
            int signatureSize,
            double similarityThreshold
    ) {
        Objects.requireNonNull(
                datasetPath,
                "Dataset path must not be null."
        );

        MinHashMetricsAccumulator accumulator =
                new MinHashMetricsAccumulator();

        long fileSize = fileSize(datasetPath);
        long started = System.nanoTime();

        long processed = loader.forEachFromFile(
                datasetPath,
                pair -> {
                    MinHashResult result = service.compare(
                            new MinHashRequest(
                                    pair.firstText(),
                                    pair.secondText(),
                                    similarityThreshold
                            )
                    );

                    accumulator.add(
                            pair.nearDuplicate(),
                            result.similar(),
                            result.exactJaccardSimilarity(),
                            result.estimatedSimilarity()
                    );
                }
        );

        long elapsed = System.nanoTime() - started;

        if (processed != accumulator.total()) {
            throw new IllegalStateException(
                    "Processed row count does not match accumulated metrics."
            );
        }

        return new MinHashBenchmarkResult(
                datasetPath.toAbsolutePath(),
                fileSize,
                shingleSize,
                signatureSize,
                similarityThreshold,
                processed,
                elapsed,
                accumulator.classification(),
                accumulator.meanAbsoluteError(),
                accumulator.rootMeanSquaredError(),
                accumulator.maximumAbsoluteError()
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
