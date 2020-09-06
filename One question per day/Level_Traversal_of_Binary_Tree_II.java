/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 
 * 方法：BFS，一层一层遍历，每遍历一层从第一个位置依次加入。
 * 		复习：Queue 中 add() 和 offer()都是用来向队列添加一个元素。
 * 		在容量已满的情况下，add() 方法会抛出IllegalStateException异常，offer() 方法只会返回 false 。
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while ( ! queue.isEmpty()) {
            int num = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            while (num -- > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                } 
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(0, list);
        }

        return ans;
    }
}