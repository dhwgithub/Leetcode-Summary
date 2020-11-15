/*
给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
注意:
    num 的长度小于 10002 且 ≥ k。
    num 不会包含任何前导零。

题解：
	贪心 + 双端队列
	由题知道让前面位数的数字越小越好，利用贪心的思想，将数字存入栈中，若栈顶元素大于将要比较的字符，则替换栈顶元素（栈顶元素推出）即k--
	直到k为0则不再删除元素。注意最后可能存在k>0的情况，需要从后面删除k个元素
	最后反序求大小，使用双端队列方便。另外由于返回的是字符串，所以注意前导零的处理
 */

class Solution {
    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<>();

        int len = num.length();
        for (int i = 0; i < len; i ++) {
            char c = num.charAt(i);
            while (! deque.isEmpty() && k > 0 && deque.peekLast() > c) {
                deque.pollLast();
                k --;
            }
            deque.addLast(c);
        }

        while (k -- > 0) {
            deque.pollLast();
        }

        StringBuilder str = new StringBuilder();
        boolean isZero = true;
        while (! deque.isEmpty()) {
            char c = deque.pollFirst();
            if (c == '0' && isZero) {
                continue;
            }
            isZero = false;
            str.append(c);
        }

        return str.length() == 0 ? "0" : str.toString();
    }
}