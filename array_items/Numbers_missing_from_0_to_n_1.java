package leetcode.array_items;

/**
 *  题意：
 *      一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 *      在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *      1 <= 数组长度 <= 10000
 *  思路1：
 *      从第一个位置开始，可以每次根据数的值来与位置下标匹配，若相等则下一位；
 *      否则与当前数对应位置的数交换重新匹配（指针不后移），若匹配位置小于当前位置或超过数组大小，则该位置即为所缺失的数
 *      注意都不满足时返回数组大小
 *  思路2：
 *      （推荐）由于是递增排序数组，可以考虑二分法。
 *      当中间元素值与位置一样时表示前面没有缺失，搜索下一段数组；
 *      否则搜索前面的一段数组
 */
class Numbers_missing_from_0_to_n_12 {
    public int missingNumber(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while(i <= j){
            int mid = (i + j) >> 1;
            if(mid == nums[mid]){
                i = mid + 1;
            }else{
                j = mid - 1;
            }
        }
        return i;
    }
}
class Numbers_missing_from_0_to_n_11 {
    public int missingNumber(int[] nums) {
        for(int i=0; i<nums.length; i++){
            int k = nums[i];
            if(k == i){
                continue;
            }
            if(k < i || k >= nums.length){
                return i;
            }

            nums[i] = nums[k];
            nums[k] = k;
            i --;
        }
        return nums.length;
    }
}
public class Numbers_missing_from_0_to_n_1 {
}
