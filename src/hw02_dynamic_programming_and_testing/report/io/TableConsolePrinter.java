package hw02_dynamic_programming_and_testing.report.io;

import hw02_dynamic_programming_and_testing.report.model.Cell;
import hw02_dynamic_programming_and_testing.report.model.ReportTable;
import hw02_dynamic_programming_and_testing.test.model.TestStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TableConsolePrinter {

    public void print(ReportTable table) {

        List<String> taskOrder = table.columnOrder();

        List<List<String>> rows = new ArrayList<>();

        List<String> header = new ArrayList<>();
        header.add("N");
        header.addAll(taskOrder);
        rows.add(header);

        for (var e : table.rows().entrySet()) {
            String rowKey = e.getKey();
            Map<String, Cell> cells = e.getValue();

            List<String> row = new ArrayList<>();
            row.add(rowKey);

            for (String task : taskOrder) {
                row.add(formatCell(cells.get(task), table));
            }
            rows.add(row);
        }

        int[] w = Format.columnWidths(rows);

        System.out.println("\nSuite: " + table.suiteName());
        System.out.println(
                "Time: " + table.aggregation() +
                        " of " + table.runs() +
                        " runs, unit: " + table.displayTimeUnit()
        );
        System.out.println();

        Format.printRow(rows.get(0), w);
        Format.printSep(w);

        for (int i = 1; i < rows.size(); i++) {
            Format.printRow(rows.get(i), w);
        }
    }


    private String formatCell(Cell c, ReportTable table) {
        if (c == null) return "";
        if (c.status() != TestStatus.PASSED) return "ERR";
        if (c.timeNanos() == null) return "";

        TimeUnit unit = table.displayTimeUnit();

        long nanos = c.timeNanos();
        long v = unit.convert(nanos, TimeUnit.NANOSECONDS);

        return Format.formatNumber(v);
    }
}
