/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，
 * 返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * 
 * 方法一：模拟大整数乘法。注意第二个式子较短，填充答案数组
 * 	计算过程中只需要统计进位以及下标开始的地方即可，最后把剩下没有进位的数字填充入答案数组
 * 	注意最后输出全零的特殊处理
 */

import java.util.Arrays;
class Solution {
    public String multiply(String num1, String num2) {
    	if (num1.length() == 0 || num2.length() == 0) {
            return "";
        }
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int len = num1.length() + num2.length() + 1;
        char[] ans = new char[len];
        Arrays.fill(ans, '0');
        
        char[] n1 = num1.toCharArray();
        char[] n2 = num2.toCharArray();
        
        // 使得第二个算式较短
        if (num1.length() < num2.length()) {
        	n1 = num2.toCharArray();
        	n2 = num1.toCharArray();
        }
        	
        int k = len - 1;  // 每次乘积开始的下标
        for (int i=n2.length-1; i>=0; i--) {
            int ans_sum = 0;  // 进位
            int index = 0;
            for (int j=n1.length-1; j>=0; j--, index++) {
                // temp = 当前乘积 + 进位 + 之前结果的上一位置的值
                int temp = (n2[i] - '0') * (n1[j] - '0') + ans_sum + (ans[k - index] - '0');
                ans[k - index] = (char)(temp % 10 + '0');
                ans_sum = temp / 10;
            }
            while (ans_sum != 0) {
                ans[k - index] = (char)(ans_sum % 10 + '0');
                ans_sum /= 10;
                index ++;
            }
            k --;
        }

        int i = 0;
        for (; i<ans.length; i++) {
            if (ans[i] != '0') {
                break;
            }
        }

        String res = new String(Arrays.copyOfRange(ans, i, ans.length));

        return res.equals("") ? "0" : res;
    }
}