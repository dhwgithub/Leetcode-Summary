package leetcode.linked_list_items;

/**
 *  题意：
 *      定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *      0 <= 节点个数 <= 5000
 *  思路：
 *      从头结点开始，每遍历到一个节点后转化指向并接着到下一个位置
 *
 */
class Reverse_linked_list1 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;  // 相对于原链表
        ListNode cur = head;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
    /**
     *  Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
public class Reverse_linked_list {
}
