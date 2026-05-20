package hw14_kosaraju_algorithm.demo;

import hw14_kosaraju_algorithm.libs.graphs.DirectedGraph;
import hw14_kosaraju_algorithm.libs.graphs.Edge;
import hw14_kosaraju_algorithm.libs.graphs.kosaraju.representations.AdjacencyListGraph;
import hw14_kosaraju_algorithm.service.GraphContext;
import hw14_kosaraju_algorithm.service.GraphService;
import hw14_kosaraju_algorithm.service.Handler;
import hw14_kosaraju_algorithm.service.steps.AddEdgesStep;
import hw14_kosaraju_algorithm.service.steps.GetAllAdjacencyStep;
import hw14_kosaraju_algorithm.service.steps.GetEdgesStep;
import hw14_kosaraju_algorithm.service.steps.KosarajuStep;
import hw14_kosaraju_algorithm.visualization.GraphConsoleVisualizer;

import java.util.List;
import java.util.Map;

public class KosarajuDemo {
    public static void main(String[] args) {
        DirectedGraph graph = new AdjacencyListGraph(8);

        List<Edge<Integer>> edges = List.of(
                new Edge<>(0, 1),
                new Edge<>(1, 2),
                new Edge<>(2, 0),

                new Edge<>(3, 4),
                new Edge<>(4, 5),
                new Edge<>(5, 3),

                new Edge<>(2, 3),
                new Edge<>(6, 5),
                new Edge<>(6, 7),
                new Edge<>(7, 6)
        );

        execute(
                graph,
                List.of(
                        new AddEdgesStep(edges)
                )
        );

        @SuppressWarnings("unchecked")
        List<Edge<Integer>> edgeList = (List<Edge<Integer>>) execute(
                graph,
                List.of(
                        new GetEdgesStep()
                )
        );

        @SuppressWarnings("unchecked")
        Map<Integer, List<Integer>> adjacency = (Map<Integer, List<Integer>>) execute(
                graph,
                List.of(
                        new GetAllAdjacencyStep()
                )
        );

        @SuppressWarnings("unchecked")
        List<List<Integer>> components = (List<List<Integer>>) execute(
                graph,
                List.of(
                        new KosarajuStep()
                )
        );

        GraphConsoleVisualizer.title("Edges");
        GraphConsoleVisualizer.edges(edgeList);

        GraphConsoleVisualizer.emptyLine();

        GraphConsoleVisualizer.title("Adjacency");
        GraphConsoleVisualizer.adjacency(adjacency);

        GraphConsoleVisualizer.emptyLine();

        GraphConsoleVisualizer.title("Strongly connected components");
        GraphConsoleVisualizer.components(components);
    }

    private static Object execute(
            DirectedGraph graph,
            List<hw14_kosaraju_algorithm.service.steps.Step<GraphContext>> steps
    ) {
        GraphService service = new GraphService(
                new Handler<>(steps)
        );

        return service.execute(new GraphContext(graph));
    }
}