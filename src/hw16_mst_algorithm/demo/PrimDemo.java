package hw16_mst_algorithm.demo;

import hw16_mst_algorithm.libs.graphs.Edge;
import hw16_mst_algorithm.service.AlgorithmService;
import hw16_mst_algorithm.service.Handler;
import hw16_mst_algorithm.service.WeightedGraphContext;
import hw16_mst_algorithm.service.steps.graph.BuildWeightedGraphStep;
import hw16_mst_algorithm.service.steps.graph.ValidateWeightedAdjacencyVectorStep;
import hw16_mst_algorithm.service.steps.mst.GetMstEdgesStep;
import hw16_mst_algorithm.service.steps.mst.PrimStep;
import hw16_mst_algorithm.visualization.MstConsoleVisualizer;

import java.util.List;

public class PrimDemo {
    public static void main(String[] args) {
        int[][] adjacencyVector = {
                {1, 2, 3},
                {0, 2, 4},
                {0, 1, 3, 4},
                {0, 2, 4},
                {1, 2, 3}
        };

        int[][] weightVector = {
                {2, 3, 6},
                {2, 1, 5},
                {3, 1, 4, 7},
                {6, 4, 2},
                {5, 7, 2}
        };

        AlgorithmService<WeightedGraphContext<Edge[]>, Edge[]> service =
                new AlgorithmService<>(
                        new Handler<>(
                                List.of(
                                        new ValidateWeightedAdjacencyVectorStep<>(),
                                        new BuildWeightedGraphStep<>(),
                                        new PrimStep<>(),
                                        new GetMstEdgesStep()
                                )
                        )
                );

        Edge[] mst = service.execute(
                new WeightedGraphContext<>(adjacencyVector, weightVector)
        );

        MstConsoleVisualizer.title("Weighted adjacency vector");
        MstConsoleVisualizer.adjacencyVector(adjacencyVector, weightVector);

        MstConsoleVisualizer.emptyLine();

        MstConsoleVisualizer.title("Prim MST edges");
        MstConsoleVisualizer.mstEdges(mst);
    }
}