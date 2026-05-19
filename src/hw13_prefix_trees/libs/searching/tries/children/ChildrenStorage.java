package hw13_prefix_trees.libs.searching.tries.children;

import hw13_prefix_trees.libs.searching.tries.TrieNode;

public interface ChildrenStorage<V> {
    TrieNode<V> get(char c);

    TrieNode<V> getOrCreate(char c);

    void remove(char c);

    boolean isEmpty();
}
