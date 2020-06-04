package leetcode.math_items;

/**
 *  题意：
 *      写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 *      首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 *      当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 *      假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 *      该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *      注意：
 *          假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 *          在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *      说明：
 *          假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。
 *          如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *  思路：
 *      首先将字符串转化为好处理的字符数组，然后令ans = 1（long），设置符号位(0)和数字位(false)（表示还没找到）
 *      然后从第一个开始遍历字符：
 *          若为空字符，且符号位、数字位都没有被找到，直接跳过
 *          若找到"+"或"-"且符号位为0，则令ans乘以相应的±1，并设置符号位为对应±1
 *          若找到数字，数字位为假时直接乘以该数字；否则乘10 再加上 该数字乘以opt，（若符号位为0）并令符号位为1
 *          若找到其他非空字符，退出遍历
 *      进行结果判断：
 *          若该数字大于int最大范围则返回int最大值，反之同理（循环中判定，否则也可能超long）
 *          若数字位为假，返回0
 *          若数字位为真，返回ans
 */
class Convert_string_to_integer1 {
    public int strToInt(String str) {
        char[] s = str.toCharArray();
        long ans =  1;
        int opt = 0;
        boolean dig = false;

        int len = s.length;
        for(int i=0; i<len; i++){
            char c = s[i];
            if(c == ' ' && dig == false && opt == 0) continue;
            else if(c == '+' && opt == 0) opt = 1;
            else if(c == '-' && opt == 0){
                ans = -1;
                opt = -1;
            }
            else if(c >= '0' && c <= '9'){
                if(dig == false){
                    ans *= (c - '0');
                    dig = true;
                    if(opt == 0) opt = 1;
                }
                else{
                    ans = ans * 10 + (c - '0') * opt;
                    if(ans > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                    else if(ans < Integer.MIN_VALUE) return Integer.MIN_VALUE;
                }
            }
            else{
                break;
            }
        }
        if(dig == false) return 0;
        else return (int)ans;
    }
}

public class Convert_string_to_integer {
}
