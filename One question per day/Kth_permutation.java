/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

    "123"
    "132"
    "213"
    "231"
    "312"
    "321"

给定 n 和 k，返回第 k 个排列。

说明：

    给定 n 的范围是 [1, 9]。
    给定 k 的范围是[1,  n!]。
 *
 *	方法：DFS+剪枝。由于按字典序递增的全排列排序方式，可以根据每一位确定后，由当前剩余的排列数来判断k是否位于这个区间
 *		（确定第一位后，剩余排列数为(n-1)!，同时注意k应该减去相应的数保证适应该区间，以此类推）而无需全排列所有区间
 *		参考：https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
 */

class getPermutation {
    public String getPermutation(int n, int k) {
        // 计算阶乘
        int[] fac = new int[n + 1];
        fac[0] = 1;
        for (int i = 1; i <= n; i ++) {
            fac[i] = fac[i - 1] * i;
        }

        boolean[] vis = new boolean[n + 1];
        StringBuffer str = new StringBuffer();

        dfs(1, k, n, fac, vis, str);

        return str.toString();
    }

    private void dfs(int cur, int k, int n, int[] fac, boolean[] vis, StringBuffer str) {
        if (cur > n) {
            return ;
        }

        int sum = fac[n - cur];  // 计算未确定数字的个数
        for (int i = 1; i <= n; i ++) {
            if (vis[i]) {
                continue;
            }
            if (k > sum) {  // 若查找的顺序大于（i之前的已经确定）当前以i开头的排列，则直接跳过该排列
                k -= sum;
                continue;
            }

            vis[i] = true;
            str.append(String.valueOf(i));
            dfs(cur + 1, k, n, fac, vis, str);  // 寻找下一个位置数字
            break;  // 找到后无需继续遍历
        }
    }
}