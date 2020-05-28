package leetcode.linked_list_items;

/**
 *  题意：
 *      给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 *      返回删除后的链表的头节点。
 *      题目保证链表中节点的值互不相同
 *  思路：
 *      分三种情况，删除链头、链中间和链尾，设置一个pre节点用来保存当前节点的上一个结点。有定位和删除两步
 *          特殊情况如删除头结点，直接返回头结点的下一个节点即可
 *          其他情况直接一一遍历查找，找到后删除
 */
class Delete_the_node_of_the_linked_list1 {
    public ListNode deleteNode(ListNode head, int val) {
        if(head.val == val){  // 头结点
            return head.next;
        }

        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != null && cur.val != val){  // 定位
            pre = cur;
            cur = cur.next;
        }

        if(cur != null){  // 删除
            pre.next = cur.next;
        }

        return head;
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
public class Delete_the_node_of_the_linked_list {
}
