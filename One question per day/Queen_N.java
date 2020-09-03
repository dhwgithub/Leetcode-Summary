/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 
 * 方法：递归回溯，从第1行开始搜，一旦搜到最后一行即为一个解法。
 * 	优化：提供二维数组进行辅助判断会更快
 *
 */

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<List<String>>();
        if (n < 1) {
            return list;
        }

        int[] flag = new int[n];
        Arrays.fill(flag, -1);
        // 遍历每一行，保证每行无需判断
        dfs(0, flag, new ArrayList<String>(), list, n);
        return list;
    }

    // int[] flag  保存每列皇后存在的行数，-1表示该列没有皇后
    private void dfs(int row, int[] flag, List<String> list, List<List<String>> ans, int n) {
        if (row >= n) {
            ans.add(new ArrayList<String>(list));
            return ;
        }

        for (int col = 0; col < n; col ++) {
            // 判断每列和斜线是否有皇后
            if (flag[col] == -1 && isXie(row, col, list, n, flag)) {
                flag[col] = row;
                list.add(getString(col, n));
                dfs(row + 1, flag, list, ans, n);
                list.remove(list.size() - 1);
                flag[col] = -1;
            }
        }
    }

    private String getString(int c, int n) {
        StringBuffer t = new StringBuffer();
        for (int i = 0; i < n; i ++) {
            if (i == c) {
                t.append("Q");
            } else {
                t.append(".");
            }
        }
        return t.toString();
    }

    private boolean isXie(int r, int c, List<String> list, int n, int[] flag) {
        // 同一斜行 列数减行数相等 或 列数加行数相等
        // 00 01 02 03
        // 10 11 12 13
        // 20 21 22 23
        // 30 31 32 33
        for (int i = c - 1; i >= 0; i --) {
            if (flag[i] != -1 && c - r == i - flag[i]) {
                return false;
            }
        }
        for (int i = c + 1; i < n; i ++) {
            if (flag[i] != -1 && c + r == i + flag[i]) {
                return false;
            } 
        }
        return true;
    }
}