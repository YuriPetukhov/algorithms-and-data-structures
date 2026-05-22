package hw17_dijkstra_algorithm.libs.shortestpath;

import hw17_dijkstra_algorithm.libs.graphs.Edge;
import hw17_dijkstra_algorithm.libs.graphs.WeightedEdge;

import java.util.List;

public final class ShortestPathEdgeConverter {
    private ShortestPathEdgeConverter() {
    }

    public static Edge[] toEdgeArray(List<WeightedEdge<Integer>> path) {
        Edge[] result = new Edge[path.size()];

        for (int i = 0; i < path.size(); i++) {
            WeightedEdge<Integer> edge = path.get(i);
            result[i] = new Edge(edge.from(), edge.to());
        }

        return result;
    }
}