package hw14_kosaraju_algorithm.service;

import hw14_kosaraju_algorithm.service.steps.Step;

import java.util.List;

public class Handler<C> {
    private final List<Step<C>> steps;

    public Handler(List<Step<C>> steps) {
        this.steps = steps;
    }

    public Object handle(C context) {
        for (Step<C> step : steps) {
            step.execute(context);
        }

        if (context instanceof GraphContext graphContext) {
            return graphContext.result();
        }

        return null;
    }
}