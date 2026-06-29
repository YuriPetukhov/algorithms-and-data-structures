package hw22_probabilistic_algorithms.experiment.model;

public record MinHashExperimentResult(
        double similarityThreshold,
        ExperimentResult classification,
        double meanAbsoluteError,
        double rootMeanSquaredError,
        double maximumAbsoluteError
) {
}
