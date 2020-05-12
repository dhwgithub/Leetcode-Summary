package leetcode.stack_items;

import java.util.Stack;

/**
 * 题目：
 *      用两个栈实现队列
 *      https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * 思路：
 *      创建两个栈对象s1、s2，其中s1负责添加元素，s2负责删除元素
 *      添加元素：
 *          直接向s1添加元素
 *      删除元素：
 *          若都无元素则返回-1
 *          若s2有元素则直接删除
 *          若s1有元素则把所有元素放入s2中，然后从s2删除
 */
class CQueue {
    private Stack<Integer> s1 = null;
    private Stack<Integer> s2 = null;

    public CQueue() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
    }

    public void appendTail(int value) {
        s1.push(value);
    }

    public int deleteHead() {
        if(s1.size() == 0 && s2.size() == 0){
            return -1;
        }else if(s2.size() > 0){
            return s2.pop();
        }else{
            while(s1.size() > 0){
                s2.push(s1.pop());
            }
            return s2.pop();
        }
    }
}
/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
public class Implement_queue_with_two_stacks {
}
