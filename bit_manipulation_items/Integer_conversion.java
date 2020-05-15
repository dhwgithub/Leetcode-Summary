package leetcode.bit_manipulation_items;

/**
 * 题目：
 *      给定两个整数，问改变几位可使两个整数相等，注意改变的是二进制位数
 * 思路：
 *      从低位开始依次比较，不相等则计数加一，直到两个都为0
 */
class Integer_conversion1 {
    public int convertInteger(int A, int B) {
        int sum = 0;
        while(!(A == 0 && B == 0)){  // 注意结束条件
            int a = A & 1;
            int b = B & 1;
            A >>>= 1;
            B >>>= 1;
            if(a != b){
                sum += 1;
            }
        }
        return sum;
    }
}
public class Integer_conversion {
}
