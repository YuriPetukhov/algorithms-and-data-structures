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
import hw14_kosaraju_algorithm.service.steps.Step;
import hw14_kosaraju_algorithm.visualization.GraphConsoleVisualizer;

import java.util.List;
import java.util.Map;

public class BipartiteGraphDemo {
    public static void main(String[] args) {
        DirectedGraph graph = new AdjacencyListGraph(7);

        List<Integer> leftPart = List.of(0, 1, 2);
        List<Integer> rightPart = List.of(3, 4, 5, 6);

        List<Edge<Integer>> edges = List.of(
                new Edge<>(0, 3),
                new Edge<>(0, 4),
                new Edge<>(1, 4),
                new Edge<>(1, 5),
                new Edge<>(2, 6)
        );

        execute(
                graph,
                List.of(new AddEdgesStep(edges))
        );

        @SuppressWarnings("unchecked")
        List<Edge<Integer>> edgeList = (List<Edge<Integer>>) execute(
                graph,
                List.of(new GetEdgesStep())
        );

        @SuppressWarnings("unchecked")
        Map<Integer, List<Integer>> adjacency = (Map<Integer, List<Integer>>) execute(
                graph,
                List.of(new GetAllAdjacencyStep())
        );

        GraphConsoleVisualizer.title("Bipartite graph A(3,4)");
        GraphConsoleVisualizer.list("Left part", leftPart);
        GraphConsoleVisualizer.list("Right part", rightPart);

        GraphConsoleVisualizer.emptyLine();

        GraphConsoleVisualizer.title("Edges");
        GraphConsoleVisualizer.edges(edgeList);

        GraphConsoleVisualizer.emptyLine();

        GraphConsoleVisualizer.title("Adjacency");
        GraphConsoleVisualizer.adjacency(adjacency);
    }

    private static Object execute(
            DirectedGraph graph,
            List<Step<GraphContext>> steps
    ) {
        GraphService service = new GraphService(
                new Handler<>(steps)
        );

        return service.execute(new GraphContext(graph));
    }
}