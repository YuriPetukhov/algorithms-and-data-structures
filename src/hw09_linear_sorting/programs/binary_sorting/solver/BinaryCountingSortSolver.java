package hw09_linear_sorting.programs.binary_sorting.solver;

import hw09_linear_sorting.programs.binary_sorting.io.UInt16BinaryReader;
import hw09_linear_sorting.programs.binary_sorting.io.UInt16BinaryWriter;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortJob;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortResult;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortingParams;

public final class BinaryCountingSortSolver implements BinarySortSolver {

    private static final int MAX_VALUE = 65536;

    private final BinarySortingParams params;

    public BinaryCountingSortSolver(BinarySortingParams params) {
        this.params = params;
    }

    @Override
    public BinarySortResult solve(BinarySortJob job) throws Exception {

        long start = System.currentTimeMillis();

        int[] count = new int[MAX_VALUE];

        try (UInt16BinaryReader reader = new UInt16BinaryReader(job.inputFile())) {

            int value;

            while ((value = reader.read()) >= 0) {
                count[value]++;
            }
        }

        try (UInt16BinaryWriter writer = new UInt16BinaryWriter(job.outputFile())) {

            for (int value = 0; value < MAX_VALUE; value++) {

                int c = count[value];

                for (int i = 0; i < c; i++) {
                    writer.write(value);
                }
            }
        }

        long time = System.currentTimeMillis() - start;

        return new BinarySortResult(time);
    }
}