package hw15_demukron_algorithm.demo;

import hw15_demukron_algorithm.service.DirectedGraphContext;
import hw15_demukron_algorithm.service.AlgorithmService;
import hw15_demukron_algorithm.service.Handler;
import hw15_demukron_algorithm.service.steps.directed.BuildGraphFromIntAdjacencyVectorStep;
import hw15_demukron_algorithm.service.steps.demukron.DemukronStep;
import hw15_demukron_algorithm.service.steps.demukron.GetLevelsStep;
import hw15_demukron_algorithm.service.steps.directed.ValidateIntAdjacencyVectorStep;
import hw15_demukron_algorithm.visualization.GraphAlgorithmConsoleVisualizer;

import java.util.List;

public class DemukronDemo {
    public static void main(String[] args) {
        int[][] adjacencyVector = {
                {1, 2},
                {3, 4},
                {4},
                {5},
                {5},
                {}
        };

        AlgorithmService<DirectedGraphContext<int[][]>, int[][]> service =
                new AlgorithmService<>(
                        new Handler<>(
                                List.of(
                                        new ValidateIntAdjacencyVectorStep<>(),
                                        new BuildGraphFromIntAdjacencyVectorStep<>(),
                                        new DemukronStep(),
                                        new GetLevelsStep()
                                )
                        )
                );

        int[][] levels = service.execute(
                new DirectedGraphContext<>(adjacencyVector)
        );

        GraphAlgorithmConsoleVisualizer.title("Adjacency vector");
        GraphAlgorithmConsoleVisualizer.adjacencyVector(adjacencyVector);

        GraphAlgorithmConsoleVisualizer.emptyLine();

        GraphAlgorithmConsoleVisualizer.title("Demukron levels");
        GraphAlgorithmConsoleVisualizer.levels(levels);
    }
}