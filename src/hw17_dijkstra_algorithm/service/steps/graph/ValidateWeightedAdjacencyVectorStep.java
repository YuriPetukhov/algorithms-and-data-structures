package hw17_dijkstra_algorithm.service.steps.graph;

import hw17_dijkstra_algorithm.service.ShortestPathContext;
import hw17_dijkstra_algorithm.service.steps.Step;

public class ValidateWeightedAdjacencyVectorStep<R> implements Step<ShortestPathContext<R>> {
    @Override
    public void execute(ShortestPathContext<R> context) {
        int[][] adjacencyVector = context.adjacencyVector();
        int[][] weightVector = context.weightVector();

        if (adjacencyVector == null) {
            throw new IllegalArgumentException("Adjacency vector must not be null");
        }

        if (weightVector == null) {
            throw new IllegalArgumentException("Weight vector must not be null");
        }

        if (adjacencyVector.length != weightVector.length) {
            throw new IllegalArgumentException("Adjacency and weight vectors must have same row count");
        }

        int vertexCount = adjacencyVector.length;

        for (int from = 0; from < vertexCount; from++) {
            if (adjacencyVector[from] == null) {
                throw new IllegalArgumentException("Adjacency row must not be null: " + from);
            }

            if (weightVector[from] == null) {
                throw new IllegalArgumentException("Weight row must not be null: " + from);
            }

            if (adjacencyVector[from].length != weightVector[from].length) {
                throw new IllegalArgumentException("Adjacency and weight rows must have same length: " + from);
            }

            for (int i = 0; i < adjacencyVector[from].length; i++) {
                int to = adjacencyVector[from][i];
                int weight = weightVector[from][i];

                if (to == -1) {
                    continue;
                }

                if (to < 0 || to >= vertexCount) {
                    throw new IllegalArgumentException(
                            "Invalid adjacent vertex " + to + " in row " + from
                    );
                }

                if (weight < 0) {
                    throw new IllegalArgumentException(
                            "Dijkstra algorithm does not support negative weights: " + weight
                    );
                }
            }
        }
    }
}