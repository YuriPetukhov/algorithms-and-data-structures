package hw02_dynamic_programming_and_testing.test.compare;

import hw02_dynamic_programming_and_testing.test.config.CompareMode;

public class NormalizationStrategyFactory {

    public static TextNormalizationStrategy from(CompareMode mode) {
        return switch (mode) {
            case NONE -> new NoNormalization();
            case TRIM -> new TrimNormalization();
        };
    }
}
