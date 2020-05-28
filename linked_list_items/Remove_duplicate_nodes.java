package leetcode.linked_list_items;

/**
 *  题意：
 *      编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *      链表长度在[0, 20000]范围内。
 *      链表元素在[0, 20000]范围内。
 *  思路：
 *      用boolean[]类型记录出现的节点值，然后判断即可
 *      或者采用冒泡思想，可以做到低空间率，但是时间开销会很大
 */
class Remove_duplicate_nodes1 {
    public ListNode removeDuplicateNodes(ListNode head) {
        if(head == null){
            return head;
        }
        boolean[] num = new boolean[20005];
        num[head.val] = true;
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != null){
            if(num[cur.val] == true){
                pre.next = cur.next;
            }else{
                num[cur.val] = true;
                pre = cur;
            }
            cur = cur.next;
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
public class Remove_duplicate_nodes {
}
