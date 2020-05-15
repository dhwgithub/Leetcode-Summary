package leetcode.bit_manipulation_items;

/**
 * 题目：
 *      将一个整数的二进制奇偶位互换，如输入整数2输出为1
 * 思路：
 *      每次取一位，然后放入栈中，最后再次组合为一个数。注意输入1返回2（不够偶数位用前导0凑齐）
 */
class Pair_exchange1 {
    public int exchangeBits(int num) {
        int[] stack = new int[65];
        int index = 0;
        while(num != 0){
            int a = num & 1;
            num >>>= 1;
            int b = num & 1;
            num >>>= 1;

            stack[index] = b;
            index += 1;
            stack[index] = a;
            index += 1;
        }

        long ans = 0;
        index -= 1;
        while(index >= 0){
            ans += stack[index];
            ans <<= 1;
            index -= 1;
        }
        ans >>>= 1;
        return (int)ans;
    }
}
public class Pair_exchange {
}
