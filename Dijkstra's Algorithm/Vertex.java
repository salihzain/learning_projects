/*
some ideas in this class were inspired by the Graph implementation found in:
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 */

public class Vertex implements Comparable<Vertex> {
    private final String name;
    private final double lat;
    private final double lon;
    private double lonX;
    private double latY;
    private double distTo = Double.POSITIVE_INFINITY;

    public Vertex(String name, double lat, double lon){
        this.name = name;
        this.lat = lat;
        this.latY = lat;
        this.lon = lon;
        this.lonX = lon;
    }

    public String name(){ return name; }

    public double lat(){ return lat; }

    public double lon(){ return lon; }

    public double latY(){ return latY; }

    public void setLatY(double latY){ this.latY = latY; }

    public double lonX(){ return lonX; }

    public void setLonX(double lonx){ this.lonX = lonx; }

    public void setDistTo(double distTo){ this.distTo = distTo; }

    public double distTo(){ return distTo; }

    public int compareTo(Vertex that){
        if (this.distTo<that.distTo) return -1;
        else if (this.distTo>that.distTo) return +1;
        else return 0; // if they're equal
    }

}
