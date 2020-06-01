package leetcode.binary_search_items;

/**
 *  题意：
 *      把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 *      输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 *      例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *  思路：
 *      利用二分。定义下标i，j分别表示该数组左右边界，其中mid=（i+j）/2（向下整除），则i<=mid<j
 *      找旋转数组的最小元素即为寻找 右排序数组 的首个元素 numbers[x] ，称 x 为旋转点。
 *      （以下用下标i替代number[i]的形式）保证x始终位于[i,j]中
 *          若mid < j，说明此时mid在右排序数组中，则旋转点在区间[i,mid]，所以j = mid
 *          若mid > j，说明此时mid在左排序数组中，则旋转点在区间[mid+1,j]，所以i = mid+1
 *          若mid = j，无法确定mid具体位置，此时需要缩小查询区间，只要保证旋转点位于[i,j]即可:
 *              若mid在右排序区间里，又其值相等但下标mid恒小于j（此时不是代指number[j]等），j=j-1仅仅是去掉重复元素
 *              若mid在左排序区间里，又因为递增的原因，可以直接返回，也可以j = j-1缩小查询范围
 *              综上，此时可以统一 j = j-1
 *          当 i >= j 时退出循环
 *
 */
class Rotate_the_smallest_number_of_an_array1 {
    public int minArray(int[] numbers) {
        if(numbers == null){
            return -1;
        }
        int i = 0;
        int j = numbers.length - 1;
        while(i < j){
            int mid = (i + j) >> 1;
            if(numbers[mid] < numbers[j]){
                j = mid;
            }else if(numbers[mid] > numbers[j]){
                i = mid + 1;
            }else{
                j = j - 1;
            }
        }
        return numbers[i];
    }
}
public class Rotate_the_smallest_number_of_an_array {
}
