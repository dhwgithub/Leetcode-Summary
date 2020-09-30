/*
给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
提示：
    给定的树上的节点数介于 0 和 10^4 之间
    每个节点都有一个唯一整数值，取值范围从 0 到 10^8
    -10^8 <= val <= 10^8
    新值和原始二叉搜索树中的任意节点值都不同

方法：模拟。从根节点开始，当插入节点比当前节点小时，转入左子树判断，否则转入右子树判断
	左子树中：当为空时，直接创建插入节点返回原根节点即可；否则将当前节点移位，进入后面的循环进行判断
	右子树中：同上（移位操作分别移向左/右节点）
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        TreeNode res = root;
        while (root != null) {
            if (val < root.val) { // 放左子树
                if (root.left == null) {  // 无左子树则直接创建
                    root.left = new TreeNode(val);
                    return res;
                } else {  // 否则接着搜索判断
                    root = root.left;
                }
            } else {  // 放右子树
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    return res;
                } else {
                    root = root.right;
                }
            }
        }
        return res;
    }
}