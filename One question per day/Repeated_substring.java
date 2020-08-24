/**
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。
 * 给定的字符串只含有小写英文字母，并且长度不超过10000。
 * 
 * 方法一：枚举每一个循环起点，然后一一对比判断即可。循环最多到中点，判断时注意子串长度是母串的倍数
 * 		注意串"abab" "abaabaaba" "aabaaba"
 *方法二：KMP。判断字符串s，则从ss[1:ss.len-1]中（即s的2倍从第2个字符到倒数第2个字符）找是否有s
 *	若有，则表示一定存在重复子串，否则没有重复子串
 */
class repeatedSubstringPattern2 {
    public boolean repeatedSubstringPattern(String s) {
        return KMP(s+s, s);  // 其中主串左右边界需要调整
    }

    private boolean KMP(String s, String t) {
        int n = s.length() - 1;  // 截止到倒数第2个位置，开始于第2个位置
        int m = t.length();
        int[] next = getNext(t);

        int i = 1;
        int j = 0;
        while(i < n) {
            while (j != -1 && s.charAt(i) != t.charAt(j)) {
                j = next[j];
            }

            i ++;
            j ++;

            if (j == m) {
                return true;
            }
        }

        return false;
    }

    private int[] getNext(String t) {
        int m = t.length();
        int[] next = new int[m + 1];
        next[0] = -1;  // 表明子串都无法匹配，需要主串后移一位

        int i = 0;
        int j = -1;  // 第一个字符无需比较

        while (i < m) {
            while (j != -1 && t.charAt(i) != t.charAt(j)) {  // 第一个无需比较，当不一致时回溯到最近
                j = next[j];
            }
            next[++i] = ++j;  // 更新next
        }

        return next;
    }
}
class repeatedSubstringPattern1 {
    public boolean repeatedSubstringPattern(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        
        for (int i = 1; i <= len / 2; i ++) {  // 最多遍历一遍即可
            int x = 0;
            int y = i;
            if (arr[x] == arr[y] && len % y == 0) {  // 寻找循环点，并且子串大小是母串的倍数
                while (y < len && arr[x] == arr[y]) {
                    x ++;
                    y ++;
                }
                if (y == len) {
                    return true;
                }
            }
        }
        return false;
    }
}