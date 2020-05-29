package leetcode.string_items;

import java.util.HashMap;
import java.util.Map;

/**
 *  题意：
 *      给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 *      回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 *      回文串不一定是字典当中的单词。
 *  思路1：
 *      利用HashMap，只要最多一个字符存在奇数时可以满足题意，然后统计次数即可
 *  思路2：
 *      直接用数组存字符Ascii码，大小为256即可。判断奇偶可以用位运算。
 *      判断条件同上，在此基础上不再统计次数，采用消除法
 */
class Palindrome2 {
    public boolean canPermutePalindrome(String s) {
        if(s == null){
            return true;
        }
        int[] num = new int[256];
        int len = s.length();
        int ok = 0;
        for(int i=0; i<len; i++){
            num[s.charAt(i)] += 1;
            if((num[s.charAt(i)] & 1) == 1){
                ok += 1;
            }else{
                ok -= 1;
            }
        }
        return ok > 1 ? false : true;
    }
}

class Palindrome1 {
    public boolean canPermutePalindrome(String s) {
        if(s == null){
            return true;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int len = s.length();
        for(int i=0; i<len; i++){
            char c = s.charAt(i);
            if( !map.containsKey(c)){
                map.put(c, 1);
            }else{
                map.put(c, map.get(c)+1);
            }
        }

        int ok = 0;
        for(Map.Entry<Character, Integer> m : map.entrySet()){
            int t = m.getValue();
            if(t % 2 == 1){
                ok += 1;
            }
            if(ok > 1){
                return false;
            }
        }
        return true;
    }
}

public class Palindrome {
}
