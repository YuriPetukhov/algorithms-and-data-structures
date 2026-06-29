package hw22_probabilistic_algorithms.libs.minhash;

import java.util.Set;

public final class JaccardSimilarity {

    private JaccardSimilarity() {
    }

    public static double calculate(
            Set<String> first,
            Set<String> second
    ) {
        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Feature sets must not be null."
            );
        }

        if (first.isEmpty() && second.isEmpty()) {
            return 1.0;
        }

        Set<String> smaller = first.size() <= second.size()
                ? first
                : second;

        Set<String> larger = first.size() <= second.size()
                ? second
                : first;

        long intersection = 0L;

        for (String feature : smaller) {
            if (larger.contains(feature)) {
                intersection++;
            }
        }

        long union = first.size()
                + (long) second.size()
                - intersection;

        return intersection / (double) union;
    }
}
