package hw22_probabilistic_algorithms.experiment;

import hw22_probabilistic_algorithms.dataset.model.DocumentPair;
import hw22_probabilistic_algorithms.experiment.model.ExperimentResult;
import hw22_probabilistic_algorithms.experiment.model.MinHashExperimentResult;
import hw22_probabilistic_algorithms.experiment.model.MinHashScoredPair;
import hw22_probabilistic_algorithms.service.MinHashService;
import hw22_probabilistic_algorithms.service.model.MinHashRequest;
import hw22_probabilistic_algorithms.service.model.MinHashResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class MinHashExperiment {

    private final MinHashService service;

    public MinHashExperiment(MinHashService service) {
        this.service = Objects.requireNonNull(
                service,
                "Service must not be null."
        );
    }

    public List<MinHashScoredPair> score(
            List<DocumentPair> pairs
    ) {
        Objects.requireNonNull(
                pairs,
                "Pairs must not be null."
        );

        List<MinHashScoredPair> scored =
                new ArrayList<>(pairs.size());

        for (DocumentPair pair : pairs) {
            MinHashResult result = service.compare(
                    new MinHashRequest(
                            pair.firstText(),
                            pair.secondText(),
                            0.0
                    )
            );

            scored.add(new MinHashScoredPair(
                    pair.pairId(),
                    pair.nearDuplicate(),
                    result.estimatedSimilarity(),
                    result.exactJaccardSimilarity()
            ));
        }

        return List.copyOf(scored);
    }

    public MinHashExperimentResult evaluate(
            List<MinHashScoredPair> pairs,
            double similarityThreshold
    ) {
        Objects.requireNonNull(
                pairs,
                "Scored pairs must not be null."
        );

        if (!Double.isFinite(similarityThreshold)
                || similarityThreshold < 0.0
                || similarityThreshold > 1.0) {

            throw new IllegalArgumentException(
                    "Similarity threshold must be between 0.0 and 1.0."
            );
        }

        MetricsAccumulator classification =
                new MetricsAccumulator();

        double absoluteErrorSum = 0.0;
        double squaredErrorSum = 0.0;
        double maximumError = 0.0;

        for (MinHashScoredPair pair : pairs) {
            boolean predicted =
                    pair.estimatedSimilarity()
                            >= similarityThreshold;

            classification.add(
                    pair.nearDuplicate(),
                    predicted
            );

            double error = pair.absoluteError();
            absoluteErrorSum += error;
            squaredErrorSum += error * error;
            maximumError = Math.max(maximumError, error);
        }

        long count = pairs.size();

        double mae = count == 0L
                ? 0.0
                : absoluteErrorSum / count;

        double rmse = count == 0L
                ? 0.0
                : Math.sqrt(squaredErrorSum / count);

        ExperimentResult result =
                classification.toResult(0);

        return new MinHashExperimentResult(
                similarityThreshold,
                result,
                mae,
                rmse,
                maximumError
        );
    }
}
