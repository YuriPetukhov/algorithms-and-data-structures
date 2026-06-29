package hw22_probabilistic_algorithms.experiment;

import hw22_probabilistic_algorithms.dataset.model.DocumentPair;
import hw22_probabilistic_algorithms.experiment.model.ExperimentResult;
import hw22_probabilistic_algorithms.experiment.model.ScoredPair;
import hw22_probabilistic_algorithms.service.SimHashService;
import hw22_probabilistic_algorithms.service.model.SimHashRequest;
import hw22_probabilistic_algorithms.service.model.SimHashResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class SimHashExperiment {

    private final SimHashService service;

    public SimHashExperiment(SimHashService service) {
        this.service = Objects.requireNonNull(
                service,
                "Service must not be null."
        );
    }

    public List<ScoredPair> score(
            List<DocumentPair> pairs
    ) {
        Objects.requireNonNull(
                pairs,
                "Pairs must not be null."
        );

        List<ScoredPair> scored =
                new ArrayList<>(pairs.size());

        for (DocumentPair pair : pairs) {
            SimHashResult result = service.compare(
                    new SimHashRequest(
                            pair.firstText(),
                            pair.secondText(),
                            Long.SIZE
                    )
            );

            scored.add(new ScoredPair(
                    pair.pairId(),
                    pair.nearDuplicate(),
                    result.hammingDistance()
            ));
        }

        return List.copyOf(scored);
    }

    public ExperimentResult evaluate(
            List<ScoredPair> pairs,
            int maxDistance
    ) {
        Objects.requireNonNull(
                pairs,
                "Scored pairs must not be null."
        );

        if (maxDistance < 0
                || maxDistance > Long.SIZE) {

            throw new IllegalArgumentException(
                    "Max distance must be between 0 and 64."
            );
        }

        long truePositive = 0;
        long falsePositive = 0;
        long trueNegative = 0;
        long falseNegative = 0;

        for (ScoredPair pair : pairs) {
            boolean predicted =
                    pair.hammingDistance()
                            <= maxDistance;

            if (pair.nearDuplicate()) {
                if (predicted) {
                    truePositive++;
                } else {
                    falseNegative++;
                }
            } else {
                if (predicted) {
                    falsePositive++;
                } else {
                    trueNegative++;
                }
            }
        }

        return new ExperimentResult(
                maxDistance,
                truePositive,
                falsePositive,
                trueNegative,
                falseNegative
        );
    }
}
