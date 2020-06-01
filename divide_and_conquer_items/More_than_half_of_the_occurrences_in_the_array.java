package leetcode.divide_and_conquer_items;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 题目：
 *      数组中有一个数字出现的次数超过数组的一半，找出那个数字
 * 思路1：
 *      用HashMap统计
 * 思路2：
 *      排序法。排序后，所求的数字一定位于数组的中间。
 * 思路3：
 *      消减法（最优）。由于所求的数和其他数在个数上一一消减后，剩下的数一定是所求的数，因此可以用此方法简单快速的求解。
 */
class More_than_half_of_the_occurrences_in_the_array1 {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> num = new HashMap<Integer, Integer>();
        int k = (nums.length + 1) / 2;

        for(int i=0; i<nums.length; i++){
            if(num.containsKey(nums[i])){
                int sum = num.get(nums[i]) + 1;
                num.put(nums[i], sum);
                if(sum >= k){
                    return nums[i];
                }
            }else{
                num.put(nums[i], 1);
            }
        }
        return nums[0];  // 只有一个元素的时候
    }
}

class More_than_half_of_the_occurrences_in_the_array2 {
    public int majorityElement(int[] nums) {
        int k = nums.length / 2;
        Arrays.sort(nums, 0, nums.length);
        return nums[k];
    }
}

class More_than_half_of_the_occurrences_in_the_array3 {
    public int majorityElement(int[] nums) {
        int ans = -1;  // 初始化答案
        int cnt = 0;  // 0表示消减平衡
        for(int num : nums){
            if(cnt == 0){  // 消减平衡后，当前数为此时答案
                ans = num;
            }
            if(ans == num){  // 与上一个数相等时，加剧不平衡
                cnt += 1;
            }else{  // 与上一个数不相等时进行消减
                cnt -= 1;
            }
        }
        return ans;
    }
}

public class More_than_half_of_the_occurrences_in_the_array {
}
