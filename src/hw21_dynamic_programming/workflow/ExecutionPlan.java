package hw21_dynamic_programming.workflow;

import java.util.List;
import java.util.Objects;

public record ExecutionPlan(List<String> stepIds) {

    public ExecutionPlan {
        Objects.requireNonNull(stepIds, "Step ids must not be null.");
        stepIds = List.copyOf(stepIds);

        if (stepIds.stream().anyMatch(id -> id == null || id.isBlank())) {
            throw new IllegalArgumentException("Step id must not be blank.");
        }
    }

    public static ExecutionPlan of(String... stepIds) {
        Objects.requireNonNull(stepIds, "Step ids must not be null.");
        return new ExecutionPlan(List.of(stepIds));
    }
}
