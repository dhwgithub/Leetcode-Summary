/**
给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符
 

示例 1：

输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
示例 2：

输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
 

提示：

0 <= word1.length, word2.length <= 500
word1 和 word2 由小写英文字母组成

题解：
    动态规划
    具体解释见代码
*/
class Solution {
    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();

        int l1 = w1.length;
        int l2 = w2.length;

        int[][] dp = new int[l1 + 1][l2 + 1];
        // 当word2字符串为空时，初始化所有距离
        for (int i = 0; i < l1 + 1; i ++) {
            dp[i][0] = i;
        }
        // 当word1字符串为空时，初始化所有距离
        for (int i = 0; i < l2 + 1; i ++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < l1 + 1; i ++) {
            for (int j = 1; j < l2 + 1; j ++) {
                // 匹配一样，距离不变
                if (w1[i - 1] == w2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 匹配不一样，为 替换、插入和删除 情况的最小距离+1
                else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[l1][l2];
    }
}