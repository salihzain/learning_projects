public class HeapTest {
    private static void printArray(int[] list){
        for (int i : list){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        int[] arr = {5,18,3,25,27,45,97,88,26,16,49,67};
        Heap.heapify(arr);
        printArray(arr);
        int[] arr2 = {15,99,3,77,27,45,7,88,26,5};
        Heap.heapsort(arr2);
        printArray(arr2);
    }
}
