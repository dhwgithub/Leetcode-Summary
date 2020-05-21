package leetcode.tree_items;

import java.util.Stack;

/**
 *  题意：
 *      给定一棵二叉树，输出其镜像表示。0 <= 节点个数 <= 1000
 *  思路1：
 *      深搜。由题知，返回的左子树等于root的右子树镜像，返回的右子树等于root的左子树镜像，以此类推
 *  思路2：
 *      用栈的思想（效率极低）。创建一个（似）栈对象，将根节点加入。
 *      当栈不为空时，出栈并将其右子树、左子树依次入栈；接着交换左子树和右子树的位置
 *      栈为空时退出。
 *      栈操作：new Stack()、push、pop、peek
 */
class Mirror_of_the_binary_tree2 {
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null){
            return root;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while( !stack.isEmpty()){
            TreeNode t = stack.pop();
            if(t != null){
                stack.push(t.right);
                stack.push(t.left);

                TreeNode temp = t.left;
                t.left = t.right;
                t.right = temp;
            }
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
class Mirror_of_the_binary_tree1 {
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null){
            return root;
        }

        TreeNode node = new TreeNode(root.val);

        TreeNode t = root.left;
        node.left = mirrorTree(root.right);
        node.right = mirrorTree(t);

        return node;
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
public class Mirror_of_the_binary_tree {
}
