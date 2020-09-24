/**
给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
假定 BST 有如下定义：
    结点左子树中所含结点的值小于等于当前结点的值
    结点右子树中所含结点的值大于等于当前结点的值
    左子树和右子树都是二叉搜索树

提示：如果众数超过1个，不需考虑输出顺序
进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）

方法：对于二叉搜索树，采用中序遍历可以得到其非降序排列。找众数，若要不记录每个数的次数则需要记录上一个数以及出现的次数，还有出现最大的次数，同时用列表方便记录
	对于遍历到的节点：若当前节点与上一个节点值相等，则计数+1
				 若不相等，则上一个数的值以及次数变为1
	然后分析：若当前数的次数等于最大次数，则列表加入当前数
			若大于当前次数，则清空列表加入当前数，并更新最大次数
			其余忽略
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
class findMode1 {
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<Integer>();
        int[] curVal = new int[1];
        curVal[0] = root.val - 1;
        int[] curSum = new int[1];
        curSum[0] = 0;
        int[] maxSum = new int[1];
        maxSum[0] = 0;
        dfs(root, curVal, curSum, maxSum, list);

        int n = list.size();
        int[] res = new int[n];
        for (int i = 0; i < n; i ++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void dfs(TreeNode node, int[] curVal, int[] curSum, int[] maxSum, List<Integer> list) {
        if (node == null) {
            return ;
        }

        dfs(node.left, curVal, curSum, maxSum, list);

        if (node.val == curVal[0]) {  // 相等计数+1
            curSum[0] ++;
        } else {
            curVal[0] = node.val;
            curSum[0] = 1;
        }
        if (curSum[0] == maxSum[0]) {  // 与当前总次数相等加入答案中
            list.add(curVal[0]);
        } else if (curSum[0] > maxSum[0]) {  // 大于当前总次数清空答案并加入
            list.clear();
            maxSum[0] = curSum[0];
            list.add(curVal[0]);
        }

        dfs(node.right, curVal, curSum, maxSum, list);
    }
}