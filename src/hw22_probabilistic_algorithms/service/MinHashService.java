package hw22_probabilistic_algorithms.service;

import hw22_probabilistic_algorithms.libs.minhash.JaccardSimilarity;
import hw22_probabilistic_algorithms.libs.minhash.MinHash;
import hw22_probabilistic_algorithms.libs.minhash.ShingleSetExtractor;
import hw22_probabilistic_algorithms.service.model.MinHashRequest;
import hw22_probabilistic_algorithms.service.model.MinHashResult;
import hw22_probabilistic_algorithms.service.validation.MinHashRequestValidator;

import java.util.Objects;
import java.util.Set;

public final class MinHashService {

    private final MinHash minHash;
    private final ShingleSetExtractor extractor;
    private final MinHashRequestValidator validator;

    public MinHashService(
            MinHash minHash,
            ShingleSetExtractor extractor,
            MinHashRequestValidator validator
    ) {
        this.minHash = Objects.requireNonNull(
                minHash,
                "MinHash must not be null."
        );
        this.extractor = Objects.requireNonNull(
                extractor,
                "Extractor must not be null."
        );
        this.validator = Objects.requireNonNull(
                validator,
                "Validator must not be null."
        );
    }

    public int[] signature(String text) {
        validator.validateText("Text", text);
        return minHash.signature(extractor.extract(text));
    }

    public MinHashResult compare(MinHashRequest request) {
        validator.validate(request);

        Set<String> firstFeatures =
                extractor.extract(request.firstText());

        Set<String> secondFeatures =
                extractor.extract(request.secondText());

        int[] firstSignature =
                minHash.signature(firstFeatures);

        int[] secondSignature =
                minHash.signature(secondFeatures);

        double estimated = minHash.similarity(
                firstSignature,
                secondSignature
        );

        double exact = JaccardSimilarity.calculate(
                firstFeatures,
                secondFeatures
        );

        return new MinHashResult(
                firstSignature,
                secondSignature,
                estimated,
                exact,
                Math.abs(exact - estimated),
                estimated >= request.similarityThreshold()
        );
    }
}
