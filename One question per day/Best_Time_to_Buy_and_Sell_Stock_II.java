/**
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

题解1：
	DP。因为存在有股票和无股票两种状态，再加上时序上的因素因此设定数组是dp[len][2]
	设定数组dp[i][0]表示第i天没有股票的最大收益，dp[i][1]表示第i天有股票的最大收益
	那么没有股票时的最大收益是前一天有股票的最大收益+当天股票价格（卖了） 与 前一天无股票的最大收益
		有股票时的最大收益是前一天无股票的最大收益-当天股票价格（买了） 与前一天有股票的最大收益
		
题解2：
	DP改进。有上述题解只当天最大收益只与前一天有关，因此可以用一个变量即可记录
	减少内存开销
	
题解3：
	贪心。由于买卖次数不受限制，因此只要可以赚到利润就卖，如果存在连续多个，相当于有股票买了有卖，
	中间状态相当于没操作，最后得到的最大收益是一样的
 */

class Solution3 {
    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i ++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}

class Solution2 {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        int have = -prices[0];
        int no_have = 0;
        
        for (int i = 1; i < prices.length; i ++) {
            int t_have = have;
            int t_no_have = no_have;
            no_have = Math.max(t_no_have, t_have + prices[i]);
            have = Math.max(t_no_have - prices[i], t_have);
        }

        return no_have;
    }
}

class Solution1 {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][1] = -prices[0];
        dp[0][0] = 0;
        
        for (int i = 1; i < prices.length; i ++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }

        return dp[prices.length - 1][0];
    }
}