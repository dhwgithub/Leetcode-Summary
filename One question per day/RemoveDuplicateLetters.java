/*
Given a string s, remove duplicate letters so that every letter 
appears once and only once. You must make sure your result is 
the smallest in lexicographical order among all possible results.

Constraints:
    1 <= s.length <= 104
    s consists of lowercase English letters.

题解：
	首先求出每个字符剩余次数（便于遍历过程中决定是否删除）
	用栈存储元素，然后每次出现一个字符且栈中没有时：
		当当前元素小于栈顶元素且栈顶元素后面还存在时，可以把栈顶元素删除（以此类推）
		否则直接加入当前元素即可，不满足以上条件也要加入当前元素
	最后得到的栈中元素即为最后的答案
	
	(Java)栈：无需判空即可判断是否存在某元素

参考：https://leetcode-cn.com/problems/remove-duplicate-letters/solution/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-4/
 */
class Solution {
    public String removeDuplicateLetters(String s) {
        int len = s.length();

        // 剩余个数
        int[] remainNum = new int[26];
        for (int i = 0; i < len; i ++) {
            remainNum[s.charAt(i) - 'a'] ++;
        }

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < len; i ++) {
            char c = s.charAt(i);

            // 判断栈是否含有某元素时无需判空
            if (! stack.contains(c)) {
                // 当栈顶元素大于当前元素且栈顶元素剩余数大于等于1时移除
                while (! stack.isEmpty() && stack.peek() > c && remainNum[stack.peek() - 'a'] >= 1) {
                    stack.pop();
                }

                stack.push(c);
            }

            remainNum[c - 'a'] -= 1;
        }

        StringBuilder str = new StringBuilder("");
        while (! stack.isEmpty()) {
            str.append(stack.pop());
        }

        return str.reverse().toString();
    }
}