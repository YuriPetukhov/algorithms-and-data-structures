package hw13_prefix_trees.libs.searching.tries.children;

public interface ChildrenStorageFactory<V> {
    ChildrenStorage<V> create(ChildrenStorageFactory<V> factory);
}
