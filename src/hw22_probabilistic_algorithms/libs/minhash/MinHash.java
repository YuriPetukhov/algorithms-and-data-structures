package hw22_probabilistic_algorithms.libs.minhash;

import java.util.Set;

public interface MinHash {

    int signatureSize();

    int[] signature(Set<String> features);

    default double similarity(
            int[] first,
            int[] second
    ) {
        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Signatures must not be null."
            );
        }

        if (first.length != second.length) {
            throw new IllegalArgumentException(
                    "Signatures must have equal length."
            );
        }

        if (first.length == 0) {
            throw new IllegalArgumentException(
                    "Signatures must not be empty."
            );
        }

        int equal = 0;

        for (int index = 0;
             index < first.length;
             index++) {

            if (first[index] == second[index]) {
                equal++;
            }
        }

        return equal / (double) first.length;
    }
}
