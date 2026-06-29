package hw22_probabilistic_algorithms.libs.simhash;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class Fnv1a64Hasher implements FeatureHasher {

    private static final long OFFSET_BASIS =
            0xcbf29ce484222325L;

    private static final long PRIME =
            0x100000001b3L;

    @Override
    public long hash(String feature) {
        Objects.requireNonNull(
                feature,
                "Feature must not be null."
        );

        long hash = OFFSET_BASIS;
        byte[] bytes = feature.getBytes(StandardCharsets.UTF_8);

        for (byte value : bytes) {
            hash ^= value & 0xffL;
            hash *= PRIME;
        }

        return hash;
    }
}
