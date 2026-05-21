package hw15_demukron_algorithm.service.steps.directed;

import hw15_demukron_algorithm.service.DirectedGraphContext;
import hw15_demukron_algorithm.service.steps.Step;

public class ValidateIntAdjacencyVectorStep<R> implements Step<DirectedGraphContext<R>> {
    @Override
    public void execute(DirectedGraphContext<R> context) {
        int[][] adjacencyVector = context.adjacencyVector();

        if (adjacencyVector == null) {
            throw new IllegalArgumentException("Adjacency vector must not be null");
        }

        int vertexCount = adjacencyVector.length;

        for (int from = 0; from < vertexCount; from++) {
            if (adjacencyVector[from] == null) {
                throw new IllegalArgumentException("Adjacency row must not be null: " + from);
            }

            for (int to : adjacencyVector[from]) {
                if (to == -1) {
                    continue;
                }

                if (to < 0 || to >= vertexCount) {
                    throw new IllegalArgumentException(
                            "Invalid adjacent vertex " + to + " in row " + from
                    );
                }
            }
        }
    }
}