/*
斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
给定 N，计算 F(N)。

题解：
	方法一：快速幂
	方法二：矩阵快速幂（如下，和快速幂原理一样），关键是找到递推式
 */
class Solution {
    public int fib(int N) {
        if (N < 1) return 0;
        else if (N <= 2) return 1;

        int[][] dp = {{1, 1}};
        int[][] ma = {{1, 1}, {1, 0}};
        N -= 2;
        while (N >= 1) {
            if ((N & 1) == 1) dp = mul(dp, ma);
            ma = mul(ma, ma);
            N >>= 1;
        }
        return dp[0][0];
    }

    public int[][] mul(int[][] a, int[][] b) {
        int I = a.length;
        int J = b.length;
        int K = a[0].length;
        int[][] c = new int[I][K];
        for (int i = 0; i < I; i ++) {
            for (int j = 0; j < J; j ++) {
                for (int k = 0; k < K; k ++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
}