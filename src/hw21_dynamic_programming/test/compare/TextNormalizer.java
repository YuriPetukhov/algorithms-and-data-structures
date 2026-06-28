package hw21_dynamic_programming.test.compare;

import hw21_dynamic_programming.test.config.CompareMode;

public final class TextNormalizer {

    private final CompareMode mode;

    public TextNormalizer(CompareMode mode) {
        this.mode = mode;
    }

    public String normalize(String text) {
        if (text == null) {
            return null;
        }
        String normalizedLineEndings = text
                .replace("\r\n", "\n")
                .replace('\r', '\n');
        return mode == CompareMode.TRIM
                ? normalizedLineEndings.trim()
                : normalizedLineEndings;
    }
}
