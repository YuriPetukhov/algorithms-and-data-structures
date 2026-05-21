package hw15_demukron_algorithm.service;

import hw15_demukron_algorithm.libs.graphs.UndirectedGraph;

public class UndirectedGraphContext<R> implements AlgorithmContext<R> {
    private final int vertexCount;
    private final int[][] edges;
    private UndirectedGraph<Integer> graph;
    private R result;

    public UndirectedGraphContext(int vertexCount, int[][] edges) {
        this.vertexCount = vertexCount;
        this.edges = edges;
    }

    public int vertexCount() {
        return vertexCount;
    }

    public int[][] edges() {
        return edges;
    }

    public UndirectedGraph<Integer> graph() {
        return graph;
    }

    public void setGraph(UndirectedGraph<Integer> graph) {
        this.graph = graph;
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