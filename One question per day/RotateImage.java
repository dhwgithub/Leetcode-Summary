/*
You are given an n x n 2D matrix representing an image, 
rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, 
which means you have to modify the input 2D matrix directly. 
DO NOT allocate another 2D matrix and do the rotation.

Constraints:
    matrix.length == n
    matrix[i].length == n
    1 <= n <= 20
    -1000 <= matrix[i][j] <= 1000

题解：
	先沿对角线翻转，再沿竖直中线翻转
 */
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return ;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        // 沿对角线翻转
        for (int i = 0; i < row; i ++) {
            for (int j = i + 1; j < col; j ++) {
                matrix[i][j] = matrix[i][j] ^ matrix[j][i];
                matrix[j][i] = matrix[j][i] ^ matrix[i][j];
                matrix[i][j] = matrix[i][j] ^ matrix[j][i];
            }
        }

        // 沿竖直中线翻转
        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < (col >> 1); j ++) {
                matrix[i][j] = matrix[i][j] ^ matrix[i][col - j - 1];
                matrix[i][col - j - 1] = matrix[i][col - j - 1] ^ matrix[i][j];
                matrix[i][j] = matrix[i][j] ^ matrix[i][col - j - 1];
            }
        }
    }
}
/*
1 4 7
2 5 8
3 6 9

先沿对角线翻转，再沿竖直中线翻转
对角线翻转：
0, 1 -> 1, 0
0, 2 -> 2, 0
1, 2 -> 2, 1
竖直中线翻转:
0, 0 -> 0, col-1
1, 1 -> 1, 1, col-1-1
*/