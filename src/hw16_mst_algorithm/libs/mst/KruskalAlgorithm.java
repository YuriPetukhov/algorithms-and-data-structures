package hw16_mst_algorithm.libs.mst;

import hw16_mst_algorithm.libs.graphs.WeightedEdge;
import hw16_mst_algorithm.libs.graphs.WeightedUndirectedGraph;
import hw16_mst_algorithm.libs.unionfind.UnionFind;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalAlgorithm<V> {
    public List<WeightedEdge<V>> findMst(WeightedUndirectedGraph<V> graph) {
        List<WeightedEdge<V>> sortedEdges = graph.edges();
        sortedEdges.sort(Comparator.comparingInt(WeightedEdge::weight));

        UnionFind<V> unionFind = new UnionFind<>(graph.vertices());
        List<WeightedEdge<V>> result = new ArrayList<>();

        for (WeightedEdge<V> edge : sortedEdges) {
            if (unionFind.union(edge.v1(), edge.v2())) {
                result.add(edge);

                if (result.size() == graph.vertexCount() - 1) {
                    break;
                }
            }
        }

        if (result.size() != graph.vertexCount() - 1) {
            throw new IllegalStateException("Graph is disconnected. MST does not exist.");
        }

        return result;
    }
}