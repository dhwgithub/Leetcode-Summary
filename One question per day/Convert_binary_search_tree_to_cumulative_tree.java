/**
给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

例如：
输入: 原始二叉搜索树:
              5
            /   \
           2     13
输出: 转换为累加树:
             18
            /   \
          20     13


方法1：先求出所有节点的值，然后遍历树求累计值
方法2：由于是二叉搜索时，根据其特性可以采用后序遍历（降序），每次遍历到的值加到当前节点上。累计值记为全局变量，因为原树后面的节点都比前面的节点值大，都应该被相加
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

class convertBST2 {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }

        convertBST(root.right);

        sum += root.val;
        root.val = sum;
        
        convertBST(root.left);

        return root;
    }
}
class convertBST1 {
    public TreeNode convertBST(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        
        dfs(root, list);
        int num = list.size();
        Collections.sort(list);

        addTree(root, list, num - 1);
        return root;
    }

    private void addTree(TreeNode node, List<Integer> list, int num) {
        if (node == null) {
            return ;
        }

        int k = node.val;
        while (num >= 0 && k < list.get(num)) {
            node.val += list.get(num);
            num --;
        }

        addTree(node.left, list, list.size() - 1);
        addTree(node.right, list, list.size() - 1);
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return ;
        }
        list.add(node.val);
        dfs(node.left, list);
        dfs(node.right, list);
    }
}