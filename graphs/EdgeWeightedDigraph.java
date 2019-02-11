import java.util.LinkedList;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private LinkedList<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V){
        this.V = V;
        this.E = 0;
        adj = new  LinkedList[V];
        for (int v = 0; v < V; v++){
            adj[v] = new LinkedList<>();
        }
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new DirectedEdge(v, w, weight));
        }
    }

    public void addEdge(DirectedEdge e){
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        LinkedList<DirectedEdge> temp = new LinkedList<>();
        for (int v = 0; v<V; v++){
            for (DirectedEdge e: adj[v])
                temp.add(e);
        }
        return temp;
    }

    public int V(){
        return this.V;
    }
}
