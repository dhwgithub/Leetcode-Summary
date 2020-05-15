package leetcode.bit_manipulation_items;

/**
 * 题目：
 *      给定两个32位整数N,M和两个表示比特位置的整数i,j，将M覆盖到N上指定位置，多余部分用0填充
 *      其中指定位置是N中从右数i位到j位依次覆盖，即位置范围（非下标）：[i+1, j+1]
 * 思路：
 *      将指定位置[i+1, j+1]全部置零，也就是构造mask掩码，然后将M移位i位后相加（或运算）即可
 *      Java需要注意，对于直接移位32位是循环移位而不是补充0，因此需要将 1<<j+1 改成 1<<j<<1
 *      关于Java这点main函数有实际运行效果
 *
 */
class insert1 {
    public int insertBits(int N, int M, int i, int j) {
        int mask = ((1 << j) << 1) - (1 << i);
        return (N & (~mask)) | (M << i);  // 或相加都可以
    }
}
public class insert {
    public static void main(String[] args) {
        int k = 0b01111001001000011001001101101001;
        System.out.println("原始k二进制  " + Integer.toBinaryString(k));          // 1111001001000011001001101101001
        System.out.println("直接移位32位 " + Integer.toBinaryString(k<<32));    // 1111001001000011001001101101001
        System.out.println("间接移位32位 " + Integer.toBinaryString(k<<31<<1)); // 0
    }
}
