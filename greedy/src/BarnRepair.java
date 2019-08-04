import java.lang.reflect.Array;
import java.util.Arrays;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        // skip fencing on longest empty sections
        // so store beginnings of gaps as well as max_gap for iteration
        //and store in an array all starts of gaps so no repeats
        Arrays.sort(occupied);
        int totalGapLength = 0;
        if(M > occupied.length - 1){
            M = occupied.length;
        }
        int[] gapStarts = new int[M -1];
        Arrays.fill(gapStarts, -1);
        for(int i = 0; i < M - 1; i++){
            int longestGap = -1;
            int start = -1;
            for(int j = 0; j < occupied.length - 1; j++){
                if(occupied[j + 1] - occupied[j] > longestGap && !contains(gapStarts, j)){
                    start = j;
                    longestGap = occupied[j + 1] - occupied[j];
                }
            }
            //because this length is exclusive -1
            totalGapLength += longestGap - 1;
            gapStarts[i] = start;
        }
        int totalLength = occupied[occupied.length - 1] - occupied[0] + 1;
        //because this length is inclusive +1
        return totalLength - totalGapLength;
    }

    private static boolean contains(int[] a, int i){
        for(int n : a){
            if(i == n) return true;
        }
        return false;
    }
}

