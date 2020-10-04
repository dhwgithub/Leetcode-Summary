/**
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

方法：模拟即可。
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
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode t = new ListNode(0);

        boolean s = true;
        int get = 0;

        while (l1 != null || l2 != null) {
            int v = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + get;
            get = v / 10;
            
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

            if (s == false){
                ans = new ListNode(v % 10);
                ans.next = t;
                t = ans;
            } else {
                t = new ListNode(v % 10);
                s = false;
            }
        }

        if (t.val > ans.val) {  // 只有一位的情况
            ans = t;
        }

        if (get != 0) {  // 多余进位
            ans = new ListNode(get);
            ans.next = t;
        }

        ListNode pre = null;
        while (ans != null) {  // 翻转
            t = ans.next;
            ans.next = pre;
            pre = ans;
            if (t == null) break;
            ans = t;
        }        

        return ans;
    }
}