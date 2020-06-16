package leetcode.math_items;

/**
 *  题意：
 *      我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *      1 是丑数。
 *      n 不超过1690。
 *  思路：
 *      由题，第i个丑数一定是前面丑数与2,3,5乘积的最小值（不重复）
 *      为此，可以设置3个数组分别记录乘积的结果，然后找到最小的数加入最终的丑数序列
 *      而对于重复的数，对应数组指针+1跳过即可
 *      基于此思路，可以进行简化，直接设置3个指针指向同一个丑数序列，每次比较每个指针对应值的乘积
 *      然后取最小值作为当前丑数值，然后把重复的数指针后移一位，可以达到上述思路相同的结果
 *      刚开始丑数序列只有1
 */
class Ugly_number1 {
    public int nthUglyNumber(int n) {
        int[] ug = new int[n];
        ug[0] = 1;

        int a = 0;
        int b = 0;
        int c = 0;

        for(int i=1; i<n; i++){
            int a2 = ug[a] * 2;
            int b3 = ug[b] * 3;
            int c5 = ug[c] * 5;
            ug[i] = Math.min(a2, Math.min(b3, c5));

            if(a2 == ug[i]) a ++;
            if(b3 == ug[i]) b ++;
            if(c5 == ug[i]) c ++;
        }

        return ug[n-1];
    }
}
public class Ugly_number {
}
