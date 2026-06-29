package hw22_probabilistic_algorithms.demo;

import hw22_probabilistic_algorithms.benchmark.BenchmarkResult;
import hw22_probabilistic_algorithms.benchmark.LargeDatasetBenchmark;
import hw22_probabilistic_algorithms.dataset.CsvDocumentPairLoader;
import hw22_probabilistic_algorithms.experiment.model.ExperimentResult;
import hw22_probabilistic_algorithms.libs.simhash.SimHash64;
import hw22_probabilistic_algorithms.service.SimHashService;
import hw22_probabilistic_algorithms.service.validation.SimHashRequestValidator;

import java.nio.file.Path;

public final class LargeDatasetBenchmarkDemo {

    private static final int DEFAULT_SHINGLE_SIZE = 1;
    private static final int DEFAULT_MAX_DISTANCE = 8;
    private static final int MAX_TEXT_LENGTH = 100_000;

    private LargeDatasetBenchmarkDemo() {
    }

    public static void main(String[] args) {
        Path datasetPath = Path.of(
                "datasets",
                "hw22",
                "finewiki_de_pairs_100000.csv"
        );

        int shingleSize = args.length >= 2
                ? parsePositiveInt(
                        args[1],
                        "shingle size"
                )
                : DEFAULT_SHINGLE_SIZE;

        int maxDistance = args.length >= 3
                ? parseDistance(args[2])
                : DEFAULT_MAX_DISTANCE;

        SimHashService service =
                new SimHashService(
                        SimHash64.forWordShingles(
                                shingleSize
                        ),
                        new SimHashRequestValidator(
                                MAX_TEXT_LENGTH
                        )
                );

        LargeDatasetBenchmark benchmark =
                new LargeDatasetBenchmark(
                        service,
                        new CsvDocumentPairLoader()
                );

        BenchmarkResult result =
                benchmark.run(
                        datasetPath,
                        shingleSize,
                        maxDistance
                );

        printResult(result);
    }

    private static int parsePositiveInt(
            String value,
            String fieldName
    ) {
        int parsed;

        try {
            parsed = Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    fieldName + " must be an integer.",
                    exception
            );
        }

        if (parsed <= 0) {
            throw new IllegalArgumentException(
                    fieldName + " must be positive."
            );
        }

        return parsed;
    }

    private static int parseDistance(String value) {
        int parsed;

        try {
            parsed = Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Max distance must be an integer.",
                    exception
            );
        }

        if (parsed < 0 || parsed > Long.SIZE) {
            throw new IllegalArgumentException(
                    "Max distance must be between 0 and 64."
            );
        }

        return parsed;
    }

    private static void printUsage() {
        System.out.println(
                "Usage: LargeDatasetBenchmarkDemo "
                        + "<csv-file> [shingle-size] "
                        + "[max-distance]"
        );

        System.out.println(
                "Example: LargeDatasetBenchmarkDemo "
                        + "datasets/hw22/large.csv 1 8"
        );
    }

    private static void printResult(
            BenchmarkResult result
    ) {
        ExperimentResult metrics =
                result.metrics();

        System.out.println(
                "Large SimHash benchmark"
        );

        System.out.printf(
                "dataset: %s%n",
                result.datasetPath()
        );

        System.out.printf(
                "file size: %.2f MB%n",
                result.fileSizeMegabytes()
        );

        System.out.printf(
                "shingle size: %d%n",
                result.shingleSize()
        );

        System.out.printf(
                "max distance: %d%n",
                result.maxDistance()
        );

        System.out.printf(
                "processed pairs: %d%n",
                result.processedPairs()
        );

        System.out.printf(
                "time: %.3f s%n",
                result.elapsedSeconds()
        );

        System.out.printf(
                "throughput: %.2f pairs/s%n",
                result.pairsPerSecond()
        );

        System.out.printf(
                "reading speed: %.2f MB/s%n",
                result.megabytesPerSecond()
        );

        System.out.printf(
                "TP=%d FP=%d TN=%d FN=%d%n",
                metrics.truePositive(),
                metrics.falsePositive(),
                metrics.trueNegative(),
                metrics.falseNegative()
        );

        System.out.printf(
                "accuracy=%.4f precision=%.4f "
                        + "recall=%.4f F1=%.4f%n",
                metrics.accuracy(),
                metrics.precision(),
                metrics.recall(),
                metrics.f1()
        );

        System.out.printf(
                "FPR=%.4f FNR=%.4f%n",
                metrics.falsePositiveRate(),
                metrics.falseNegativeRate()
        );
    }
}
