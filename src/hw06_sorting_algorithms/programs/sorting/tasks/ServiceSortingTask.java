package hw06_sorting_algorithms.programs.sorting.tasks;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.programs.sorting.registry.SortingVariantRegistry;
import hw06_sorting_algorithms.programs.sorting.solver.SortSolverAdapter;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;

import java.util.StringTokenizer;

public final class ServiceSortingTask implements MeasurableTask<int[], int[]> {

    private static final SortingVariantRegistry VARIANT_REGISTRY = new SortingVariantRegistry();

    private final String variantId;
    private final String variantDisplayName;
    private final SortSolverAdapter solverAdapter;

    public ServiceSortingTask(String variantId) {
        if (variantId == null || variantId.isBlank()) {
            throw new IllegalArgumentException("variantId is blank");
        }

        SortingVariant sortingVariant = VARIANT_REGISTRY.find(variantId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown sorting variant id: " + variantId));

        this.variantId = sortingVariant.id();
        this.variantDisplayName = sortingVariant.displayName();

        SortAlgorithm sortAlgorithm = sortingVariant.build(SortingParams.defaults());
        if (sortAlgorithm == null) {
            throw new IllegalStateException("SortingVariant.build() returned null for id: " + this.variantId);
        }

        this.solverAdapter = new SortSolverAdapter(sortAlgorithm, true);
    }

    @Override
    public String id() {
        return variantId;
    }

    @Override
    public String displayName() {
        return variantDisplayName;
    }

    @Override
    public int[] parse(String rawInput) {
        if (rawInput == null) throw new IllegalArgumentException("input is null");

        String trimmedInput = rawInput.trim();
        if (trimmedInput.isEmpty()) throw new IllegalArgumentException("empty input");

        String normalized = trimmedInput.replace("\r\n", "\n").replace("\r", "\n");
        String[] lines = normalized.split("\n");

        if (lines.length >= 2) {
            int expectedLength = parseIntStrict(lines[0].trim(), "n");
            if (expectedLength < 0) throw new IllegalArgumentException("n must be >= 0");
            return parseIntArray(lines[1], expectedLength);
        }

        return parseIntArray(lines[0], -1);
    }

    @Override
    public int[] compute(int[] inputArray) {
        return solverAdapter.solve(inputArray);
    }

    @Override
    public String format(int[] sortedArray) {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < sortedArray.length; index++) {
            if (index != 0) builder.append(' ');
            builder.append(sortedArray[index]);
        }
        return builder.toString();
    }

    private static int parseIntStrict(String token, String fieldName) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected integer " + fieldName + ", got: " + token);
        }
    }

    private static int[] parseIntArray(String line, int expectedLengthOrMinus1) {
        StringTokenizer tokenizer = new StringTokenizer(line);
        int tokenCount = tokenizer.countTokens();

        if (expectedLengthOrMinus1 >= 0 && tokenCount != expectedLengthOrMinus1) {
            throw new IllegalArgumentException(
                    "Expected " + expectedLengthOrMinus1 + " numbers, got: " + tokenCount
            );
        }

        int[] parsedArray = new int[tokenCount];
        for (int index = 0; index < tokenCount; index++) {
            parsedArray[index] = parseIntStrict(tokenizer.nextToken(), "a[" + index + "]");
        }

        return parsedArray;
    }
}