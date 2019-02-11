import java.util.Random;
public class Shot{
    //clubs values based on the table in the assignment sheet. 
    private int clubs [][] = {{1,230,30}, {2,215,20}, {3,180,20}, {4,170,17}, {5,155,15},
    {6,145,15}, {7,135,15}, {8,125,15}, {9,110,10},{10,50,10}};

    //calculating the distance based on the idea presented by our professor 
    public  int nextDistance(int club, int power) {
        int mean = clubs[club][1]; 
        int stddev = clubs[club][2]; 
        double mean_adj = mean * power / 10.0;
        double stddev_adj = stddev * power / 10.0;
        Random rand = new Random();
        double val = Math.abs(rand.nextGaussian() * stddev_adj + mean_adj);
        return (int)val;
        }

}