/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 
 * 方法一：要满足高度平衡，又因为是有序链表，因此可以每次找到链表的中点作为当前子树的根节点
 * 对于中点，设置快慢指针，初始时指向第一个节点，然后快指针每次移动两步，这样快指针指向最后时，
 * 慢指针指向的即为中点节点（偶数时指向中间的其中一个）
 * 递归构建平衡二叉搜索树，其中对于每个子树传入链表的头结点和尾结点（前闭后开，方便左右子树构建）
 * 这样在递归体中将该子链表的中点作为子树根节点，两边的部分分别再次递归构建子树
 * 
 * 注意递归结束的条件是左右节点相等的时候，而不是左节点为null
 * 查询中点时传入左右两个限制
 * 
 * 方法二：原理同上，只不过修改了原链表结构，使得效率更高
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
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
class sortedListToBST2 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode down = null;  // 用于断开原链表，便于后续正确递归
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            down = slow;
            slow = slow.next;
        }
        down.next = null;

        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);

        return root;
    }
}
class sortedListToBST1 {
    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    private TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }

        ListNode mid = getMidNode(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);

        return root;
    }

    private ListNode getMidNode(ListNode left, ListNode right) {
        ListNode m = left;
        ListNode q = left;
        while (q != right && q.next != right) {
            m = m.next;
            q = q.next.next;
        }
        return m;
    }
}