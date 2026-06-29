package hw22_probabilistic_algorithms.service.validation;

import hw22_probabilistic_algorithms.service.model.MinHashRequest;

import java.util.regex.Pattern;

public final class MinHashRequestValidator {

    private static final Pattern LETTER_OR_DIGIT =
            Pattern.compile("[\\p{L}\\p{N}]");

    private final int maxTextLength;

    public MinHashRequestValidator(int maxTextLength) {
        if (maxTextLength <= 0) {
            throw new IllegalArgumentException(
                    "Max text length must be positive."
            );
        }

        this.maxTextLength = maxTextLength;
    }

    public void validate(MinHashRequest request) {
        if (request == null) {
            throw new InputValidationException(
                    "Request must not be null."
            );
        }

        validateText("First text", request.firstText());
        validateText("Second text", request.secondText());
        validateThreshold(request.similarityThreshold());
    }

    public void validateText(
            String fieldName,
            String text
    ) {
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
                            + maxTextLength
                            + " characters."
            );
        }

        if (!LETTER_OR_DIGIT.matcher(text).find()) {
            throw new InputValidationException(
                    fieldName
                            + " must contain at least one letter or digit."
            );
        }
    }

    private void validateThreshold(double threshold) {
        if (!Double.isFinite(threshold)
                || threshold < 0.0
                || threshold > 1.0) {

            throw new InputValidationException(
                    "Similarity threshold must be between 0.0 and 1.0."
            );
        }
    }
}
