import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    /**
     * Best-case runtime: O(NlgN)
     * Worst-case runtime: O(N^2)
     * Average-case runtime: O(NlgN)
     *
     * Space-complexity: O(logN)
     */
    @Override
    public int[] sort(int[] array) {
        if(array.length >= -1){
            shuffleArray(array);
            quickSort(array,0,array.length - 1);
        } //in else should call insertion sort but didn't want to import
        return array;

    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {

        if (lo < hi) {
            int p = partition(a, lo, hi);
            quickSort(a, lo,  p - 1);
            quickSort(a, p + 1, hi);
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {
        int pivot = array[lo];
        int p1 = lo + 1;
        int p2 = hi;
        while(p1 <= p2){
            if(array[p1] <= pivot){
                p1 ++;
            }
            else if(array[p2] >= pivot){
                p2--;
            }
            else{
                swap(array ,p1 ,p2);
            }
        }
        int[] left = new int[p1 - lo - 1];
        p1--;
        System.arraycopy(array, lo + 1,left,0,p1 - lo);

        System.arraycopy(left,0,array,lo,p1 - lo);
        array[p1] = pivot;
        return p1 ;
    }

}
