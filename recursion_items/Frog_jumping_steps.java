package leetcode.recursion_items;

/**
 *  题意：
 *      一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *      答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *      0 <= n <= 100
 *  思路：
 *      当n = 0时，ans = 1;
 *      当n = 1时，ans = 1;
 *      当n = 2时，ans = 2;
 *      当n = 3时，ans = 3;
 *      ......
 *      由此发现规律，ans[i] = ans[i-1] + ans[i-2]
 */
class Frog_jumping_steps1 {
    public int numWays(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        int[] sum = new int[n+5];
        sum[0] = 1;
        sum[1] = 1;
        return getNum(n, sum);
    }

    public int getNum(int n, int[] sum){
        if(n == 0 || n == 1){
            return sum[0];
        }
        if(sum[n] == 0){
            sum[n] = (getNum(n-1, sum) + getNum(n-2, sum)) % 1000000007;
        }
        return sum[n];
    }
}
public class Frog_jumping_steps {
}
