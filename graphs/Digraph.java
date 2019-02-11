import java.awt.*;
import java.util.LinkedList;

public class Digraph {
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;
    public Digraph(int V){
        this.V = V;
        this.E = 0;
        adj = new LinkedList[V];
        for (int v = 0; v<V; v++){
            adj[v] = new LinkedList<>();
        }
    }

    public Digraph(In in){
        this(in.readInt()); //read V and construct Graph
        int E = in.readInt();
        for (int i = 0; i<E; i++){
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() { return  V; }
    public int E() { return E; }

    public void addEdge(int v, int w){
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public Digraph reverse(){
        Digraph R = new Digraph(V);
        for (int v = 0; v<V; v++)
            for (int w : adj[v])
                R.addEdge(w,v);
        return R;
    }




    public static void main(String[] args){
        In in = new In("src/tiny.txt");
        Digraph g = new Digraph(in);

        for (int i = 0; i<g.V(); i++){
            g.adj(i).forEach(n -> {
                System.out.println(n);
            });
        }

    }
}
