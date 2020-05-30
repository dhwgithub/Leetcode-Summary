package leetcode.string_items;

/**
 *  题意：
 *      字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 *      字符串长度在[0, 100000]范围内。
 *  思路：
 *      复制一遍s1，即可判断.可用 contains 或 indexOf 来判断
 */
class String_rotation1 {
    public boolean isFlipedString(String s1, String s2) {
        if(s2.equals("") && s1.equals(s2) == false){
            return false;
        }
        return (s1 + s1).contains(s2);
    }
}
public class String_rotation {
}
