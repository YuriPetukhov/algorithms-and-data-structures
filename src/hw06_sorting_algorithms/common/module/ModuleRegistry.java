package hw06_sorting_algorithms.common.module;

import java.util.*;

public final class ModuleRegistry {

    private final Map<String, ModuleProvider> providersByType = new LinkedHashMap<>();

    public ModuleRegistry() {
        ServiceLoader<ModuleProvider> serviceLoader = ServiceLoader.load(ModuleProvider.class);

        for (ModuleProvider moduleProvider : serviceLoader) {
            String normalizedType = normalize(moduleProvider.type());

            if (providersByType.containsKey(normalizedType)) {
                throw new IllegalStateException("Duplicate task type: " + normalizedType);
            }

            providersByType.put(normalizedType, moduleProvider);
        }
    }

    public Optional<ModuleProvider> find(String type) {
        if (type == null) return Optional.empty();
        return Optional.ofNullable(providersByType.get(normalize(type)));
    }

    private static String normalize(String value) {
        return value.trim().toLowerCase(Locale.ROOT);
    }
}