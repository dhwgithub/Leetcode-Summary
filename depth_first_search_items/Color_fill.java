package leetcode.depth_first_search_items;

/**
 *  题意：
 *      将与指定位置的点相连的区域填涂指定颜色（值），相连区域是指上下左右有连接的区域
 *  思路：
 *      从指定点开始，上下左右开始搜索，每搜索到一个与指定位置一样的点则填涂指定颜色
 *      不需要填涂的点有：
 *          非指定颜色的点
 *          与新颜色一样的点
 *          超出边界的点
 */
class Color_fill1 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int r = image.length;
        int c = image[0].length;
        dfs(image, sr, sc, r, c, image[sr][sc], newColor);
        return image;
    }

    public void dfs(int[][] image, int x, int y, int r, int c, int src, int newColor){
        if(x<0 || x>=r || y<0 || y>=c){
            return ;
        }
        if(image[x][y] != newColor && image[x][y] == src){
            image[x][y] = newColor;
        }else{
            return ;
        }

        dfs(image, x+1, y, r, c, src, newColor);
        dfs(image, x-1, y, r, c, src, newColor);
        dfs(image, x, y+1, r, c, src, newColor);
        dfs(image, x, y-1, r, c, src, newColor);
    }
}
public class Color_fill {
}
