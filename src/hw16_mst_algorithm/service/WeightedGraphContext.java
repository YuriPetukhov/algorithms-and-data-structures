package hw16_mst_algorithm.service;

import hw16_mst_algorithm.libs.graphs.WeightedEdge;
import hw16_mst_algorithm.libs.graphs.WeightedUndirectedGraph;

import java.util.List;

public class WeightedGraphContext<R> implements AlgorithmContext<R> {
    private final int[][] adjacencyVector;
    private final int[][] weightVector;

    private WeightedUndirectedGraph<Integer> graph;
    private List<WeightedEdge<Integer>> mst;
    private R result;

    public WeightedGraphContext(int[][] adjacencyVector, int[][] weightVector) {
        this.adjacencyVector = adjacencyVector;
        this.weightVector = weightVector;
    }

    public int[][] adjacencyVector() {
        return adjacencyVector;
    }

    public int[][] weightVector() {
        return weightVector;
    }

    public WeightedUndirectedGraph<Integer> graph() {
        return graph;
    }

    public void setGraph(WeightedUndirectedGraph<Integer> graph) {
        this.graph = graph;
    }

    public List<WeightedEdge<Integer>> mst() {
        return mst;
    }

    public void setMst(List<WeightedEdge<Integer>> mst) {
        this.mst = mst;
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