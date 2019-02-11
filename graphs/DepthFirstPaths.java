import java.util.Iterator;
import java.util.Stack;

public class DepthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;


    public DepthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G,s);
    }

    //depth first search
    private void dfs(Graph G, int v){
        marked[v] = true;
        for (int w : G.adj(v)){
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x!=s; x=edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args){
        In in = new In("src/tiny.txt");
        Graph g = new Graph(in);
//        BreadthFirstPaths s = new BreadthFirstPaths(g, 0);
//        System.out.println(s.hasPathTo(4));
//
//
//        Iterable<Integer> z = s.pathTo(4);
//        z.forEach(t -> {
//            System.out.println(t);
//        });

        CC comp = new CC(g);
        System.out.println(comp.connected(0,2));
    }
}
