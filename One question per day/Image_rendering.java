/**
 * 给定一个二维矩阵，表示一幅图像。给定一个点和一个像素值，将与这个点初始像素值相同
 * 并且相邻（和相邻的相邻点）的点换为给定的像素值
 *
 *方法：DFS或BFS都可，注意当原像素值和给定像素值相同时要特殊处理，否则超时
 */

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null) return null;
        if (image[sr][sc] == newColor) return image;  // 注意：否则超时

        fill(sr, sc, image[sr][sc], newColor, image);
        
        // LinkedList<int[]> queue = new LinkedList<int[]>();
        // queue.add(new int[]{sr, sc});

        // int newc = image[sr][sc];
        // int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // while ( !queue.isEmpty()) {
        //     int[] pos = queue.pop();
        //     int x = pos[0];
        //     int y = pos[1];
        //     image[x][y] = newColor;

        //     for (int i=0; i<4; i++) {
        //         int u = x + dir[i][0];
        //         int v = y + dir[i][1];
        //         if (u < 0 || u >= image.length || v < 0 || v >= image[0].length) continue;
        //         if (image[u][v] != newc) continue;
        //         queue.add(new int[]{u, v});
        //     }
        // }
        return image;
    }

    private void fill(int i, int j, int oldc, int newc, int[][] img) {
        if (img[i][j] != oldc) return ;

        img[i][j] = newc;

        int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for (int u=0; u<4; u++) {
            int x = i + dir[u][0];
            int y = j + dir[u][1];
            if (x < 0 || x >= img.length || y < 0 || y >= img[0].length) continue;
            if (img[x][y] != oldc) continue;
            fill(x, y, oldc, newc, img);
        }
    }
}