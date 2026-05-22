package hw17_dijkstra_algorithm.service;

import hw17_dijkstra_algorithm.libs.graphs.WeightedDirectedGraph;
import hw17_dijkstra_algorithm.libs.graphs.WeightedEdge;

import java.util.List;

public class ShortestPathContext<R> implements AlgorithmContext<R> {
    private final int[][] adjacencyVector;
    private final int[][] weightVector;
    private final int start;
    private final int target;

    private WeightedDirectedGraph<Integer> graph;
    private List<WeightedEdge<Integer>> path;
    private R result;

    public ShortestPathContext(
            int[][] adjacencyVector,
            int[][] weightVector,
            int start,
            int target
    ) {
        this.adjacencyVector = adjacencyVector;
        this.weightVector = weightVector;
        this.start = start;
        this.target = target;
    }

    public int[][] adjacencyVector() {
        return adjacencyVector;
    }

    public int[][] weightVector() {
        return weightVector;
    }

    public int start() {
        return start;
    }

    public int target() {
        return target;
    }

    public WeightedDirectedGraph<Integer> graph() {
        return graph;
    }

    public void setGraph(WeightedDirectedGraph<Integer> graph) {
        this.graph = graph;
    }

    public List<WeightedEdge<Integer>> path() {
        return path;
    }

    public void setPath(List<WeightedEdge<Integer>> path) {
        this.path = path;
    }

    @Override
    public R result() {
        return result;
    }

    @Override
    public void setResult(R result) {
        this.result = result;
    }
}