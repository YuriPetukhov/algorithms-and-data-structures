package hw10_search_trees.demo;

import hw10_search_trees.benchmark.*;
import hw10_search_trees.libs.searching.trees.avl.AvlTreeInt;
import hw10_search_trees.libs.searching.trees.bst.BinaryIntSearchTree;
import hw10_search_trees.libs.searching.trees.treap.Treap;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class SearchTreesBenchmarkApp {

    private SearchTreesBenchmarkApp() {
    }

    public static void main(String[] args) {
        try {
            int n = 20_000;
            long seed = 42L;

            List<SearchTreeVariant> variants = List.of(
                    new SearchTreeVariant(
                            "bst",
                            "BinarySearchTree",
                            BinaryIntSearchTree::new
                    ),
                    new SearchTreeVariant(
                            "avl",
                            "AvlTree",
                            AvlTreeInt::new
                    ),
                    new SearchTreeVariant(
                            "treap",
                            "Treap",
                            Treap::new
                    )
            );

            TreeBenchmarkRunner runner = new TreeBenchmarkRunner();
            List<TreeBenchmarkRow> rows = new ArrayList<>();

            TreeBenchmarkData randomData = TreeBenchmarkDataFactory.randomOrder(n, seed);
            TreeBenchmarkData sortedData = TreeBenchmarkDataFactory.sortedOrder(n, seed);

            for (SearchTreeVariant variant : variants) {
                rows.add(runner.run(variant, "random", randomData));
                rows.add(runner.run(variant, "sorted", sortedData));
            }

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            Path resultsDir = Path.of("tests/results");
            Files.createDirectories(resultsDir);

            Path reportFile = resultsDir.resolve("hw10_search_trees_report_" + timestamp + ".txt");

            try (PrintStream out = new PrintStream(Files.newOutputStream(reportFile))) {
                TreeBenchmarkReportPrinter.print(rows, out);
            }

            TreeBenchmarkReportPrinter.print(rows, System.out);
            System.out.println("Report saved to: " + reportFile.toAbsolutePath());

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
