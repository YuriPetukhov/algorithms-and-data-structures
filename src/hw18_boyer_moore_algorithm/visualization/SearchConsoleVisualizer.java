package hw18_boyer_moore_algorithm.visualization;

import hw18_boyer_moore_algorithm.libs.searching.SearchResult;

public final class SearchConsoleVisualizer {
    private SearchConsoleVisualizer() {
    }

    public static void title(String title) {
        System.out.println(title);
        System.out.println("-".repeat(title.length()));
    }

    public static void input(String text, String pattern, String algorithmName) {
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Algorithm: " + algorithmName);
    }

    public static void result(SearchResult result) {
        System.out.println("Index: " + result.index());
        System.out.println("Comparisons: " + result.comparisons());
    }

    public static void emptyLine() {
        System.out.println();
    }
}