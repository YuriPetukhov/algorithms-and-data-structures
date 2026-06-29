package hw22_probabilistic_algorithms.libs.minhash;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class Fnv1a32Hasher
        implements StringHasher32 {

    private static final int OFFSET_BASIS = 0x811c9dc5;
    private static final int PRIME = 0x01000193;

    @Override
    public int hash(String value) {
        Objects.requireNonNull(
                value,
                "Value must not be null."
        );

        int hash = OFFSET_BASIS;

        for (byte current
                : value.getBytes(StandardCharsets.UTF_8)) {

            hash ^= current & 0xff;
            hash *= PRIME;
        }

        return hash;
    }
}
