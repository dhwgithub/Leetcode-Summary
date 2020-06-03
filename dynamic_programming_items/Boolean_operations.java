package leetcode.divide_and_conquer_items;

import java.util.Arrays;

/**
 *  题意：
 *      给定一个布尔表达式和一个期望的布尔结果 result，
 *      布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
 *      实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
 *      运算符的数量不超过 19 个
 *  思路：
 *      求当前字符串经过运算得到指定值有多少方法，可以分解为小问题解决。
 *      设dp[s][e][r]代表从索引s到索引e值为r的方案数，那么可以将此分为两个子问题，并存储子问题结果重复利用
 *      用指针k（从s遍历到e-1）将区间[s,e]分为两部分，即[s,k]和[k+2,e]，其中k+1位置是运算符
 *      又因为是布尔运算，其值只有0、1两种情况，两个小问题组合后共4中情况，即00，11,01,10
 *
 *      分别计算这两部分的方案数最后相乘即可，不过首先要分别判断这四种情况下通过位置k+1运算后结果是否等于r
 *      若等于r则将运算符k+1划分的两部分方案数相乘；否则不做处理
 *
 *      递归分解的结束条件是：
 *          当s==e表示只有一个数时，直接判断是否是r，若是则返回1，否则返回0
 *          当dp[s][e][r]不等于初始值时，表示之前已计算得到。直接返回其值
 *
 *      参考：https://leetcode-cn.com/problems/boolean-evaluation-lcci/solution/java-ji-yi-hua-sou-suo-by-npe_tle/
 */
class Boolean_operations1 {
    public int countEval(String s, int result) {
        char[] arr = s.toCharArray();
        int len = arr.length;

        int[][][] dp = new int[len][len][2];
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        return countDp(0, len-1, result, dp, arr);
    }

    public int countDp(int s, int e, int r, int[][][] dp, char[] arr) {
        if(s == e) return arr[s] - '0' == r ? 1 : 0;
        if(dp[s][e][r] != -1) return dp[s][e][r];

        int sum = 0;
        for(int k=s; k<e; k+=2){  // 枚举划分位置
            char opt = arr[k+1];  // 运算符
            for(int i=0; i<=1; i++){  // 枚举前一段[s,k]计算结果值
                for(int j=0; j<=1; j++){  // 枚举后半段[k+2, e]计算结果值
                    // 若两部分的值通过运算符opt计算等于r，则计算两部分方案数的乘积
                    if(getAns(i, j, opt) == r){
                        sum += countDp(s, k, i, dp, arr) * countDp(k+2, e, j, dp, arr);
                    }
                }
            }
        }

        dp[s][e][r] = sum;  // 保存计算结果便于重复利用
        return sum;
    }

    public int getAns(int i, int j, char opt) {
        switch(opt){
            case '&': return i & j;
            case '|': return i | j;
            case '^': return i ^ j;
        }
        return -1;
    }
}

public class Boolean_operations {
}
