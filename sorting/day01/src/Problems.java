import java.util.*;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        System.out.println(Arrays.toString(out));
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        PriorityQueue<Integer> top = minPQ();
        PriorityQueue<Integer> bottom = maxPQ();
        double[] runningMedian = new double[inputStream.length];
        if(inputStream.length > 0){
            bottom.offer(inputStream[0]);
            runningMedian[0] = inputStream[0];
        }
        for(int i = 1; i < runningMedian.length; i++){
            top.offer(inputStream[i]);
            top.offer(bottom.poll());
            bottom.offer(top.poll());
            if(i % 2 == 1){
                int temp = bottom.peek();
                bottom.offer(top.poll());
                runningMedian[i] = (1.0 * temp + bottom.peek()) / 2;
            } else {
                runningMedian[i] = bottom.peek();
            }
        }
        return runningMedian;
    }

}
