package hw22_probabilistic_algorithms.demo;

import hw22_probabilistic_algorithms.dataset.CsvDocumentPairLoader;
import hw22_probabilistic_algorithms.dataset.model.DocumentPair;
import hw22_probabilistic_algorithms.experiment.SimHashExperiment;
import hw22_probabilistic_algorithms.experiment.model.ExperimentResult;
import hw22_probabilistic_algorithms.experiment.model.ScoredPair;
import hw22_probabilistic_algorithms.libs.simhash.SimHash64;
import hw22_probabilistic_algorithms.service.SimHashService;
import hw22_probabilistic_algorithms.service.validation.SimHashRequestValidator;

import java.util.Comparator;
import java.util.List;

public final class SimHashDatasetDemo {

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
            List.of(1, 2, 3, 4);

    private static final int MIN_THRESHOLD = 0;
    private static final int MAX_THRESHOLD = 20;

    private SimHashDatasetDemo() {
    }

    public static void main(String[] args) {
        CsvDocumentPairLoader loader =
                new CsvDocumentPairLoader();

        List<DocumentPair> train =
                loader.loadFromResource(TRAIN_DATASET);

        List<DocumentPair> validation =
                loader.loadFromResource(
                        VALIDATION_DATASET
                );

        List<DocumentPair> test =
                loader.loadFromResource(TEST_DATASET);

        printDatasetSizes(train, validation, test);

        ConfigurationResult bestConfiguration = null;

        for (int shingleSize : SHINGLE_SIZES) {
            SimHashExperiment experiment =
                    createExperiment(shingleSize);

            List<ScoredPair> trainScores =
                    experiment.score(train);

            ExperimentResult trainResult =
                    findBestThreshold(
                            experiment,
                            trainScores
                    );

            List<ScoredPair> validationScores =
                    experiment.score(validation);

            ExperimentResult validationResult =
                    experiment.evaluate(
                            validationScores,
                            trainResult.maxDistance()
                    );

            ConfigurationResult current =
                    new ConfigurationResult(
                            shingleSize,
                            trainResult.maxDistance(),
                            trainResult,
                            validationResult
                    );

            printConfiguration(current);

            if (bestConfiguration == null
                    || CONFIGURATION_COMPARATOR
                    .compare(
                            current,
                            bestConfiguration
                    ) > 0) {

                bestConfiguration = current;
            }
        }

        if (bestConfiguration == null) {
            throw new IllegalStateException(
                    "No configuration was evaluated."
            );
        }

        printSelectedConfiguration(
                bestConfiguration
        );

        SimHashExperiment finalExperiment =
                createExperiment(
                        bestConfiguration.shingleSize()
                );

        List<ScoredPair> testScores =
                finalExperiment.score(test);

        ExperimentResult testResult =
                finalExperiment.evaluate(
                        testScores,
                        bestConfiguration.maxDistance()
                );

        printResult("TEST", testResult);
    }

    private static SimHashExperiment createExperiment(
            int shingleSize
    ) {
        SimHashService service =
                new SimHashService(
                        SimHash64.forWordShingles(
                                shingleSize
                        ),
                        new SimHashRequestValidator(
                                MAX_TEXT_LENGTH
                        )
                );

        return new SimHashExperiment(service);
    }

    private static ExperimentResult findBestThreshold(
            SimHashExperiment experiment,
            List<ScoredPair> scoredPairs
    ) {
        ExperimentResult best = null;

        for (int threshold = MIN_THRESHOLD;
             threshold <= MAX_THRESHOLD;
             threshold++) {

            ExperimentResult current =
                    experiment.evaluate(
                            scoredPairs,
                            threshold
                    );

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

    private static void printDatasetSizes(
            List<DocumentPair> train,
            List<DocumentPair> validation,
            List<DocumentPair> test
    ) {
        System.out.println(
                "SimHash dataset experiment"
        );

        System.out.printf(
                "train=%d, validation=%d, test=%d%n%n",
                train.size(),
                validation.size(),
                test.size()
        );
    }

    private static void printConfiguration(
            ConfigurationResult result
    ) {
        System.out.printf(
                "shingle=%d, selected threshold=%d%n",
                result.shingleSize(),
                result.maxDistance()
        );

        printResult("TRAIN", result.trainResult());
        printResult(
                "VALIDATION",
                result.validationResult()
        );

        System.out.println();
    }

    private static void printSelectedConfiguration(
            ConfigurationResult result
    ) {
        System.out.println(
                "Selected configuration"
        );

        System.out.printf(
                "shingle size: %d%n",
                result.shingleSize()
        );

        System.out.printf(
                "max distance: %d%n%n",
                result.maxDistance()
        );
    }

    private static void printResult(
            String name,
            ExperimentResult result
    ) {
        System.out.printf(
                "%s: TP=%d FP=%d TN=%d FN=%d "
                        + "accuracy=%.4f "
                        + "precision=%.4f recall=%.4f "
                        + "F1=%.4f FPR=%.4f FNR=%.4f%n",
                name,
                result.truePositive(),
                result.falsePositive(),
                result.trueNegative(),
                result.falseNegative(),
                result.accuracy(),
                result.precision(),
                result.recall(),
                result.f1(),
                result.falsePositiveRate(),
                result.falseNegativeRate()
        );
    }

    private static final Comparator<ExperimentResult>
            RESULT_COMPARATOR =
            Comparator
                    .comparingDouble(
                            ExperimentResult::f1
                    )
                    .thenComparingDouble(
                            ExperimentResult::precision
                    )
                    .thenComparingInt(
                            result ->
                                    -result.maxDistance()
                    );

    private static final Comparator<ConfigurationResult>
            CONFIGURATION_COMPARATOR =
            Comparator
                    .<ConfigurationResult>comparingDouble(
                            value ->
                                    value.validationResult()
                                            .f1()
                    )
                    .thenComparingDouble(
                            value ->
                                    value.validationResult()
                                            .precision()
                    )
                    .thenComparingInt(
                            value ->
                                    -value.shingleSize()
                    );

    private record ConfigurationResult(
            int shingleSize,
            int maxDistance,
            ExperimentResult trainResult,
            ExperimentResult validationResult
    ) {
    }
}
