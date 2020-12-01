/*
给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
若可行，输出任意可行的结果。若不可行，返回空字符串。

注意:
    S 只包含小写字母并且长度在[1, 500]区间内。
    
题解：
	记录每个字符出现的次数，由于两个相同字符不能相邻，因此最大出现字符次数不能超过总长度的一半
	对于重构字符串，首先将出现次数最大的字符首先放置在间隔位置上（偶数下标）
	然后遍历每一个字符，接着原下标间隔进行存放。中间满了从下标1开始（奇数下标）

 */

class Solution {
    public String reorganizeString(String S) {
        char[] arr = S.toCharArray();
        int len = arr.length;

        int th = (len + 1) >> 1;
        int[] num = new int[26];  // 记录字符数量
        char max_c = 'a';  // 出现最多字符
        int max_n = 0;  // 出现最多字符次数

        for (int i = 0; i < len; i ++) {
            int t = arr[i] - 'a';
            num[t] ++;
            // 超过阈值则返回空字符串
            if (num[t] > th) {
                return "";
            }
            // 找到出现次数最大的字符
            if (num[t] > max_n) {
                max_n = num[t];
                max_c = arr[i];
            }
        }

        // 填充偶数位字符串
        char[] ans = new char[len];
        int i = 0;
        max_n = max_c - 'a';  // 下标
        while (num[max_n] -- > 0) {
            ans[i] = max_c;
            i += 2;
        }

        // 填充剩下的字符，注意要利用之前 i 下标
        for (int j = 0; j < 26; j ++) {
            while (num[j] -- > 0) {
                if (i >= len) {
                    i = 1;
                }
                ans[i] = (char)(j + 'a');
                i += 2;
            }
        }

        return new String(ans);
    }
}