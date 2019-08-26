public class LongestIncreasingSubsequence {

    // Runtime: O(N^2)
    // Space: O(N)
    public static int LIS(int[] A) {
        int[] DP = new int[A.length];
        int max = 0;
        if(DP.length == 0) return 0;
        //if there is none there is 0, if there is 1 there is 1
        DP[0] = 1;
        for(int i = 1; i < A.length; i++){
            int count = 1; //minimum length of subsequence
            for(int j = 0; j < i; j++){
                if(A[j] < A[i]){
                    if(DP[j] + 1> count) count = DP[j] + 1;
                }
            }
            DP[i] = count;
            if(count > max) max = count;
        }
        return max;
    }
}