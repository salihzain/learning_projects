import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class View extends JComponent implements ActionListener{
    private Timer timer;
    private ArrayList<Edge> shortestPath;
    private Graph map;
    private LinkedList<Edge> edgesToDraw;
    private double westMost = Double.POSITIVE_INFINITY;
    private double southMost = Double.POSITIVE_INFINITY;
    private double eastMost = Double.NEGATIVE_INFINITY;
    private double northMost = Double.NEGATIVE_INFINITY;

    private int i;
    public View(ArrayList<Edge> shortestPath, Graph map){
        this.shortestPath = shortestPath;
        this.map = map;
        edgesToDraw = new LinkedList<>();
        if (shortestPath!=null) i = shortestPath.size();
        timer = new Timer(1, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e){
        if (i>0) {repaint();}
        else timer.stop();
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        //The first thing we want to do is to scale the points correctly.
        map.vertices().forEach((n, v)->{
            if (v.lat()<southMost) southMost = v.lat();
            if (v.lon()<westMost) westMost = v.lon();
        });
        // now that we have found the southMost and the west most, we scale all vertices
        map.vertices().forEach((n, v)->{
            v.setLatY(v.lat() - Math.abs(southMost));
            v.setLonX(v.lon() + Math.abs(westMost));
        });
        //then we find the northMost and eastMost
        map.vertices().forEach((n, v)->{
            if (v.lonX() > eastMost) eastMost = v.lonX();
            if (v.latY() > northMost) northMost = v.latY();
        });
        //then we scale again to fill all the screen
        map.vertices().forEach((n, v)->{
            v.setLatY(v.latY() * (getHeight() / northMost));
            v.setLonX(v.lonX() * (getWidth() / eastMost));
        });



        g2.setColor(Color.BLACK);
        for (Edge e : map.edges())
            g2.drawLine((int) e.from().lonX(), (int) (getHeight() - e.from().latY()), (int) e.to().lonX(), (int) (getHeight() - e.to().latY()));


        //with animation
        i--;
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(5));
        Edge toDraw = shortestPath.get(i);
        edgesToDraw.add(toDraw);
        for (Edge e : edgesToDraw)
            g2.drawLine((int) e.from().lonX(), (int) (getHeight() - e.from().latY()), (int) e.to().lonX(), (int) (getHeight() - e.to().latY()));


//        //without animation
//        g2.setColor(Color.BLUE);
//        g2.setStroke(new BasicStroke(5));
//        if (shortestPath!=null){
//            for (Edge e : shortestPath) {
//                g2.drawLine((int) e.from().lonX(), (int) (getHeight() - e.from().latY()), (int) e.to().lonX(),
//                        (int) (getHeight() - e.to().latY()));
//            }
//        }

    }

    public void resetI(){
        if (shortestPath!=null) i = shortestPath.size();
    }

}
