import java.util.LinkedList;

public class GFG {
    //based on geeks for geeks

     static class Graph{
        int numOfV;
        LinkedList<Integer>[] adjListArray;

        //Constructor
         Graph(int numOfV){
             this.numOfV = numOfV;
             //define
             adjListArray = new LinkedList[numOfV];

             //for every vertex, create a new linkedList
             for (int i = 0; i<numOfV; i++){
                 adjListArray[i] = new LinkedList<>();
             }
         }
    }

    //adds edge to undirected graph
    static void addEdge(Graph graph, int src, int dest){
         //add edge from src to dest
        graph.adjListArray[src].add(dest);

        //since graph is undirected, add src to dest too
        graph.adjListArray[dest].add(src);
    }

    //utility func to print the adj list
    static void printGraph(Graph graph){
         for (int v = 0; v<graph.numOfV; v++){
             System.out.println("Adj list of vertex " + v);
             for (Integer connected : graph.adjListArray[v]){
                 System.out.println(" - > " + connected);
             }
             System.out.println();
         }
    }

    public static void main(String[] args){
         Graph graph = new Graph(5);
         /*
         0 --- 3 ---4
         |      |
         1 ---- 2
          */
         addEdge(graph,0, 3 );
         addEdge(graph, 0, 1);
         addEdge(graph, 1, 2);
         addEdge(graph,2,3);
         addEdge(graph,3,4);
         printGraph(graph);
    }
}

