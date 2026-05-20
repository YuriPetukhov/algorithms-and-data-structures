package hw14_kosaraju_algorithm.libs.graphs;

public abstract class AbstractDirectedGraph implements DirectedGraph {
    private final int vertexCount;
    private int edgeCount;

    protected AbstractDirectedGraph(int vertexCount) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("Vertex count must not be negative");
        }

        this.vertexCount = vertexCount;
    }

    @Override
    public int vertexCount() {
        return vertexCount;
    }

    @Override
    public int edgeCount() {
        return edgeCount;
    }

    protected void increaseEdgeCount() {
        edgeCount++;
    }

    protected void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= vertexCount) {
            throw new IllegalArgumentException("Invalid vertex: " + vertex);
        }
    }

    protected void validateEdge(int from, int to) {
        validateVertex(from);
        validateVertex(to);
    }
}
