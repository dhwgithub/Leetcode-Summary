package leetcode.math_items;

/**
 * 题目：
 *      不使用if-else等比较运算符求两数之间较大的数
 * 思路：
 *      首先应该知道a-b的正负数，这样就可以利用a*k+b*(!k)得到最后的解
 *      其中对于传入int类型的数，为了防止溢出。将其转化为long类型
 *      然后(a-b)的值右移63位即可得到符号位（long类型是64位），注意是无符号的右移
 *
 */
class Maximum_value1 {
    public int maximum(int a, int b) {
        long t = (long)a - (long)b;
        int k = (int)(t >>> 63);  // 求取符号位，0为正
        return a * (k ^ 1) + b * k;
    }
}

public class Maximum_value {
}
