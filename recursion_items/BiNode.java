package leetcode.recursion_items;

/**
 *  题意：
 *      二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
 *      实现一个方法，把二叉搜索树转换为单向链表，要求值的顺序保持不变，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 *      返回转换后的单向链表的头节点。
 *  思路：
 *      二叉搜索树的顺序与中序遍历一致，因此可以用递归的方式修改中序遍历的节点连接方式，且不影响之后遍历的结果；
 *      其中，修改连接节点时，需要知道当前节点的左节树最右节点（若无则赋值为当前节点）连接当前节点，当前节点连接其右子树最左节点
 */
class BiNode1 {
    public TreeNode convertBiNode(TreeNode root) {
        if(root == null){
            return null;
        }

        TreeNode leftNode = convertBiNode(root.left);
        if(leftNode == null){
            leftNode = root;
        }else{
            TreeNode leftNodeTemp = leftNode;
            while(leftNodeTemp.right != null){  // 找左子树最右节点
                leftNodeTemp = leftNodeTemp.right;
            }
            leftNodeTemp.right = root;  // 左子树最右节点连接当前根节点
        }
        root.left = null;  // 单向链表无前置
        root.right = convertBiNode(root.right);  // 当前根节点连接下一个节点
        return leftNode;  // 最后返回单向链表的头结点
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

public class BiNode {
}
