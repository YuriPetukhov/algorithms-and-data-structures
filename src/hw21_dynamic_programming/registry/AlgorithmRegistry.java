package hw21_dynamic_programming.registry;

import hw21_dynamic_programming.algorithms.api.Algorithm;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface AlgorithmRegistry {

    List<Algorithm<?, ?>> getAll();

    Optional<Algorithm<?, ?>> findById(String algorithmId);

    default Algorithm<?, ?> getRequired(String algorithmId) {
        return findById(algorithmId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Algorithm not found: " + algorithmId
                ));
    }
}
