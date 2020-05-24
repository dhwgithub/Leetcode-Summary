package leetcode.design_items;

/**
 * 题目：
 *      包含最小函数的栈，且求最小、pop和top的时间复杂度都为O(1)
 * 思路：
 *      两个数组s1,s2。其中s1模拟栈操作，保存指针指向栈顶，可以实现pop和top条件
 *      s2保存当前位置的最小值，每个位置与s1一一对应
 */
class MinStack {

    private int[] s1 = null;
    private int[] s2 = null;
    private int p = 0;
    /** initialize your data structure here. */
    public MinStack() {
        s1 = new int[20005];
        s2 = new int[20005];
        p = 0;
    }

    public void push(int x) {
        int index = p;
        s1[index] = x;
        if(p == 0){
            s2[index] = x;
        }else{
            s2[index] = Math.min(x, s2[index-1]);
        }
        p += 1;
    }

    public void pop() {
        p -= 1;
    }

    public int top() {
        if(p == 0){
            return -1;
        }else{
            return s1[p-1];
        }
    }

    public int min() {
        if(p == 0){
            return -1;
        }else{
            return s2[p-1];
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
public class The_stack_containing_the_min_function {
}
