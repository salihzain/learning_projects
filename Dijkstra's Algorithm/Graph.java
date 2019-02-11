/*
some ideas in this class were inspired by the Graph implementation found in:
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 */

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class Graph {
    private final int V;
    private int E;
    private ConcurrentHashMap<Vertex, LinkedList<Edge>> adj;
    private ConcurrentHashMap<String, Vertex> vertices;

    public Graph(In in) {
        adj = new ConcurrentHashMap<>();
        vertices = new ConcurrentHashMap<>();
        readVertices(in);
        this.V = vertices.size();
        this.E = 0;
        readEdges(in);
    }

    private void readVertices(In in) {
        String name;
        double lat;
        double lon;
        while (in.readString().equals("i")) {
            name = in.readString();
            lat = in.readDouble();
            lon = in.readDouble();
            Vertex toAdd = new Vertex(name, lat, lon);
            adj.put(toAdd, new LinkedList<>());
            vertices.put(name, toAdd);
        }
    }

    private void readEdges(In in) {
        String name;
        Vertex from;
        Vertex to;
        while (true){
            name = in.readString();
            from = vertices.get(in.readString());
            to = vertices.get(in.readString());
            addEdge(new Edge(name, from, to));
            addEdge(new Edge(name, to, from));
            if (!in.hasNextLine()) break;
            in.readString();
        }
    }

    public void addEdge(Edge e) {
        adj.get(e.from()).add(e);
        E++;
    }

    public Iterable<Edge> adj(Vertex v) {
        return adj.get(v);
    }

    public Iterable<Edge> edges() {
        LinkedList<Edge> temp = new LinkedList<>();
        adj.forEach((vertex, edges) -> {
            temp.addAll(edges);
        });
        return temp;
    }

    public ConcurrentHashMap<String, Vertex> vertices() { return vertices; }

    public int V() { return this.V; }

    public int E() { return this.E; }

}
