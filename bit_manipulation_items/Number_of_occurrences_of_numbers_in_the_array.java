package leetcode.bit_manipulation_items;

/**
 *  题意：
 *      一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。
 *      要求时间复杂度是O(n)，空间复杂度是O(1)。
 *      2 <= nums.length <= 10000
 *  思路：
 *      当只有一个数字出现一次，其他都是两次时，我们可以全员异或来求得其值。
 *      现在有两个数字出现一次，可以采用相同的思路，将两个数字分组：
 *          相同的数字分到同一组
 *          不同的这两个数组分到不同的组
 *      然后对这两个组分别全员异或，这样就得到了这两个不同的数
 *
 *      而对于如何分组，首先可以全员异或，求得这两个数的异或结果
 *      对于这个结果，找到其二进制位中为1的那个位数，这个位数代表要求的这两个数二进制位不一样的位置
 *      通过对所有数这个二进制位是否是0分为两组，这样：
 *          相同的数当前二进制位数一定相同，可以被分到同一组
 *          不同的数（即所求的数）当前二进制位数一定不同，会被分到不同的组
 *      这样每组异或的结果并集即为所求
 *
 *      1、2代码同思路
 *      注：
 *          将2代码中的mask = res & -res;其他不变也可达到相同的结果
 *          因为该mask代表从第一个不同的最低位到最后一位的二进制数，同样等价于找到不同的那个二进制位数
 */
class Number_of_occurrences_of_numbers_in_the_array2 {
    public int[] singleNumbers(int[] nums) {
        int res = 0;
        for(int i : nums) res ^= i;

        int mask = 1;
        while((res & mask) == 0) mask <<= 1;

        int[] ans = new int[2];
        for(int i=0; i<nums.length; i++){
            if((nums[i] & mask) == 0) ans[0] ^= nums[i];
            else ans[1] ^= nums[i];
        }

        return ans;
    }
}
class Number_of_occurrences_of_numbers_in_the_array1 {
    public int[] singleNumbers(int[] nums) {
        int res = 0;
        for(int i : nums) res ^= i;

        int index = 0;
        while((res & 1) != 1){
            res >>= 1;
            index += 1;
        }

        res = nums.length;
        int a = 0;
        int b = 0;
        for(int i=0; i<res; i++){
            int k = nums[i] >> index;
            if((k & 1) == 1) a ^= nums[i];
            else b ^= nums[i];
        }

        return new int[]{a, b};
    }
}
public class Number_of_occurrences_of_numbers_in_the_array {
}
