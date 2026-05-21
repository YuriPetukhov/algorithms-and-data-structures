package hw16_mst_algorithm.libs.mst;

import hw16_mst_algorithm.libs.graphs.WeightedEdge;
import hw16_mst_algorithm.libs.graphs.WeightedUndirectedGraph;
import hw16_mst_algorithm.libs.unionfind.UnionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoruvkaAlgorithm<V> {
    public List<WeightedEdge<V>> findMst(WeightedUndirectedGraph<V> graph) {
        UnionFind<V> unionFind = new UnionFind<>(graph.vertices());
        List<WeightedEdge<V>> result = new ArrayList<>();

        int components = graph.vertexCount();

        while (components > 1) {
            Map<V, WeightedEdge<V>> cheapest = new HashMap<>();

            for (WeightedEdge<V> edge : graph.edges()) {
                V root1 = unionFind.find(edge.v1());
                V root2 = unionFind.find(edge.v2());

                if (root1.equals(root2)) {
                    continue;
                }

                updateCheapest(cheapest, root1, edge);
                updateCheapest(cheapest, root2, edge);
            }

            if (cheapest.isEmpty()) {
                throw new IllegalStateException("Graph is disconnected. MST does not exist.");
            }

            for (WeightedEdge<V> edge : cheapest.values()) {
                if (unionFind.union(edge.v1(), edge.v2())) {
                    result.add(edge);
                    components--;
                }
            }
        }

        return result;
    }

    private void updateCheapest(
            Map<V, WeightedEdge<V>> cheapest,
            V component,
            WeightedEdge<V> edge
    ) {
        WeightedEdge<V> current = cheapest.get(component);

        if (current == null || edge.weight() < current.weight()) {
            cheapest.put(component, edge);
        }
    }
}