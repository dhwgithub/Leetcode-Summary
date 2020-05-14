package leetcode.bit_manipulation_items;

/**
 * 题目：
 *      输出一个整数二进制表示时1的个数
 * 思路1：
 *      移位操作。n&1=1表示整数n最后一位是1，然后右移移位循环计算即可
 *      注意：>> 表示正数右移高位补0，负数右移高位补1；>>> 无论正负其高位都是补0
 * 思路2：
 *      巧妙运算。n-1表示将其最右边的1变为0，后面的0都变为1；此时将其与n与运算，这样就可以消除原n中的一个1,循环计算即可求解
 */
class The_number_of_1s_in_binary1 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int sum = 0;
        while(n != 0){
            sum += (n & 1);
            n >>>= 1;
        }
        return sum;
    }
}
class The_number_of_1s_in_binary2 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int sum = 0;
        while(n != 0){
            sum += 1;
            n = n & (n - 1);
        }
        return sum;
    }
}
public class The_number_of_1s_in_binary {
}
