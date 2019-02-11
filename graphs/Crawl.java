
import java.util.regex.Matcher;
import  java.util.regex.Pattern;
import java.util.LinkedList;

public class Crawl {

    public Crawl(String root){
        LinkedList<String> queue = new LinkedList<>();
        LinkedList<String> discovered = new LinkedList<>();

        queue.addLast(root);
        discovered.add(root);

        while (!queue.isEmpty()){
            String v = queue.poll();
            System.out.println(v);
            In in = new In(v);
            String input = in.readAll();

            String regexp = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()){
                String w = matcher.group();
                if (!discovered.contains(w)){
                    discovered.add(w);
                    queue.addLast(w);
                }
            }
        }


    }

    public static void main(String[] args){
        Crawl c = new Crawl("https://rochester.edu");
    }


}
