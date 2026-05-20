package hw14_kosaraju_algorithm.service.steps;

import hw14_kosaraju_algorithm.service.GraphContext;

import java.util.LinkedHashMap;
import java.util.Map;

public class GetAllAdjacencyStep implements Step<GraphContext> {
    @Override
    public void execute(GraphContext context) {
        Map<Integer, Object> result = new LinkedHashMap<>();

        for (int vertex = 0; vertex < context.graph().vertexCount(); vertex++) {
            result.put(vertex, context.graph().adjacent(vertex));
        }

        context.setResult(result);
    }
}