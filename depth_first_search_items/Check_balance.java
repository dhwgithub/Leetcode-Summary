package leetcode.depth_first_search_items;

/**
 *  题目：
 *      检查一棵二叉树是否是平衡二叉树。平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
 *  思路：
 *      求出每一个节点左右子树的深度，比较大小
 */
class Check_balance1 {
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }

        int leftNum = getDeepNum(root.left, 1);
        int rightNum = getDeepNum(root.right, 1);

        if(Math.abs(leftNum - rightNum) <= 1){
            return isBalanced(root.left) && isBalanced(root.right);
        }else{
            return false;
        }
    }

    public int getDeepNum(TreeNode node, int num){
        if(node == null){
            return num;
        }

        int leftNum = getDeepNum(node.left, num);
        int rightNum = getDeepNum(node.right, num);

        return Math.max(leftNum, rightNum) + 1;
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

public class Check_balance {
}
