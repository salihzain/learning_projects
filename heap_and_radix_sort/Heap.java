/*
HeapSort is based on the implementation of "A Practical Introduction to Data
        Structures and Algorithm Analysis, 3rd Edition (Java)"
        by Clifford A. Shaffer
        Copyright 2008-2011 by Clifford A. Shaffer
        the code has been modified to make it simpler. I.e. not generic
 */
public class Heap {
    //based on the overall ideas of the book
    //return if a node is a lear
    private static boolean isLeaf(int pos, int n){
        return (pos>= n/2) && (pos<n);
    }

    //return position for the left child
    /** @return Position for left child of pos */
    private static int leftchild(int pos, int n) {
        assert pos < n/2 : "Position has no left child";
        return 2*pos + 1;
    }

    /** @return Position for right child of pos */
    private static int rightchild(int pos, int n) {
        assert pos < (n-1)/2 : "Position has no right child";
        return 2*pos + 2;
    }

    /** @return Position for parent */
    private static int parent(int pos) {
        assert pos > 0 : "Position has no parent";
        return (pos-1)/2;
    }
    public static void heapify(int[] list){
        int length = list.length;
        for (int i=length/2; i>=0; i--){
            siftdown(list, i, list.length);
        }
     }
    private static void siftdown(int[] list, int pos, int n) {
        assert (pos >= 0) && (pos < n) : "Illegal heap position";
        while (!isLeaf(pos, n)) {
            int j = leftchild(pos, n);
            if ((j<(n-1)) && (list[j]<list[j+1]))
                j++; // j is now index of child with greater value
            if (list[pos]>=list[j]) return;
            swap(list, pos, j);
            pos = j;  // Move down
        }
    }
     private static void swap(int[] list, int i, int j){
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
     }

     private static void removeMax(int[] list, int n){
        if (n == 1){
            if (list[0]>list[2]){
                swap(list, 0, 2);
            }
            swap(list, 0, n);
            return;
        }
        swap(list, 0, n);
        siftdown(list, 0, n-1);
     }

     private static void prinArray(int[] list){
        for (int s : list){
            System.out.print(s+" ");
        }
         System.out.println();
     }

     public static void heapsort(int[] list){
        heapify(list);
        for (int i = 1; i<list.length; i++){
            removeMax(list, list.length-i);
        }
     }
}
