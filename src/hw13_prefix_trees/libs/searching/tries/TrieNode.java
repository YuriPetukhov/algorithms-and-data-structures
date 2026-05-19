package hw13_prefix_trees.libs.searching.tries;

import hw13_prefix_trees.libs.searching.tries.children.ChildrenStorage;
import hw13_prefix_trees.libs.searching.tries.children.ChildrenStorageFactory;

public class TrieNode<V> {
    private final ChildrenStorage<V> children;
    private boolean terminal;
    private V value;

    public TrieNode(ChildrenStorageFactory<V> factory) {
        this.children = factory.create(factory);
    }

    public TrieNode<V> child(char c) {
        return children.get(c);
    }

    public TrieNode<V> ensureChild(char c) {
        return children.getOrCreate(c);
    }

    public void removeChild(char c) {
        children.remove(c);
    }

    public boolean hasNoChildren() {
        return children.isEmpty();
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public V value() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}