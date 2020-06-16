package leetcode.hash_table_items;

import java.util.HashMap;

/**
 *  题意：
 *      请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *      注意：子串是连续的，子序列不是必须连续的
 *      s.length <= 40000
 *  思路：
 *      用一个哈希表（也可以用数组表示有限字符的位置）查询当前位置字符上一次出现的下标
 *      然后计算其相距长度，遍历一遍取最大值即可
 *      设置sum表示当前子串长度，ans表示答案，pre表示当前子串的开始位置
 *
 *      当哈希表不存在当前值 或者 上一个当前值坐标小于pre时：sum = sum + 1
 *      当存在当前值时：
 *          计算其相距长度，不过要记录当前子串开始的位置pre，不然之前存在于哈希表中的内容将会影响后续结果
 *          对于pre值，每次发现存在于哈希表中时，使得pre等于之前出现当前字符位置的下一个位置
 *          然后使得代表当前子串长度的sum值等于min(sum, i - pre + 1)
 *      每次遍历更新ans值并将当前字符加入哈希表
 */
class The_longest_substring_without_repeated_characters1 {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;

        char[] str = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        int ans = 1;
        int sum = 0;
        int pre = 0;
        for(int i=0; i<str.length; i++){
            char c = str[i];
            if(map.get(c) == null || map.get(c) < pre){
                sum ++;
            }else{
                pre = map.get(c) + 1;
                sum = Math.min(sum, i - pre + 1);
            }
            map.put(c, i);
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
public class The_longest_substring_without_repeated_characters {
}
