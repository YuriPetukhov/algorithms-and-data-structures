package hw14_kosaraju_algorithm.demo;

import hw14_kosaraju_algorithm.libs.graphs.DirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.Edge;
import hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations.AdjacencyArrayGraph;
import hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations.AdjacencyListGraph;
import hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations.AdjacencyMatrixGraph;
import hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations.AdjacencyVectorGraph;
import hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations.EdgeListGraph;
import hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations.HeaderGraph;
import hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations.IncidenceMatrixGraph;
import hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations.VertexEdgeListGraph;
import hw14_kosaraju_algorithm.visualization.GraphConsoleVisualizer;

import java.util.List;

public class GraphRepresentationsDemo {
    private static final int VERTEX_COUNT = 7;

    public static void main(String[] args) {
        List<Integer> leftPart = List.of(0, 1, 2);
        List<Integer> rightPart = List.of(3, 4, 5, 6);

        List<Edge<Integer>> edges = List.of(
                new Edge<>(0, 3),
                new Edge<>(0, 4),
                new Edge<>(1, 4),
                new Edge<>(1, 5),
                new Edge<>(2, 6)
        );

        GraphConsoleVisualizer.title("Sets");
        GraphConsoleVisualizer.list("Left part", leftPart);
        GraphConsoleVisualizer.list("Right part", rightPart);

        GraphConsoleVisualizer.emptyLine();

        printAdjacencyMatrix(edges);
        GraphConsoleVisualizer.emptyLine();

        printIncidenceMatrix(edges);
        GraphConsoleVisualizer.emptyLine();

        printEdgeList(edges);
        GraphConsoleVisualizer.emptyLine();

        printAdjacencyVector(edges);
        GraphConsoleVisualizer.emptyLine();

        printAdjacencyArray(edges);
        GraphConsoleVisualizer.emptyLine();

        printAdjacencyList(edges);
        GraphConsoleVisualizer.emptyLine();

        printHeaderGraph(edges);
        GraphConsoleVisualizer.emptyLine();

        printVertexEdgeList(edges);
    }

    private static void printAdjacencyMatrix(List<Edge<Integer>> edges) {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(VERTEX_COUNT);
        addEdges(graph, edges);

        GraphConsoleVisualizer.title("Adjacency matrix");
        GraphConsoleVisualizer.adjacencyMatrix(graph.matrix());
    }

    private static void printIncidenceMatrix(List<Edge<Integer>> edges) {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph(VERTEX_COUNT);
        addEdges(graph, edges);

        GraphConsoleVisualizer.title("Incidence matrix");
        GraphConsoleVisualizer.incidenceMatrix(graph.matrix());
    }

    private static void printEdgeList(List<Edge<Integer>> edges) {
        EdgeListGraph graph = new EdgeListGraph(VERTEX_COUNT);
        addEdges(graph, edges);

        GraphConsoleVisualizer.title("Edge list");
        GraphConsoleVisualizer.edges(graph.edges());
    }

    private static void printAdjacencyVector(List<Edge<Integer>> edges) {
        AdjacencyVectorGraph graph = new AdjacencyVectorGraph(VERTEX_COUNT);
        addEdges(graph, edges);

        GraphConsoleVisualizer.title("Adjacency vectors");
        GraphConsoleVisualizer.adjacency(adjacencyOf(graph));
    }

    private static void printAdjacencyArray(List<Edge<Integer>> edges) {
        AdjacencyArrayGraph graph = new AdjacencyArrayGraph(VERTEX_COUNT);
        addEdges(graph, edges);
        graph.build();

        GraphConsoleVisualizer.title("Adjacency arrays");
        GraphConsoleVisualizer.array("offsets", graph.offsets());
        GraphConsoleVisualizer.array("adjacent", graph.adjacentArray());
    }

    private static void printAdjacencyList(List<Edge<Integer>> edges) {
        AdjacencyListGraph graph = new AdjacencyListGraph(VERTEX_COUNT);
        addEdges(graph, edges);

        GraphConsoleVisualizer.title("Adjacency lists");
        GraphConsoleVisualizer.adjacency(adjacencyOf(graph));
    }

    private static void printHeaderGraph(List<Edge<Integer>> edges) {
        HeaderGraph graph = new HeaderGraph(VERTEX_COUNT);
        addEdges(graph, edges);

        GraphConsoleVisualizer.title("Header graph");
        GraphConsoleVisualizer.array("head", graph.head());
        GraphConsoleVisualizer.list("to", graph.to());
        GraphConsoleVisualizer.list("next", graph.next());
    }

    private static void printVertexEdgeList(List<Edge<Integer>> edges) {
        VertexEdgeListGraph graph = new VertexEdgeListGraph(VERTEX_COUNT);
        addEdges(graph, edges);

        GraphConsoleVisualizer.title("Vertex and edge list");
        GraphConsoleVisualizer.list("vertices", graph.vertices());
        GraphConsoleVisualizer.edges(graph.edges());
    }

    private static void addEdges(
            DirectedGraph graph,
            List<Edge<Integer>> edges
    ) {
        for (Edge<Integer> edge : edges) {
            graph.addEdge(edge.from(), edge.to());
        }
    }

    private static java.util.Map<Integer, List<Integer>> adjacencyOf(DirectedGraph graph) {
        java.util.Map<Integer, List<Integer>> adjacency = new java.util.LinkedHashMap<>();

        for (int vertex = 0; vertex < graph.vertexCount(); vertex++) {
            adjacency.put(vertex, graph.adjacent(vertex));
        }

        return adjacency;
    }
}