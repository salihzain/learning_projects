Given is a undirected graph and two of its vertices s and t. Give an O(n + m) algorithm that computes
the number of shortest paths from s to t.

input:
|V| |E|

the remaining represent edges going from `vertex s` to `vertex t`

output:
number of shortest paths

Classes used:
In: helped library by princeton to read input files without having to build that from scratch.
Graph: Graph simple implementation using princeton implementation with a bif of modification. Also to avoid building it from scratch.
Graph uses indexing that starts at 0 to |V|-1.
ShortestPaths: This is where the magic happens. BFS in this class is the the modified implementation of princeton. however, everything else is mine.
s, t are given in the input file.
to select an input file, modify the input file name in the main method in ShortestPaths class.

To run the program: 

In the terminal:

`javac *.java`

`java ShortestPaths`



