import java.util.Arrays;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        int front = 0;
        int back = nums.length - 1;
        while(front < back){
            int mid = (front + back) / 2;
            if(peakOneD(front,nums) == 0){
                return front;
            }
            if(peakOneD(back, nums) == 0){
                return back;
            }
            if(peakOneD(mid, nums) == 0){
                return mid;
            }
            //if there is a mismatch in directions on the side, a center point is contained
            if(peakOneD(mid, nums) != peakOneD(front, nums)){
                back = mid;
            }
            else{
                front = mid;
            }

        }
        return 0;
    }

    public static int[] findTwoDPeak(int[][] nums) {
        int yfront = 0;
        int yback = nums.length - 1;
        int xfmax = maxXIndex(yfront, 0, nums.length, nums);
        int xbmax = maxXIndex(yback, 0, nums.length, nums);
        int ymid;
        int xmmax;

        while(yfront <= yback){

            ymid = (yfront + yback) / 2;
            xmmax = maxXIndex(ymid, 0, nums.length, nums);
            int pyf = peakY(xfmax,yfront, nums);
            int pyb = peakY(xbmax,yback,  nums);
            int pym = peakY(xmmax,ymid,  nums);
            if(pyf== 0){
                return new int[] {yfront, xfmax};
            }
            if(pyb == 0){
                return new int[] {yback, xbmax};
            }
            if(pyf != pym){
                yback = ymid;
                xbmax = maxXIndex(yback, 0, nums.length, nums);
            } else {
                yfront = ymid;
                xfmax = maxXIndex(yfront, 0, nums.length, nums);
            }

        }
        System.out.println("oops");
        return null;
    }

}


