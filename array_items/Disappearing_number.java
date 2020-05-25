package leetcode.array_items;

/**
 * 题目：
 *      给定数组中包含从0到n所有整数，但是其中缺了一个，请输出缺失的数
 * 思路1：
 *      创建一个大小为n的计数数组统计每个数字的次数（当数字大于n时不统计），然后从0到n遍历一遍输出次数为0的数
 * 思路2：
 *      异或方法。异或运算特性之一是对一个数连续两次异或还是其本身
 *      因此可以对数组进行异或一次，再对0~n异或一次，最后得到的数就是缺失的那个数
 */
class Disappearing_number1 {
    public int missingNumber(int[] nums) {
        int[] key = new int[nums.length + 5];
        for(int num : nums){
            if(num > nums.length){
                continue;
            }
            key[num] += 1;
        }
        for(int i=0; i<=nums.length; i++){
            if(key[i] == 0){
                return i;
            }
        }
        return -1;
    }
}

class Disappearing_number2 {
    public int missingNumber(int[] nums) {
        int ans = 0;
        for(int i=0; i<nums.length; i++){
            ans ^= i;
            ans ^= nums[i];
        }
        ans ^= nums.length;
        return ans;
    }
}

public class Disappearing_number {
}
