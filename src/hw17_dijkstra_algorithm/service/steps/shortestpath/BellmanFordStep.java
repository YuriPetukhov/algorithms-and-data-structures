package hw17_dijkstra_algorithm.service.steps.shortestpath;

import hw17_dijkstra_algorithm.libs.shortestpath.BellmanFordAlgorithm;
import hw17_dijkstra_algorithm.service.ShortestPathContext;
import hw17_dijkstra_algorithm.service.steps.Step;

public class BellmanFordStep<R> implements Step<ShortestPathContext<R>> {
    private final BellmanFordAlgorithm<Integer> algorithm = new BellmanFordAlgorithm<>();

    @Override
    public void execute(ShortestPathContext<R> context) {
        if (context.graph() == null) {
            throw new IllegalStateException("Graph must be built before running Bellman-Ford algorithm");
        }

        context.setPath(
                algorithm.findShortestPath(
                        context.graph(),
                        context.start(),
                        context.target()
                )
        );
    }
}