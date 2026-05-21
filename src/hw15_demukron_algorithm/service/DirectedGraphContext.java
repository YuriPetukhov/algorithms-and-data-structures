package hw15_demukron_algorithm.service;

import hw15_demukron_algorithm.libs.graphs.DirectedGraph;

import java.util.List;

public class DirectedGraphContext<R> implements AlgorithmContext<R> {
    private final int[][] adjacencyVector;
    private DirectedGraph<Integer> graph;
    private List<List<Integer>> levels;
    private R result;

    public DirectedGraphContext(int[][] adjacencyVector) {
        this.adjacencyVector = adjacencyVector;
    }

    public int[][] adjacencyVector() {
        return adjacencyVector;
    }

    public DirectedGraph<Integer> graph() {
        return graph;
    }

    public void setGraph(DirectedGraph<Integer> graph) {
        this.graph = graph;
    }

    public List<List<Integer>> levels() {
        return levels;
    }

    public void setLevels(List<List<Integer>> levels) {
        this.levels = levels;
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