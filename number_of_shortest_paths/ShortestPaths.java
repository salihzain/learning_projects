/******************************************************************************
 * I should mention that the basic structure of the original BFS was inspired by
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *however, I came up with the algorithm to find the number of the shortest paths.
 *The original BFS doesn't provide the number of shortest paths at all.
 * Therefore, this solution is mine.
 */


import java.util.LinkedList;
import java.util.Stack;

public class ShortestPaths {
    //keeping track if a vertex has been visited
    private boolean[] marked;
    //the path taken to go to vertex v
    private int[] edgeTo;
    //the distance needed to vertex v
    private int[] distanceTo;
    //source vertex
    private final int s;

    public ShortestPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distanceTo = new int[G.V()];
        this.s = s;
        //run bfs to scan the graph and mark what vertices are reachable
        bfs(G, s);
    }

    private void bfs(Graph G, int s){
        // this is our queue
        LinkedList<Integer> queue = new LinkedList<>();
        // we mark the source as visited
        marked[s] = true;
        queue.addLast(s);
        // we simply mark every vertex and fill the distance and edgeTo arrays
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

    // a method to check if there is a path from vertex s to vertex v
    public boolean hasPathTo(int v){
        return marked[v];
    }


    //this is the method that counts the number of shortest paths from s to t
    public int pathsTo(Graph g, int t){
        if (!hasPathTo(t)) return 0;
        LinkedList<Integer> queue = new LinkedList<>();
        //we create a new array of boolean called visited to keep track of the visited vertices
        boolean [] visited = new boolean[g.V()];
        visited[s] = true;
        queue.addLast(s);
        //
        int countShortestPaths = 0;
        while (!queue.isEmpty()){
            int v = queue.poll();
            // we create a linkedlist to keep track of the previsited adjacent vertices.
            LinkedList<Integer> prevVisited = new LinkedList<>();
            for (int w : g.adj(v)){
                if (!visited[w]){
                    visited[w] = true;
                    queue.addLast(w);
                } else {
                    // if it has been already visited, add it to the previsited vertices of v
                    prevVisited.addLast(w);
                }
            }

            // if there is more than one element, we know there is a possibility that we have more than one path.
            if (prevVisited.size()>1){
                // we turn the list into an array
                int [] preVisitedArray = new int[prevVisited.size()];
                for (int i = 0; i<preVisitedArray.length; i++){
                    preVisitedArray[i] = prevVisited.poll();
                }

                // loop through all the possible pairs of the preVisitedArray
                for (int i = 0; i<preVisitedArray.length; i++){
                    for (int j = i+1; j<preVisitedArray.length; j++){
                        //if there is a pair that matches the same distance,
                        if (distanceTo[preVisitedArray[i]]==distanceTo[preVisitedArray[j]]){
                            // and we reached the pair from the same vertex, then we have to two additional shortest paths to v
                            if (edgeTo[preVisitedArray[i]]==edgeTo[preVisitedArray[j]])countShortestPaths+=2;
                            //otherwise we only have one additional shortest path
                            else countShortestPaths++;
                        }
                    }
                }
            }
            //if we have reached the destination t, break
            if (v == t) break;
        }
        //  we already know that there is a path since we checked in the beginning
        // so if there is no more than one shortest path, return one.
        if (countShortestPaths==0) return 1;
        //otherwise, return the number of shortest paths that we found above
        return countShortestPaths;
    }




    public static void main(String[] args){
        // In is a helper library to read input files
//        In in = new In("p2_in1");
        In in = new In("p2_in2");
        //the first number is the number of vertices
        int v = in.readInt();
        // the second number is the number of edges
        int e = in.readInt();
        // the third is the source vertex. notice that I'm subtracting by 1 because my graph is indexed at zero.
        int s = in.readInt()-1;
        // the destination vertex
        int t = in.readInt()-1;
        // we create a new graph
        Graph g = new Graph(v);
        // then we add the edges
        for (int i = 0; i<e; i++){
            int t1 = in.readInt()-1;
            int t2 = in.readInt()-1;
            g.addEdge(t1,t2);
        }

        // we create an object of ShortestPaths, give it the graph and source
        ShortestPaths bfs = new ShortestPaths(g, s);
        // then we call the method pathsTo with destination t to return the number of shortest paths.
        int num_of_paths = bfs.pathsTo(g, t);
        System.out.println("The total number of shortest paths is " + num_of_paths);
    }
}
