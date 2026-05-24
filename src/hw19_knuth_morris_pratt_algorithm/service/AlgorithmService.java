package hw19_knuth_morris_pratt_algorithm.service;

public class AlgorithmService<C extends AlgorithmContext<R>, R> {
    private final Handler<C, R> handler;

    public AlgorithmService(Handler<C, R> handler) {
        this.handler = handler;
    }

    public R execute(C context) {
        return handler.handle(context);
    }
}