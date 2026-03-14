package hw09_linear_sorting.programs.distribution_sorting;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.ops.PlainIntArrayOps;
import hw06_sorting_algorithms.visual.platform.*;
import hw06_sorting_algorithms.visual.platform.compare.*;
import hw06_sorting_algorithms.visual.scene.Scene;
import hw06_sorting_algorithms.visual.ui.status.UiState;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingParams;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingVariant;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionVisualization;
import hw09_linear_sorting.programs.distribution_sorting.ui.DistributionSortingController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public final class DistributionSortingProgramBundle
        implements ProgramBundle<int[], UiState>, CompareCapable<int[]>, ModeCapable {

    private static final String SORTING_PREFIX_RU = "Сортировка:";

    private final DistributionSortingController controller;
    private final List<AlgorithmVariant<int[]>> variants;

    public DistributionSortingProgramBundle(
            DistributionSortingController controller
    ) {
        if (controller == null) throw new IllegalArgumentException("controller is null");
        this.controller = controller;
        this.variants = controller.variants().stream()
                .map(variant -> (AlgorithmVariant<int[]>) new DistributionVariantAdapter(
                        variant,
                        controller::params
                ))
                .toList();
    }

    @Override
    public String id() {
        return "distribution_sorting";
    }

    @Override
    public String programName() {
        return "Distribution Sorting";
    }

    @Override
    public ProgramController<int[]> controller() {
        return controller;
    }

    @Override
    public Scene<? extends UiState> scene() {
        return currentVisualization().createScene();
    }

    @Override
    public Player<? extends UiState> buildPlayer(int[] input) {
        return currentVisualization().buildPlayer(input);
    }

    @Override
    public List<AlgorithmVariant<int[]>> variants() {
        return variants;
    }

    @Override
    public String inputLabel(int[] lockedInput) {
        int size = lockedInput == null ? 0 : lockedInput.length;
        return "n=" + size;
    }

    @Override
    public List<ModeDescriptor> modes() {
        return List.of(
                new ModeDescriptor("compare", "Compare", true)
        );
    }

    @Override
    public void applyMode(String modeId) {
        controller.setVariantFilter(null);
    }

    @Override
    public Scene<?> sceneForMode(String modeId) {
        return currentVisualization().createScene();
    }

    @Override
    public CompareReport compare(int[] lockedInput, CompareRequest request) {
        CompareSettings settings = request.settings();

        List<CompareRow> rows = new ArrayList<>();
        for (AlgorithmVariant<int[]> variant : variants) {
            if (!request.selectedIds().contains(variant.id())) {
                continue;
            }

            try {
                long bestNanos = measureBestNanos(
                        variant,
                        lockedInput,
                        settings.warmupRuns(),
                        settings.runs()
                );

                rows.add(new CompareRow(
                        variant.id(),
                        variant.algorithmName(),
                        bestNanos,
                        "OK"
                ));
            } catch (Exception exception) {
                String message = exception.getMessage() == null
                        ? exception.toString()
                        : exception.getMessage();

                rows.add(new CompareRow(
                        variant.id(),
                        variant.algorithmName(),
                        -1L,
                        "ERROR: " + message
                ));
            }
        }

        return new CompareReport(
                "Compare — " + programName(),
                inputLabel(lockedInput),
                rows
        );
    }

    private static long measureBestNanos(
            AlgorithmVariant<int[]> algorithmVariant,
            int[] input,
            int warmupRuns,
            int measuredRuns
    ) {
        for (int warmupIndex = 0; warmupIndex < warmupRuns; warmupIndex++) {
            algorithmVariant.runPlain(input);
        }

        long bestNanos = Long.MAX_VALUE;
        for (int runIndex = 0; runIndex < measuredRuns; runIndex++) {
            long startNanos = System.nanoTime();
            algorithmVariant.runPlain(input);
            long endNanos = System.nanoTime();

            bestNanos = Math.min(bestNanos, endNanos - startNanos);
        }

        return bestNanos;
    }

    private record DistributionVariantAdapter(
            DistributionSortingVariant variant,
            Supplier<DistributionSortingParams> paramsSupplier
    ) implements AlgorithmVariant<int[]> {

        @Override
        public String id() {
            return variant.id();
        }

        @Override
        public String algorithmName() {
            return shortenSortingDisplayName(variant.displayName());
        }

        @Override
        public void runPlain(int[] input) {
            if (input == null) {
                throw new IllegalArgumentException("input is null");
            }

            SortAlgorithm algorithm = variant.build(paramsSupplier.get());
            if (algorithm == null) {
                throw new IllegalStateException("algorithm is null for variant: " + variant.id());
            }

            int[] workingCopy = Arrays.copyOf(input, input.length);
            algorithm.sort(workingCopy, PlainIntArrayOps.INSTANCE);
        }
    }

    private static String shortenSortingDisplayName(String displayName) {
        if (displayName == null) return "";
        return displayName.startsWith(SORTING_PREFIX_RU)
                ? displayName.substring(SORTING_PREFIX_RU.length()).trim()
                : displayName;
    }

    private DistributionSortingVariant currentVariant() {
        DistributionSortingVariant selectedVariant = controller.selectedVariant();
        if (selectedVariant != null) {
            return selectedVariant;
        }

        List<DistributionSortingVariant> availableVariants = controller.variants();
        if (availableVariants.isEmpty()) {
            throw new IllegalStateException("No distribution variants available");
        }

        return availableVariants.get(0);
    }

    private DistributionVisualization currentVisualization() {
        return currentVariant().buildVisualization(controller.params());
    }
}