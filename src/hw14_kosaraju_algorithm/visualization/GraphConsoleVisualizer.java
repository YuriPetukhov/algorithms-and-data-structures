package hw14_kosaraju_algorithm.visualization;

import hw14_kosaraju_algorithm.libs.graphs.Edge;

import java.util.List;
import java.util.Map;

public final class GraphConsoleVisualizer {
    private GraphConsoleVisualizer() {
    }

    public static void title(String title) {
        System.out.println(title);
        System.out.println("-".repeat(title.length()));
    }

    public static void edges(List<Edge<Integer>> edges) {
        for (Edge<Integer> edge : edges) {
            System.out.println(edge.from() + " -> " + edge.to());
        }
    }

    public static void adjacency(Map<Integer, List<Integer>> adjacency) {
        for (Map.Entry<Integer, List<Integer>> entry : adjacency.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void components(List<List<Integer>> components) {
        for (int i = 0; i < components.size(); i++) {
            System.out.println("Component " + (i + 1) + ": " + components.get(i));
        }
    }

    public static void adjacencyMatrix(boolean[][] matrix) {
        System.out.print("    ");

        for (int col = 0; col < matrix.length; col++) {
            System.out.print(col + " ");
        }

        System.out.println();

        for (int row = 0; row < matrix.length; row++) {
            System.out.print(row + " | ");

            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print((matrix[row][col] ? 1 : 0) + " ");
            }

            System.out.println();
        }
    }

    public static void incidenceMatrix(int[][] matrix) {
        if (matrix.length == 0) {
            System.out.println("[]");
            return;
        }

        int edgeCount = matrix[0].length;

        System.out.print("    ");

        for (int edge = 0; edge < edgeCount; edge++) {
            System.out.print("e" + edge + " ");
        }

        System.out.println();

        for (int vertex = 0; vertex < matrix.length; vertex++) {
            System.out.print(vertex + " | ");

            for (int edge = 0; edge < edgeCount; edge++) {
                int value = matrix[vertex][edge];
                System.out.print((value >= 0 ? " " + value : value) + " ");
            }

            System.out.println();
        }
    }

    public static void array(String name, int[] array) {
        System.out.print(name + ": [");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);

            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }

    public static void list(String name, List<Integer> list) {
        System.out.println(name + ": " + list);
    }

    public static void emptyLine() {
        System.out.println();
    }
}