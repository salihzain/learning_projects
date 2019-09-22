package NQueenCSP;

public class Pair implements Comparable{
    protected int i;
    protected int j;

    Pair(int i, int j){
        this.i = i;
        this.j = j;
    }

    //It doesn't matter in our case, we just need it to be treated like Comparable objects
    @Override
    public int compareTo(Object o) {
        Pair that = (Pair) o;

        if (this.i == that.i && this.j > that.j || this.i > that.i && this.j == that.j){
            return 1;
        } else if (this.i == that.i && this.j < that.j || this.i < that.i && this.j == that.j){
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Pair [i= " + this.i + " - j= " +this.j + "]";
    }
}
