package hw13_prefix_trees.service;

public class TrieOperationContext<V> {
    private final String operation;
    private final String key;
    private final V value;

    private Object result;

    public TrieOperationContext(String operation, String key, V value) {
        this.operation = operation;
        this.key = key;
        this.value = value;
    }

    public String operation() {
        return operation;
    }

    public String key() {
        return key;
    }

    public V value() {
        return value;
    }

    public Object result() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}