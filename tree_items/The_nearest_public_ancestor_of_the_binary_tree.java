package leetcode.tree_items;

/**
 * 题意：
 *      给定一个二叉树和其中的两个不同节点，求最近公共祖先节点。其中本身也可以为公共节点，且二叉树所有节点唯一
 * 思路：
 *      问题是从根节点r找p、q两结点的最近公共节点，对函数lowestCommonAncestor(root, p, q)可以分为以下情况：
 *          1、p、q是r的左右节点，则直接返回r
 *          2、p、q是r左子树中的节点，则返回lowestCommonAncestor(root.left, p, q)
 *          3、p、q是r右子树中的节点，则返回lowestCommonAncestor(root.right, p, q)
 *          对于后两种情况，只要一项为空，那么另一项一定可以找到最近公共祖先并返回
 *      依次递归下去即可，对于递归结束的条件有如下几点：
 *          若r为空，表示没有公共节点，返回r(即null)
 *          若r==p，表示p在同一子树上先被遍历，则p为最近公共祖先，返回r(即p)
 *          若r==q，同上返回r
 *          综上，上述三种情况只要满足一点即可返回r
 */
class The_nearest_public_ancestor_of_the_binary_tree1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }

        return root;
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
public class The_nearest_public_ancestor_of_the_binary_tree {
}
