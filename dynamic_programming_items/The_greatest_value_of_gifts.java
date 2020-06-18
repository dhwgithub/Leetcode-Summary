package leetcode.dynamic_programming_items;

/**
 *  题意：
 *      在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 *      你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 *      给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *      0 < grid.length <= 200
 *      0 < grid[0].length <= 200
 *  思路：
 *      令dp[i][j]表示第i行第j列时礼物的最大价值，那么dp[i][j] = max(dp[i-1][j], dp[i][j-1]) + v[i][j]
 *      注意初始化第一行和第一列特殊的元素
 *      对于优化，可以直接在原数组中进行操作
 */
class The_greatest_value_of_gifts1 {
    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];
        for(int j=1; j<n; j++) dp[0][j] = dp[0][j-1] + grid[0][j];
        for(int i=1; i<m; i++) dp[i][0] = dp[i-1][0] + grid[i][0];

        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }
}
public class The_greatest_value_of_gifts {
}
