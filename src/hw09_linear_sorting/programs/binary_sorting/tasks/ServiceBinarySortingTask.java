package hw09_linear_sorting.programs.binary_sorting.tasks;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw09_linear_sorting.programs.binary_sorting.io.UInt16RandomBinaryFileGenerator;
import hw09_linear_sorting.programs.binary_sorting.registry.BinarySortingVariantRegistry;
import hw09_linear_sorting.programs.binary_sorting.solver.BinarySortSolver;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortJob;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortResult;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortingParams;
import hw09_linear_sorting.programs.binary_sorting.variants.BinarySortingVariant;

import java.nio.file.Files;
import java.nio.file.Path;

public final class ServiceBinarySortingTask
        implements MeasurableTask<String, String> {

    private final String variantId;
    private final BinarySortingVariantRegistry registry =
            new BinarySortingVariantRegistry();

    public ServiceBinarySortingTask(String variantId) {
        this.variantId = variantId;
    }

    @Override
    public String id() {
        return variantId;
    }

    @Override
    public String displayName() {
        return "Binary programs: " + variantId;
    }

    @Override
    public String parse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input is null");
        }

        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Expected: <N>");
        }

        Long.parseLong(trimmed);
        return trimmed;
    }

    @Override
    public String compute(String parsedInput) {
        try {
            long n = Long.parseLong(parsedInput);

            if (n <= 0) {
                throw new IllegalArgumentException("N must be > 0");
            }

            BinarySortingVariant variant = registry.find(variantId)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Unknown variant: " + variantId));

            BinarySortingParams params = new BinarySortingParams(1_000_000);

            BinarySortSolver solver = variant.build(params);

            Path baseDir = Path.of("tests", "hw09", "binary_sorting");
            Files.createDirectories(baseDir);

            String runName = "run_" + System.currentTimeMillis();
            Path workDir = baseDir.resolve(runName);
            Files.createDirectories(workDir);

            Path inputFile = workDir.resolve("input.bin");
            Path outputFile = workDir.resolve("output.bin");

            UInt16RandomBinaryFileGenerator.generate(inputFile, n);

            BinarySortJob job = new BinarySortJob(
                    inputFile,
                    outputFile,
                    workDir,
                    n
            );

            BinarySortResult result = solver.solve(job);

            return "Time: " + result.timeMillis() + " ms";

        } catch (Exception e) {
            throw new RuntimeException("Binary programs failed", e);
        }
    }

    @Override
    public String format(String result) {
        return result;
    }
}