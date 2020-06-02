package leetcode.divide_and_conquer_items;

/**
 *  题意：
 *      给定一个整数数组，找出总和最大的连续数列，并返回总和。
 *  思路：
 *      从头遍历求和，当和为负数时，重新从下一位开始遍历。保存每次结果的最大值
 *
 */
class Continuous_sequence1 {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int ans = nums[0];
        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            ans = Math.max(ans, sum);
            if(sum < 0){
                sum = 0;
            }
        }
        return ans;
    }
}

public class Continuous_sequence {
}
