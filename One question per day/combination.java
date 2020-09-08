/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 *	方法：DFS+剪枝。递归数字1到n，当考虑当前位置时加入list，否则直接跳到下一位，直到满足达到k位。
 *		剪枝：当已有数字加上为遍历数字之和小于k时，可以直接返回，不可能存在满足条件的情况。
 */

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        dfs(1, list, ans, k, n);
        return ans;
    }

    private void dfs(int cur, List<Integer> list, List<List<Integer>> ans, int k, int n) {
        if (list.size() + (n - cur + 1) < k) {
            return ;
        }
        if (list.size() == k) {
            ans.add(new ArrayList<Integer>(list));
            return ;
        }

        // 加入当前位置
        list.add(cur);
        dfs(cur + 1, list, ans, k, n);
        list.remove(list.size() - 1);

        // 不加入当前位置
        dfs(cur + 1, list, ans, k, n);
    }
}