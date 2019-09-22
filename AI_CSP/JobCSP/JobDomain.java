package JobCSP;

import java.util.HashSet;

public class JobDomain {
    public static HashSet<Comparable> timeBlockDomain(int maxTime){
        HashSet<Comparable> values = new HashSet<>();

        for (int i = 1; i<=maxTime; i++){
            values.add(i);
        }

        return values;
    }
}
