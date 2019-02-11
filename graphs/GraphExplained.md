The following notes on Graph are taken from Algorithms by Sedgewick 

path in a graph is a sequence of vertices connected by edges. 

A  simple path is one with no repeated vertices. 

A   cycle is a path with at least one edge whose ﬁrst and last vertices are the same. 

A  simple cycle is a cycle with no repeated edges or vertices (except the requisite repetition of the ﬁrst and last vertices). 

The  length of a path or a cycle is its number of edges.

Connected graph is when a vertex is connected to every other vertex 

otherwise, not connected. But consists of connected components. 

A tree is an acyclic connected graph

a disjoint set of trees is called a forest 

A spanning tree of a connected graph is a subgraph that contains all the vertices in one tree.

 



### Depth first search 

Maze graph? 

Vertex = intersection 

Edge = passage 

Goal: Systematically search through a graph 

Idea: mimic maze exploration 

DFS (to visit a vertex v)

Mark v as visited 

Recursively visit all unmarked vertices w adjacent to v 

0 ----- 1  ---- 2  

|  	| 

3 ---- 4 

depth first start at 0 

process 0 mark visited 

process 1 mark visited 

process 2 mark visited 

return to 1 

process 4 mark visited 

process 3 mark visited 

return to four 

return to 1 

return to 0 

done 





### Breadth-first search 

Repeat until queue is empty 

- remove vertex v from queue 
- add to queue all unmarked vertices adjacent to  v and mark them. 



0 ----- 2 ---------- 4 

| 	\  |	\	/ 

|	  1  	|	/	

| 		|	/ 

5 ---- 	3    



start at 0 

dequeue 0 

check all adj vertices to 0 

check 2

add to queue 

check 1 not marked 

add to queue

check 5 

add to queue 

then go back to 2 -- since it's in the queue 

process 0 already marked 

process 1 already marked -- added to queue 

process 3 add to queue and mark 

process 4 add to queue and mark 

repeat until done 

dfs: unvisited in stack

bfs: unvisited in queue 



Proposition: BFS computes shortest paths (fewest number of edges from s to all other vertices in a graph in time prop. to E+V)

### Connected Components 

Def: Vertices v and w are connected if there is a path between them 

Goal: process graph to answer queries of the form is v connected to w? in constant time



reflexive: v is connected to v 

symmetric:  v -> w then w -> v 

transitive: if v -> w -> x, then v -> x 

connected component: maximal set of connect vertices 

remark: given connected components, can answer queries in constant time 



Goal: partition vertices into connected components 

init. all vertices v as unmarked. 

for each unmarked vertex v, run DFS to identify all vertices discovered as part of the same component. 





# Directed Graphs 

path: is there a directed path from s to t? 

what is the shortest path? 

topological sort? can you draw a diagraph so that all edges point upwards? 

strong connectivity: is there a directed path between all pairs of vertices? 

Transitive closure: for which vertices v and w is there a path from v to w 

pageRank: what is the importance of web page? 

how many different digraphs are there on V vertices? allowing self-loops? 2^(v^2)



### Digraph search 

problem: find all vertices reachable from s along a directed path. 

Same method for undirected graphs

DFS 

![](princeton_lectures\1.png)

every program is a diagraph! 

vertex = basic block of instructions 

edge = jump 



Dead-code elimination = unreachable code 



every data structure is a diagraph 

vertex = object 

edge = reference 

Roots: objects known to be directly accessible by program 

Mark-sweep algorithm 

- mark all reachable objects 

- sweep: if object is unmarked, it's  garbage (add to free list)



breadth first is the same 

breadth-first search 

Goal: Crawl web, starting from some root web page, say www.google.com 

solution: 

- choose root web page as source s 
- maintain a queue of websites to explore 
- maintain a set of discovered websites 
- dequeue the next website and enqueue websites to which it links 





### precedence scheduling 

goal: given a set of tasks to be completed with precedence constraints, in which order should we schedule the tasks? 

digraph model: vertex = task; edge = precedence constraint 

### Topological sort 

DAG: directed acyclic graph 

Topo sort: redraw DAG so all edges point upwards 

solution: DFS 

run DFS 

return vertices in reverse postorder 





### String connected components 

def: vertices v and w are strongly connected if there is a directed path from v to w and a directed path from w to v. 







## Minimum spanning trees 

given: undirected graph G with positive edge weights (connected) 

def: a spanning tree of G is a subgraph T that is both a tree (connected and acyclic) and spanning (includes all of the vertices)

Goal: find a min weight spanning tree 

## greedy algorithm 

simplifying assumptions: 

-  edge weights are distinct 
- graph is connected. 

Consequence: MST exists and is unique 

#### cut property 

def: a cut in a graph is a partition of its vertices into two (nonempty) sets. 

a crossing edge connects a vertex in one set with a vertex in the other. 

cut property: given any cut, the crossing edge of min weight is the in the MST. 



steps: 

- start with all edges colored gray
- find cut with no black crossing edges; colors it min weight edge black 
- repeat until v-1 edges are colored black. 



proposition: the greedy algorithm computes the MST. 

pf: any edge colored black is in hte MST (via cut property)

fewer than v-1 black edge => cut with no black crossing edges consider cut whose vertices are one connected component. 

two cases to consider: 

1- what if edge weights are not all distinct? still correct. 

2- what if graph is not connected? minimum spanning forest. 





## Kruskal's algorithm demo 

consider edges in ascending order of weight 

add next edge to tree T unless doing so would create a cycle. 



proposition: Kuskal's algo computes the MST 

pf: it's a special case of greedy MST algo 

- suppose kurskal's algo colors the edge e = v-w black 
- cut = set of vertices connected to  v in tree T 
- no crossing edge is black 
- no crossing edge has lower weight 

challenge: would adding edge v-w to tree T create a cycle? if not, add it

how difficult? 

we need union find data structure. 

- maintain a set for each connected component 
- if v and w are in the same set, then adding v-w would create a cycle. 
- to add v-w to T, merge sets containing v and w. 

Running time = E log E in worst case 



### Prim's algo demo 

- start with vertex 0 and greedily grow tree T 
- Add to T the min weight edge with exactly one endpoint in T 
- repeat until V-1 Edges

challenge: find the min weight edge with exactly one endpoint in T. 

lazy solution: 

- maintain a pq of edges with at least one endpoint in T 
- key - edge; priority - weight of edge 
- delete-min to determine next edge e -= v-w to add to T
-  disregard if both endpoints v and w are in T 
- otherwise, let w be the vertex not in T: 
  - add to PQ any edge incident to w(assuming other endpoint not in T)
  - add w to T 
- running time is E log E worst case 



challenge: find min weight edge with exactly one endpoint in T 

eager solution: maintain a pq of vertices connected by an edge to T where priority of vertex v = weight of shortest edge connecting v to T. 

- delete min vertex v and add its edge to T 
- update PQ by considering all edge e = v-x incident to v 
  - ignore if x is already in T 
  - add x to PQ if not already on it 
  - decrease priority of x if v-x becomes shortest edge connecting x to T. 





# shortest paths

we now begin our discussion of what I believe to be the most important application of graphs, shortest paths problem. 

So let's get started! 



Given an edge-weighted digraph, find the shortest path from s to t. 

which vertices? 

- source-sink: from one vertex to another 
- single source: from one vertex to every other
- all pairs: between all pairs of vertices 



restriction on edge weights: 

- nonnegative weights 
- arbitrary weights 
- Euclidean weights 



cycles? 

- no directed cycles 
- no negative cycles 

assumption: shortest path exist 



### shortest paths properties 

Goal: find the shortest path from s to every other vertex 

Observation: a shortest paths tree (SPT) solution exists 

Consequence can represent the SPT with two vertex indexed arrays 

distTo[v] is length of shortest path from s to v 

edgeTo[v] is last edge on shortest path from s to v 

Edge Relaxation: 

relax edge e = v > w 

- distTo[v] is length of shortest known path from s to v. 
- distTo[w] is length of shortest known path from s to w. 
- edgeTo[w] is last edge on shortest known path from s to w 
- if e = v -> w gives shorter path to w through w, update both distTo[w] and edgeTo[w].

```java
private void relax(DirectedEdge e){
    int v = e.from(), w = e.to(); 
    if (distTo[w]>distTo[v] + e.weight())
    {
        distTo[w] = distTo[v] + e.weight(); 
        distTo[w] = e; 
    }
}
```



proposition: let G be an edge-weighted digraph

then distTo[] are the shortest path distances form s iff: 

- distTo[s] = 0; 
- for each vertex v, distTo[v] is the length of some path from s to v. 
- for each edge e = v->w, distTo[w] <= distTo[v] + e.weight()

pf: 

<= 

- suppose that distTo[w] >distTo[v]+e.weight() for some edge e= v -> w 
- then, e gives a path from s to w through v of length less that distTo[w]

=> 

suppose that s  = v0 -> v1 ...... vk = w is a shortest path from s to w

distTo[v1] <= distTo[v0] + e1.weight()

distTo[v2] <= distTo[v1] + e2.weight()

... 

ditoTo[vk] <= distTo[vk-1] + ek.weight()

add inequalities; simplify and substitute distTo[v0] = distTo[s] = 0; 

distTo[w] = distTo[vk] <= e1.weight() + e2.weight() + ... + ek.weight() {weight of shortest path from s to w} 

thus, distTo[w] is the weight of the shortest path to w. 



Generic algorithm (to compute SPT from s)

initialize distTo[s] = 0 and distTo[v] = inf for all other vertices 

repeat until optimality conditions are satisfied

-  relax any edge 



## Dijkstra's Algorithm 

ladies and gentlemen, let's now talk about the most important algorithm in Graphs (I'm making that up). 

- consider vertices in increasing order of distance from s (non-tree vertex with the lowest distTo[] value)
- add vertex to tree and relax all edges pointing from the vertex 

proposition: dijkstra's algo computes a SPT in any edge-weighted digraph with non-negative weights 

pf: 

- each edge e = v->w is relaxed exactly once (when v is relaxed), leaving distTo[w] <= distTo[v] + e.weight()
- inequality holds until algo terminates because: 
  - distTo[w] cannot increase 
  - distTo[v] will not change 
- thus, upon termination, shortest-paths optimality conditions holds. 





## Edge weighted DAGs 

q: suppose that an edge-weighted DAG has no directed cycles, is it easier to find shortest paths than in a general graph? 

a: yes 

- consider vertices in topological order 
- relax all edges pointing from that vertex 

proposition: topological sort algo computes SPT in any edge-weighted DAG in time proportional to E+V

pf: 

- each edge e = v -> w is relaxed exactly once when v is relaxed, leaving distTo[w] <= distTo[v] + e.weight()
- inequality holds until algorithm terminates because: 
  - distTo[w] cannot increase 
  - distTo[v] will not change 
- thus, upon termination, shortest paths optimality conditions hold. 





