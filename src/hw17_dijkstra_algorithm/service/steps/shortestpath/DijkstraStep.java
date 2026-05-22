package hw17_dijkstra_algorithm.service.steps.shortestpath;

import hw17_dijkstra_algorithm.libs.shortestpath.DijkstraAlgorithm;
import hw17_dijkstra_algorithm.service.ShortestPathContext;
import hw17_dijkstra_algorithm.service.steps.Step;

public class DijkstraStep<R> implements Step<ShortestPathContext<R>> {
    private final DijkstraAlgorithm<Integer> algorithm = new DijkstraAlgorithm<>();

    @Override
    public void execute(ShortestPathContext<R> context) {
        if (context.graph() == null) {
            throw new IllegalStateException("Graph must be built before running Dijkstra algorithm");
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