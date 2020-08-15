/**
 * 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。
 * 每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。
 * 当你将所有盒子都去掉之后，求你能获得的最大积分和。
 *
 * 方法：定义dp[i][j][k]，表示在区间[i, j]上，与b[i]值相等的且位于[0, i-1]的数有k个
 * 那么有两种方式，见代码注释
 */

class removeBoxes {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n+5][n+5][n+5];
        return removeSubBoxes(0, n-1, 0, dp, boxes);
    }

    private int removeSubBoxes(int i, int j, int k, int[][][] dp, int[] b) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j][k] != 0) {
            return dp[i][j][k];
        }

        // 第一种情况：直接使b[i]与前面连续k个进行移除
        // 可以首先缩小查询范围，增加效率
        while (i < j && b[i] == b[i+1]) {
            i ++;
            k ++;
        }
        dp[i][j][k] = (k + 1) * (k + 1) + removeSubBoxes(i+1, j, 0, dp, b);

        // 第二种情况：首先找到[i+1. j]区间的某一个与b[i]等值的一个数位置m，移除[i+1, m-1]
        //            然后再做与上述第一种方法同理操作。相当于改变了移除的顺序
        for (int m=i+1; m<=j; m++) {
            if (b[i] == b[m]) {
                dp[i][j][k] = Math.max(dp[i][j][k],
                    removeSubBoxes(i+1, m-1, 0, dp, b) + removeSubBoxes(m, j, k+1, dp, b));
            }
        }

        // 两种方式取最大值即可
        return dp[i][j][k];
    }
}