/**
给定一个二叉树，返回它的 后序 遍历。
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

方法：DFS略；
	对于迭代，已知前序遍历是（根，左，右），后序遍历是（左，右，根）
	不过对于翻转的后序遍历是（根，右，左），因此可以写出前序遍历的结果，然后将左右子树的遍历顺序交换即可
	用LinkedList可以直接加在第一个位置，省去耗时占空间的翻转操作
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
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                list.addFirst(cur.val);
                stack.push(cur);
                cur = cur.right;  // 考虑右子树
            }

            cur = stack.pop();
            cur = cur.left;  // 考虑左子树
        }

        return list;
    }
}