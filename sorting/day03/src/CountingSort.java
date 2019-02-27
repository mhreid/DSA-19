public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: O(k + N)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int max = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] > max){
                max = A[i];
            }
        }
        int[] entries = new int[max + 1];
        for(int i = 0; i < A.length; i++){
            entries[A[i]]++;
        }
        int index = 0;
        for(int i = 0; i < max + 1; i++){
            for(int j = 0; j < entries[i]; j++){
                A[index] = i;
                index++;
            }
        }
    }

}
