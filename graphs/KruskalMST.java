import java.util.LinkedList;
import java.util.PriorityQueue;

public class KruskalMST {
    private LinkedList<Edge> mst;
    private double weight;

    public KruskalMST(EdgeWeightedGraph G){
        mst = new LinkedList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Edge e: G.edges()){
            pq.add(e);
        }
        UF uf = new UF(G.V());

        while (!pq.isEmpty()&&mst.size()<G.V()-1){
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v,w)) continue;
            uf.union(v,w);
            mst.addLast(e);
            weight += e.weight();
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        return weight;
    }

    public static void main(String[]args){

    }
}
