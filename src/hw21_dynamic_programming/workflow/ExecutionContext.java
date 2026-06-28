package hw21_dynamic_programming.workflow;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ExecutionContext {

    private final String taskId;
    private final Object originalInput;
    private final Map<String, Object> attributes = new HashMap<>();

    private Object payload;
    private Object result;

    public ExecutionContext(String taskId, Object input) {
        if (taskId == null || taskId.isBlank()) {
            throw new IllegalArgumentException("Task id must not be blank.");
        }
        this.taskId = taskId;
        this.originalInput = input;
        this.payload = input;
    }

    public String taskId() {
        return taskId;
    }

    public Object originalInput() {
        return originalInput;
    }

    public Object payload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public Object result() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void putAttribute(String key, Object value) {
        attributes.put(Objects.requireNonNull(key, "Attribute key must not be null."), value);
    }

    public <T> T getAttribute(String key, Class<T> type) {
        Object value = attributes.get(key);
        return value == null ? null : type.cast(value);
    }
}
