package hw21_dynamic_programming.service.steps;

import hw21_dynamic_programming.workflow.ExecutionStep;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class StepCatalog {

    private final Map<String, ExecutionStep> stepsById;

    public StepCatalog(Collection<? extends ExecutionStep> steps) {
        Objects.requireNonNull(steps, "Step collection must not be null.");

        Map<String, ExecutionStep> catalog = new LinkedHashMap<>();
        for (ExecutionStep step : steps) {
            Objects.requireNonNull(step, "Registered step must not be null.");
            if (step.id() == null || step.id().isBlank()) {
                throw new IllegalArgumentException("Step id must not be blank.");
            }
            ExecutionStep previous = catalog.putIfAbsent(step.id(), step);
            if (previous != null) {
                throw new IllegalArgumentException("Duplicate step id: " + step.id());
            }
        }
        this.stepsById = Map.copyOf(catalog);
    }

    public ExecutionStep getRequired(String stepId) {
        ExecutionStep step = stepsById.get(stepId);
        if (step == null) {
            throw new NoSuchElementException("Step not found: " + stepId);
        }
        return step;
    }
}
