# Mapping Project



## Task

This project will require you to create a rudimentary mapping program in Java. Given a data set representing the roads and intersections in a speciﬁc geographic region, your program should be able to plot a map of the data and provide shortest path directions between any two arbitrary intersections.



## How to run the program 

There are three maps to choose from: ur.txt, monroe.txt, nys.txt. Select one of the maps, select any two arbitrary points. In the command line, do the following: 

`javac *.java`

Then, you have three choices: 

1. Get the direction from a starting point to an end point, in this case, run the following command: `java StreetMap map_name.txt --directions starting_point destination_point` for example, `java StreetMap ur.txt --directions LOVEJOY SIMON`
2. Get the direction and view a map of the directions, in this case, run the following command: `java StreetMap map_name.txt --show --directions starting_point destination_point` for example `java StreetMap ur.txt --show --directions LOVEJOY SIMON`
3. View a single map only without getting directions between two points. in this case, run the following command: `java StreetMap map_name.txt --show` for example, `java StreetMap ur.txt --show`



## Implementation 

First of all, let's think about we need to do in steps. 

1. I take a text file as input. Every line of the text file is formatted as one of the following two ways:
   1. `i intersection_name latitude longtitude` for intersections 
   2. `r road_name from_intersection to_intersection` for roads 
2. I create an object of our implementation of directed graph and we add all the vertices first, then the edges. 
3. Based on the command in the input, I do the following: 
   1. if the command is `--directions` then I  take a starting point and a destination point. I create an object of ShortestPath that runs Dijkstra's algorithm to find the shortest paths to all the connected intersections. Then I find the path to the destination point, print the total distance, and the directions from the starting point to the destination point. 
   2. if the command is `--show` then I just render a graphical representation of the map using java graphics. 
   3. if the command is `--show --directions` then I do 1 & 2 in their order. I also graph the path between the starting point and the destination point in blue. 

### Classes 

In this project, I apply the MVC (model, view, controller) principle of programming. I divide the classes into these three categories to ensure clarity and readability as well as efficiency. 

In general, we have the classes below: 

| Model        | View | Controller |
| ------------ | ---- | ---------- |
| Graph        | View | StreetMap  |
| Vertex       |      |            |
| Edge         |      |            |
| ShortestPath |      |            |

I'm  also using In.java as a helper library to read the input. The appropriate citation to the authors was stated at the bottom In.java file. 

I will dive into the details of every class. I would like to clarify that the ideas behind the Graph data structure implementation as well as the Dijaktra's algorithm (i.e. ShortestPath) were inspired by the ideas presented in 

```
Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
*      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
*      http://algs4.cs.princeton.edu
```

Now, let's dive into each class

#### Graph

| Name                                               | Field/Method | Private/Public | Description                                      | Runtime Complexity |
| -------------------------------------------------- | ------------ | -------------- | ------------------------------------------------ | ------------------ |
| V: int                                             | field        | private        | keeps track of the number of vertices            | -                  |
| E: int                                             | field        | private        | Keeps track of the number of Edges               | -                  |
| adj: ConcurrentHashMap<Vertex, LinkedList<Vertex>> | field        | private        | adjacency list for each Vertex and its neighbors | -                  |
| vertices: ConcurrentHashMap<String, Vertex>        | field        | private        | serves as a dictionary of the vertices           | -                  |
| Graph(In)                                          | constructor  | public         | Construct a new Graph object                     | O(1)               |
| readVertices(In)                                   | method       | private        | Read and add vertices to the Graph               | O(V)               |
| readEdges(In)                                      | method       | private        | Read and add edges to the Graph                  | O(E)               |
| addEdge(Edge)                                      | method       | public         | add an edge to the adjacency list                | O(1)               |
| adj(Vertex): Iterable<Edge>                        | method       | public         | Return all the adjacent vertices to Vertex **V** | O(1)               |
| Edges(): Iterable<Edge>                            | method       | public         | Return all the edges in Graph **G**              | O(V)               |
| vertices(): ConcurrentHashMap<String, Vertex>      | method       | public         | Return all the vertices in Graph **G**           | O(1)               |
| V(): int                                           | method       | public         | Return the number of vertices                    | O(1)               |
| E(): int                                           | method       | public         | Return the number of edges                       | O(1)               |



#### Vertex (Intersection)

| Name                           | Field/Method | Private/Public | Description                                                  | Runtime Complexity |
| ------------------------------ | ------------ | -------------- | ------------------------------------------------------------ | ------------------ |
| name: String                   | field        | private        | Stores Vertex **name**                                       | -                  |
| lat: double                    | field        | private        | stores the **latitude** of the vertex                        | -                  |
| lon: double                    | filed        | private        | stores the **longitude** of the vertex                       | -                  |
| latY: double                   | field        | private        | used to store the y-axis coordinate for java graphics        | -                  |
| lonX: double                   | field        | private        | used to store the x-axis coordinate for java graphics        | -                  |
| distTo: double                 | field        | private        | used to store the distance from a starting point to Vertex **V**. Originally set to + Infinity | -                  |
| Vertex(String, double, double) | constructor  | public         | construct a Vertex object, sets the name, **lat, latY, lon, lonX** to the given two double values. | O(1)               |
| name(): string                 | method       | public         | returns the name of Vertex **V**                             | O(1)               |
| lat(): double                  | method       | public         | returns **lat** of Vertex **V**                              | O(1)               |
| lon(): double                  | method       | public         | returns **lon** of Vertex **V**                              | O(1)               |
| latY(): double                 | method       | public         | returns **latY** of Vertex **V**                             | O(1)               |
| setLatY(double)                | mehtod       | public         | sets **latY** to a new value                                 | O(1)               |
| lonX(): double                 | method       | public         | returns **lonX** of Vertex **V**                             | O(1)               |
| setLonX(double)                | mehtod       | public         | sets **lonX** to a new value                                 | O(1)               |
| distTo(): double               | method       | public         | returns **distTo** of Vertex **V**                           | O(1)               |
| setDistTo(double)              | method       | public         | sets **distTo** to a new value                               | O(1)               |
| compareTo(): double            | method       | public         | compares **this** Vertex to **that** Vertex. returns +1 if **this> that**, -1 if **this<that**, 0 if **this == that** | O(1)               |



#### Edge (Road)

| Name                         | Field/Method | Private/Public | Description                                                  | Runtime Complexity |
| ---------------------------- | ------------ | -------------- | ------------------------------------------------------------ | ------------------ |
| name: String                 | field        | private        | stores Edge E **name**                                       | -                  |
| from: Vertex                 | filed        | private        | stores a pointer to Vertex where the Edge starts             | -                  |
| to: Vertex                   | field        | private        | stores a pointer to Vertex where the Edge ends               | -                  |
| distance: double             | field        | private        | stores the distance from the starting Vertex to the ending one | -                  |
| R: double                    | field        | private        | constant value used in calcDistance() as Earth's radius in miles | -                  |
| Edge(String, Vertex, Vertex) | constructor  | public         | Constructs Edge E with name, pointer to Vertex from, pointer to Vertex to | O(1)               |
| calcDistance(): double       | method       | private        | calculates the distance from (starting point) Vertex **from** to Vertex **to** (ending point) using haversine formula | O(1)               |
| distance(): double           | method       | public         | returns the distance from the starting point to the ending point | O(1)               |
| name(): String               | method       | public         | returns the name of Edge **E**                               | O(1)               |
| from(): Vertex               | method       | public         | returns a pointer to Vertex **from** (starting point)        | O(1)               |
| to(): Vertex                 | method       | public         | returns a pointer to Vertex **to** (ending point)            | O(1)               |
| toString(): String           | method       | public         | returns a formatted String with Edge **E** information       | O(1)               |



#### ShortestPath

This class implements Dijkstra's Algorithm to find the shortest path

| Name                                    | Field/Method | Private/Public | Description                                                  | Runtime Complexity |
| --------------------------------------- | ------------ | -------------- | ------------------------------------------------------------ | ------------------ |
| edgeTo: ConcurrentHashMap<Vertex, Edge> | field        | priavte        | Given a certain path **P** of V1, V2, ... Vn vertices. For any given Vertex Vk, edgeTo keeps a pointer to Edge Ek from **Vk-1** to **Vk** | -                  |
| pq: PriorityQueue<Vertex>               | field        | private        | Given a vertex **S**, **pq** arranges {V1...Vn} based on the distance from **S** to each Vi ∈ {V1...Vn} | -                  |
| reachableVertices: LinkedList<Vertex>   | field        | private        | list of all reachable vertices ({V1, .. , Vk} ⊆ {V1, ... , Vm}) from a given vertex **S** | -                  |
| ShortestPath(Graph, Vertex)             | Constructor  | public         | sets the source Vertex **S**. adds all vertices to **pq**. Calls relax() for all vertices in **pq**. | O(V)               |
| realx(Graph, Vertex)                    | method       | private        | Finds the shortest path from vertex **V** to Vertex **W** (Dijkstra's algorithm) | O(ElogV)           |
| distTo(Vertex): double                  | method       | public         | Returns the distance from s to Vertex V if V is reachable from S | O(1)               |
| hasPathTo(Vertex): boolean              | method       | public         | Given Vertex **V**, it returns true if **V** is reachable from **S**, false otherwise. | O(1)               |
| pathTo(Vertex): ArrayList<Edge>         | method       | public         | returns all Edges {E1, E2, ...., En} needed to go from **S** to Vertex **V** in reverse order. | O(n)               |
| reachableVertices(): LinkedList<Vertex> | method       | public         | returns list of all reachable vertices ({V1, .. , Vk} ⊆ {V1, ... , Vm}) from a given vertex **S** | O(1)               |



#### View

| Name                          | Field/Method | Private/Public | Description                                                  | Runtime Complexity |
| ----------------------------- | ------------ | -------------- | ------------------------------------------------------------ | ------------------ |
| shortestPath: ArrayList<Edge> | field        | private        | stores all edges {E1,...,En} that represents a path from Vertex **S** to **V** | -                  |
| map: Graph                    | field        | private        | pointer to Graph **G** that stores all vertices and edges    | -                  |
| westMost: double              | field        | private        | stores the value of west-most longitude. Used for plotting the map in java graphics. | -                  |
| southMost: double             | field        | private        | stores the value of south-most latitude. Used for plotting the map in java graphics. | -                  |
| eastMost: double              | field        | private        | stores the value of east-most longitude. Used for plotting the map in java graphics. | -                  |
| northMost: double             | field        | private        | stores the value of north-most latitude. Used for plotting the map in java graphics. | -                  |
| View(ArrayList<Edge>, Graph)  | constructor  | public         | sets the values of **shortestPath** and **map**              | O(1)               |
| paintComponent(Graphics)      | method       | public         | plots all edges ∈ **G** as well **shortestPath**             | O(E)               |



#### StreetMap

| Name                          | Field/Method | Private/Public | Description                                                  | Runtime Complexity |
| ----------------------------- | ------------ | -------------- | ------------------------------------------------------------ | ------------------ |
| map: Graph                    | field        | private        | a pointer to a Graph that represents that map                | -                  |
| allPaths: ShortestPath        | field        | private        | a pointer to ShortestPath object that stores all paths from Vertex **S** to all reachable vertices | -                  |
| shortestPath: ArrayList<Edge> | field        | private        | stores all edges {E1,...,En} that represents a path from Vertex **S** to **V** | -                  |
| scanner: Scanner              | field        | private        | used as internal helper to read user's input                 | -                  |
| distToEndPoint: double        | field        | private        | stores the distance from Vertex **S** (starting point) to Vertex **V** (destination point) | -                  |
| StreetMap(String)             | constructor  | public         | takes filename as input, creates a new Graph and assigns it to **map**. | O(1)               |
| goTo(String, String)          | method       | public         | creates a new object of ShortestPath given a starting point and end point. Assigned object to **allPaths** | O(1)               |
| printDirections()             | method       | public         | prints the directions (path needs to be taken) from Vertex **S** to Vertex **V** using Edges {E1,...,En} that connect **S** to **V** | O(n)               |
| renderMap()                   | method       | public         | creates a new **View** object to plot the map using Java graphics | O(1)               |



### Expected Total Runtime

The expected total runtime will be given using the worst case scenario. In this case, using nys.txt (New York State) as the input map. There are three test cases depending on the three different possible commands:

1. `--show` ≤ 10 seconds 

2. `--directions` from *i0* (almost east-most point) to *i50236* (west-most) -- worst case ≤  35 seconds 
3. `--show --directions` for the second case -- worst case ≤  35 seconds



### Test Cases

We tried our program by running test cases for each map using different inputs. We wanted to see if the our program is returning the expected output or not. Below are our test cases which all passed. 

1. ur.txt

   | From           | To          | Expected: hasPathTo() output | Program's hasPathTo() output |
   | -------------- | ----------- | ---------------------------- | ---------------------------- |
   | GILBERT-COMMON | CROSBY      | true                         | true                         |
   | LOVEJOY        | ITS         | true                         | true                         |
   | LOVEJOY        | DEWEY       | true                         | true                         |
   | LOVEJOY        | SIMON       | true                         | true                         |
   | GAVETT         | SUEB        | true                         | true                         |
   | LECHASE        | OBRIEN      | true                         | true                         |
   | HOPEMAN        | HUTCH-LOWER | true                         | true                         |
   | RUSH-RHEES     | HOYT        | true                         | true                         |
   | SAGE           | HYLAN       | true                         | true                         |
   | UHS            | SUEB        | true                         | true                         |
   | CSB            | HUTCH-UPPER | true                         | true                         |
   | TUNNEL-MOREY   | ITS         | true                         | true                         |

2. monroe.txt

   | From   | To     | Expected: hasPathTo() output | Program's hasPathTo() output |
   | ------ | ------ | ---------------------------- | ---------------------------- |
   | i0     | i14    | false                        | false                        |
   | i0     | i5     | true                         | true                         |
   | i118   | i36    | true                         | true                         |
   | i20    | i39    | true                         | true                         |
   | i130   | i1687  | true                         | true                         |
   | i1712  | i2454  | true                         | true                         |
   | i5086  | i8108  | false                        | false                        |
   | i8198  | i8269  | true                         | true                         |
   | i58930 | i13    | true                         | true                         |
   | i236   | i698   | true                         | true                         |
   | i67017 | i19643 | false                        | false                        |
   | i19702 | i5     | false                        | false                        |

3. nys.txt

   | From   | To     | Expected: hasPathTo() output | Program's hasPathTo() output |
   | ------ | ------ | ---------------------------- | ---------------------------- |
   | i0     | i97    | true                         | true                         |
   | i102   | i128   | true                         | true                         |
   | i46    | i674   | true                         | true                         |
   | i715   | i26659 | true                         | true                         |
   | i26698 | i27100 | true                         | true                         |
   | i27313 | i32165 | true                         | true                         |
   | i32066 | i21640 | true                         | true                         |
   | i21713 | i15443 | true                         | true                         |
   | i15572 | i12555 | true                         | true                         |
   | i13    | i32164 | true                         | true                         |
   | i32154 | i32000 | true                         | true                         |
   | i31318 | i5515  | true                         | true                         |
