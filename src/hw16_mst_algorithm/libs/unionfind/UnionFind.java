package hw16_mst_algorithm.libs.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UnionFind<V> {
    private final Map<V, V> parent = new HashMap<>();
    private final Map<V, Integer> rank = new HashMap<>();

    public UnionFind(Set<V> vertices) {
        for (V vertex : vertices) {
            parent.put(vertex, vertex);
            rank.put(vertex, 0);
        }
    }

    public V find(V vertex) {
        if (!parent.containsKey(vertex)) {
            throw new IllegalArgumentException("Unknown vertex: " + vertex);
        }

        V root = vertex;

        while (!root.equals(parent.get(root))) {
            root = parent.get(root);
        }

        while (!vertex.equals(root)) {
            V next = parent.get(vertex);
            parent.put(vertex, root);
            vertex = next;
        }

        return root;
    }

    public boolean union(V first, V second) {
        V firstRoot = find(first);
        V secondRoot = find(second);

        if (firstRoot.equals(secondRoot)) {
            return false;
        }

        int firstRank = rank.get(firstRoot);
        int secondRank = rank.get(secondRoot);

        if (firstRank < secondRank) {
            parent.put(firstRoot, secondRoot);
        } else if (firstRank > secondRank) {
            parent.put(secondRoot, firstRoot);
        } else {
            parent.put(secondRoot, firstRoot);
            rank.put(firstRoot, firstRank + 1);
        }

        return true;
    }
}