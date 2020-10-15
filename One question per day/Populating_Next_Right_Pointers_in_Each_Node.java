/**
给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
初始状态下，所有 next 指针都被设置为 NULL。

提示：
    你只能使用常量级额外空间。
    使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
    
思路1：队列层次遍历，不符合题意常量级额外空间
思路2：遍历时，每次从一层的第一个节点head开始，其左节点下一个是右节点；
	若head.next存在则其右节点的下一个是head.next的左节点
	最后一层遍历结束后，重置head节点到下一层（head初始节点是根节点）
	结束条件是最左节点的左节点为空

 */

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution2 {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Node leftMost = root;
        while (leftMost.left != null) {
            Node head = leftMost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftMost = leftMost.left;
        }

        return root;
    }
}

class Solution1 {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Node leftMost = root;
        while (leftMost.left != null) {
            Node head = leftMost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftMost = leftMost.left;
        }

        return root;
    }
}