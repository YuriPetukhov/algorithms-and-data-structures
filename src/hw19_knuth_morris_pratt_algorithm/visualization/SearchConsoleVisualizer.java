package hw19_knuth_morris_pratt_algorithm.visualization;

import hw19_knuth_morris_pratt_algorithm.libs.searching.SearchResult;

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

    public static void prefixFunction(String pattern, int[] prefix) {
        System.out.println("Pattern: " + pattern);
        System.out.print("Prefix:  ");

        printArray(prefix);
    }

    public static void automatonTransitions(int[][] transitions) {
        for (int state = 0; state < transitions.length; state++) {
            System.out.print("State " + state + ": ");
            printArray(transitions[state]);
        }
    }

    public static void emptyLine() {
        System.out.println();
    }

    private static void printArray(int[] array) {
        System.out.print("[");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);

            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}