/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
 * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 *
 *	方法：模拟，注意考虑周全，具体解释见代码。
 *	由题分析只需要记录e/E和.的个数即可，其他根据相对位置判断是否合理
 */

class isNumber {
    public boolean isNumber(String s) {
        // e+E <= 1 且后面必须有数字
        // . <= 1，e+E后无
        int point = 0;
        int e = 0;
        char[] arr = s.toCharArray();
        int len = arr.length;

        // 去除前后空格
        // 只有空格返回false
        int i = 0;
        while (i < len && arr[i] == ' ') i ++;
        while (len >= 1 && arr[len - 1] == ' ') len --;
        if (i >= len) {
            return false;
        }

        int first = i;
        for ( ; i < len; i ++) {
            char c = arr[i];
            if (c == 'e' || c == 'E') {
                e ++;
                // e/E 最多出现一次
                // 前面要么是数字要么是小数点
                // 后面要么跟一个数字，要么后面是正负号
                if (e == 1 && (i - 1 >= 0 && (isDigit(arr[i - 1]) || arr[i - 1] == '.'))
                && ((i + 1 < len && isDigit(arr[i + 1]))
                || (i + 2 < len && (arr[i + 1] == '+' || arr[i + 1] == '-')))) {
                    continue;
                } else {
                    return false;
                }
            } else if (c == '.') {
                point ++;
                // 最多一个
                // e/E 为0
                // 前后至少有一个数字(.1  3. 46.e3)
                if (point == 1 && e == 0 && ((i - 1 >= 0 && isDigit(arr[i - 1]))
                || (i + 1 < len && isDigit(arr[i + 1])))) {
                    continue;
                } else {
                    return false;
                }
            } else if (c == '-' || c == '+') {
                // 后面要么是数字要么是小数点（+.5）
                // 只能在第一位或者e/E后第一位
                if (i + 1 < len && (isDigit(arr[i + 1]) || arr[i + 1] == '.')
                && (i == first || (i - 1 >= 1 && (arr[i - 1] == 'e' || arr[i - 1] == 'E')))) {
                    continue;
                } else {
                    return false;
                }
            } else if (isDigit(c)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
    private boolean isDigit(char c) {
        return c <= '9' && c >= '0';
    } 
}