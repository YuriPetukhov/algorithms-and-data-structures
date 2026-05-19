package hw13_prefix_trees.libs.searching.tries;

import hw13_prefix_trees.libs.searching.tries.children.ChildrenStorageFactory;

public class TrieMap<V> implements TrieMapInterface<V> {
    private final TrieNode<V> root;
    private int size;

    public TrieMap(ChildrenStorageFactory<V> factory) {
        this.root = new TrieNode<>(factory);
    }

    @Override
    public void put(String key, V value) {
        TrieNode<V> current = root;

        for (char c : key.toCharArray()) {
            current = current.ensureChild(c);
        }

        if (!current.isTerminal()) {
            size++;
        }

        current.setTerminal(true);
        current.setValue(value);
    }

    @Override
    public V get(String key) {
        TrieNode<V> node = findNode(key);
        return node != null && node.isTerminal() ? node.value() : null;
    }

    @Override
    public boolean containsKey(String key) {
        TrieNode<V> node = findNode(key);
        return node != null && node.isTerminal();
    }

    @Override
    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    @Override
    public boolean remove(String key) {
        if (!containsKey(key)) {
            return false;
        }

        remove(root, key, 0);
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    private TrieNode<V> findNode(String s) {
        TrieNode<V> current = root;

        for (char c : s.toCharArray()) {
            current = current.child(c);

            if (current == null) {
                return null;
            }
        }

        return current;
    }

    private boolean remove(TrieNode<V> node, String key, int depth) {
        if (depth == key.length()) {
            node.setTerminal(false);
            node.setValue(null);
            return node.hasNoChildren();
        }

        char c = key.charAt(depth);
        TrieNode<V> child = node.child(c);

        boolean deleteChild = remove(child, key, depth + 1);

        if (deleteChild) {
            node.removeChild(c);
        }

        return !node.isTerminal() && node.hasNoChildren();
    }
}