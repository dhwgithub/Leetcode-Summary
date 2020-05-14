package leetcode.bit_manipulation_items;

/**
 * 题目：
 *      不使用加号和其他算术运算符实现加法运算，假定结果不会溢出32位整数
 * 思路：
 *      位运算。当不考虑进位时，1+1=0,1+0=1,0+0=0知为异或运算；
 *      而进位只有1+1时才产生，通过且运算可以得出是否需要进位；
 *      然后每次迭代
 */
class Addition_without_plus_sign1 {
    public int add(int a, int b) {
        int sum = a;
        while(b != 0){
            sum = a ^ b;  // 无进位的加法
            int temp = (a & b) << 1;  // 进位，左移是为了下次加上进位
            a = sum;
            b = temp;
        }
        return sum;
    }
}
public class Addition_without_plus_sign {
}
