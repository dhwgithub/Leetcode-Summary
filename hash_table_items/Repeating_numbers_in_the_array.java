package leetcode.hash_table_items;

/**
 *  题意：
 *      找出数组中重复的数字。
 *      在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 *      数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *      2 <= n <= 100000
 *  思路：
 *      在不重复的情况下，每个下标对应的数即为下标值。
 *      因此可以让下标和值一一对应，若出现下标一样的即为重复的数
 *      当依次遍历时，当前位置对应的数应大于等于下标，因为之前的都匹配了。若不符合即为重复的数
 *
 */
class Repeating_numbers_in_the_array1 {
    public int findRepeatNumber(int[] nums) {
        for(int i=0; i<nums.length; i++){
            if(nums[i] < i){
                return nums[i];
            }
            if(nums[i] == i){
                continue;
            }else{
                if(nums[i] == nums[nums[i]]){
                    return nums[i];
                }
                int t = nums[i];
                int tt = nums[nums[i]];
                nums[nums[i]] = t;
                nums[i] = tt;
                i --;
            }
        }
        return -1;
    }
}

public class Repeating_numbers_in_the_array {
}
