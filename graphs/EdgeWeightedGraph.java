import java.util.LinkedList;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private LinkedList<Edge>[] adj;

    public EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        adj = new LinkedList[V];
        for (int v = 0; v<V; v++)
            adj[v] = new LinkedList<>();
    }

    public EdgeWeightedGraph(In in){
        this(in.readInt());
        int E = in.readInt();
        if(E<0) throw new IllegalArgumentException("edges<0");
        for (int i = 0; i<E; i++){
            int v = in.readInt();
            int w = in.readInt();
            double weigth = in.readDouble();

            Edge e = new Edge(v,w, weigth);
            addEdge(e);
        }
    }

    public int V(){return V;}
    public int E(){return E;}

    public void addEdge(Edge e){
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public Iterable<Edge> edges(){
        LinkedList<Edge> b = new LinkedList<>();
        for (int v = 0; v<V; v++){
            for (Edge e: adj[v])
                if (e.other(v)>v) b.add(e);
        }
        return b;
    }




}
