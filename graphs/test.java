import java.util.concurrent.ConcurrentHashMap;

public class test {
    public static void main(String[] args){
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(1, 0);
        map.put(1, 1);
        System.out.println(map);
    }
}
