/*
给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
（当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）

说明: N 是在 [0, 10^9] 范围内的一个整数。

题解：
	若要找到最大的非严格递增数字，则要使得低位尽可能比高位大
	从低位开始，
		若当前位数字等于高一位数字，则保持不变（若加则比原数大）
		若当前位数字小于高一位数字，则将当前位及所有低位全部变为9，高一位数字-1（满足题意的最大数）
		若当前位数字大于高一位数字，则保持不变即加上前面算过的低位为此时最大数
	综上，
		若当前数字小于高一位数字，则需要将最高位-1，然后所有低位变为9
			可以设置一个变量seed用来记录位数，然后可以直接重置结果
		若其他情况，则保持不变
			此时结果为当前位与之前结果的合并（位数上连接）
			
参考：https://leetcode-cn.com/problems/monotone-increasing-digits/solution/java-ologn-bu-xu-yao-zhuan-zi-fu-chuan-b-b7lk/
 */
class Solution {
    public int monotoneIncreasingDigits(int N) {
        int res = 0;
        int seed = 1;  // 位数辅助变量
        while (N > 0) {
            int low = N % 10;
            N /= 10;

            int high = N % 10;
            if (high > low) {
                res = seed * 10 - 1;
                N --;  // 相当于高位减一（借位）
            }
            else {
                res = low * seed + res;
            }

            seed *= 10;
        }
        return res;
    }
}