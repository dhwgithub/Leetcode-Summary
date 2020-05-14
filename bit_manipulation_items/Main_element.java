package leetcode.bit_manipulation_items;

/**
 * 题目：
 *      从给定数组中找到一个出现次数超过一半且唯一的数返回，否则返回-1
 * 思路：
 *      与找出现一半的数那道题类似，只不过当不唯一时返回-1。
 *      可以采用消减法，即出现最多的数与其他数一一抵消，剩下的数一定是出现最多的
 *      不过需要额外处理，若该数统计次数为0，表示不唯一，此时输出-1
 */
class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0;
        int ans = 0;
        for(int num : nums){
            if(cnt == 0){
                ans = num;
            }
            if(ans == num){
                cnt += 1;
            }else{
                cnt -= 1;
            }
        }
        if(cnt == 0){
            return -1;
        }else{
            return ans;
        }
    }
}
public class Main_element {
}
