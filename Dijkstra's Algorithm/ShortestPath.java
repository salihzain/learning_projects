/*
some ideas in this class were inspired by the Graph implementation found in:
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

public class ShortestPath {
    private ConcurrentHashMap<Vertex, Edge> edgeTo;
    private PriorityQueue<Vertex> pq;
    private LinkedList<Vertex> reachableVertices;

    public ShortestPath(Graph G, Vertex s) {
        edgeTo = new ConcurrentHashMap<>();
        pq = new PriorityQueue<>();
        reachableVertices = new LinkedList<>();
        s.setDistTo(0.0);
        G.vertices().forEach((name, vertex)-> pq.add(vertex));
        while (!pq.isEmpty())
            relax(G, pq.poll());
    }

    private void relax(Graph G, Vertex v) {
        for (Edge e : G.adj(v)) {
            Vertex w = e.to();
            if (w.distTo() > v.distTo() + e.distance()) {
                w.setDistTo(v.distTo() + e.distance());
                edgeTo.put(w, e);
                reachableVertices.add(w);
                if (pq.contains(w)) {
                    pq.remove(w);
                    pq.add(w);
                }
            }
        }
    }

    public double distTo(Vertex v) { return v.distTo(); }

    public boolean hasPathTo(Vertex v) { return v.distTo() < Double.POSITIVE_INFINITY; }

    public ArrayList<Edge> pathTo(Vertex v) {
        if (!hasPathTo(v))
            return null;
        ArrayList<Edge> path = new ArrayList<>();
        for (Edge e = edgeTo.get(v); e != null; e = edgeTo.get(e.from()))
            path.add(e);
        return path;
    }

    public Iterable<Vertex> reachableVertices(){ return reachableVertices; }

}
