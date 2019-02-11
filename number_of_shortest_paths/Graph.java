/******************************************************************************
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 */

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

    public int[] adjs(int v){
        int size = adj[v].size();
        int [] temp = new int[size];
        int i = 0;
        for (int s : adj(v)){
            if (i>=temp.length) break;
            temp[i] = s;
            i++;
        }
        return temp;
    }

}