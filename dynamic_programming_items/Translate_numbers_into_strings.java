package leetcode.dynamic_programming_items;

/**
 *  题意：
 *      给定一个数字，我们按照如下规则把它翻译为字符串：
 *      0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 *      一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *      例如12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *      0 <= num < 2^31
 *  思路：
 *      动态规划。令dp[i]表示以第i位结尾时总的方案数，
 *      当第i位数字不能和其他数字进行组合时，当前方案数和上一次方案数一样，有dp[i] = dp[i-1]
 *      当可以组合时，那么和上一次的字符组合后总方案数为前两项方案数之和，有dp[i] = dp[i-1] + dp[i-2]
 *      对于是否可组合：
 *          数字大小在10~25时可以进行组合，其他情况不能组合，如04、26等
 *
 *      对于对数字进行判断，一种方法是将数字转化为字符串一一判断，另一种方法是不断取最低位判断（可从左开始也可从右开始遍历）
 *      对于第二种方法，其空间复杂度将由O(N)变为O(1)
 */
class Translate_numbers_into_strings1 {
    public int translateNum(int num) {
        int pre = 1;  // 等价于dp[i-2]
        int cur = 1;  // 等价于dp[i],dp[i-1]（在同一表达式存在先后顺序）

        int npre = num % 10;
        num /= 10;
        while(num > 0){  // 从后往前找
            int n = num % 10;
            num /= 10;

            int t = n;
            n = n * 10 + npre;
            npre = t;  // 下次迭代使用

            t = cur;  // 保存原cur即dp[i-1]的值
            if(n >= 10 && n <= 25) cur = cur + pre;
            // 对于其他情况cur无需更新（方案数不变）
            pre = t;  // 每次变化后更新dp[i-2]
        }

        return cur;
    }
}
public class Translate_numbers_into_strings {
}
