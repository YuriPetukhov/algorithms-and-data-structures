package hw09_linear_sorting.programs.binary_sorting.registry;

import hw09_linear_sorting.programs.binary_sorting.variants.BinarySortingVariant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

public final class BinarySortingVariantRegistry {

    private final Map<String, BinarySortingVariant> variantsById = new LinkedHashMap<>();

    public BinarySortingVariantRegistry() {
        ServiceLoader<BinarySortingVariant> serviceLoader =
                ServiceLoader.load(BinarySortingVariant.class);

        for (BinarySortingVariant variant : serviceLoader) {
            if (variant == null) continue;

            String normalizedId = normalize(variant.id());

            if (normalizedId.isEmpty()) {
                throw new IllegalStateException("Blank distribution programs variant id");
            }

            if (variantsById.containsKey(normalizedId)) {
                throw new IllegalStateException(
                        "Duplicate distribution programs variant id: " + normalizedId
                );
            }

            variantsById.put(normalizedId, variant);
        }

        if (variantsById.isEmpty()) {
            throw new IllegalStateException(
                    "No BinarySortingVariant implementations found"
            );
        }
    }

    public Optional<BinarySortingVariant> find(String variantId) {
        if (variantId == null) return Optional.empty();
        return Optional.ofNullable(variantsById.get(normalize(variantId)));
    }

    public List<BinarySortingVariant> list() {
        return List.copyOf(variantsById.values());
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}