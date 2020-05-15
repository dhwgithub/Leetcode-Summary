package leetcode.bit_manipulation_items;

/**
 * 题目：
 *      给定一个32位整数，问只修改一位二进制的0变为1，最多有几个1连续
 * 思路：
 *      对每一段1进行计数，当遇到0或该数移位为0时结束。
 *      在每段结束后，进行两部分的相加再加上变换的0，取最大值
 */
class Flip_digital1 {
    public int reverseBits(int num) {
        int pre = 0;
        int now = 0;
        int ans = 1;
        while(num != 0){
            int t = num & 1;
            num >>>= 1;
            if(t == 0){
                pre = now;
                now = 0;
            }else{
                now += 1;
            }
            ans = ans > (pre + now + 1) ? ans : (pre + now + 1);
        }
        return ans;
    }
}
public class Flip_digital {
}
