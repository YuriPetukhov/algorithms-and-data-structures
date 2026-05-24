package hw19_knuth_morris_pratt_algorithm.service;

import hw19_knuth_morris_pratt_algorithm.service.steps.Step;

import java.util.List;

public class Handler<C extends AlgorithmContext<R>, R> {
    private final List<Step<C>> steps;

    public Handler(List<Step<C>> steps) {
        this.steps = steps;
    }

    public R handle(C context) {
        for (Step<C> step : steps) {
            step.execute(context);
        }

        return context.result();
    }
}