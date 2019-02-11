import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class RadixSort {
    //the following is the traditional radix implementation that was discussed in lecture
    //simplified from Integer to int
    static int THRESHOLD = 4;
    static int ARRAYSIZE = 0;
    static int[] POW2 = {1, 2, 4, 8, 16, 32, 64, 128, 256};
    static Random rand = new Random();
    @SuppressWarnings("unchecked") // Generic array allocation
    static void oldRadix(int[] A) {
        assert THRESHOLD > 0 :
                "Usage: Sortmain [+/-] <size> <threshold>, " +
                        "MUST SET THRESHOLD AS NUMBER OF BITS IN RADIX";
        int[] temp = new int[A.length];
        int[] count = new int[POW2[THRESHOLD]];
        radix(A, temp, 16/THRESHOLD, POW2[THRESHOLD], count);
    }
    static void radix(int[] A, int[] B,
                      int k, int r, int[] count) {
        // Count[i] stores number of records in bin[i]
        int i, j, rtok;
        for (i=0, rtok=1; i<k; i++, rtok*=r) { // For k digits
            for (j=0; j<r; j++) count[j] = 0;    // Initialize count
            // Count the number of records for each bin on this pass
            for (j=0; j<A.length; j++) count[(A[j]/rtok)%r]++;
            // count[j] will be index in B for last slot of bin j.
            for (j=1; j<r; j++) count[j] = count[j-1] + count[j];
            // Put records into bins, working from bottom of bin
            // Since bins fill from bottom, j counts downwards
            for (j=A.length-1; j>=0; j--)
                B[--count[(A[j]/rtok)%r]] = A[j];
            for (j=0; j<A.length; j++) A[j] = B[j]; // Copy B back
        }
    }


    public static void RadixsortBitshifting(int[] arr) {
        //create two queues for storing 0s and 1s in bits
        Queue<Integer> zero = new LinkedList<>();
        Queue<Integer> one = new LinkedList<>();

        //call getMax method to get the max value of the array
        int max = getMax(arr);
        //equal to log2(max)+1
        int numbits = (int) (Math.log(max) / Math.log(2)) + 1;

        //loop for going through the bits
        for (int i = 0; i < numbits; i++) {
            int mask = 1 << i;//mask starting 1 , padding 0s
            for (int j = 0; j < arr.length; j++) {//loop through the array
                if ((arr[j] & mask) == 0) {
                    zero.add(arr[j]);
                } else {
                    one.add(arr[j]);
                }
            }
            //starting the index at 0
            int index = 0;
            //if 0s queue is not empty, we add the values to the arr and remove them from the queue
            while (!zero.isEmpty()) {
                arr[index++] = zero.remove();
            }
            //if 1s queue is not empty, we add the values to the arr and remove them from the queue
            while (!one.isEmpty()) {
                arr[index++] = one.remove();
            }
        }
    }


    //method for getting the max value of the array
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }



    private static void printArray(int[] list){
        for (int i : list){
            System.out.print(i + " ");
        }
        System.out.println();
    }


    /*
    These functions generate the five cases we need
    1- 10k ints reversed (worst case)
    2- 10k ints sorted (best case)
    3- 10k int randomly generated (average case)
    4- 100k ints reversed order (worst case)
    5- 100k ints sorted (best case)
    6- 100 ints randomly generated (average case)
     */

    private static int[] thousandReversed(){
        int[] temp = new int[1000];
        for (int i = 0; i<1000; i++){
            temp[i] = 1000-i;
        }
        return temp;
    }

    private static int[] thousandSorted(){
        int[] temp = new int[1000];
        for (int i = 0; i<1000; i++){
            temp[i] = i+1;
        }
        return temp;
    }

    private static int[] thousandRandom(){
        int[] temp = new int[1000];
        for (int i = 0; i<1000; i++){
            temp[i] = rand.nextInt(1000);
        }
        return temp;
    }

    private static int[] thousandKReversed(){
        int[] temp = new int[10000];
        for (int i = 0; i<10000; i++){
            temp[i] = 10000-i;
        }
        return temp;
    }

    private static int[] thousandKSorted(){
        int[] temp = new int[10000];
        for (int i = 0; i<10000; i++){
            temp[i] = i+1;
        }
        return temp;
    }

    private static int[] thousandKRandom(){
        int[] temp = new int[10000];
        for (int i = 0; i<10000; i++){
            temp[i] = rand.nextInt(10000);
        }
        return temp;
    }

    // methods to help calculate the run time

    /*
    the following class is inspired by similar implementation of
     @author Robert Sedgewick
      @author Kevin Wayne
      in http://algs4.cs.princeton.edu/14analysis
     */
    private static class stopWatch{
        long startTime;
        stopWatch(){
            startTime = System.currentTimeMillis();
        }
        double elapsedTime(){
            return (System.currentTimeMillis()-startTime)/1000.0;
        }
    }


    public static void main(String[] args){
        //case 1: 1000 ints in reversed order
        int[] case1 = thousandReversed();
        //case2: 1000 sorted
        int[] case2 = thousandSorted();
        //case 3: 1000 randomly generated
        int[] case3 = thousandRandom();
        //case 4: 10k ints reversed
        int[] case4 = thousandKReversed();
        //case 5: 10k sorted
        int[] case5 = thousandKSorted();
        //case 6: 10k randomly generated
        int[] case6 = thousandKRandom();

        stopWatch sWatch;

        /*
        all code below works, to test any case, just uncomment it :)
         */

        //case 1 using old radix
//        sWatch = new stopWatch();
//        oldRadix(case1);
//        System.out.println(sWatch.elapsedTime());
//        printArray(case1);

        //case 1 using new radix
//        sWatch = new stopWatch();
//        RadixsortBitshifting(case1);
//        System.out.println(sWatch.elapsedTime());
//        printArray(case1);

        //case 2 using old radix
//        sWatch = new stopWatch();
//        oldRadix(case2);
//        System.out.println(sWatch.elapsedTime());
//        printArray(case2);

        //case 2 using new radix
//        sWatch = new stopWatch();
//        RadixsortBitshifting(case2);
//        System.out.println(sWatch.elapsedTime());
//        printArray(case2);

        //case 3 using old radix
//        sWatch = new stopWatch();
//        oldRadix(case3);
//        System.out.println(sWatch.elapsedTime());
//        printArray(case3);

        //case 3 using new radix
//        sWatch = new stopWatch();
//        RadixsortBitshifting(case3);
//        System.out.println(sWatch.elapsedTime());
//        printArray(case3);

        //case 4 using old radix
//        sWatch = new stopWatch();
//        oldRadix(case4);
//        System.out.println(sWatch.elapsedTime());
//        printArray(case4);

        //case 4 using new radix
//        sWatch = new stopWatch();
//        RadixsortBitshifting(case4);
//        System.out.println(sWatch.elapsedTime());
//        printArray(case4);

        //case 5 using old radix
//        sWatch = new stopWatch();
//        oldRadix(case5);
//        System.out.println(sWatch.elapsedTime());
//        printArray(case5);

        //case 5 using new radix
//        sWatch = new stopWatch();
//        RadixsortBitshifting(case5);
//        System.out.println(sWatch.elapsedTime());
//        printArray(case5);


        //case 6 using old radix
//        sWatch = new stopWatch();
//        oldRadix(case6);
//        System.out.println(sWatch.elapsedTime());
//        printArray(case6);

        //case 6 using new radix
//        sWatch = new stopWatch();
//        RadixsortBitshifting(case6);
//        System.out.println(sWatch.elapsedTime());
//        printArray(case6);

    }
}
