package hw16_mst_algorithm.visualization;

import hw16_mst_algorithm.libs.graphs.Edge;

public final class MstConsoleVisualizer {
    private MstConsoleVisualizer() {
    }

    public static void title(String title) {
        System.out.println(title);
        System.out.println("-".repeat(title.length()));
    }

    public static void adjacencyVector(int[][] adjacencyVector, int[][] weightVector) {
        for (int vertex = 0; vertex < adjacencyVector.length; vertex++) {
            System.out.print(vertex + " -> ");

            boolean first = true;
            System.out.print("[");

            for (int i = 0; i < adjacencyVector[vertex].length; i++) {
                int to = adjacencyVector[vertex][i];

                if (to == -1) {
                    continue;
                }

                if (!first) {
                    System.out.print(", ");
                }

                System.out.print(to + "(w=" + weightVector[vertex][i] + ")");
                first = false;
            }

            System.out.println("]");
        }
    }

    public static void mstEdges(Edge[] edges) {
        for (Edge edge : edges) {
            System.out.println(edge.v1() + " - " + edge.v2());
        }
    }

    public static void emptyLine() {
        System.out.println();
    }
}