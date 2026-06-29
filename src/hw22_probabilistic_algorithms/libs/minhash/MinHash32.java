package hw22_probabilistic_algorithms.libs.minhash;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public final class MinHash32 implements MinHash {

    private static final int SEED_STEP = 0x9e3779b9;

    private final int signatureSize;
    private final StringHasher32 featureHasher;
    private final int[] seeds;

    public MinHash32(
            int signatureSize,
            StringHasher32 featureHasher
    ) {
        if (signatureSize <= 0) {
            throw new IllegalArgumentException(
                    "Signature size must be positive."
            );
        }

        this.signatureSize = signatureSize;
        this.featureHasher = Objects.requireNonNull(
                featureHasher,
                "Feature hasher must not be null."
        );
        this.seeds = createSeeds(signatureSize);
    }

    public static MinHash32 withSignatureSize(
            int signatureSize
    ) {
        return new MinHash32(
                signatureSize,
                new Fnv1a32Hasher()
        );
    }

    @Override
    public int signatureSize() {
        return signatureSize;
    }

    @Override
    public int[] signature(Set<String> features) {
        Objects.requireNonNull(
                features,
                "Features must not be null."
        );

        if (features.isEmpty()) {
            throw new IllegalArgumentException(
                    "Feature set must not be empty."
            );
        }

        int[] signature = new int[signatureSize];
        Arrays.fill(signature, -1);

        for (String feature : features) {
            int baseHash = featureHasher.hash(
                    Objects.requireNonNull(
                            feature,
                            "Feature must not be null."
                    )
            );

            for (int index = 0;
                 index < signatureSize;
                 index++) {

                int candidate = mix32(
                        baseHash ^ seeds[index]
                );

                if (Integer.compareUnsigned(
                        candidate,
                        signature[index]
                ) < 0) {
                    signature[index] = candidate;
                }
            }
        }

        return signature;
    }

    private int[] createSeeds(int size) {
        int[] result = new int[size];
        int seed = 0x243f6a88;

        for (int index = 0;
             index < size;
             index++) {

            seed += SEED_STEP;
            result[index] = mix32(seed);
        }

        return result;
    }

    private static int mix32(int value) {
        int result = value;
        result ^= result >>> 16;
        result *= 0x7feb352d;
        result ^= result >>> 15;
        result *= 0x846ca68b;
        result ^= result >>> 16;
        return result;
    }
}
