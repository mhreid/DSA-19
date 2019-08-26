public class DiceRollSum {

    // Runtime: O(N)
    // Space: O(N)
    public static int diceRollSum(int N) {
        int[] prev = new int[N + 1];
        if(N == 0){
            return 1;
        }
        prev[1] = 1;
        for(int i = 2; i <= N; i++){
                int count = 0;
                if(i <= 6){
                    count++;
                }
                int min = Math.max(1, i - 6);
                for(int j = min; j <= i - 1 ; j++){
                    count += prev[j];
                }
            prev[i] = count;
        }
        return prev[N];
    }
}
