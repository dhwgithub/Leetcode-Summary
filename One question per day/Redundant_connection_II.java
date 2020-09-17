/**
在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。
每一个节点只有一个父节点，除了根节点没有父节点。
输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，
其中 u 是 v 的一个父节点。
返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。

注意:
    二维数组大小的在3到1000范围内。
    二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。

思路：如题，要使得形成一棵树，除了根节点所有节点入度都是1，而本题多了一条边，因此存在两种情况：
	一是存在一个入度为2的点所连接的边，且不形成环，找到最后一条（题意）
	二是入度都为1，找到一条边若去除不形成环，找到最后一条（题意）
	倒序遍历可满足找到最后一条
	
	对于不形成环，可用并查集连接查找，若连接重复说明形成了环，否则直接连接即可
 */

class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int len = edges.length;

        int[] in = new int[len + 1];  // 记录入度
        for (int[] edge : edges) {
            in[edge[1]] ++;
        }
        
        // 判断存在入度为2的情况，返回最后一个删除前使得成环的边        
        for (int i = len - 1; i >= 0; i --) {
            if (in[edges[i][1]] == 2 && ! judgeIsCircle(edges, i)) {
                return edges[i];
            }
        }

        // 判断入度为1的情况，返回最后一个使得删除前成环的边（顺序不能反）
        for (int i = len - 1; i >= 0; i --) {
            if (in[edges[i][1]] == 1 && ! judgeIsCircle(edges, i)) {
                return edges[i];
            }
        }

        return new int[2];
    }

    // 判断去掉边num是否构成环
    public boolean judgeIsCircle(int[][] edges, int num) {
        int len = edges.length;
        UnionFind uf = new UnionFind(len);
        for (int i = 0; i < len; i ++) {
            if (i == num) continue;  // 去掉指定边来判断是否成环
            if ( ! uf.unionXY(edges[i][0], edges[i][1])) {  // 合并失败表示构成环
                return true;
            }
        }
        return false;
    }

    public class UnionFind {
        private int[] f = null;

        public UnionFind(int n) {
            f = new int[n + 1];
            for (int i = 1; i <= n; i ++) {
                f[i] = i;
            }
        }

        // 路径压缩
        public int find(int x) {
            return x == f[x] ? f[x] : (f[x] = find(f[x]));
        }

        // 合并。若合并成功，即不存在环则返回true
        public boolean unionXY(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            } else {
                f[x] = y;
                return true;
            }
        }
    }
}