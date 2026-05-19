package hw13_prefix_trees.libs.searching.tries.children;

public class HashMapChildrenStorageFactory<V> implements ChildrenStorageFactory<V> {
    @Override
    public ChildrenStorage<V> create(ChildrenStorageFactory<V> factory) {
        return new HashMapChildrenStorage<>(factory);
    }
}