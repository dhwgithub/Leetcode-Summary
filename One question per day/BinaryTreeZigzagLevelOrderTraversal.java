/*
Given a binary tree, return the zigzag level order traversal of its nodes' values.
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

Solution:
    BFS
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean f = true;
        while (! queue.isEmpty()) {
            Deque<Integer> list = new LinkedList<>();
            int sum = queue.size();
            while (sum -- > 0) {
                TreeNode node = queue.poll();
                if (f) {
                    list.offerLast(node.val);
                }else {
                    list.offerFirst(node.val);
                }

                TreeNode leftNode = node.left;
                TreeNode rightNode = node.right;
                if  (leftNode != null) {
                    queue.add(leftNode);
                }
                if (rightNode != null) {
                    queue.add(rightNode);
                }
            }
            f = !f;
            res.add(new ArrayList<>(list));
        }
        return res;
    }
}