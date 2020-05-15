package leetcode.tree_items;

/**
 * 题目：
 *      求二叉搜索树的最近公共祖先，其中p、q为不同节点，树中的值唯一
 * 思路1：
 *      同二叉树的最近公共祖先求法一样，具体思路见：The_nearest_public_ancestor_of_the_binary_tree.java
 * 思路2：
 *      利用二叉搜索树的性质，即每一个子树的根节点都比其左子树大，比右子树小，通过与根节点比较大小来确定两节点的左右子树位置
 *          当根节点处于两节点之间时，根节点即为所求节点
 *          当根节点比两个节点都小时，从右子树搜索
 *          当根节点比两个节点都大时，从左子树搜索
 *      此时包含所有情况且都能够返回，不会无限递归下去
 */
class The_nearest_common_ancestor_of_the_binary_search_tree1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
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
public class The_nearest_common_ancestor_of_the_binary_search_tree {
}
