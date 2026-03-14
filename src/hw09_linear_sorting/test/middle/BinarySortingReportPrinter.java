package hw09_linear_sorting.test.middle;

import java.io.PrintStream;
import java.util.List;

public final class BinarySortingReportPrinter {

    private BinarySortingReportPrinter() {
    }

    public static void print(List<BinarySortingRow> rows, PrintStream out) {
        out.println();
        out.println("============== RESULTS TABLE ==============");
        out.printf("%12s | %15s | %15s | %15s%n",
                "N", "CountingSort", "RadixSort", "BucketSort");
        out.println("---------------------------------------------------------------------");

        for (BinarySortingRow row : rows) {
            out.printf("%12d | %12d ms | %12d ms | %12d ms%n",
                    row.n(),
                    row.countingMs(),
                    row.radixMs(),
                    row.bucketMs());
        }

        out.println("===========================================");
        out.println();
        out.println("Correctness:");

        for (BinarySortingRow row : rows) {
            out.printf("N=%d -> Counting=%s, Radix=%s, Bucket=%s%n",
                    row.n(),
                    row.countingOk(),
                    row.radixOk(),
                    row.bucketOk());
        }
    }
}