package leetcode.hash_table_items;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  题意：
 *      在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *      0 <= s 的长度 <= 50000
 *  思路1：
 *      利用有序哈希表LinkedHashMap，若出现一次为true，否则为false
 *  思路2：
 *      用数组记录每个字母次数，然后按序找到只出现一次的
 *  思路3：
 *      利用库函数，求最小的string.indexOf() == string.lastIndexOf()
 */
class The_first_character_that_appears_only_once3 {
    public char firstUniqChar(String s) {
        if(s == null){
            return ' ';
        }
        int index = s.length();
        for(char c='a'; c<='z'; c++){
            int n1 = s.indexOf(c);
            int n2 = s.lastIndexOf(c);
            if(n1 != -1 && n1 == n2){
                index = Math.min(index, n1);
            }
        }
        return index == s.length() ? ' ' : s.charAt(index);
    }
}
class The_first_character_that_appears_only_once2 {
    public char firstUniqChar(String s) {
        if(s == null){
            return ' ';
        }
        int[] num = new int[26];
        int len = s.length();
        for(int i=0; i<len; i++){
            num[s.charAt(i) - 'a'] += 1;
        }
        for(int i=0; i<len; i++){
            if(num[s.charAt(i) - 'a'] == 1){
                return s.charAt(i);
            }
        }
        return ' ';
    }
}
class The_first_character_that_appears_only_once1 {
    public char firstUniqChar(String s) {
        if(s == null){
            return ' ';
        }
        Map<Character, Boolean> map = new LinkedHashMap<Character, Boolean>();
        int len = s.length();
        for(int i=0; i<len; i++){
            char c = s.charAt(i);
            map.put(c, !map.containsKey(c));
        }
        for(Map.Entry<Character, Boolean> m : map.entrySet()){
            if(m.getValue() == true){
                return m.getKey();
            }
        }
        return ' ';
    }
}
public class The_first_character_that_appears_only_once {
}
