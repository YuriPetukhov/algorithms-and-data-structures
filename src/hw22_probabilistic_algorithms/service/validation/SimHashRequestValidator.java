package hw22_probabilistic_algorithms.service.validation;

import hw22_probabilistic_algorithms.service.model.SimHashRequest;

import java.util.regex.Pattern;

public final class SimHashRequestValidator {

    private static final int HASH_BITS = Long.SIZE;

    private static final Pattern LETTER_OR_DIGIT =
            Pattern.compile("[\\p{L}\\p{N}]");

    private final int maxTextLength;

    public SimHashRequestValidator(int maxTextLength) {
        if (maxTextLength <= 0) {
            throw new IllegalArgumentException(
                    "Max text length must be positive."
            );
        }

        this.maxTextLength = maxTextLength;
    }

    public void validate(SimHashRequest request) {
        if (request == null) {
            throw new InputValidationException(
                    "Request must not be null."
            );
        }

        validateText("First text", request.firstText());
        validateText("Second text", request.secondText());
        validateMaxDistance(request.maxDistance());
    }

    public void validateText(String fieldName, String text) {
        if (text == null) {
            throw new InputValidationException(
                    fieldName + " must not be null."
            );
        }

        if (text.isBlank()) {
            throw new InputValidationException(
                    fieldName + " must not be blank."
            );
        }

        if (text.length() > maxTextLength) {
            throw new InputValidationException(
                    fieldName + " length must not exceed "
                            + maxTextLength + " characters."
            );
        }

        if (!LETTER_OR_DIGIT.matcher(text).find()) {
            throw new InputValidationException(
                    fieldName
                            + " must contain at least one letter or digit."
            );
        }
    }

    private void validateMaxDistance(int maxDistance) {
        if (maxDistance < 0 || maxDistance > HASH_BITS) {
            throw new InputValidationException(
                    "Max distance must be between 0 and 64."
            );
        }
    }
}
