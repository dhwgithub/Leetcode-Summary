package leetcode.linked_list_items;

/**
 *  题意：
 *      实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 *      给定的 k 保证是有效的。
 *  思路：
 *      设置两个指针，第一个先走k-1步，然后同时往后走，直到第一个到达链表尾部，那么第二个指针就是倒数第k个
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Returns_the_penultimate_k_th_node1 {
    public int kthToLast(ListNode head, int k) {
        if(head == null){
            return -1;
        }
        ListNode f = head;
        ListNode t = head;
        while(f.next != null && k > 1){
            k -= 1;
            f = f.next;
        }
        while(f.next != null){
            f = f.next;
            t = t.next;
        }
        return t.val;
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
public class Returns_the_penultimate_k_th_node {
}
