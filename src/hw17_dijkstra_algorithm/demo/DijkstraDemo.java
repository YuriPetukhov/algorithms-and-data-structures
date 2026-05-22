package hw17_dijkstra_algorithm.demo;

import hw17_dijkstra_algorithm.libs.graphs.Edge;
import hw17_dijkstra_algorithm.service.AlgorithmService;
import hw17_dijkstra_algorithm.service.Handler;
import hw17_dijkstra_algorithm.service.ShortestPathContext;
import hw17_dijkstra_algorithm.service.steps.graph.BuildWeightedDirectedGraphStep;
import hw17_dijkstra_algorithm.service.steps.graph.ValidatePathExistsStep;
import hw17_dijkstra_algorithm.service.steps.graph.ValidateShortestPathVerticesStep;
import hw17_dijkstra_algorithm.service.steps.graph.ValidateWeightedAdjacencyVectorStep;
import hw17_dijkstra_algorithm.service.steps.shortestpath.DijkstraStep;
import hw17_dijkstra_algorithm.service.steps.shortestpath.GetShortestPathEdgesStep;
import hw17_dijkstra_algorithm.visualization.ShortestPathConsoleVisualizer;

import java.util.List;

public class DijkstraDemo {
    public static void main(String[] args) {
        int[][] adjacencyVector = {
                {1, 2},
                {2, 3},
                {3, 4},
                {4},
                {}
        };

        int[][] weightVector = {
                {4, 2},
                {1, 5},
                {8, 10},
                {2},
                {}
        };

        int start = 0;
        int target = 4;

        AlgorithmService<ShortestPathContext<Edge[]>, Edge[]> service =
                new AlgorithmService<>(
                        new Handler<>(
                                List.of(
                                        new ValidateWeightedAdjacencyVectorStep<>(),
                                        new ValidateShortestPathVerticesStep<>(),
                                        new BuildWeightedDirectedGraphStep<>(),
                                        new DijkstraStep<>(),
                                        new ValidatePathExistsStep<>(),
                                        new GetShortestPathEdgesStep()
                                )
                        )
                );

        Edge[] path = service.execute(
                new ShortestPathContext<>(adjacencyVector, weightVector, start, target)
        );

        ShortestPathConsoleVisualizer.title("Weighted adjacency vector");
        ShortestPathConsoleVisualizer.adjacencyVector(adjacencyVector, weightVector);

        ShortestPathConsoleVisualizer.emptyLine();

        ShortestPathConsoleVisualizer.title("Dijkstra shortest path from " + start + " to " + target);
        ShortestPathConsoleVisualizer.pathEdges(path);
    }
}