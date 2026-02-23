package hw06_sorting_algorithms.programs.sorting;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.ops.PlainIntArrayOps;
import hw06_sorting_algorithms.programs.sorting.player.SortPlaybackState;
import hw06_sorting_algorithms.programs.sorting.player.TracePlayer;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;
import hw06_sorting_algorithms.programs.sorting.trace.TraceRecorder;
import hw06_sorting_algorithms.programs.sorting.trace.TracingIntArrayOps;
import hw06_sorting_algorithms.programs.sorting.ui.SortingController;
import hw06_sorting_algorithms.visual.platform.AlgorithmVariant;
import hw06_sorting_algorithms.visual.platform.Player;
import hw06_sorting_algorithms.visual.platform.ProgramBundle;
import hw06_sorting_algorithms.visual.platform.ProgramController;
import hw06_sorting_algorithms.visual.platform.compare.CompareCapable;
import hw06_sorting_algorithms.visual.platform.compare.CompareReport;
import hw06_sorting_algorithms.visual.platform.compare.CompareRequest;
import hw06_sorting_algorithms.visual.platform.compare.CompareRow;
import hw06_sorting_algorithms.visual.platform.compare.CompareSettings;
import hw06_sorting_algorithms.visual.scene.Scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public final class SortingProgramBundle implements ProgramBundle<int[], SortPlaybackState>, CompareCapable<int[]> {

    private static final String SORTING_PREFIX_RU = "Сортировка:";

    private final SortingController controller;
    private final Scene<SortPlaybackState> scene;
    private final List<AlgorithmVariant<int[]>> variants;

    public SortingProgramBundle(SortingController controller, Scene<SortPlaybackState> scene) {
        this.controller = controller;
        this.scene = scene;

        this.variants = controller.variants().stream()
                .map(sortingVariant -> (AlgorithmVariant<int[]>) new SortingVariantAdapter(sortingVariant, controller::params))
                .toList();
    }

    @Override
    public String id() {
        return "sorting";
    }

    @Override
    public String programName() {
        return "Sorting";
    }

    @Override
    public ProgramController<int[]> controller() {
        return controller;
    }

    @Override
    public Scene<SortPlaybackState> scene() {
        return scene;
    }

    @Override
    public Player<SortPlaybackState> buildPlayer(int[] input) {
        SortingVariant selectedVariant = controller.selectedVariant();
        if (selectedVariant == null) throw new IllegalStateException("No algorithm selected");

        SortAlgorithm sortAlgorithm = selectedVariant.build(controller.params());
        if (sortAlgorithm == null) throw new IllegalStateException("Selected variant has null algorithm");

        TraceRecorder traceRecorder = new TraceRecorder();
        int[] workingCopy = Arrays.copyOf(input, input.length);

        sortAlgorithm.sort(workingCopy, new TracingIntArrayOps(traceRecorder));

        return new TracePlayer(input, traceRecorder.snapshot());
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
    public CompareReport compare(int[] lockedInput, CompareRequest request) {
        CompareSettings settings = request.settings();
        Set<String> selectedVariantIds = request.selectedIds();

        List<CompareRow> rows = new ArrayList<>();
        for (AlgorithmVariant<int[]> algorithmVariant : variants) {
            if (!selectedVariantIds.contains(algorithmVariant.id())) continue;

            try {
                long bestNanos = measureBestNanos(
                        algorithmVariant,
                        lockedInput,
                        settings.warmupRuns(),
                        settings.runs()
                );
                rows.add(new CompareRow(
                        algorithmVariant.id(),
                        algorithmVariant.algorithmName(),
                        bestNanos,
                        "OK"
                ));
            } catch (Exception exception) {
                String message = exception.getMessage() == null ? exception.toString() : exception.getMessage();
                rows.add(new CompareRow(
                        algorithmVariant.id(),
                        algorithmVariant.algorithmName(),
                        -1L,
                        "ERROR: " + message
                ));
            }
        }

        return new CompareReport("Compare — " + programName(), inputLabel(lockedInput), rows);
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

    private static String shortDisplayName(String displayName) {
        if (displayName == null) return "";
        return displayName.startsWith(SORTING_PREFIX_RU)
                ? displayName.substring(SORTING_PREFIX_RU.length()).trim()
                : displayName;
    }

    private record SortingVariantAdapter(
            SortingVariant sortingVariant,
            Supplier<SortingParams> paramsSupplier
    ) implements AlgorithmVariant<int[]> {

        private SortingVariantAdapter {
            if (sortingVariant == null) throw new IllegalArgumentException("sortingVariant is null");
            if (paramsSupplier == null) throw new IllegalArgumentException("paramsSupplier is null");
        }

        @Override
        public String id() {
            return sortingVariant.id();
        }

        @Override
        public String algorithmName() {
            return shortDisplayName(sortingVariant.displayName());
        }

        @Override
        public void runPlain(int[] input) {
            if (input == null) throw new IllegalArgumentException("input is null");

            SortingParams params = paramsSupplier.get();
            SortAlgorithm sortAlgorithm = sortingVariant.build(params);

            if (sortAlgorithm == null) {
                throw new IllegalStateException("algorithm is null for variant: " + sortingVariant.id());
            }

            int[] workingCopy = Arrays.copyOf(input, input.length);
            sortAlgorithm.sort(workingCopy, PlainIntArrayOps.INSTANCE);
        }
    }
}