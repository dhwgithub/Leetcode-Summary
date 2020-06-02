package leetcode.dynamic_programming_items;

/**
 *  题目:
 *      三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。
 *      实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
 *  思路1：
 *      动态规划。假设dp(n)表示到达第n个阶梯可能的方式数；
 *      考虑倒数第二步：可以在第n-1、n-2、n-3这3个台阶上，然后一步就可以到达终点
 *      所以dp(n) = dp(n-1) + dp(n-2) + dp(n-3)
 *      改进：用三个变量进行替换，不需要创建数组
 *  思路2：
 *      矩阵快速幂。由状态转移方程知：dp(n) = dp(n-3) + dp(n-2) + dp(n-1)
 *      那么将其转化为向量形式为 [f(n-3) f(n-2) f(n-1)]
 *      接着右乘一个矩阵  [[0 0 1]
 *                      [1 0 1]
 *                      [0 1 1]]
 *      得到结果向量 [f(n-2) f(n-1) f(n-3)+f(n-2)+f(n-1)] 即 [f(n-2) f(n-1) f(n)]
 *      因此将当前值乘以一个矩阵就得到了下一个值，从初始值 [4 2 1] 向量开始，乘以矩阵 A^（n-3) 得到 [f(n-2) f(n-1) f(n)]
 *      而矩阵可以通过矩阵快速幂得到
 *
 *      对于本题，初始化数组 [1, 2, 4]，然后计算 矩阵的(n-3)次方
 *      最后两者相乘，其中与矩阵的最后一列相乘之和即为所求的f(n)，注意取模
 *      对于矩阵快速幂，首先从单位矩阵开始，按照求快速幂的思路进行计算。进行相乘的时候定义一个方法计算矩阵乘法
 */
class Three_step_problem2 {
    final int MOD = 1000000007;

    public int waysToStep(int n) {
        long[] p = new long[]{1, 2, 4};
        if(n <= 3) return (int)p[n-1];

        long[][] A = {{0, 0, 1}, {1, 0, 1}, {0, 1, 1}};
        long[][] B = matPow(A, n-3);

        long res = 0;
        for(int i=0; i<3; i++){
            res = res + (p[i] * B[i][2]) % MOD;
            res %= MOD;
        }

        return (int)res;
    }

    public long[][] matPow(long[][] A, int n) {
        int len = A.length;
        long[][] res = new long[len][len];
        for(int i=0; i<len; i++) res[i][i] = 1;

        while(n > 0){
            if((n & 1) == 1) res = matMul(res, A);
            A = matMul(A, A);
            n >>= 1;
        }

        return res;
    }

    public long[][] matMul(long[][] A, long[][] B) {
        int row = A.length;
        int col = B[0].length;
        int len = B.length;
        long[][] C = new long[row][col];

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                for(int k=0; k<len; k++){
                    C[i][j] += (A[i][k] * B[k][j]) % MOD;
                    C[i][j] %= MOD;
                }
            }
        }

        return C;
    }
}
class Three_step_problem1 {
    public int waysToStep(int n) {
        int[] num = new int[n + 5];
        final int MOD = 1000000007;
        num[1] = 1;
        num[2] = 2;
        num[3] = 4;
        for(int i=4; i<=n; i++){
            num[i] = ((num[i-1] + num[i-2]) % MOD) + num[i-3];
            num[i] = num[i] % MOD;
        }
        return num[n];
    }
}
public class Three_step_problem {
}
