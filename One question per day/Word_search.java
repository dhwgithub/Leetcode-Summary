/**
给定一个二维网格和一个单词，找出该单词是否存在于网格中。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
同一个单元格内的字母不允许被重复使用。
提示：
    board 和 word 中只包含大写和小写英文字母。
    1 <= board.length <= 200
    1 <= board[i].length <= 200
    1 <= word.length <= 10^3


方法：深搜。从第一个符合条件的位置开始，遍历其上下左右四个方向；注意回溯的时候对res值的使用
*/
class exist1 {
    public boolean exist(char[][] board, String word) {
        char[] arr = word.toCharArray();
        boolean[][] vis = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (board[i][j] == arr[0]) {
                    boolean f = dfs(0, i, j, board, arr, vis);
                    if (f) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(int index, int x, int y, char[][] board, char[] arr, boolean[][] vis) {
        if (index == arr.length) {
            return true;
        }
        if (x >= board.length || x < 0 || y >= board[0].length || y < 0) {
            return false;
        }
        if (vis[x][y] || board[x][y] != arr[index]) {
            return false;
        }

        vis[x][y] = true;

        boolean res = dfs(index + 1, x + 1, y, board, arr, vis) ||
            dfs(index + 1, x - 1, y, board, arr, vis) ||
            dfs(index + 1, x, y + 1, board, arr, vis) ||
            dfs(index + 1, x, y - 1, board, arr, vis);

        vis[x][y] = false;

        return res;
    }
}