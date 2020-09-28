/**
给定一个二叉树
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
初始状态下，所有 next 指针都被设置为 NULL。

进阶：
    你只能使用常量级额外空间。
    使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
    
提示：
    树中的节点数小于 6000
    -100 <= node.val <= 100

方法1：层次遍历
方法2：设置全局变量last和nextNode指针，然后将每一层看做一个链表，对于每一个节点每次记录链表的头结点和上一个节点
	并不断更新（每层只更新一次下一层的头结点）
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

class connect2 {
    Node last = null;
    Node nextNode = null;
    public Node connect(Node root) {
        Node start = root;
        while (start != null) {
            last = null;
            nextNode = null;
            // 类似于层次遍历
            for (Node t = start; t != null; t = t.next) {
                if (t.left != null) {
                    headle(t.left);
                }
                if (t.right != null) {
                    headle(t.right);
                }
            }
            start = nextNode;
        }
        return root;
    }

    private void headle(Node t) {
        // 记录下一层的第一个节点
        if (nextNode == null) {  
            nextNode = t;
        }
        // 记录上一个节点
        if (last != null) {
            last.next = t;
        }
        last = t;
    }
}
class connect1 {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int num = queue.size();
            for (int i = 0; i < num; i ++) {
                Node t = queue.poll();
                if (i != num - 1) {
                    t.next = queue.peek();
                }
                if (t.left != null) {
                    queue.add(t.left);
                }
                if (t.right != null) {
                    queue.add(t.right);
                }
            }
        }
        return root;
    }
}