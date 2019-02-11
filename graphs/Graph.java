import java.util.LinkedList;

public class Graph{
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = new LinkedList[V];
        for (int v= 0; v<V; v++){
            adj[v] = new LinkedList<>();
        }
    }

    public Graph( In in){
        this(in.readInt()); //read V and construct Graph
        int E = in.readInt();
        for (int i = 0; i<E; i++){
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() { return  V; }
    public int E() {return  E; }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

}