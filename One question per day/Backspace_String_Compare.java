/**
给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
注意：如果对空文本输入退格字符，文本继续为空。

提示：
    1 <= S.length <= 200
    1 <= T.length <= 200
    S 和 T 只含有小写字母以及字符 '#'。

进阶：
    你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？

思路1：
	栈或队列进行判断，无代码
思路2：
	双指针。
	每个指针从字符串最后开始往前遍历，当遇到'#'时记录下其个数s，当遇到普通字符时，
	判断是否有s>0，有则跳过继续遍历；否则结束当前字符串遍历去遍历另一个字符串
	同样的方法，最后当两个指针都不越界时判断对应的字符是否一样；
	当出现越界时，若都结束了则返回真；否则返回假（有一个字符串还存在普通字符）
 */

class Solution {
    public boolean backspaceCompare(String S, String T) {
        int s = 0;
        int t = 0;

        int i = S.length() - 1;
        int j = T.length() - 1;

        while (i >= 0 || j >= 0) {

            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    s ++;
                    i --;
                } else if (s > 0) {
                    s --;
                    i --;
                } else {
                    break;
                }
            }
            
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    t ++;
                    j --;
                } else if (t > 0) {
                    t --;
                    j --;
                } else {
                    break;
                }
            }

            if (i >= 0 && j >= 0) {
                if (S.charAt(i) == T.charAt(j)) {
                    i --;
                    j --;
                } else {
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                } 
            }
        }

        return true;
    }
}