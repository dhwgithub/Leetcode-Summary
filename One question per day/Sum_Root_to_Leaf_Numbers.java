/*
给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
例如，从根到叶子节点路径 1->2->3 代表数字 123。
计算从根到叶子节点生成的所有数字之和。
说明: 叶子节点是指没有子节点的节点。

方法：从根节点开始，遇到非叶子节点则×10+节点值，返回左右节点的和，否则直接输出
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
class Solution {
    public int sumNumbers(TreeNode root) {
        return getAns(root, 0);
    }

    private int getAns(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        } 

        int v = sum * 10 + node.val;

        if (node.left == null && node.right == null) {
            return v;
        } else {
            return getAns(node.left, v) + getAns(node.right, v);
        }
    }
}