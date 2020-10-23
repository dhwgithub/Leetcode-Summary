/**
请判断一个链表是否为回文链表。

方法1：存储起来再双指针遍历
方法2：用快慢指针找到中间节点，然后翻转后半段，双指针进行判断
	最后再次翻转还原原链表
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode mid = getMid(head);
        ListNode h2 = mid.next;  // 第二段链表
        mid.next = null;

        h2 = reverse(h2);
        ListNode new_mid = h2;

        while (head != null && h2 != null && head.val == h2.val) {
            head = head.next;
            h2 = h2.next;
        }

        // 恢复原链表
        new_mid = reverse(new_mid);
        mid.next = new_mid;

        if (h2 != null) {  // head可以为null，因为奇数时会比h2链表多一个节点
            return false;
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        return pre;
    }

    private ListNode getMid(ListNode head) {
        ListNode h1 = head;
        ListNode h2 = head;
        while (h2.next != null && h2.next.next != null) {
            h1 = h1.next;
            h2 = h2.next.next;
        }
        return h1;
    }
}