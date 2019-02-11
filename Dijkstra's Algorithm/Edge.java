/*
some ideas in this class were inspired by the Graph implementation found in:
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 */

public class Edge {
    private final String name;
    private final Vertex from;
    private final Vertex to;
    private final double distance;
    private  final double R = 3958.756; // In miles

    public Edge(String name, Vertex from, Vertex to){
        this.name = name;
        this.from = from;
        this.to = to;
        this.distance = calcDistance();
    }

    // This method was found on https://rosettacode.org/wiki/Haversine_formula#Java
    private double calcDistance() {
        double lat1 = from.lat();
        double lon1 = from.lon();
        double lat2 = to.lat();
        double lon2 = to.lon();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

    public double distance(){ return distance; }

    public String name(){ return name; }

    public Vertex from() { return from; }

    public Vertex to(){ return to; }

    public String toString(){ return "Go from " + this.from.name() + " to " + this.to.name() + " through " + this.name; }

}
