package leetcode.string_items;

/**
 *  题意：
 *      URL化。编写一种方法，将字符串中的空格全部替换为%20。
 *      假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
 *      （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 *      字符串长度在[0, 500000]范围内。
 *  思路：
 *      倒序组合，每遇到一个空格则进行变化，注意数组长度已给定
 *      给定的length表示该字符串的大小，而S的大小比length大（足以容下新增字符）
 *      因此从S的真实长度length开始倒序遍历，然后以S.length()长度尾部开始添加元素
 *      由于S数组足够大，因此最后返回的是String.valueOf(s, j+1, S.length()-(j+1))
 *      其中j表示URL化后的起始下标（最后一次操作多减了一次）
 */
class URLization1 {
    public String replaceSpaces(String S, int length) {
        char[] s = S.toCharArray();
        int j = S.length() - 1;
        for(int i=length-1; i>=0; i--){
            if(s[i] == ' '){
                s[j] = '0';
                s[j-1] = '2';
                s[j-2] = '%';
                j -= 3;
            }else{
                s[j] = s[i];
                j -= 1;
            }
        }
        return String.valueOf(s, j+1, S.length()-(j+1));
    }
}
public class URLization {
}
