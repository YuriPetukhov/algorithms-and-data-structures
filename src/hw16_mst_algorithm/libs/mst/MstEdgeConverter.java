package hw16_mst_algorithm.libs.mst;

import hw16_mst_algorithm.libs.graphs.Edge;
import hw16_mst_algorithm.libs.graphs.WeightedEdge;

import java.util.List;

public final class MstEdgeConverter {
    private MstEdgeConverter() {
    }

    public static Edge[] toEdgeArray(List<WeightedEdge<Integer>> weightedEdges) {
        Edge[] result = new Edge[weightedEdges.size()];

        for (int i = 0; i < weightedEdges.size(); i++) {
            WeightedEdge<Integer> edge = weightedEdges.get(i);
            result[i] = new Edge(edge.v1(), edge.v2());
        }

        return result;
    }
}