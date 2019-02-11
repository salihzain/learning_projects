import java.util.Random;
public class Putting {
    //the powers based on the table in the assignment sheet. 
    int powers [][] = {{1,1,1},{2,2,1}, {3,4,2},{4,8,2},{5,12,3},{6,16,3},
                        {7,20,4}, {8,25,4},{9,30,5},{10,40,5}};

    public int[][] getPowers() {
        return powers;
    }

    public void setPowers(int[][] powers) {
        this.powers = powers;
    }

    //calculating the distance based ont the power selected by the player and its value in the above array. 
    public  int nextDistance(int power) {
                        int mean = powers[power][1];
                        int stddev = powers[power][2];
                        double mean_adj = mean * power / 10.0;
                        double stddev_adj = stddev * power / 10.0;
                        Random rand = new Random();
                        double val = Math.abs(rand.nextGaussian() * stddev_adj + mean_adj);
                        return (int)val;
                            }                      
}