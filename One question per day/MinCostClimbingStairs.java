/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
Once you pay the cost, you can either climb one or two steps.
You need to find minimum cost to reach the top of the floor,
and you can either start from the step with index 0, or the step with index 1.

Note:
    cost will have a length in the range [2, 1000].
    Every cost[i] will be an integer in the range [0, 999].

Solution:
    设dp[i]表示最后一步到达第i个台阶，这样能到达第i个台阶的方式只有两种：
        1、从第i-1个台阶到达，即dp[i-1]，花费dp[i-1] + cost[i]
        2、从第i-2个台阶到达，即dp[i-2]，花费dp[i-2] + cost[i]
        取最小值即可
    对于初始化，
        当最后一个到达第1个台阶时，总花费dp[0] = cost[0]
        当最后一个到达第2个台阶时，总花费dp[1] = min(cost[0] + cost[1], cost[1]) = cost[1]
    优化:
        由于当前状态只与前两个状态有关，因此选两个变量即可
 */
class Solution2 {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;

        int one = cost[0];
        int two = cost[1];

        for (int i = 2; i < len; i ++) {
            int t = two;
            two = Math.min(one, two) + cost[i];
            one = t;
        }

        return Math.min(one , two);
    }
}
class Solution1 {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;

        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < len; i ++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }

        return Math.min(dp[len - 1] , dp[len - 2]);
    }
}