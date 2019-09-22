package NQueenCSP;

import java.util.HashSet;

public class NQueenDomain {
    public static HashSet<Comparable> boardPairsDomain(int n){
        HashSet<Comparable> values = new HashSet<>();

        for (int i = 0; i<n; i++){
            for (int j = 0; j<n; j++){
                values.add(new Pair(i,j));
            }
        }
        
        return values;
    }
}
