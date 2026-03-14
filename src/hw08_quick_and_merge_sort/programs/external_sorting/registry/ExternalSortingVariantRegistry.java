package hw08_quick_and_merge_sort.programs.external_sorting.registry;

import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortingVariant;

import java.util.*;

public final class ExternalSortingVariantRegistry {

    private final Map<String, ExternalSortingVariant> variantsById = new LinkedHashMap<>();

    public ExternalSortingVariantRegistry() {

        ServiceLoader<ExternalSortingVariant> loader =
                ServiceLoader.load(ExternalSortingVariant.class);

        for (ExternalSortingVariant variant : loader) {
            if (variant == null) continue;

            String id = normalize(variant.id());

            if (id.isEmpty()) {
                throw new IllegalStateException("Blank external programs variant id");
            }

            if (variantsById.containsKey(id)) {
                throw new IllegalStateException("Duplicate external programs variant id: " + id);
            }

            variantsById.put(id, variant);
        }

        if (variantsById.isEmpty()) {
            throw new IllegalStateException(
                    "No ExternalSortingVariant implementations found"
            );
        }
    }

    public Optional<ExternalSortingVariant> find(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(variantsById.get(normalize(id)));
    }

    public List<ExternalSortingVariant> list() {
        return List.copyOf(variantsById.values());
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
