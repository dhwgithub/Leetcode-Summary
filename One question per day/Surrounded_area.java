/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 
 * 方法一：遍历边缘位置，将与边缘相邻的全部标记为不可修改，然后最后遍历一遍矩阵变化即可
 * 方法二：不使用额外数组，直接在原矩阵将不可变换标记为另一符号，最后遍历时做相应修改即可
 */

class solve1 {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return ;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] frozen = new boolean[m+5][n+5];  // 将不需要变化的'O'标记为真
        for (int i=0; i<n; i++) {
            dfs(0, i, board, frozen, m, n);
            dfs(m-1, i, board, frozen, m, n);
        }
        for (int i=0; i<m; i++) {
            dfs(i, 0, board, frozen, m, n);
            dfs(i, n-1, board, frozen, m, n);
        }

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == 'O' && frozen[i][j] == false) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(int i, int j, char[][] board, boolean[][] frozen, int m, int n){
        if (board[i][j] == 'X' || frozen[i][j] == true) {
            return ;
        }

        frozen[i][j] = true;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int u=0; u<4; u++) {
            int x = i + dir[u][0];
            int y = j + dir[u][1];
            if (x < 0 || x >= m || y < 0 || y >= n
                || frozen[x][y] == true || board[x][y] == 'X') {
                continue;
            }
            dfs(x, y, board, frozen, m, n);
        }
    }
}