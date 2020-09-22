/**
给定一个二叉树，我们在树的节点上安装摄像头。
节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
计算监控树的所有节点所需的最小摄像头数量。

思路：由于要每个节点都被监控到，可以得出每个节点都只有一下三种状态:
	0：无摄像头，且未被监控
	1：无摄像头，且被监控
	2：有摄像头（且被监控）
	
递归搜索，这样是由下往上开始判断的，当前节点的状态应该有其直接子节点状态来决定
其中空节点状态应该为1，这样就无需叶节点必须安装摄像头，同时也不违背提议；
最后需要判断根节点状态，若根节点未被监控，则摄像头数量应该+1
（具体实现思路见代码）

参考：https://leetcode-cn.com/problems/binary-tree-cameras/solution/dfsjavajian-dan-yi-dong-by-rorke76753/

 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class minCameraCover{
    public int minCameraCover(TreeNode root) {
        int[] sum = new int[1];
        // 若当前节点未被监控，则摄像头数量+1
        return dfs(root, sum) == 0 ? sum[0] + 1 : sum[0];
    }

    // 每个节点只有有三种状态
    // 0：无摄像头，当前未被监控
    // 1：无摄像头，当前被监控
    // 2：有摄像头
    // 针对以上三种状态，当前节点相应返回需要的状态
    private int dfs(TreeNode node, int[] sum) {
        if (node == null) {  // 空节点：无摄像头，假设被监控（这样无需叶节点必须安装摄像头）
            return 1;
        }

        // 求取子节点状态，进而判断当前节点应该满足什么状态
        int leftStatus = dfs(node.left, sum);
        int rightStatus = dfs(node.right, sum);

        // 以下状态都是在sum最小时下做出的判断
        if (leftStatus == 0 || rightStatus == 0) {
            sum[0] ++;
            return 2;  // 子节点无摄像头且未被监控，则当前节点应该有摄像头
        } else if (leftStatus == 2 || rightStatus == 2) {
            return 1;  // 子节点有摄像头，则当前节点应该是无摄像头且被监控状态
        } else {  
            return 0;  // 子节点无摄像头且被监控，则当前节点应该无摄像头（由于子节点无摄像头则当前未被监控）
        }
    }
}