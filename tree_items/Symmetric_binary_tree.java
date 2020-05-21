package leetcode.tree_items;

/**
 * 题意：
 *      判断一棵二叉树是否是镜面对称的，0 <= 节点个数 <= 1000
 * 思路：
 *      对于一棵对称二叉树，满足：
 *          左节点L的值 等于 右节点R的值；
 *          左节点的左子树值 等于 右节点右子树的值
 *          左节点的右子树值 等于 右节点左子树的值
 *      特殊情况：
 *          若root为空则直接返回true
 *          若两子树都为空则返回true
 *          若只有一个为空则返回false
 *          其他情况如上
 *      对于后两个满足条件，需要创建函数进行两节点的判断
 */
class Symmetric_binary_tree1 {
    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : isSym(root.left, root.right);
    }

    public boolean isSym(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }else if(left == null || right == null){
            return false;
        }else if(left.val != right.val){
            return false;
        }

        return isSym(left.left, right.right) && isSym(left.right, right.left);
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
public class Symmetric_binary_tree {
}
