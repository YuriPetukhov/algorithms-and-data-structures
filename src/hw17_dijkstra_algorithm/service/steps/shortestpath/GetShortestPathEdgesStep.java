package hw17_dijkstra_algorithm.service.steps.shortestpath;

import hw17_dijkstra_algorithm.libs.graphs.Edge;
import hw17_dijkstra_algorithm.libs.shortestpath.ShortestPathEdgeConverter;
import hw17_dijkstra_algorithm.service.ShortestPathContext;
import hw17_dijkstra_algorithm.service.steps.Step;

public class GetShortestPathEdgesStep implements Step<ShortestPathContext<Edge[]>> {
    @Override
    public void execute(ShortestPathContext<Edge[]> context) {
        if (context.path() == null) {
            throw new IllegalStateException("Path must be calculated before converting result");
        }

        context.setResult(
                ShortestPathEdgeConverter.toEdgeArray(context.path())
        );
    }
}