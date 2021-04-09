/**
已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。

 

示例 1：

输入：nums = [1,3,5]
输出：1
示例 2：

输入：nums = [2,2,2,0,1]
输出：0
 

提示：

n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 

进阶：

这道题是 寻找旋转排序数组中的最小值 的延伸题目。
允许重复会影响算法的时间复杂度吗？会如何影响，为什么？

题解：
    由题意知旋转后的数组是两段非递减数组，其中一段（第一段）的所有值大于等于另一段数组（第二段），因此可用二分确定两数组分界点
    当中点位置 大于 二分左端点时，其一定位于第一段数组，此时 left = mid + 1
    当中点位置 小于 二分右端点时，其一定位于第二段数组，此时 right = mid
    其他情况即三者相等时，可令 right -= 1 且不会遗漏

    参考：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/154-find-minimum-in-rotated-sorted-array-ii-by-jyd/
*/
class Solution {
    public int findMin(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;

            if (nums[mid] > nums[i]) {
                i = mid + 1;
            }
            else if (nums[mid] < nums[j]) {
                j = mid;
            }
            else {
                j -= 1;
            }
            
        }

        return nums[i];
    }
}