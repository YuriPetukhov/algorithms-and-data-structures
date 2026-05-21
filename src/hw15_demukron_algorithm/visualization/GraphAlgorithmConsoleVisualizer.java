package hw15_demukron_algorithm.visualization;

import hw15_demukron_algorithm.libs.graphs.Edge;

import java.util.List;
import java.util.Set;

public final class GraphAlgorithmConsoleVisualizer {
    private GraphAlgorithmConsoleVisualizer() {
    }

    public static void title(String title) {
        System.out.println(title);
        System.out.println("-".repeat(title.length()));
    }

    public static void adjacencyVector(int[][] adjacencyVector) {
        for (int vertex = 0; vertex < adjacencyVector.length; vertex++) {
            System.out.print(vertex + " -> ");
            printArray(adjacencyVector[vertex]);
        }
    }

    public static void levels(int[][] levels) {
        for (int level = 0; level < levels.length; level++) {
            System.out.print("Level " + level + ": ");
            printArray(levels[level]);
        }
    }

    public static void components(List<List<Integer>> components) {
        for (int i = 0; i < components.size(); i++) {
            System.out.println("Component " + (i + 1) + ": " + components.get(i));
        }
    }

    public static void edges(List<Edge<Integer>> edges) {
        for (Edge<Integer> edge : edges) {
            System.out.println(edge.from() + " - " + edge.to());
        }
    }

    public static void vertices(String title, Set<Integer> vertices) {
        System.out.println(title + ": " + vertices);
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

    public static void rawEdges(int[][] edges) {
        for (int[] edge : edges) {
            System.out.println(edge[0] + " - " + edge[1]);
        }
    }
}