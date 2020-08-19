/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * 
 * 方法：求每个节点的左右子树高度，若满足条件则返回其高度，否则返回-1，表示不符合
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
class isBalanced {
    public boolean isBalanced(TreeNode root) {
        return getNum(root) >= 0;
    }

    private int getNum(TreeNode node) {
        if (node == null) return 0;

        int left_node = getNum(node.left);
        int right_node = getNum(node.right);

        if (left_node >= 0 && right_node >= 0 && Math.abs(left_node - right_node) <= 1) {
            return Math.max(left_node, right_node) + 1;
        } else {
            return -1;
        }
    }
}