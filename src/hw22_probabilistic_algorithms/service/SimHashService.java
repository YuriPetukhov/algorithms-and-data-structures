package hw22_probabilistic_algorithms.service;

import hw22_probabilistic_algorithms.libs.simhash.SimHash;
import hw22_probabilistic_algorithms.service.model.SimHashRequest;
import hw22_probabilistic_algorithms.service.model.SimHashResult;
import hw22_probabilistic_algorithms.service.validation.SimHashRequestValidator;

import java.util.Objects;

public final class SimHashService {

    private final SimHash simHash;
    private final SimHashRequestValidator validator;

    public SimHashService(
            SimHash simHash,
            SimHashRequestValidator validator
    ) {
        this.simHash = Objects.requireNonNull(
                simHash,
                "SimHash must not be null."
        );

        this.validator = Objects.requireNonNull(
                validator,
                "Validator must not be null."
        );
    }

    public long fingerprint(String text) {
        validator.validateText("Text", text);
        return simHash.fingerprint(text);
    }

    public SimHashResult compare(SimHashRequest request) {
        validator.validate(request);

        long firstFingerprint =
                simHash.fingerprint(request.firstText());

        long secondFingerprint =
                simHash.fingerprint(request.secondText());

        int distance = simHash.distance(
                firstFingerprint,
                secondFingerprint
        );

        double similarity = simHash.similarity(
                firstFingerprint,
                secondFingerprint
        );

        boolean similar = distance <= request.maxDistance();

        return new SimHashResult(
                firstFingerprint,
                secondFingerprint,
                distance,
                similarity,
                similar
        );
    }
}
