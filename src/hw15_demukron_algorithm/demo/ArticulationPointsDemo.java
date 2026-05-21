package hw15_demukron_algorithm.demo;

import hw15_demukron_algorithm.service.AlgorithmService;
import hw15_demukron_algorithm.service.Handler;
import hw15_demukron_algorithm.service.UndirectedGraphContext;
import hw15_demukron_algorithm.service.steps.undirected.ArticulationPointFinderStep;
import hw15_demukron_algorithm.service.steps.undirected.BuildUndirectedGraphStep;
import hw15_demukron_algorithm.service.steps.undirected.ValidateUndirectedEdgesStep;
import hw15_demukron_algorithm.visualization.GraphAlgorithmConsoleVisualizer;

import java.util.List;
import java.util.Set;

public class ArticulationPointsDemo {
    public static void main(String[] args) {
        int vertexCount = 6;

        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 0},
                {1, 3},
                {3, 4},
                {4, 5},
                {5, 3}
        };

        AlgorithmService<UndirectedGraphContext<Set<Integer>>, Set<Integer>> service =
                new AlgorithmService<>(
                        new Handler<>(
                                List.of(
                                        new ValidateUndirectedEdgesStep<>(),
                                        new BuildUndirectedGraphStep<>(),
                                        new ArticulationPointFinderStep()
                                )
                        )
                );

        Set<Integer> points = service.execute(
                new UndirectedGraphContext<>(vertexCount, edges)
        );

        GraphAlgorithmConsoleVisualizer.title("Undirected graph edges");
        GraphAlgorithmConsoleVisualizer.rawEdges(edges);

        GraphAlgorithmConsoleVisualizer.emptyLine();

        GraphAlgorithmConsoleVisualizer.title("Articulation points");
        GraphAlgorithmConsoleVisualizer.vertices("Points", points);
    }
}