package hw09_linear_sorting.test.middle;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AllBinarySortingTestApp {

    public static void main(String[] args) {
        try {
            List<BinarySortingRow> rows = BinarySortingBenchmarkRunner.runAll();

            String ts = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            Files.createDirectories(Path.of("tests/results"));

            try (PrintStream out = new PrintStream(
                    "tests/results/hw09_sorting_report_" + ts + ".txt")) {

                BinarySortingReportPrinter.print(rows, out);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}