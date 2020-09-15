/**
编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：

    数字 1-9 在每一行只能出现一次。
    数字 1-9 在每一列只能出现一次。
    数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。

空白格用 '.' 表示。

Note:

    给定的数独序列只包含数字 1-9 和字符 '.' 。
    你可以假设给定的数独只有唯一解。
    给定数独永远是 9x9 形式的。

方法：搜索。注意，本题找到一种解法后需要及时返还（因为不需要存储结果又需要避免回溯）：
	一种方法是设置全局变量进行监督；另一个方法是提供返回值进行判断是否需要结束
 */
class solveSudoku2 {
    public void solveSudoku(char[][] board) {
        boolean[][] col = new boolean[9][9];
        boolean[][] row = new boolean[9][9];
        boolean[][][] tri = new boolean[3][3][9];

        List<int[]> list = new ArrayList<int[]>();
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0' - 1;
                    row[i][num] = col[j][num] = tri[i / 3][j / 3][num] = true;
                } else {
                    list.add(new int[]{i, j});
                }
            }
        }
        dfs(0, list, board, col, row, tri);
    }

    private boolean dfs(int index, List<int[]> list, char[][] board,
	                    boolean[][] col, boolean[][] row, boolean[][][] tri) {
        if (index == list.size()) {
            return true;
        }

        int[] t = list.get(index);
        int i = t[0];
        int j = t[1];
        for (int num = 0; num < 9; num ++) {
            if (!row[i][num] && !col[j][num] && !tri[i / 3][j / 3][num]) {

                board[i][j] = (char)(num + '0' + 1);
                row[i][num] = col[j][num] = tri[i / 3][j / 3][num] = true;

                // 若需要及时返还结果，一种方法是提供返回值进行判断
                if (dfs(index + 1, list, board, col, row, tri)) {
                    return true;
                }

                board[i][j] = '.';
                row[i][num] = col[j][num] = tri[i / 3][j / 3][num] = false;
            }
        }

        return false;
    }
}
class solveSudoku1 {
    public void solveSudoku(char[][] board) {
        boolean[][] col = new boolean[9][9];
        boolean[][] row = new boolean[9][9];
        boolean[][][] tri = new boolean[3][3][9];

        List<int[]> list = new ArrayList<int[]>();
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0' - 1;
                    row[i][num] = col[j][num] = tri[i / 3][j / 3][num] = true;
                } else {
                    list.add(new int[]{i, j});
                }
            }
        }
        dfs(0, list, board, col, row, tri);
    }

    boolean ok = false;  // 全局变量在找到解时及时终止搜索

    private void dfs(int index, List<int[]> list, char[][] board,
	                    boolean[][] col, boolean[][] row, boolean[][][] tri) {
        if (index == list.size()) {
            ok = true;
            return ;
        }

        int[] t = list.get(index);
        int i = t[0];
        int j = t[1];
        for (int num = 0; num < 9 && ! ok; num ++) {
            if (!row[i][num] && !col[j][num] && !tri[i / 3][j / 3][num]) {

                board[i][j] = (char)(num + '0' + 1);
                row[i][num] = col[j][num] = tri[i / 3][j / 3][num] = true;

                dfs(index + 1, list, board, col, row, tri);

                // 注意这里不需要重置board，否则最终结果也会改变
                row[i][num] = col[j][num] = tri[i / 3][j / 3][num] = false;
            }
        }
    } 
}