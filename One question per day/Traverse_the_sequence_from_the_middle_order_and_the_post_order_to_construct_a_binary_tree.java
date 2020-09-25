/**
根据一棵树的中序遍历与后序遍历构造二叉树。
注意:
你可以假设树中没有重复的元素。

方法：直接模拟即可。（方法一中可将）buildTree方法中的node生成放在子方法中；
	方法二采用哈希表存储了inorder位置，提高了运行速度
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
class buildTree2 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null
            || inorder.length != postorder.length
            || inorder.length <= 0) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int len = inorder.length;
        for (int i = 0; i < len; i ++) {
            map.put(inorder[i], i);
        }
        
        return getTree(inorder, 0, len, postorder, 0, postorder.length, map);
    }

    private TreeNode getTree(int[] inorder, int is, int ie,
                            int[] postorder, int ps, int pe,
                            Map<Integer, Integer> map) {
        if (is >= ie || ps >= pe) {
            return null;
        }

        int val = postorder[pe - 1];
        TreeNode node = new TreeNode(val);

        int k = map.get(val);
        int num = k - is;  // node左子树数量
        
        node.left = getTree(inorder, is, k, postorder, ps, ps + num, map);
        node.right = getTree(inorder, k + 1, ie, postorder, ps + num, pe - 1, map);

        return node;
    }
}
class buildTree1 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null
            || inorder.length != postorder.length
            || inorder.length <= 0) {
            return null;
        }
        
        int len1 = inorder.length;
        int len2 = postorder.length;
        TreeNode node = new TreeNode(postorder[len2 - 1]);

        int k = 0;
        while (inorder[k] != postorder[len2 - 1]) {
            k ++;
        }
        node.left = getTree(inorder, 0, k, postorder, 0, k);
        node.right = getTree(inorder, k + 1, len1, postorder, k, len2 - 1);

        return node;
    }

    private TreeNode getTree(int[] inorder, int is, int ie,
                            int[] postorder, int ps, int pe) {
        if (is >= ie || ps >= pe) {
            return null;
        }

        TreeNode node = new TreeNode(postorder[pe - 1]);

        int k = is;
        int num = 0;  // node左子树数量
        while (inorder[k] != postorder[pe - 1]) {
            k ++;
            num ++;
        }
        node.left = getTree(inorder, is, k, postorder, ps, ps + num);
        node.right = getTree(inorder, k + 1, ie, postorder, ps + num, pe - 1);

        return node;
    }
}
 