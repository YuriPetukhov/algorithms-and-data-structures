package hw14_kosaraju_algorithm.service;

import hw14_kosaraju_algorithm.libs.graphs.DirectedGraph;

public class GraphContext {
    private final DirectedGraph graph;
    private final Integer vertex;
    private Object result;

    public GraphContext(DirectedGraph graph) {
        this(graph, null);
    }

    public GraphContext(DirectedGraph graph, Integer vertex) {
        this.graph = graph;
        this.vertex = vertex;
    }

    public DirectedGraph graph() {
        return graph;
    }

    public Integer vertex() {
        return vertex;
    }

    public Object result() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}