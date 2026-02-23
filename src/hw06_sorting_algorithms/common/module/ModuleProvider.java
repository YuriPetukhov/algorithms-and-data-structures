package hw06_sorting_algorithms.common.module;

import hw02_dynamic_programming_and_testing.app.core.Task;

import java.util.List;

public interface ModuleProvider {
    String type();

    List<TaskVariant> variants();

    Task task(String variantId);

    record TaskVariant(String id, String name) {}
}