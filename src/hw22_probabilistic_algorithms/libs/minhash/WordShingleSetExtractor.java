package hw22_probabilistic_algorithms.libs.minhash;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public final class WordShingleSetExtractor
        implements ShingleSetExtractor {

    private final int shingleSize;

    public WordShingleSetExtractor(int shingleSize) {
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
    public Set<String> extract(String text) {
        String normalized = normalize(text);

        if (normalized.isEmpty()) {
            throw new IllegalArgumentException(
                    "Text must contain letters or digits."
            );
        }

        String[] words = normalized.split("\\s+");
        Set<String> shingles = new HashSet<>();

        if (words.length < shingleSize) {
            shingles.add(normalized);
            return Set.copyOf(shingles);
        }

        for (int start = 0;
             start <= words.length - shingleSize;
             start++) {

            shingles.add(buildShingle(words, start));
        }

        return Set.copyOf(shingles);
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
