/*
如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，
第二个序列是因为它的最后一个差值为零。
给定一个整数序列，返回作为摆动序列的最长子序列的长度。 
通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。

进阶:
你能否用 O(n) 时间复杂度完成此题?

题解：
	up[i] 表示 nums[0:i] 中最后两个数字递增的最长摆动序列长度，
	down[i] 表示 nums[0:i] 中最后两个数字递减的最长摆动序列长度，只有一个数字时默认为 1。

	当nums[i+1] > nums[i]时，up[i]一定是从down[i]+1才可以保证是摆动序列；down[i]同理
	当nums[i+1] < nums[i]，类似第一种情况
	当nums[i+1] == nums[i]，新的元素不能用于任何序列，保持不变
	
	参考：https://leetcode-cn.com/problems/wiggle-subsequence/solution/tan-xin-si-lu-qing-xi-er-zheng-que-de-ti-jie-by-lg/
 */
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int up = 1;
        int down = 1;

        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } 
            else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }

        return nums.length == 0 ? 0 : Math.max(up, down);
    }
}