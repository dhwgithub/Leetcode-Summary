/**
给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
注意:
    每个数组中的元素不会超过 100
    数组的大小不会超过 200

题解：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
	当数组长度小于2时不能分割
	当数组总和为奇数时不能分割
	当数组总和的一半小于数组最大值时不能分割
	
	定义一个dp[i][j]，表示从[0, i]下标中任取一些数可以等于j，则标记为true
	则遍历每个i从[1, len - 1]，且j从[1, sum / 2]（已初始化）
	那么当当前值nums[i]大于j时：不可取当前值，则dp[i][j] = dp[i-1][j]
	   否则，可取可不取，则dp[i][j] = dp[i-1][j] | dp[i-1][j-nums[i]]
	   
	对于初始化：
		当dp[0][j]时都为true，因为可取能为0
		当dp[0][nums[0]]时为true，当前值即为对应和
 */

class Solution {
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return false;
        }

        int sum = 0;
        int maxV = 0;
        for (int i = 0; i < len; i ++) {
            sum += nums[i];
            maxV = Math.max(maxV, nums[i]);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxV > target) {
            return false;
        }

        boolean[][] dp = new boolean[len][target + 1];
        for (int i = 0; i < len; i ++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < len; i ++) {
            for (int j = 1; j <= target; j ++) {
                if (nums[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[len - 1][target];
    }
}