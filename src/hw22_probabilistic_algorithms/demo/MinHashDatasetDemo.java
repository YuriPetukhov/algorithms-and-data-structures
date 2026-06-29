package hw22_probabilistic_algorithms.demo;

import hw22_probabilistic_algorithms.dataset.CsvDocumentPairLoader;
import hw22_probabilistic_algorithms.dataset.model.DocumentPair;
import hw22_probabilistic_algorithms.experiment.MinHashExperiment;
import hw22_probabilistic_algorithms.experiment.model.ExperimentResult;
import hw22_probabilistic_algorithms.experiment.model.MinHashExperimentResult;
import hw22_probabilistic_algorithms.experiment.model.MinHashScoredPair;
import hw22_probabilistic_algorithms.libs.minhash.MinHash32;
import hw22_probabilistic_algorithms.libs.minhash.WordShingleSetExtractor;
import hw22_probabilistic_algorithms.service.MinHashService;
import hw22_probabilistic_algorithms.service.validation.MinHashRequestValidator;

import java.util.Comparator;
import java.util.List;

public final class MinHashDatasetDemo {

    private static final String DATASET_DIRECTORY =
            "/hw22/dataset/";

    private static final String TRAIN_DATASET =
            DATASET_DIRECTORY
                    + "simhash_pairs_train.csv";

    private static final String VALIDATION_DATASET =
            DATASET_DIRECTORY
                    + "simhash_pairs_validation.csv";

    private static final String TEST_DATASET =
            DATASET_DIRECTORY
                    + "simhash_pairs_test.csv";

    private static final int MAX_TEXT_LENGTH = 100_000;

    private static final List<Integer> SHINGLE_SIZES =
            List.of(1, 2, 3);

    private static final List<Integer> SIGNATURE_SIZES =
            List.of(32, 64, 128);

    private static final int MIN_THRESHOLD_PERCENT = 40;
    private static final int MAX_THRESHOLD_PERCENT = 95;

    private MinHashDatasetDemo() {
    }

    public static void main(String[] args) {
        CsvDocumentPairLoader loader =
                new CsvDocumentPairLoader();

        List<DocumentPair> train =
                loader.loadFromResource(TRAIN_DATASET);

        List<DocumentPair> validation =
                loader.loadFromResource(VALIDATION_DATASET);

        List<DocumentPair> test =
                loader.loadFromResource(TEST_DATASET);

        System.out.printf(
                "MinHash dataset experiment%n"
                        + "train=%d, validation=%d, test=%d%n%n",
                train.size(),
                validation.size(),
                test.size()
        );

        ConfigurationResult best = null;

        for (int shingleSize : SHINGLE_SIZES) {
            for (int signatureSize : SIGNATURE_SIZES) {
                MinHashExperiment experiment =
                        createExperiment(
                                shingleSize,
                                signatureSize
                        );

                List<MinHashScoredPair> trainScores =
                        experiment.score(train);

                MinHashExperimentResult trainResult =
                        findBestThreshold(
                                experiment,
                                trainScores
                        );

                List<MinHashScoredPair> validationScores =
                        experiment.score(validation);

                MinHashExperimentResult validationResult =
                        experiment.evaluate(
                                validationScores,
                                trainResult.similarityThreshold()
                        );

                ConfigurationResult current =
                        new ConfigurationResult(
                                shingleSize,
                                signatureSize,
                                trainResult.similarityThreshold(),
                                trainResult,
                                validationResult
                        );

                printConfiguration(current);

                if (best == null
                        || CONFIGURATION_COMPARATOR.compare(
                        current,
                        best
                ) > 0) {
                    best = current;
                }
            }
        }

        if (best == null) {
            throw new IllegalStateException(
                    "No MinHash configuration was evaluated."
            );
        }

        System.out.println("Selected configuration");
        System.out.printf(
                "shingle size: %d%n"
                        + "signature size: %d%n"
                        + "similarity threshold: %.2f%n%n",
                best.shingleSize(),
                best.signatureSize(),
                best.similarityThreshold()
        );

        MinHashExperiment finalExperiment =
                createExperiment(
                        best.shingleSize(),
                        best.signatureSize()
                );

        MinHashExperimentResult testResult =
                finalExperiment.evaluate(
                        finalExperiment.score(test),
                        best.similarityThreshold()
                );

        printResult("TEST", testResult);
    }

    private static MinHashExperiment createExperiment(
            int shingleSize,
            int signatureSize
    ) {
        MinHashService service = new MinHashService(
                MinHash32.withSignatureSize(signatureSize),
                new WordShingleSetExtractor(shingleSize),
                new MinHashRequestValidator(MAX_TEXT_LENGTH)
        );

        return new MinHashExperiment(service);
    }

    private static MinHashExperimentResult findBestThreshold(
            MinHashExperiment experiment,
            List<MinHashScoredPair> pairs
    ) {
        MinHashExperimentResult best = null;

        for (int percent = MIN_THRESHOLD_PERCENT;
             percent <= MAX_THRESHOLD_PERCENT;
             percent++) {

            double threshold = percent / 100.0;

            MinHashExperimentResult current =
                    experiment.evaluate(pairs, threshold);

            if (best == null
                    || RESULT_COMPARATOR.compare(
                    current,
                    best
            ) > 0) {
                best = current;
            }
        }

        return best;
    }

    private static void printConfiguration(
            ConfigurationResult result
    ) {
        System.out.printf(
                "shingle=%d, signature=%d, threshold=%.2f%n",
                result.shingleSize(),
                result.signatureSize(),
                result.similarityThreshold()
        );

        printResult("TRAIN", result.trainResult());
        printResult(
                "VALIDATION",
                result.validationResult()
        );
        System.out.println();
    }

    private static void printResult(
            String name,
            MinHashExperimentResult result
    ) {
        ExperimentResult metrics =
                result.classification();

        System.out.printf(
                "%s: TP=%d FP=%d TN=%d FN=%d "
                        + "accuracy=%.4f precision=%.4f "
                        + "recall=%.4f F1=%.4f "
                        + "FPR=%.4f FNR=%.4f "
                        + "MAE=%.4f RMSE=%.4f MAX_ERR=%.4f%n",
                name,
                metrics.truePositive(),
                metrics.falsePositive(),
                metrics.trueNegative(),
                metrics.falseNegative(),
                metrics.accuracy(),
                metrics.precision(),
                metrics.recall(),
                metrics.f1(),
                metrics.falsePositiveRate(),
                metrics.falseNegativeRate(),
                result.meanAbsoluteError(),
                result.rootMeanSquaredError(),
                result.maximumAbsoluteError()
        );
    }

    private static final Comparator<MinHashExperimentResult>
            RESULT_COMPARATOR =
            Comparator
                    .<MinHashExperimentResult>comparingDouble(
                            value -> value
                                    .classification()
                                    .f1()
                    )
                    .thenComparingDouble(
                            value -> value
                                    .classification()
                                    .precision()
                    )
                    .thenComparingDouble(
                            value -> -value.meanAbsoluteError()
                    );

    private static final Comparator<ConfigurationResult>
            CONFIGURATION_COMPARATOR =
            Comparator
                    .<ConfigurationResult>comparingDouble(
                            value -> value
                                    .validationResult()
                                    .classification()
                                    .f1()
                    )
                    .thenComparingDouble(
                            value -> value
                                    .validationResult()
                                    .classification()
                                    .precision()
                    )
                    .thenComparingDouble(
                            value -> -value
                                    .validationResult()
                                    .meanAbsoluteError()
                    );

    private record ConfigurationResult(
            int shingleSize,
            int signatureSize,
            double similarityThreshold,
            MinHashExperimentResult trainResult,
            MinHashExperimentResult validationResult
    ) {
    }
}
