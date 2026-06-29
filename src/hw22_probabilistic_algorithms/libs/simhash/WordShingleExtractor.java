package hw22_probabilistic_algorithms.libs.simhash;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class WordShingleExtractor
        implements FeatureExtractor {

    private final int shingleSize;

    public WordShingleExtractor(int shingleSize) {
        if (shingleSize <= 0) {
            throw new IllegalArgumentException(
                    "Shingle size must be positive."
            );
        }

        this.shingleSize = shingleSize;
    }

    public int shingleSize() {
        return shingleSize;
    }

    @Override
    public Map<String, Integer> extract(String text) {
        String normalized = normalize(text);

        if (normalized.isEmpty()) {
            throw new IllegalArgumentException(
                    "Text must contain letters or digits."
            );
        }

        String[] words = normalized.split("\\s+");
        Map<String, Integer> features = new HashMap<>();

        if (words.length < shingleSize) {
            features.put(normalized, 1);
            return Map.copyOf(features);
        }

        for (int start = 0;
             start <= words.length - shingleSize;
             start++) {

            String shingle = buildShingle(words, start);
            features.merge(shingle, 1, Integer::sum);
        }

        return Map.copyOf(features);
    }

    private String buildShingle(
            String[] words,
            int start
    ) {
        StringBuilder result = new StringBuilder();

        for (int offset = 0;
             offset < shingleSize;
             offset++) {

            if (offset > 0) {
                result.append(' ');
            }

            result.append(words[start + offset]);
        }

        return result.toString();
    }

    private String normalize(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException(
                    "Text must not be blank."
            );
        }

        return Normalizer
                .normalize(text, Normalizer.Form.NFKC)
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^\\p{L}\\p{N}]+", " ")
                .trim();
    }
}
