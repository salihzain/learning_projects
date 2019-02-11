import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class StreetMap {
    private Graph map;
    private ShortestPath allPaths;
    private ArrayList<Edge> shorestPath;
    private Scanner scanner;
    private double distToEndPoint;

    StreetMap(String fileName){
        In in = new In(fileName);
        scanner = new Scanner(System.in);
        map = new Graph(in);
    }

    public void goTo(String startAtPoint, String stopAtPoint){
        allPaths = new ShortestPath(map, map.vertices().get(startAtPoint));
        //just for extra credit
        while (!allPaths.hasPathTo(map.vertices().get(stopAtPoint))){
            System.out.println("There is no path to " + stopAtPoint);
            System.out.println("Would you like to see all the reachable points from " + startAtPoint + " ?");
            System.out.println("Enter yes or no");
            String decision = scanner.next();
            while (!decision.equals("yes")&&!decision.equals("no")){
                System.out.println("Invalid input, try again");
                decision = scanner.next();
            }
            if (decision.equals("yes")) {
                allPaths.reachableVertices().forEach(vertex -> System.out.println(vertex.name()));
            } else {
                System.out.println("Good bye!");
                System.exit(0);
            }
            System.out.println("Enter the name of the new destination");
            stopAtPoint = scanner.next();
        }

        shorestPath = allPaths.pathTo(map.vertices().get(stopAtPoint));
        distToEndPoint = allPaths.distTo(map.vertices().get(stopAtPoint));
    }

    public void renderMap(){
        JFrame frame = new JFrame();
        View view = new View(shorestPath, map);
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,850);
        frame.setVisible(true);
    }

    public void printDirections(){
        for (int i = shorestPath.size()-1; i>=0; i--){
            System.out.println(shorestPath.get(i).toString());
        }
        System.out.println("The total distance is " + distToEndPoint + " miles");
    }

    public static void main(String[] args){
        double startTime = System.currentTimeMillis();
        boolean directions = false;
        boolean showMap = false;
        String startAtPoint, stopAtPoint;
        StreetMap streetMap = new StreetMap(args[0]);

        for (String s: args){
            if (s.equals("--directions")) directions = true;
            if (s.equals("--show")) showMap = true;
        }

        if (directions){
            startAtPoint = args[args.length-2];
            stopAtPoint = args[args.length-1];
            streetMap.goTo(startAtPoint, stopAtPoint);
            streetMap.printDirections();
        }

        if (showMap){
            streetMap.renderMap();
        }

        double endTime = (System.currentTimeMillis()-startTime)/1000.0;
        System.out.println(endTime);


    }
}
