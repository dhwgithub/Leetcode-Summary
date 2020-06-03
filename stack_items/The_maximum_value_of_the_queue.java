package leetcode.stack_items;

/**
 *  题意：
 *      请定义一个队列并实现函数 max_value 得到队列里的最大值，
 *      要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *      若队列为空，pop_front 和 max_value 需要返回 -1
 *
 *      1 <= push_back,pop_front,max_value的总操作数 <= 10000
 *      1 <= value <= 10^5
 *  思路1：
 *      对于push_back 和 pop_front可以在O(1)时间复杂度下用队列实现
 *      而对于max_value，需要用双端队列记录当前的最大值：
 *          若需要加入的元素比双端队列的最后一个元素（递减）小或等于，则直接加入
 *          否则删除原来的比当前值小的元素，然后加入当前元素
 *          每次出队时，判断双端队列队首元素和要出队的值，若相等则推出（由于相等元素会被保留所以不必担心会多推出元素）
 *      尽量使用offer()方法添加元素，使用poll()方法移除元素。add()和remove()方法在失败的时候会抛出异常
 *  思路2：
 *      由于总操作数不超过10000，可以采用一个数组进行存储，并设置变量进行标记
 *      对于max_value，用一个maxn值表示当前最大值，maxIndex表示当前最大值下标，用于后续更新使用
 *      对于pop_front，可直接返回队首元素（l,r分别表示数组左右边界），然后更新最大值信息
 *      对于push_back，如果当前只有一个元素，可以直接赋值最大值，否则判断是否更新最大值。对于新增元素直接加入即可
 */

import java.util.Queue;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
class MaxQueue2 {

    int[] queue = null;
    int l = 0;
    int r = 0;
    int maxn = -1;
    int maxIndex = -1;

    public MaxQueue2() {
        queue = new int[10005];
    }

    public int max_value() {
        if(l == r) return -1;
        else return maxn;
    }

    public void push_back(int value) {
        queue[r ++] = value;
        if(r - l == 1){
            maxn = value;
            maxIndex = l;
        }else{
            if(value > maxn){
                maxn = value;
                maxIndex = r - 1;
            }
        }
    }

    public int pop_front() {
        if(r - l == 0) return -1;
        int t = queue[l ++];
        if(maxIndex < l && r - l != 0){  // 若最大值过期则更新maxn
            maxn = queue[l];
            maxIndex = l;
            for(int i=l+1; i<r; i++){
                if(maxn < queue[i]){
                    maxn = queue[i];
                    maxIndex = i;
                }
            }
        }
        return t;
    }
}
class MaxQueue1 {

    Queue<Integer> q = null;
    Deque<Integer> dq = null;

    public MaxQueue1() {
        q = new LinkedList<Integer>();
        dq = new LinkedList<Integer>();
    }

    public int max_value() {
        if(dq.size() == 0) return -1;
        else return dq.peek();
    }

    public void push_back(int value) {
        q.offer(value);
        while( !dq.isEmpty() && value > dq.getLast()){
            dq.removeLast();
        }
        dq.offer(value);
    }

    public int pop_front() {
        if(q.size() == 0) return -1;
        int v = q.poll();
        if(v == dq.peek()) dq.poll();
        return v;
    }
}
public class The_maximum_value_of_the_queue {
}
