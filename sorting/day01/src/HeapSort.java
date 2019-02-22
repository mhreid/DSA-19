import java.util.Arrays;

public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        while(leftChild(i) < size && ((right < size && heap[right] > heap[i]) || heap[left] > heap[i])){
            if(right < size && heap[right] > heap[left]){
                int temp = heap[i];
                heap[i] = heap[right];
                heap[right] = temp;
                i = right;

            } else {
                int temp = heap[i];
                heap[i] = heap[left];
                heap[left] = temp;
                i = left;
            }
            left = leftChild(i);
            right = rightChild(i);
        }

    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime: O(nlgn)
     * Worst-case runtime: O(nlgn)
     * Average-case runtime: O(nlgn)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);
        //System.out.println(size);

        for (int i=size-1; i>0; i--) {
            int temp = array[i];
            array[i] = array[0];
            //System.out.print(array[0]);
            array[0] = temp;
            size --;
            sink(0);
        }
        return heap;
    }
}
