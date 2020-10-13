/**
题意：交换链表中相邻的节点，返回头结点

方法1：递归调用
方法2：迭代。定义一个节点t（t.next = head），使得t->n1->n2，则变换后为t->n2->n1
	每次判断是否存在两个节点，存在则交换n1和n2位置
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
class Solution2 {
    public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;

        ListNode t = pre;
        while (t.next != null && t.next.next != null) {
            ListNode n1 = t.next;
            ListNode n2 = t.next.next;

            t.next = n2;
            n1.next = n2.next;
            n2.next = n1;

            t = n1;
        }

        return pre.next;
    }
}
class Solution1 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}