/**
 * 让我们一起来玩扫雷游戏！
 * 给定一个代表游戏板的二维字符矩阵。
 *  'M' 代表一个未挖出的地雷，
 *  'E' 代表一个未挖出的空方块，
 *  'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，
 *  数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：

    如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
    如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
    如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
    如果在此次点击中，若无更多方块可被揭露，则返回面板。
    
    方法：模拟
 *
 */

class updateBoard1 {
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0},
                {-1, 1}, {1, 1}, {-1, -1}, {1, -1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        dfs(board, click);
        return board;
    }

    private void dfs(char[][] b, int[] click) {
        int x = click[0];
        int y = click[1];
        char c = b[x][y];
        if (c == 'B') {
            return ;
        }
        if (c == 'M') {
            b[x][y] = 'X';
            return ;
        }

        int num = cntDigit(b, x, y);
        if (num > 0) {
            b[x][y] = (char)(num + '0');
            return ;
        } 
        
        b[x][y] = 'B';
        
        for (int i = 0; i < 8; i ++) {
            int u = x + dir[i][0];
            int v = y + dir[i][1];
            if (u < 0 || u >= b.length || v < 0 || v >= b[0].length) {
                continue;
            }
            if (b[u][v] != 'E') {
                continue;
            }
            dfs(b, new int[]{u, v});
        }
    }

    private int cntDigit(char[][] b, int x, int y) {
        int num = 0;
        for (int i = 0; i < 8; i ++) {
            int u = x + dir[i][0];
            int v = y + dir[i][1];
            if (u < 0 || u >= b.length || v < 0 || v >= b[0].length) {
                continue;
            }
            if (b[u][v] == 'M') {
                num ++;
            }
        }
        return num;
    }
}