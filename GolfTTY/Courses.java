import java.util.Random; 


public class Courses {
    //Genesee valley park course
    private int GVP[][] = {{1,530,5}, {2,305,4},{3,331,4},
                    {4,201,3}, {5,500,5}, {6,226,3},
                    {7,409,4}, {8,410,4}, {9,229,3}, 
                    {10,433,4}, {11,363,4}, {12,174,3}, 
                    {13,545,5}, {14,419,4}, {15,512,5},
                    {16,410,4}, {17,320,4}, {18,170,3}};
    
    //St andrew old course
    private int STA[][] = {{1,376,4}, {2,453,4},{3,397,4},
                    {4,480,4}, {5,568,5}, {6,412,4},
                    {7,371,4}, {8,175,3}, {9,352,4}, 
                    {10,386,4}, {11,174,3}, {12,348,4}, 
                    {13,465,4}, {14,618,5}, {15,455,4},
                    {16,423,4}, {17,495,4}, {18,357,4}}; 
                    
    
    //setters and getts for the arrays. 
    public int[][] getGVP() {
        return GVP;
    }

    public void setGVP(int[][] GVP) {
        this.GVP = GVP;
    }

    public int[][] getSTA() {
        return STA;
    }

    public void setSTA(int[][] STA) {
        this.STA = STA;
    }

  

    }