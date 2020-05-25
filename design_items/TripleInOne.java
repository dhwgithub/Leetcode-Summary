package leetcode.design_items;

/**
 *  题意：
 *      三合一。用类描述如何只用一个数组来实现三个栈。
 *      你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。
 *      stackNum表示栈下标，value表示压入的值。
 *      构造函数会传入一个stackSize参数，代表每个栈的大小。
 *  思路：
 *      用一个3倍于stackSize的数组，按3倍关系分配空间，第一个栈占0、3、6...等，第二个栈占1、4、7...以此类推
 *      出栈、入栈的步长都是3
 *      或者是前stackSize个是第一个栈空间，接着是第二个等
 */
/**
 * Your TripleInOne object will be instantiated and called as such:
 * TripleInOne obj = new TripleInOne(stackSize);
 * obj.push(stackNum,value);
 * int param_2 = obj.pop(stackNum);
 * int param_3 = obj.peek(stackNum);
 * boolean param_4 = obj.isEmpty(stackNum);
 */
class TripleInOne1 {
    private int[] stack = null;
    private int s0, s1, s2;
    private int size = 0;

    public TripleInOne1(int stackSize) {
        int t = stackSize * 3 + 5;
        stack = new int[t];
        size = stackSize;
        s0 = 0;
        s1 = 1;
        s2 = 2;
    }

    public void push(int stackNum, int value) {
        if(stackNum == 0 && Math.abs(size - s0/3) > 0){
            stack[s0] = value;
            s0 += 3;
        }else if(stackNum == 1 && Math.abs(size - s1/3) > 0){
            stack[s1] = value;
            s1 += 3;
        }else if(stackNum == 2 && Math.abs(size - s2/3) > 0){
            stack[s2] = value;
            s2 += 3;
        }
    }

    public int pop(int stackNum) {
        if(stackNum == 0){
            if(s0 > 0){
                s0 -= 3;
                return stack[s0];
            }else{
                return -1;
            }
        }else if(stackNum == 1){
            if(s1 > 1){
                s1 -= 3;
                return stack[s1];
            }else{
                return -1;
            }
        }else if(stackNum == 2){
            if(s2 > 2){
                s2 -= 3;
                return stack[s2];
            }else{
                return -1;
            }
        }
        return -1;
    }

    public int peek(int stackNum) {
        if(stackNum == 0){
            if(s0 > 0){
                return stack[s0-3];
            }else{
                return -1;
            }
        }else if(stackNum == 1){
            if(s1 > 1){
                return stack[s1-3];
            }else{
                return -1;
            }
        }else if(stackNum == 2){
            if(s2 > 2){
                return stack[s2-3];
            }else{
                return -1;
            }
        }
        return -1;
    }

    public boolean isEmpty(int stackNum) {
        if(stackNum == 0){
            if(s0 > 0){
                return false;
            }else{
                return true;
            }
        }else if(stackNum == 1){
            if(s1 > 1){
                return false;
            }else{
                return true;
            }
        }else if(stackNum == 2){
            if(s2 > 2){
                return false;
            }else{
                return true;
            }
        }
        return true;
    }
}
public class TripleInOne {
}
