public class Haversine {
    // code is used from https://rosettacode.org/wiki/Haversine_formula#Java
    // or should we use the default of 6372.8 km
    public static final double R = 3958.756; // In kilometers
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        System.out.println("lat " + lat1);
        System.out.println("D " + (lat2 - lat1));
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
    public static void main(String[] args) {
        System.out.println(R);
        System.out.println(haversine(43.131092,  -77.636058, 43.130996,  -77.646593));

    }
}