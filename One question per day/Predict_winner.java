/**
 * 给定一个表示分数的非负整数数组。
 *  玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。
 *  每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。
 *  最终获得分数总和最多的玩家获胜。
 *  给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 *  
 *  方法：由题意知每次从两端选择，因此每次玩家只有两种选择。令dp[i][j]表示数组[i, j]分差
 *  	当前玩家选nums[i]时，dp[i][j] = nums[i] - dp[i+1][j]
 *  	当前玩家选nums[j]时，dp[i][j] = nums[j] - dp[i][j-1]
 *		然后取最大值即可
 *	由于dp[i][j]每次之和两个相邻状态有关，因此可以使用一维数组记录（代码注释为二维）
 *	当数组为偶数时，先手必赢。
 *	如题，为什么呢？随便举一个例子[3,1,9,7,4,5,8,2],这是一个偶数长度，因为和是一定的，
 *	所以奇数索引数字[3,9,4,8]+偶数索引数字[1,7,5,2]一定。同时一定会存在谁大谁小，如果相等，题目规定了还是先手获胜！
 *	那么我们可以限制让先手要么都拿奇数索引数字，或者只拿偶数索引数字,这样一定会获胜！（数组索引从1开始算起）
 *	参考：https://leetcode-cn.com/problems/predict-the-winner/solution/ni-yi-ding-hui-li-jie-wei-shi-yao-shu-zu-chang-du-/
 */

class PredictTheWinner1 {
    public boolean PredictTheWinner(int[] nums) {
        if (nums.length % 2 == 0) {
            return true;
        }

        int len = nums.length;
        // int[][] dp = new int[len][len];
        int[] dp = new int[len];
        for (int i = 0; i < len; i ++) {
            // dp[i][i] = nums[i];
            dp[i] = nums[i];
        }

        for (int i = len - 2; i >= 0; i --) {
            for (int j = i + 1; j < len; j ++) {
                // dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }

        // return dp[0][len - 1] >= 0;
        return dp[len - 1] >= 0;
    }
}