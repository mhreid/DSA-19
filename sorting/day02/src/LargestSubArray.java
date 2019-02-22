
public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        // TODO
        int score = 0;
        int left = 0;
        int right = nums.length -1;
        int mid = nums.length / 2;
        int midscore = 0;
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] == 0){
                score++;
            } else {
                score--;
            }
            if(i == mid){
                midscore = score;
            }
        }
        while(score != 0){
            if(score > midscore){
                right--;
                if(nums[right + 1] == 0){
                    score--;
                } else {
                    score++;
                }
            } else {
        }

        return new int[]{0, 0};
    } return null;}
}

//01010111
