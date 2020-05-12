package leetcode.stack_items;

/**
 * 题目：
 *      输出滑动窗口的最大值
 *      https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 * 思路1：
 *      构建单调栈来求取滑动窗口最大值，用双端队列实现对单调栈窗口大小的维护。因为题目中已经给出是保存所有数字的数组，因此只需要对下标进行存储即可
 *      在窗口大小内，若当前数小于双端队列对应的最小值即尾值（单调递减），则加入，否则删除最小值并加入
 *      对于超出窗口大小的数，从双端队列首部删除即可
 *      每次滑动窗口的最大值即为双端队列的首部对应值
 * 思路2（效率更高）：
 *      对于每个滑动窗口，每次从该窗口的第一个开始比较。对上一个滑动窗口最大值下标pre和当前窗口第一个元素下标index比较分析
 *      若index大于等于pre时，则从当前下标开始遍历到当前滑动窗口最后一个位置，找到新的最大值和其下标
 *      反正，则直接把最大值与当前窗口的最后一个元素比较，因为在上一个滑动窗口比较中已经比较过该窗口的其他元素了
 */
import java.util.Deque;
import java.util.LinkedList;
class Solution1 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k <= 0){
            return new int[0];
        }
        Deque<Integer> q = new LinkedList<Integer>();
        int len = nums.length;
        int[] ans = new int[len-k+1];

        for(int i=0; i<len; i++){
            if(q.size() > 0){
                int index = q.peek();
                if(index+k-1 < i){
                    q.removeFirst();
                }
            }
            int val = nums[i];  // 当前元素

            if(q.size() > 0){
                int v = nums[q.getLast()];  // 队尾元素
                if(val >= v){
                    q.removeLast();
                    while(q.size() > 0 && val >= nums[q.getLast()]){
                        q.removeLast();
                    }
                }
            }
            q.addLast(i);

            if(i+1 >= k){
                ans[i+1-k] = nums[q.peek()];
            }
        }

        return ans;
    }
}
class Solution2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k <= 0){
            return new int[0];
        }
        int len = nums.length;
        int[] ans = new int[len-k+1];
        int maxV = nums[0];
        int maxI = 0;
        for(int i=0; i<len-k+1; i++){
            if(i >= maxI){
                maxV = nums[i];
                for(int j=i; j<=i+k-1; j++){
                    if(maxV < nums[j]){
                        maxV = nums[j];
                        maxI = j;
                    }
                }
            }else{
                if(nums[i+k-1] > maxV){
                    maxV = nums[i+k-1];
                    maxI = i+k-1;
                }
            }
            ans[i] = maxV;
        }
        return ans;
    }
}
public class Maximum_value_of_sliding_window {
}
