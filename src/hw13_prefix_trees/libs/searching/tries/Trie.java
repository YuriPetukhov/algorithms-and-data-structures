package hw13_prefix_trees.libs.searching.tries;

import hw13_prefix_trees.libs.searching.tries.children.ArrayChildrenStorageFactory;

public class Trie {
    private final TrieMapInterface<Boolean> map;

    public Trie() {
        this.map = new TrieMap<>(new ArrayChildrenStorageFactory<>());
    }

    public void insert(String word) {
        map.put(word, true);
    }

    public boolean search(String word) {
        return map.containsKey(word);
    }

    public boolean startsWith(String prefix) {
        return map.startsWith(prefix);
    }

    public boolean delete(String word) {
        return map.remove(word);
    }
}
