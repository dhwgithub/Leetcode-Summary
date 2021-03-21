/**
给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

进阶：

    一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
    一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
    你能想出一个仅使用常量空间的解决方案吗？

题解：
    法1：用集合记录每行/列情况，两遍遍历即可
    法2：用两个变量记录第一行/列是否有0，然后令第一行和第一列分别作为整个矩阵的行标记和列标记
        最后对除第一行/列的所有元素进行置0，并对第一行和第一列根据标记变量做相应处理
    法3：用一个变量作为第一列的标记，然后令第一列作为整个矩阵的行标记。然后矩阵置0（除第一行/列）
        最后根据标记变量处理第一列情况，且注意一定先处理第一行再处理第一列
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        boolean rowF = false;
        boolean colF = false;
        int row = matrix.length;
        int col = matrix[0].length;

        // 标记第一行/列是否有0
        for (int i = 0; i < col; i ++) {
            if (matrix[0][i] == 0) {
                rowF = true;
                break;
            }
        }
        for (int i = 0; i < row; i ++) {
            if (matrix[i][0] == 0) {
                colF = true;
                break;
            }
        }

        // 将第一行和第一列作为标记位
        for (int i = 1; i < row; i ++) {
            for (int j = 1; j < col; j ++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 除第一行/列进行置0
        for (int i = 1; i < row; i ++) {
            for (int j = 1; j < col; j ++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 对第0行/列进行置0
        if (rowF) {
            for (int i = 0; i < col; i ++) {
                matrix[0][i] = 0;
            }
        }
        if (colF) {
            for (int i = 0; i < row; i ++) {
                matrix[i][0] = 0;
            }
        }
    }
}

class Solution {
    public void setZeroes(int[][] matrix) {
        boolean colF = false;
        int row = matrix.length;
        int col = matrix[0].length;

        // 标记第一列
        for (int i = 0; i < row; i ++) {
            if (matrix[i][0] == 0){
                colF = true;
                break;
            }
        }
        // 令 matrix[0][0] 标记第一行
        for (int i = 0; i < col; i ++) {
            if (matrix[0][i] == 0) {
                matrix[0][0] = 0;
                break;
            }
        }

        // 对矩阵进行标记
        for (int i = 1; i < row; i ++) {
            for (int j = 1; j < col; j ++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 矩阵置0
        for (int i = 1; i < row; i ++) {
            for (int j = 1; j < col; j ++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 第一行/列置0，且首先对第一行做处理
        if (matrix[0][0] == 0) {
            for (int i = 1; i < col; i ++) {
                matrix[0][i] = 0;
            }
        }
        if (colF) {
            for (int i = 0; i < row; i ++) {
                matrix[i][0] = 0;
            }
        }
    }
}