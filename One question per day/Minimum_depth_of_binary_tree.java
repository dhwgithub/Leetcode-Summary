/**
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 方法一：DFS，首先判断根节点是否存在以及是否有子树。然后是递归求出子树的最小深度，取最小值+1即可
 * 方法二：BFS，一层一层遍历，只要找到无左右子树的节点，那么当前节点所在的深度即为最小深度
 * 方法三：BFS，方法二的改进，效率更高。每次求出队列中的每层数量进行遍历
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
class minDepth3 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        int minDeep = 1;
        while ( ! queue.isEmpty()) {
            int num = queue.size();
            while (num-- > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return minDeep;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            minDeep ++;
        }

        return -1;
    }
}
class minDepth2 {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(root, 1));

        while ( ! queue.isEmpty()) {
            Node nodes = queue.poll();
            TreeNode node = nodes.node;
            int deep = nodes.deep;

            if (node.left == null && node.right == null) {
                return deep;
            }
            if (node.left != null) {
                queue.add(new Node(node.left, deep + 1));
            }
            if (node.right != null) {
                queue.add(new Node(node.right, deep + 1));
            }
        }

        return -1;
    }

    public class Node {
        TreeNode node;
        int deep;
        public Node(TreeNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }
}
class minDepth1 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int minD = Integer.MAX_VALUE;
        if (root.left != null) {
            minD = Math.min(minDepth(root.left), minD);
        }
        if (root.right != null) {
            minD = Math.min(minDepth(root.right), minD);
        }

        return minD + 1;
    }
}