/*
给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；
非负整数 fee 代表了交易股票的手续费用。
你可以无限次地完成交易，但是你每笔交易都需要付手续费。
如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
返回获得利润的最大值。

注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。

题解：
	由于添加了手续费，因此不能直接简单的用贪心
	可以采用动态规划：首先找出状态数量，然后分析状态转移
	
	每笔交易后存在两种状态：有股票和无股票
	对于有股票：当前最大利润是上次没有股票减去交易额，与有股票 的最大值
	对于无股票：当前最大利润是上次没有股票，与有股票加交易额再减去手续费 的最大值
	
	由于每次状态只与上一次状态相关，因此不必使用数组存储
 */
class Solution2 {
    public int maxProfit(int[] prices, int fee) {
        int day = prices.length;
        int no_stock = 0;
        int stock = -prices[0];

        for (int i = 1; i < day; i ++) {
            int no_stock_temp = no_stock;
            no_stock = Math.max(no_stock_temp, stock + prices[i] - fee);
            stock = Math.max(no_stock_temp - prices[i], stock);
        }

        return no_stock;
    }
}
class Solution1 {
    public int maxProfit(int[] prices, int fee) {
        int day = prices.length;
        int[][] dp = new int[day][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < day; i ++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }

        return dp[day - 1][0];
    }
}