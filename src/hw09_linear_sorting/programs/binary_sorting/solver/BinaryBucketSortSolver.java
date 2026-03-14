package hw09_linear_sorting.programs.binary_sorting.solver;

import hw09_linear_sorting.programs.binary_sorting.io.UInt16BinaryReader;
import hw09_linear_sorting.programs.binary_sorting.io.UInt16BinaryWriter;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortJob;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortResult;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortingParams;

public final class BinaryBucketSortSolver implements BinarySortSolver {

    private static final int BUCKETS = 256;
    private static final int BUCKET_SIZE = 256;

    private final BinarySortingParams params;

    public BinaryBucketSortSolver(BinarySortingParams params) {
        this.params = params;
    }

    @Override
    public BinarySortResult solve(BinarySortJob job) throws Exception {

        long start = System.currentTimeMillis();

        int[][] bucketCounts = new int[BUCKETS][BUCKET_SIZE];

        try (UInt16BinaryReader reader = new UInt16BinaryReader(job.inputFile())) {
            int value;

            while ((value = reader.read()) >= 0) {
                int bucketIndex = (value >>> 8) & 0xFF;
                int offset = value & 0xFF;

                bucketCounts[bucketIndex][offset]++;
            }
        }

        try (UInt16BinaryWriter writer = new UInt16BinaryWriter(job.outputFile())) {

            for (int bucketIndex = 0; bucketIndex < BUCKETS; bucketIndex++) {
                int base = bucketIndex << 8;

                for (int offset = 0; offset < BUCKET_SIZE; offset++) {
                    int count = bucketCounts[bucketIndex][offset];
                    int value = base | offset;

                    for (int i = 0; i < count; i++) {
                        writer.write(value);
                    }
                }
            }
        }

        long time = System.currentTimeMillis() - start;
        return new BinarySortResult(time);
    }
}