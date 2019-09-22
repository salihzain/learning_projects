package AustraliaCSP;

import java.util.HashSet;

public class AustraliaDomain {
    public static HashSet<Comparable> rgbDomain(){
        HashSet<Comparable> values = new HashSet<>();

        values.add("RED");
        values.add("BLUE");
        values.add("GREEN");
        
        return values;
    }
}
