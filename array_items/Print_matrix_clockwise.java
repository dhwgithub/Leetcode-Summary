package leetcode.array_items;

/**
 *  题意：
 *      输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *      0 <= matrix.length <= 100
 *      0 <= matrix[i].length <= 100
 *  思路：
 *      定义上下左右四个边界，然后从上往下，从左往右依次顺时针遍历。
 *      遍历的时候注意是否需要结束，若结束即上边界与下边界冲突或左边界与右边界冲突退出遍历
 *      同时每次遍历一个方向结束后更改对应边界线位置（注意每次遍历完防止越界）
 *
 */
class Print_matrix_clockwise1 {
    public int[] spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return new int[0];
        }

        int t = 0, d = matrix.length-1, l = 0, r = matrix[0].length-1;
        int maxn = matrix.length * matrix[0].length;  // 计算res大小
        int[] res = new int[maxn];
        int sum = 0;  // 记录res下标
        while(t <= d && l <= r){
            for(int i = l; i <= r; i ++){
                res[sum ++] = matrix[t][i];
            }
            t += 1; // 上边界变化
            if(sum == maxn) break;

            for(int i = t; i <= d; i ++){
                res[sum ++] = matrix[i][r];
            }
            r -= 1;
            if(sum == maxn) break;

            for(int i = r; i >= l; i --){
                res[sum ++] = matrix[d][i];
            }
            d -= 1;
            if(sum == maxn) break;

            for(int i = d; i >= t; i --){
                res[sum ++] = matrix[i][l];
            }
            l += 1;
        }
        return res;
    }
}
public class Print_matrix_clockwise {
}
