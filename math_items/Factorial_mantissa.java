package leetcode.math_items;

/**
 *  题意：
 *      设计一个算法，算出 n 阶乘有多少个尾随零。
 *      说明: 你算法的时间复杂度应为 O(log n) 。
 *  思路：
 *      只有当 n 的因子含有2、5时才可能产生尾0，又因为是阶乘，所以不必都算个数，只需要算5的个数即可
 *      如n = 25，有5，10，15，20，25这5个5的倍数，其中25 = 5 * 5；
 *          所以25 / 5 = 5，5 / 5 = 1，即 5 + 1 = 6，则一共6个尾0。
 *      如n = 50，有5，10，15，20，25，30，35，40，45，50，其中25 = 5 * 5，50 = 5 * 5 * 2；
 *          所以50 / 5 = 10， 10 / 5 = 2，即10 + 2 = 12，则一共12个尾0。
 *      以此类推
 *
 */
class Factorial_mantissa1 {
    public int trailingZeroes(int n) {
        int sum = 0;
        int t = -1;
        while(t != 0){
            t = n / 5;
            n /= 5;
            sum += t;
        }
        return sum;
    }
}
public class Factorial_mantissa {
}
