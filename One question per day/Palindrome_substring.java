/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 
 * 方法一：暴力枚举（所有的回文子串）
 * 方法二：暴力枚举（所有的回文中心）
 * 方法三：“马拉车”算法（Manacher）
 * 		主要步骤是：	填充字符串使得变为奇数长度（注意填充方法）
 * 					设置p[i]、id和mr变量
 * 					在循环中，若当前i<=mr，则取min(id * 2 - i, mr - i)；否则是 1（当前位置即为回文中心点）
 * 					中心扩展，每次判断 (i + p[i]) 和 (i - p[i]) 是否相等，若相等则 p[i]++；否则退出扩展
 * 					更新中心点和mr，满足 (i + p[i]) > mr 时更新
 * 					累加求和，每次加 p[i] / 2，即为子串数量（除2是因为有等量#要消除）
 * 		原理参考：https://zhuanlan.zhihu.com/p/67171603
 *
 */
class countSubstrings3 {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("^#");
        int len = s.length();
        for (int i = 0; i < len; i ++) {
            sb.append(s.charAt(i));
            sb.append("#");
        }
        len = sb.length();  // 注意求长度的时机
        sb.append("$");  // 防止越界，并终止扩展
        char[] str = sb.toString().toCharArray();

        int[] p = new int[len + 5];
        int mr = 0;  // 最有边界
        int id = 0;  // 最右边界中点
        int ans = 0;

        for (int i = 1; i < len; i ++) {
            // 初始化p[i], 根据当前位置是否在最右边界内进行赋值
            // id * 2 - i 表示i相对于id的对称点坐标
            // 在最右边界以内，分为值等于对称值和与最右边界相邻两种情况
            p[i] = (i <= mr) ? Math.min(p[id * 2 - i], mr - i) : 1;
            // 中心扩展
            while (str[i + p[i]] == str[i - p[i]]) {
                p[i] ++;
            } 
            // 更新中心坐标和最右边界
            if (i + p[i] > mr) {
                id = i;
                mr = i + p[i];
            }

            ans += p[i] / 2;
            // ans = Math.max(ans, p[i] - 1) 为求最大回文子串
        }

        return ans;
    }
}
class countSubstrings2 {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int ans = 0;
        char[] arr = s.toCharArray();
        int len = arr.length * 2 - 1;
        for (int i=0; i<len; i++) {
            ans += extend(arr, i, i);  // 回文中心是奇数个
            ans += extend(arr, i, i+1);  // 回文中心是偶数个
        }

        return ans;
    }

    private int extend(char[] arr, int i, int j) {
        int sum = 0;
        while (i >= 0 && j < arr.length && arr[i] == arr[j]) {
            sum ++;
            i --;
            j ++;
        }
        return sum;
    }
}
class countSubstrings1 {
    public int countSubstrings(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }

        char[] str = s.toCharArray();
        int ans = str.length;

        for (int i=0; i<str.length; i++) {
            StringBuffer temp = new StringBuffer();
            temp.append(str[i]);
            for (int j=i+1; j<str.length; j++) {
                temp.append(str[j]);
                if (check(temp.toString().toCharArray())) {
                    ans ++;
                }
            }
        }

        return ans;
    }

    private boolean check(char[] arr) {
        int len = arr.length;
        for (int i=0; i<len/2; i++) {
            if (arr[i] != arr[len-i-1]) {
                return false;
            }
        }
        return true;
    }
}