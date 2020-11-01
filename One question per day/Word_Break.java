/*
给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
说明：
    拆分时可以重复使用字典中的单词。
    你可以假设字典中没有重复的单词。

方法：
	DP，前 N 位是否可分取决于前 N-M 位是否可分 且 第 N-M+1到N是否在字典里
	因此可以用一维数组来记录前N位是否可分（不包括第N位），用哈希表记录字典中的值
 */

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }

        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        // 前 N 位是否可分取决于前 N-M 位是否可分 且 第 N-M+1到N是否在字典里
        for (int i = 1; i <= len; i ++) {
            for (int j = i - 1; j >= 0; j --) {
                dp[i] = dp[j] & set.contains(s.substring(j, i));
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[len];
    }
}