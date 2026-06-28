package hw21_dynamic_programming.registry;

import hw21_dynamic_programming.algorithms.api.Algorithm;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class InMemoryAlgorithmRegistry implements AlgorithmRegistry {

    private final Map<String, Algorithm<?, ?>> algorithmsById;

    public InMemoryAlgorithmRegistry(Collection<? extends Algorithm<?, ?>> algorithms) {
        Objects.requireNonNull(algorithms, "Algorithm collection must not be null.");

        Map<String, Algorithm<?, ?>> registry = new LinkedHashMap<>();
        for (Algorithm<?, ?> algorithm : algorithms) {
            validateAlgorithm(algorithm);
            Algorithm<?, ?> previous = registry.putIfAbsent(algorithm.id(), algorithm);
            if (previous != null) {
                throw new IllegalArgumentException("Duplicate algorithm id: " + algorithm.id());
            }
        }
        this.algorithmsById = Collections.unmodifiableMap(new LinkedHashMap<>(registry));
    }

    @Override
    public List<Algorithm<?, ?>> getAll() {
        return List.copyOf(algorithmsById.values());
    }

    @Override
    public Optional<Algorithm<?, ?>> findById(String algorithmId) {
        if (algorithmId == null || algorithmId.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(algorithmsById.get(algorithmId));
    }

    private static void validateAlgorithm(Algorithm<?, ?> algorithm) {
        Objects.requireNonNull(algorithm, "Registered algorithm must not be null.");
        if (algorithm.id() == null || algorithm.id().isBlank()) {
            throw new IllegalArgumentException("Algorithm id must not be blank.");
        }
        Objects.requireNonNull(algorithm.inputType(), "Algorithm input type must not be null.");
        Objects.requireNonNull(algorithm.resultType(), "Algorithm result type must not be null.");
    }
}
