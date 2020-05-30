package leetcode.string_items;

/**
 *  题意：
 *      输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 *      为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
 *          无空格字符构成一个单词。
 *          输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *          如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  思路1：
 *      利用递归思路，每次将前面的一个单词移到最后
 *  思路2：
 *      利用StringBuilder进行分解组合
 */
class Flip_word_order2 {
    public String reverseWords(String s) {
        if(s.trim() == "" || s.length() == 1){
            return s.trim();
        }

        StringBuilder str = new StringBuilder();
        String[] st = s.split(" ");
        for(int i=st.length-1; i>=0; i--){
            if(st[i].length() != 0){
                str.append(st[i]).append(" ");
            }
        }
        return str.toString().trim();
    }
}
class Flip_word_order1 {
    public String reverseWords(String s) {
        if(s == null){
            return s;
        }
        s = s.trim();
        return flip_word_order(s).trim();
    }

    public String flip_word_order(String s){
        if(s == null || s.length() == 0){
            return "";
        }

        int i = 0;
        while(i < s.length() && s.charAt(i) != ' '){
            i += 1;
        }
        int j = i;
        while(j < s.length() && s.charAt(j) == ' '){
            j += 1;
        }
        return flip_word_order(s.substring(j)) + " " + s.substring(0, i);
    }
}
public class Flip_word_order {
}
