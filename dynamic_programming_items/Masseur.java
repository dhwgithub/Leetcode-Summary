package leetcode.dynamic_programming_items;

/**
 *  题目：
 *      一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。
 *      在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
 *      给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 *  思路1：
 *      动态规划。对于每个预约，要么接受要么拒绝，设置dp[i]表示第 i 次的最大总分钟
 *      当第 i 次接受预约时，则前一次不能接受，即 dp[i] = dp[i-2] + nums[i]
 *      当第 i 次拒绝预约时，则等于前一次预约的最大总分钟，即 dp[i] = dp[i-1]
 *      由状态转移方程知需要计算前两次的情况：
 *          只有一次时肯定接受 dp[0] = nums[0]
 *          有两次预约时只能接受其中最大的一个 dp[1] = Math.max(nums[0], nums[1])
 *  思路2：
 *      思路1的改进，由于dp数组只涉及3个范围的变化，因此可以将dp大小设置为3构造滚动数组
 *      用取模运算进行变化，此时空间复杂度为O(1)，时间复杂度同思路1的O(N)
 *
 *      参考：https://leetcode-cn.com/problems/the-masseuse-lcci/solution/dong-tai-gui-hua-by-liweiwei1419-8/
 *
 */
class Masseur2 {
    public int massage(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int len = nums.length;
        if(len == 1) return nums[0];

        int[] dp = new int[3];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i=2; i<len; i++){
            dp[i % 3] = Math.max(dp[(i-2) % 3] + nums[i], dp[(i-1) % 3]);
        }

        return dp[(len-1) % 3];
    }
}
class Masseur1 {
    public int massage(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int len = nums.length;
        if(len == 1) return nums[0];

        int[] dp = new int[len];  // 最小需要num.length即可
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i=2; i<len; i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }

        return dp[len-1];
    }
}
public class Masseur {
}
