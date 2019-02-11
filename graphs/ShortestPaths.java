import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class ShortestPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distanceTo;
    private final int s;

    public ShortestPaths(Graph G, int s){
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
        int paths = 0;
        while (!queue.isEmpty()){
            int v = queue.poll();
            Stack<Integer> prevVisited = new Stack<>();
            for (int w : G.adj(v)){
                if (!marked[w]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    distanceTo[w] = distanceTo[v]+1;
                    queue.addLast(w);
                } else {
                    prevVisited.push(w);
                }
            }
            while (prevVisited.size()>1) {
                int adj1 = prevVisited.pop();
                int adj2 = prevVisited.pop();
                if (distanceTo[adj1]==distanceTo[adj2]) {
                    if (edgeTo[adj1] == edgeTo[adj2]){
                        System.out.println("V is " + v  + "adj1 " + adj1 + " adj2 " + adj2 + " distance " + distanceTo[adj1]);
                        paths+=2;
                    } else {
                        paths++;
                    }
                }
            }
        }
        System.out.println("num of paths are " + paths);
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
        ShortestPaths bfs = new ShortestPaths(g, 0);




    }
}
