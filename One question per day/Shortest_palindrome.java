/**
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * 
 * 方法：此题可转化为求最长的（与后缀相同的）前缀，然后将前缀后面的翻转后加到前面即可得到本题答案
 * 	因此重点是求前缀，而一种方法是利用KMP算法的next数组，求s+"#"+s.reverse的next[len]值
 * 	这样就间接求出了s字符串的最长前缀，然后将后面的翻转相加即可
 * 	（中间加#是为了不使得next[len]大于原字符串的长度）
 *
 */

class Solution {
    public String shortestPalindrome(String s) {
        if (s == null) {
            return "";
        }

        String str = s + "#" + new StringBuffer(s).reverse();
        int len = getNext(str.toCharArray());
        return new StringBuffer(s.substring(len)).reverse() + s;
    }

    private int getNext(char[] s) {
        int len = s.length;
        int[] next = new int[len + 1];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < len) {
            while (j != -1 && s[i] != s[j]) {
                j = next[j];
            }
            next[++ i] = ++ j;
        }

        return next[len];
    }
}