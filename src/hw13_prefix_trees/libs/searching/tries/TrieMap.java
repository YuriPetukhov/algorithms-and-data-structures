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

        for (int i = 0; i < key.length(); i++) {
            current = current.ensureChild(key.charAt(i));
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
        RemoveResult result = remove(root, key, 0);

        if (result.removed()) {
            size--;
        }

        return result.removed();
    }

    @Override
    public int size() {
        return size;
    }

    private TrieNode<V> findNode(String s) {
        TrieNode<V> current = root;

        for (int i = 0; i < s.length(); i++) {
            current = current.child(s.charAt(i));

            if (current == null) {
                return null;
            }
        }

        return current;
    }

    private RemoveResult remove(TrieNode<V> node, String key, int depth) {
        if (node == null) {
            return new RemoveResult(false, false);
        }

        if (depth == key.length()) {
            if (!node.isTerminal()) {
                return new RemoveResult(false, false);
            }

            node.setTerminal(false);
            node.setValue(null);

            return new RemoveResult(true, node.hasNoChildren());
        }

        char c = key.charAt(depth);
        TrieNode<V> child = node.child(c);

        RemoveResult result = remove(child, key, depth + 1);

        if (result.deleteCurrentNode()) {
            node.removeChild(c);
        }

        boolean deleteCurrentNode =
                result.removed()
                        && !node.isTerminal()
                        && node.hasNoChildren();

        return new RemoveResult(result.removed(), deleteCurrentNode);
    }

    private record RemoveResult(
            boolean removed,
            boolean deleteCurrentNode
    ) {
    }
}