package hw22_probabilistic_algorithms.libs.simhash;

import java.util.Map;
import java.util.Objects;

public final class SimHash64 implements SimHash {

    private static final int HASH_BITS = Long.SIZE;

    private final FeatureExtractor featureExtractor;
    private final FeatureHasher featureHasher;

    public SimHash64(
            FeatureExtractor featureExtractor,
            FeatureHasher featureHasher
    ) {
        this.featureExtractor = Objects.requireNonNull(
                featureExtractor,
                "Feature extractor must not be null."
        );

        this.featureHasher = Objects.requireNonNull(
                featureHasher,
                "Feature hasher must not be null."
        );
    }

    public static SimHash64 forWordShingles(
            int shingleSize
    ) {
        return new SimHash64(
                new WordShingleExtractor(shingleSize),
                new Fnv1a64Hasher()
        );
    }

    @Override
    public long fingerprint(String text) {
        Map<String, Integer> features =
                featureExtractor.extract(text);

        if (features == null || features.isEmpty()) {
            throw new IllegalArgumentException(
                    "Feature set must not be null or empty."
            );
        }

        long[] vector = new long[HASH_BITS];

        for (Map.Entry<String, Integer> entry
                : features.entrySet()) {

            String feature = Objects.requireNonNull(
                    entry.getKey(),
                    "Feature must not be null."
            );

            Integer weightObject = Objects.requireNonNull(
                    entry.getValue(),
                    "Feature weight must not be null."
            );

            int weight = weightObject;

            if (weight <= 0) {
                throw new IllegalArgumentException(
                        "Feature weight must be positive."
                );
            }

            long featureHash = featureHasher.hash(feature);
            addToVector(vector, featureHash, weight);
        }

        return buildFingerprint(vector);
    }

    private void addToVector(
            long[] vector,
            long featureHash,
            int weight
    ) {
        for (int bit = 0; bit < HASH_BITS; bit++) {
            boolean bitIsSet =
                    ((featureHash >>> bit) & 1L) != 0L;

            vector[bit] += bitIsSet
                    ? weight
                    : -weight;
        }
    }

    private long buildFingerprint(long[] vector) {
        long fingerprint = 0L;

        for (int bit = 0; bit < HASH_BITS; bit++) {
            if (vector[bit] > 0L) {
                fingerprint |= 1L << bit;
            }
        }

        return fingerprint;
    }
}
