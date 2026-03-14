package hw09_linear_sorting.programs.binary_sorting.solver;

import hw09_linear_sorting.programs.binary_sorting.io.UInt16BinaryReader;
import hw09_linear_sorting.programs.binary_sorting.io.UInt16BinaryWriter;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortJob;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortResult;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortingParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class BinaryRadixSortSolver implements BinarySortSolver {

    private static final int RADIX = 256;

    private final BinarySortingParams params;

    public BinaryRadixSortSolver(BinarySortingParams params) {
        this.params = params;
    }

    @Override
    public BinarySortResult solve(BinarySortJob job) throws Exception {

        long start = System.currentTimeMillis();

        Path workDir = job.workDir();
        Files.createDirectories(workDir);

        Path temp1 = workDir.resolve("radix_pass_1.bin");

        countingPass(job.inputFile(), temp1, 0);
        countingPass(temp1, job.outputFile(), 8);

        Files.deleteIfExists(temp1);

        long time = System.currentTimeMillis() - start;
        return new BinarySortResult(time);
    }

    private void countingPass(Path inputFile, Path outputFile, int shift) throws IOException {

        int[] count = new int[RADIX];

        try (UInt16BinaryReader reader = new UInt16BinaryReader(inputFile)) {
            int value;

            while ((value = reader.read()) >= 0) {
                int digit = (value >>> shift) & 0xFF;
                count[digit]++;
            }
        }

        int[] startPos = new int[RADIX];
        for (int i = 1; i < RADIX; i++) {
            startPos[i] = startPos[i - 1] + count[i - 1];
        }

        int total = 0;
        for (int c : count) {
            total += c;
        }

        int[] values = new int[total];

        try (UInt16BinaryReader reader = new UInt16BinaryReader(inputFile)) {
            int value;

            while ((value = reader.read()) >= 0) {
                int digit = (value >>> shift) & 0xFF;
                values[startPos[digit]++] = value;
            }
        }

        try (UInt16BinaryWriter writer = new UInt16BinaryWriter(outputFile)) {
            for (int value : values) {
                writer.write(value);
            }
        }
    }
}