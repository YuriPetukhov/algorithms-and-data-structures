package hw22_probabilistic_algorithms.demo;

import hw22_probabilistic_algorithms.benchmark.LargeMinHashBenchmark;
import hw22_probabilistic_algorithms.benchmark.MinHashBenchmarkResult;
import hw22_probabilistic_algorithms.dataset.CsvDocumentPairLoader;
import hw22_probabilistic_algorithms.experiment.model.ExperimentResult;
import hw22_probabilistic_algorithms.libs.minhash.MinHash32;
import hw22_probabilistic_algorithms.libs.minhash.WordShingleSetExtractor;
import hw22_probabilistic_algorithms.service.MinHashService;
import hw22_probabilistic_algorithms.service.validation.MinHashRequestValidator;

import java.nio.file.Path;

public final class LargeMinHashBenchmarkDemo {

    private static final int DEFAULT_SHINGLE_SIZE = 1;
    private static final int DEFAULT_SIGNATURE_SIZE = 64;
    private static final double DEFAULT_THRESHOLD = 0.80;
    private static final int MAX_TEXT_LENGTH = 100_000;

    private LargeMinHashBenchmarkDemo() {
    }

    public static void main(String[] args) {
        Path datasetPath = Path.of(
                "datasets",
                "hw22",
                "finewiki_de_pairs_100000.csv"
        );

        int shingleSize = args.length >= 2
                ? parsePositiveInt(args[1], "shingle size")
                : DEFAULT_SHINGLE_SIZE;

        int signatureSize = args.length >= 3
                ? parsePositiveInt(args[2], "signature size")
                : DEFAULT_SIGNATURE_SIZE;

        double threshold = args.length >= 4
                ? parseThreshold(args[3])
                : DEFAULT_THRESHOLD;

        MinHashService service = new MinHashService(
                MinHash32.withSignatureSize(signatureSize),
                new WordShingleSetExtractor(shingleSize),
                new MinHashRequestValidator(MAX_TEXT_LENGTH)
        );

        LargeMinHashBenchmark benchmark =
                new LargeMinHashBenchmark(
                        service,
                        new CsvDocumentPairLoader()
                );

        MinHashBenchmarkResult result = benchmark.run(
                datasetPath,
                shingleSize,
                signatureSize,
                threshold
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

    private static double parseThreshold(String value) {
        double parsed;

        try {
            parsed = Double.parseDouble(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Similarity threshold must be a number.",
                    exception
            );
        }

        if (!Double.isFinite(parsed)
                || parsed < 0.0
                || parsed > 1.0) {

            throw new IllegalArgumentException(
                    "Similarity threshold must be between 0.0 and 1.0."
            );
        }

        return parsed;
    }

    private static void printUsage() {
        System.out.println(
                "Usage: LargeMinHashBenchmarkDemo "
                        + "<csv-file> [shingle-size] "
                        + "[signature-size] [similarity-threshold]"
        );

        System.out.println(
                "Example: LargeMinHashBenchmarkDemo "
                        + "datasets/hw22/finewiki_de_pairs_10000.csv "
                        + "1 64 0.80"
        );
    }

    private static void printResult(
            MinHashBenchmarkResult result
    ) {
        ExperimentResult metrics =
                result.classification();

        System.out.println("Large MinHash benchmark");
        System.out.printf("dataset: %s%n", result.datasetPath());
        System.out.printf(
                "file size: %.2f MB%n",
                result.fileSizeMegabytes()
        );
        System.out.printf(
                "shingle size: %d%n",
                result.shingleSize()
        );
        System.out.printf(
                "signature size: %d%n",
                result.signatureSize()
        );
        System.out.printf(
                "similarity threshold: %.2f%n",
                result.similarityThreshold()
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
        System.out.printf(
                "MAE=%.4f RMSE=%.4f MAX_ERR=%.4f%n",
                result.meanAbsoluteError(),
                result.rootMeanSquaredError(),
                result.maximumAbsoluteError()
        );
    }
}
