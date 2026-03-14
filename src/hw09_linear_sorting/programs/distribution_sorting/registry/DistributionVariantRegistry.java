package hw09_linear_sorting.programs.distribution_sorting.registry;

import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingVariant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

public final class DistributionVariantRegistry {

    private final Map<String, DistributionSortingVariant> variantsById = new LinkedHashMap<>();

    public DistributionVariantRegistry() {
        ServiceLoader<DistributionSortingVariant> serviceLoader =
                ServiceLoader.load(DistributionSortingVariant.class);

        for (DistributionSortingVariant variant : serviceLoader) {
            if (variant == null) {
                continue;
            }

            String normalizedId = normalize(variant.id());

            if (normalizedId.isEmpty()) {
                throw new IllegalStateException("Blank distribution variant id");
            }

            if (variantsById.containsKey(normalizedId)) {
                throw new IllegalStateException(
                        "Duplicate distribution variant id: " + normalizedId
                );
            }

            variantsById.put(normalizedId, variant);
        }

        if (variantsById.isEmpty()) {
            throw new IllegalStateException(
                    "No DistributionSortingVariant implementations found (ServiceLoader returned empty)"
            );
        }
    }

    public Optional<DistributionSortingVariant> find(String variantId) {
        if (variantId == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(variantsById.get(normalize(variantId)));
    }

    public List<DistributionSortingVariant> list() {
        return List.copyOf(variantsById.values());
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}