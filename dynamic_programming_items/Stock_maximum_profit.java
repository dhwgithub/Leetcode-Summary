package leetcode.dynamic_programming_items;

/**
 *  题意：
 *      假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 *      0 <= 数组长度 <= 10^5
 *  思路：
 *      暴力求解其时间复杂度是O(n^2)，可以采取优化
 *      我们令dp[i]表示前i日（包含第i日）股票的最大利润，
 *      那么前i天的最大利润可以由 前i-1天最大利润 与 第i天售价减去前i天的最小值 取最大值求得
 *      即 dp[i] = max(dp[i-1], p[i]-min(p[0--i]))
 *      对于前i天的最小值，可以设定一个变量来记录前i-1天的最小值
 *      此时时间复杂度是O(N),空间复杂度是O(N)
 *
 *      又因为前i天的最大利润计算只与前i-1天的最大利润有关，因此可以使用一个变量来替代dp[i]和dp[i-1]
 *      综上即 prof = max(prof, p[i] - minx)，其中prof初始化为0，minx初始化为p[0]
 *      此时时间复杂度为O(N),空间复杂度为O(1)
 */
class Stock_maximum_profit1 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;

        int prof = 0;
        int minx = prices[0];
        for(int i=0; i<prices.length; i++){
            minx = Math.min(minx, prices[i]);
            prof = Math.max(prof, prices[i] - minx);
        }
        return prof;
    }
}
public class Stock_maximum_profit {
}
