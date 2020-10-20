/*
给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

方法1：用队列保存每一个节点，然后进行更改节点操作
方法2：先找到中点进行截断，之后翻转后半部分，最后进行合并操作
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
    public void reorderList(ListNode head) {
        if (head == null) {
            return ;
        }

        // 找到中点（注意截断）
        ListNode mid = getMiddle(head);
        ListNode two = mid.next;
        mid.next = null;

        // 翻转第二段链表，即one链表
        two = reverseL(two);

        // 合并
        mergeL(head, two);
    }

    private void mergeL(ListNode one, ListNode two) {
        while (one != null && two != null) {
            ListNode ot = one.next;
            ListNode tt = two.next;

            one.next = two;
            one = ot;

            two.next = one;
            two = tt;
        }

    }

    private ListNode reverseL(ListNode head) {
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

    private ListNode getMiddle(ListNode head) {
        ListNode one = head;
        ListNode two = head;
        while (two.next != null && two.next.next != null) {
            two = two.next.next;
            one = one.next;
        }
        return one;
    }
}

class Solution1 {
    public void reorderList(ListNode head) {
        ArrayList<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }

        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            if (i + 1 < j){
                list.get(j).next = list.get(i + 1);
            } else {
                list.get(j).next = null;
            }
            i ++;
            j --;
        }
        if (i == j && list.size() > 2) {  // 处理最后一个节点，防止出现循环链表
            list.get(i).next = null;
        }
    }
}