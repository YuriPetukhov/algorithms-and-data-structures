package hw08_quick_and_merge_sort.programs.external_sorting.demo;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw06_sorting_algorithms.common.module.ModuleProvider;
import hw06_sorting_algorithms.common.module.ModuleRegistry;

import java.util.Optional;
import java.util.Scanner;

public final class ExternalSortingApp {

    public static void main(String[] args) {

        ModuleRegistry registry = new ModuleRegistry();

        Optional<ModuleProvider> providerOpt =
                registry.find("external_sorting");

        if (providerOpt.isEmpty()) {
            System.out.println("external_sorting module not found.");
            return;
        }

        ModuleProvider provider = providerOpt.get();

        System.out.println("Available variants:");

        for (ModuleProvider.TaskVariant variant : provider.variants()) {
            System.out.println(" - " + variant.id() +
                    " : " + variant.name());
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter variant id: ");
        String variantId = scanner.nextLine();

        Task task = provider.task(variantId);

        System.out.print("Enter input (N T): ");
        String input = scanner.nextLine();

        try {
            String result = task.run(input);
            System.out.println("\nResult:");
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
