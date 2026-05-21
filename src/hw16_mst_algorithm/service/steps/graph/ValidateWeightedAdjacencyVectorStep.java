package hw16_mst_algorithm.service.steps.graph;

import hw16_mst_algorithm.service.WeightedGraphContext;
import hw16_mst_algorithm.service.steps.Step;

public class ValidateWeightedAdjacencyVectorStep<R> implements Step<WeightedGraphContext<R>> {
    @Override
    public void execute(WeightedGraphContext<R> context) {
        int[][] adjacencyVector = context.adjacencyVector();
        int[][] weightVector = context.weightVector();

        validateBasicStructure(adjacencyVector, weightVector);
        validateEdges(adjacencyVector, weightVector);
        validateUndirectedSymmetry(adjacencyVector, weightVector);
    }

    private void validateBasicStructure(int[][] adjacencyVector, int[][] weightVector) {
        if (adjacencyVector == null) {
            throw new IllegalArgumentException("Adjacency vector must not be null");
        }

        if (weightVector == null) {
            throw new IllegalArgumentException("Weight vector must not be null");
        }

        if (adjacencyVector.length != weightVector.length) {
            throw new IllegalArgumentException("Adjacency and weight vectors must have same row count");
        }

        for (int row = 0; row < adjacencyVector.length; row++) {
            if (adjacencyVector[row] == null) {
                throw new IllegalArgumentException("Adjacency row must not be null: " + row);
            }

            if (weightVector[row] == null) {
                throw new IllegalArgumentException("Weight row must not be null: " + row);
            }

            if (adjacencyVector[row].length != weightVector[row].length) {
                throw new IllegalArgumentException("Adjacency and weight rows must have same length: " + row);
            }
        }
    }

    private void validateEdges(int[][] adjacencyVector, int[][] weightVector) {
        int vertexCount = adjacencyVector.length;

        for (int from = 0; from < vertexCount; from++) {
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

                if (from == to) {
                    throw new IllegalArgumentException("Self-loop is not allowed: " + from);
                }

                if (weight < 0) {
                    throw new IllegalArgumentException(
                            "Edge weight must not be negative: " + weight
                    );
                }
            }
        }
    }

    private void validateUndirectedSymmetry(int[][] adjacencyVector, int[][] weightVector) {
        int vertexCount = adjacencyVector.length;

        for (int from = 0; from < vertexCount; from++) {
            for (int i = 0; i < adjacencyVector[from].length; i++) {
                int to = adjacencyVector[from][i];

                if (to == -1) {
                    continue;
                }

                int weight = weightVector[from][i];
                int reverseWeight = findWeight(adjacencyVector, weightVector, to, from);

                if (reverseWeight == -1) {
                    throw new IllegalArgumentException(
                            "Undirected edge is not symmetric: " + from + " - " + to
                    );
                }

                if (reverseWeight != weight) {
                    throw new IllegalArgumentException(
                            "Different weights for edge " + from + " - " + to
                                    + ": " + weight + " and " + reverseWeight
                    );
                }
            }
        }
    }

    private int findWeight(
            int[][] adjacencyVector,
            int[][] weightVector,
            int from,
            int target
    ) {
        for (int i = 0; i < adjacencyVector[from].length; i++) {
            if (adjacencyVector[from][i] == target) {
                return weightVector[from][i];
            }
        }

        return -1;
    }
}