
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= 10
     *
     * TODO
     * Best-case runtime: O(nlgn)
     * Worst-case runtime: O(nlgn)
     * Average-case runtime: O(nlgn)
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {
        int l = (int)Math.ceil((double)array.length / 2);
        int r = (int)Math.floor((double)array.length / 2);
        //split sizes

        if(array.length <= 1){

            return array;
        }

        int[] left = new int[l];
        int[] right = new int[r];
        System.arraycopy(array, 0, left, 0, l);
        System.arraycopy(array, l, right, 0,r);
        return merge(sort(left), sort(right));
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int[] out = new int[a.length + b.length];
        //pointers for a and b
        int pa = 0;
        int pb = 0;
        for(int i = 0; i < a.length + b.length; i++){
            if(pb >= b.length || pa < a.length && a[pa] < b[pb]){
                out[pa + pb] = a[pa];

                pa++;
            } else {

                out[pa + pb] = b[pb];
                pb++;
            }

        }

        return out;
    }

}
