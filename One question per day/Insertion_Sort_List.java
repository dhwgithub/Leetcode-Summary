/*
 * 对链表进行插入排序。
插入排序算法：
    插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
    每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
    重复直到所有输入数据插入完为止。

题解：
	设置一个哑头结点，这样不必处理插入头结点的特殊情况，并维护一个排序好的链表的最后一个节点lastNode，默认是从第一个节点开始【技巧】
	当当前节点值大于等于lastNode时，直接后移lastNode，并更新当前节点
	否则：
		找到需要插入节点的位置的前一个节点，然后lastNode连接后一个节点（跳过当前节点）【技巧】
		之后当前节点连接插入位置的后一个节点，前一个节点连接当前节点
		最后更新当前节点即可
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
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummyHead = new ListNode(0);  // 该节点排除了插入头结点的特殊处理
        dummyHead.next = head;

        ListNode lastNode = head;
        ListNode cur = head.next;
        while (cur != null) {
            // 判断是否需要插入
            if (lastNode.val <= cur.val) {
                lastNode = lastNode.next;
            } else {
                ListNode pre = dummyHead;
                while (pre.next.val <= cur.val) {
                    pre = pre.next;
                }

                lastNode.next = cur.next;  // 连接断开当前节点的位置
                cur.next = pre.next; // 插入后连接1
                pre.next = cur;  // 插入后连接2
            }

            cur = lastNode.next;
        }

        return dummyHead.next;
    }
}