/*
给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 
我们就将 (i, j) 称作一个重要翻转对。
你需要返回给定数组中的重要翻转对的数量。

注意:
    给定数组的长度不会超过50000。
    输入数组中的所有数字都在32位整数的表示范围内。

题解：
	运用归并排序，该题与求逆序对思路一样。
	在归并排序进行再合并之前进行统计满足题意的个数
	即若nums[i]>nums[j]（i<=mid & j <=right）
		则个数 += mid-i+1，然后后移j。否则后移i
 */

class Solution {
    public int reversePairs(int[] nums) {
        int[] count = new int[1];
        int len = nums.length;
        mergeSort(nums, 0, len - 1, new int[len], count);
        return count[0];
    }

    public void mergeSort(int[] nums, int left, int right, int[] temp, int[] count) {
        if (left >= right) {
            return ;
        }

        int mid = left + ((right - left) >> 1);
        int i = left;
        int j = mid + 1;

        // 排序左右序列
        mergeSort(nums, i, mid, temp, count);
        mergeSort(nums, mid + 1, right, temp, count);

        // 根据排序好的两个数组统计重要翻转对
        while (i <= mid && j <= right) {
            if ((long) nums[i] > (long) nums[j] * 2) {
                count[0] += mid - i + 1;
                j ++;
            } else {
                i ++;
            }
        }

        // 合并数组
        i = left;
        j = mid + 1;
        int index = left;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                temp[index ++] = nums[i ++];
            } else {
                temp[index ++] = nums[j ++];
            }
        }
        while (i <= mid) temp[index ++] = nums[i ++];
        while (j <= right) temp[index ++] = nums[j ++];
        
        for (i = left; i <= right; i ++) {
            nums[i] = temp[i];
        }
    }
}