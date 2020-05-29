package leetcode.math_items;

/**
 *  题意：
 *      输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *      用返回一个整数列表来代替打印
 *      n 为正整数
 *  思路：
 *      首先求出循环输出的上界，即10^n-1
 *      这也是最难的部分，可以使用快速幂来实现快速求解
 *
 *      非递归快速幂：
 *          对于7^10，可以把10换为2进制即(1010)2
 *          若要进行计算，可以分解为7^(1000)2 * 7^(10)2
 *          实际上，对于任意的正整数，我们都可以拆解成若干个7^(100...)2的形式相乘，其恰好是7^1,7^2，7^4......
 *          我们只需要不断地对底数平方就可以计算它们
 *          也就是当奇数时，将结果与7相乘，否则是底数平方；然后移位循环
 */
class Print_n_digits_from_1_to_maximum1 {
    public int[] printNumbers(int n) {
        // 1  9
        // 2  99
        // 3  999
        int maxn = qpow(10, n) - 1;
        int[] num = new int[maxn];
        for(int i=1; i<=maxn; i++){
            num[i-1] = i;
        }
        return num;
    }

    public int qpow(int n, int k){
        int ans = 1;
        while(k != 0){
            if((k & 1) == 1){
                ans = ans * n;
            }
            n = n * n;
            k >>>= 1;
        }
        return ans;
    }
}
public class Print_n_digits_from_1_to_maximum {
}
