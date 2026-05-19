package hw13_prefix_trees.libs.searching.tries.children;

public class ArrayChildrenStorageFactory<V> implements ChildrenStorageFactory<V> {
    @Override
    public ChildrenStorage<V> create(ChildrenStorageFactory<V> factory) {
        return new ArrayChildrenStorage<>(factory);
    }
}