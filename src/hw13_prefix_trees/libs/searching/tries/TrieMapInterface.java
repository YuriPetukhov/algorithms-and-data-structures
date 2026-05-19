package hw13_prefix_trees.libs.searching.tries;

public interface TrieMapInterface<V> {
    void put(String key, V value);

    V get(String key);

    boolean containsKey(String key);

    boolean startsWith(String prefix);

    boolean remove(String key);

    int size();
}
