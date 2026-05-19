package hw13_prefix_trees.libs.searching.tries.children;

import hw13_prefix_trees.libs.searching.tries.TrieNode;

import java.util.HashMap;
import java.util.Map;

public class HashMapChildrenStorage<V> implements ChildrenStorage<V> {
    private final Map<Character, TrieNode<V>> children = new HashMap<>();
    private final ChildrenStorageFactory<V> factory;

    public HashMapChildrenStorage(ChildrenStorageFactory<V> factory) {
        this.factory = factory;
    }

    @Override
    public TrieNode<V> get(char c) {
        return children.get(c);
    }

    @Override
    public TrieNode<V> getOrCreate(char c) {
        return children.computeIfAbsent(c, key -> new TrieNode<>(factory));
    }

    @Override
    public void remove(char c) {
        children.remove(c);
    }

    @Override
    public boolean isEmpty() {
        return children.isEmpty();
    }
}
