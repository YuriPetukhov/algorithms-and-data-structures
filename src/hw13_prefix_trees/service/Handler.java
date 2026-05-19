package hw13_prefix_trees.service;

import hw13_prefix_trees.service.steps.Step;

import java.util.List;

public class TrieOperationHandler<V> {
    private final List<Step<TrieOperationContext<V>>> steps;

    public TrieOperationHandler(List<Step<TrieOperationContext<V>>> steps) {
        this.steps = steps;
    }

    public Object handle(TrieOperationContext<V> context) {
        for (Step<TrieOperationContext<V>> step : steps) {
            step.execute(context);
        }

        return context.result();
    }
}