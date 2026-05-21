package hw15_demukron_algorithm.service.steps.undirected;

import hw15_demukron_algorithm.libs.graphs.AdjacencyVectorUndirectedGraph;
import hw15_demukron_algorithm.libs.graphs.UndirectedGraph;
import hw15_demukron_algorithm.service.UndirectedGraphContext;
import hw15_demukron_algorithm.service.steps.Step;

public class BuildUndirectedGraphStep<R> implements Step<UndirectedGraphContext<R>> {
    @Override
    public void execute(UndirectedGraphContext<R> context) {
        UndirectedGraph<Integer> graph = new AdjacencyVectorUndirectedGraph<>();

        for (int vertex = 0; vertex < context.vertexCount(); vertex++) {
            graph.addVertex(vertex);
        }

        for (int[] edge : context.edges()) {
            graph.addEdge(edge[0], edge[1]);
        }

        context.setGraph(graph);
    }
}