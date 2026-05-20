package hw14_kosaraju_algorithm.service;

public class GraphService {
    private final Handler<GraphContext> handler;

    public GraphService(Handler<GraphContext> handler) {
        this.handler = handler;
    }

    public Object execute(GraphContext context) {
        return handler.handle(context);
    }
}