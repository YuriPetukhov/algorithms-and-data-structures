package hw15_demukron_algorithm.demo;

import hw15_demukron_algorithm.service.DirectedGraphContext;
import hw15_demukron_algorithm.service.AlgorithmService;
import hw15_demukron_algorithm.service.Handler;
import hw15_demukron_algorithm.service.steps.directed.BuildGraphFromIntAdjacencyVectorStep;
import hw15_demukron_algorithm.service.steps.tarjan.TarjanStep;
import hw15_demukron_algorithm.service.steps.directed.ValidateIntAdjacencyVectorStep;
import hw15_demukron_algorithm.visualization.GraphAlgorithmConsoleVisualizer;

import java.util.List;

public class TarjanDemo {
    public static void main(String[] args) {
        int[][] adjacencyVector = {
                {1},
                {2},
                {0, 3},
                {4},
                {5},
                {3},
                {5, 7},
                {6}
        };

        AlgorithmService<DirectedGraphContext<List<List<Integer>>>, List<List<Integer>>> service =
                new AlgorithmService<>(
                        new Handler<>(
                                List.of(
                                        new ValidateIntAdjacencyVectorStep<>(),
                                        new BuildGraphFromIntAdjacencyVectorStep<>(),
                                        new TarjanStep()
                                )
                        )
                );

        List<List<Integer>> components = service.execute(
                new DirectedGraphContext<>(adjacencyVector)
        );

        GraphAlgorithmConsoleVisualizer.title("Adjacency vector");
        GraphAlgorithmConsoleVisualizer.adjacencyVector(adjacencyVector);

        GraphAlgorithmConsoleVisualizer.emptyLine();

        GraphAlgorithmConsoleVisualizer.title("Tarjan strongly connected components");
        GraphAlgorithmConsoleVisualizer.components(components);
    }
}