/**
 * https://leetcode-cn.com/problems/count-binary-substrings/
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，
 * 并且这些子字符串中的所有0和所有1都是组合（挨着）在一起的。
 * 重复出现的子串要计算它们出现的次数。
 * 
 * 方法一：对0/1分别分组计数
 * 方法二：按组划分后取相邻两组最小值即为所求
*/
class countBinarySubstrings2 {
    public int countBinarySubstrings(String s) {
        int last = 0;  // 上一个0/1组合个数
        int ans = 0;
        int i = 0;
        char[] arr = s.toCharArray();

        int n = arr.length;
        while(i < n) {
            char c = arr[i];
            int cnt = 0;
            while(i < n && arr[i] == c){
                i ++;
                cnt ++;
            }
            ans += cnt < last ? cnt : last;  // 取最小值即为相等的组数
            last = cnt;
        }
        return ans;
    }
}
class countBinarySubstrings1 {
    public int countBinarySubstrings(String s) {
        int n0 = 0;
        int n1 = 0;
        int pre = -1;
        int ans = 0;
        char[] arr = s.toCharArray();
        for (int i=0; i<arr.length; i ++){
        	int k = arr[i] - '0';
            if (k == 0){
                n0 += 1;
                if (pre == 1){
                    n0 = 1;
                }
            }
            if (k == 1){
                n1 += 1;
                if (pre == 0){
                    n1 = 1;
                }
            }
            
            if (k == 0 && n0 <= n1){
                ans += 1;
            }
            if (k == 1 && n1 <= n0){
                ans += 1;
            }

            pre = k;
        }
        return ans;
    }
}