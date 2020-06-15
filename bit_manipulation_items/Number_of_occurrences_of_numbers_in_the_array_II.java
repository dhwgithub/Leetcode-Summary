package leetcode.bit_manipulation_items;

/**
 *  题意：
 *      在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *      1 <= nums.length <= 10000
 *      1 <= nums[i] < 2^31
 *  思路1：
 *      由题意，将每个二进制位数都对应相加，然后对3取模，那么其结果即为只出现一次的那个数
 *      由此可以遍历32位整数，然后计算其结果
 *  思路2：
 *      有限状态机 + 卡诺图。
 *      用两个数a,b来记录二进制中1出现的个数，且每次达到3的倍数时归0，那么ab的值为00,01,10,00...等3中不同的状态
 *      而只有当前二进制位c为1时，状态才发生改变，如下（a'、b'是变换后的状态值）
 *      ab   c   a'b'   c   a'b'
 *      00   1   01     0   00
 *      01   1   10     0   01
 *      10   1   00     0   10
 *      11   1   xx     0   xx
 *      其中x代表不关心该状态。接着对每一个状态进行求解，便于编程实现，首先对状态a计算：
 *          c   0   1
 *      ab
 *      00      0   0
 *      01      0   1
 *      10      1   0
 *      11      x   x
 *      其次，我们按照格雷码（相邻数只有一位变换）排序进行索引表示：
 *          c   0   1
 *      ab
 *      00      0   0
 *      01      0   1
 *      11      x   x
 *      10      1   0
 *      下面，我们就可以进行最小项合并了。其算法如下：
 *      迭代圈出满足如下条件的矩形，直至全部1都被圈中：
 *          1.该矩形中至少包含1个未被圈中的1
 *          2.该矩形中不包含0
 *          3.该矩形由2^n个小方块组成（即其长宽只能是1、2、4、8...）
 *          4.能圈多大就圈多大
 *          注意，表中最上面和最下面是相连的，最左边和最右边也是相连的。
 *      然后，我们就可以根据这个画出来的卡诺图来推导出对应的位运算表达式啦，被推导出的表达式应当是与—或表达式的形式。
 *      [下述表达式均为go语言语法，其中按位取反 ^a 在java中用 ~a 表示，直接替换即可]
 *      根据a状态可以推得表达式：a=a(杠c)+bc，即 a = a&^c | b&c；接着求b状态的值
 *
 *      最后，回到原题，我们对每一个数进行处理，最后返回状态为01的位，即返回(杠a)b，即^a&b。
 *
 *      参考：
 *      https://www.bilibili.com/video/BV1c4411b7mR?from=search&seid=1967376032407687077
 *      https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/zhuang-tai-ji-yu-qia-nuo-tu-wei-yun-suan-de-tui-da/
 */
class Number_of_occurrences_of_numbers_in_the_array_II2 {
    public int singleNumber(int[] nums) {
        int a = 0;
        int b = 0;  // 低位
        for(int c : nums){
            int t = a;
            a = (a & (~c)) | (b & c);
            b = (b & (~c)) | ((~t) & (~b) & c);
        }
        return (~a) & b;
    }
}
class Number_of_occurrences_of_numbers_in_the_array_II1 {
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i=0; i<32; i++){
            int sum = 0;
            int key = 1 << i;
            for(int j=0; j<nums.length; j++){
                if((nums[j] & key) != 0) sum += 1;
            }
            if(sum % 3 == 1) res |= key;
        }
        return res;
    }
}
public class Number_of_occurrences_of_numbers_in_the_array_II {
}
