package hw06_sorting_algorithms.programs.sorting.registry;

import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;

import java.util.*;

public final class SortingVariantRegistry {

    private final Map<String, SortingVariant> variantsById = new LinkedHashMap<>();

    public SortingVariantRegistry() {
        ServiceLoader<SortingVariant> serviceLoader = ServiceLoader.load(SortingVariant.class);

        for (SortingVariant sortingVariant : serviceLoader) {
            if (sortingVariant == null) continue;

            String normalizedId = normalize(sortingVariant.id());

            if (normalizedId.isEmpty()) {
                throw new IllegalStateException("Blank sorting variant id");
            }

            if (variantsById.containsKey(normalizedId)) {
                throw new IllegalStateException("Duplicate sorting variant id: " + normalizedId);
            }

            variantsById.put(normalizedId, sortingVariant);
        }

        if (variantsById.isEmpty()) {
            throw new IllegalStateException(
                    "No SortingVariant implementations found (ServiceLoader returned empty)"
            );
        }
    }

    public Optional<SortingVariant> find(String variantId) {
        if (variantId == null) return Optional.empty();
        return Optional.ofNullable(variantsById.get(normalize(variantId)));
    }

    public List<SortingVariant> list() {
        return List.copyOf(variantsById.values());
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}