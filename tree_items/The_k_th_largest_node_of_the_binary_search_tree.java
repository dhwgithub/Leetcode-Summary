package leetcode.tree_items;

/**
 * 题目：
 *      给定一棵二叉搜索树，请找出其中第k大的节点。
 * 思路：
 *      由二叉搜索树知其中序遍历为递增，那么反序就是递减，这样该题就可以变为求递减序列的第k项
 *      由此可以反中序遍历该二叉搜索树，然后遍历过程中判定该点是否是第k个，若是则提前终止遍历，直接返回即可
 */
class The_k_th_largest_node_of_the_binary_search_tree1 {
    public int kthLargest(TreeNode root, int k) {
        int[] ans = new int[1];
        ans[0] = 0;
        dfs(root, k, new boolean[1], ans);
        return ans[0];
    }

    // 之所以将find定义为数组，是因为当找到k大的数时，boolean变量无法及时更新
    public void dfs(TreeNode root, int k, boolean[] find, int[] ans){
        if(root == null || find[0] == true){
            return;
        }

        if(root.right != null){
            dfs(root.right, k, find, ans);
        }

        if(find[0] == true){  // 防止ans[0]多执行+1操作
            return;
        }
        ans[0] += 1;
        if(k == ans[0]){
            find[0] = true;
            ans[0] = root.val;
            return;
        }

        if(root.left != null){
            dfs(root.left, k, find, ans);
        }
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
public class The_k_th_largest_node_of_the_binary_search_tree {
}
