package leetcode.tree_items;

/**
 * 题目：
 *      判断一棵二叉树是否是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * 思路：
 *      分别求取左、右子树的深度，然后比较大小并返回结果
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }

        int left = getDeep(root.left);
        int right = getDeep(root.right);

        if(Math.abs(left - right) > 1){
            return false;
        }else{
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int getDeep(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = 0;
        if(root.left != null){
            left = getDeep(root.left);
        }
        int right = 0;
        if(root.right != null){
            right = getDeep(root.right);
        }

        return Math.max(left, right) + 1;
    }
    /**
     * Definition for a binary tree node.
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
public class Balanced_binary_tree {
}
