package leetcode.linked_list_items;

/**
 *  题意：
 *      编写一个函数，检查输入的链表是否是回文的。
 *  思路：
 *      分为三步，第一步找到中间节点；第二部把中间节点的后一个节点（偶数时完全对半，奇数时排除中间节点影响）为头结点进行链表反转
 *      最后一个逐个匹配，循环结束条件是第二个链表遍历结束（第一个可能因为是奇数链表而多一位）
 *      其中找中间节点：设置步长为2倍关系的快慢指针，从同一点开始扫描，当快指针为空时慢指针即为指向中间节点
 *      其中链表反转：见文件Reverse_linked_list.java
 */
class Palindrome_list1 {
    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }

        ListNode mid = getMidNode(head);
        ListNode p1 = head;
        ListNode p2 = reverseList(mid.next);
        while(p2 != null){
            if(p1.val != p2.val){
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public ListNode getMidNode(ListNode head){
        ListNode s = head;
        ListNode q = head;
        while(q.next != null && q.next.next != null){
            s = s.next;
            q = q.next.next;
        }
        return s;
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
public class Palindrome_list {
}
