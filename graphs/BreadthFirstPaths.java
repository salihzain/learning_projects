import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distanceTo;
    private final int s;

    public BreadthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distanceTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s){
        LinkedList<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.addLast(s);
        while (!queue.isEmpty()){
            int v = queue.poll();
            for (int w : G.adj(v)){
                if (!marked[w]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    distanceTo[w] = distanceTo[v]+1;
                    queue.addLast(w);
                }
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

    public HashMap<Integer, Integer> pathWithDistance(int v){
        if (!hasPathTo(v)) return null;
        HashMap<Integer, Integer> temp = new HashMap<>();
        temp.put(s, distanceTo[s]);
        for (int x = v; x!=s; x=edgeTo[x]) {
            temp.put(x, distanceTo[x]);
        }
        temp.put(v, distanceTo[v]);
        return temp;
    }


    public static void main(String[] args){
        In in = new In("src/tiny.txt");
        Graph g = new Graph(in);
        BreadthFirstPaths bfs = new BreadthFirstPaths(g, 0);
//        bfs.pathTo(7).forEach(v -> {
//            System.out.println(v);
//        });

        bfs.pathWithDistance(7).forEach((key, value) ->{
            System.out.println(key + " " + value);
        });

        System.out.println();
        for (int s : bfs.distanceTo){
            System.out.print(s + " ");
        }

    }
}
