package leetcode.linked_list_items;

/**
 *  题意：
 *      输入两个链表，找出它们的第一个公共节点。
 *      如果两个链表没有交点，返回 null.
 *      在返回结果后，两个链表仍须保持原有的结构。
 *      可假定整个链表结构中没有循环。
 *      程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *  思路：
 *      由题意知：A链表的长度+相交前B链表长度 = B链表的长度 + 相交前A链表长度
 *      因此可以使a指针和b指针分别从两链表头结点出发，当遍历到链表尾部时返回（另一个链表的）头结点
 *      当再次相遇时即为第一个公共点
 *      有两种方式，思路一样。第2种更高效
 */
class The_first_common_node_of_two_linked_lists2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while(a != b){
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
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
class The_first_common_node_of_two_linked_lists1 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        int ok = 0;
        while(ok <= 2){
            if(a == b){
                return a;
            }
            a = a.next;
            b = b.next;
            if(a == null){
                ok += 1;
                a = headB;
            }
            if(b == null){
                ok += 1;
                b = headA;
            }
        }
        return null;
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
public class The_first_common_node_of_two_linked_lists {
}
