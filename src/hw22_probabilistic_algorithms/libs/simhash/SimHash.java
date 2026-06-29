package hw22_probabilistic_algorithms.libs.simhash;

public interface SimHash {

    long fingerprint(String text);

    default int distance(long first, long second) {
        return Long.bitCount(first ^ second);
    }

    default double similarity(long first, long second) {
        return 1.0 - distance(first, second) / (double) Long.SIZE;
    }

    default boolean isSimilar(
            long first,
            long second,
            int maxDistance
    ) {
        if (maxDistance < 0 || maxDistance > Long.SIZE) {
            throw new IllegalArgumentException(
                    "Max distance must be between 0 and 64."
            );
        }

        return distance(first, second) <= maxDistance;
    }
}
