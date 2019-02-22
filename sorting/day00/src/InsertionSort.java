
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        // TODO
        for(int i = 0; i < array.length; i++){
            int p = i;
            while(p>0 && array[p-1] > array[p]){
                int temp = array[p];
                array[p] = array[p-1];
                array[p-1] = temp;
                p--;
            }
        }
        return array;
    }
}
