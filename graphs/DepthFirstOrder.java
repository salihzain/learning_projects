
import java.util.LinkedList;
import java.util.Stack;

public class DepthFirstOrder {
    private boolean[] marked;
    private LinkedList<Integer> pre; // in preorder
    private LinkedList<Integer> post; // in post
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G){
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();
        marked = new boolean[G.V()];

        for (int v = 0; v<G.V(); v++){
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v){
        pre.addLast(v);
        marked[v] = true;
        for (int w: G.adj(v))
            if (!marked[w])
                dfs(G, w);
        post.addLast(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }

    public static void main(String[]args){
        In in = new In("src/tiny.txt");
        Digraph g = new Digraph(in);
        DepthFirstOrder order = new DepthFirstOrder(g);
        order.reversePost().forEach(v -> {
            System.out.println(v);
        });
    }

}
