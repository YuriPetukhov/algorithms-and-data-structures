package hw13_prefix_trees.libs.searching.tries.children;

import hw13_prefix_trees.libs.searching.tries.TrieNode;

public class ArrayChildrenStorage<V> implements ChildrenStorage<V> {
    private final TrieNode<V>[] children;
    private final ChildrenStorageFactory<V> factory;

    @SuppressWarnings("unchecked")
    public ArrayChildrenStorage(ChildrenStorageFactory<V> factory) {
        this.children = (TrieNode<V>[]) new TrieNode[26];
        this.factory = factory;
    }

    @Override
    public TrieNode<V> get(char c) {
        return children[c - 'a'];
    }

    @Override
    public TrieNode<V> getOrCreate(char c) {
        int index = c - 'a';

        if (children[index] == null) {
            children[index] = new TrieNode<>(factory);
        }

        return children[index];
    }

    @Override
    public void remove(char c) {
        children[c - 'a'] = null;
    }

    @Override
    public boolean isEmpty() {
        for (TrieNode<V> child : children) {
            if (child != null) {
                return false;
            }
        }

        return true;
    }
}
