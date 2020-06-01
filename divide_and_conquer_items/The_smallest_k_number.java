package leetcode.divide_and_conquer_items;

/**
 * 题目：
 *      最小的k个数
 * 思路1：
 *      利用快速排序思想，即可以每次定位出一个元素的最终位置，这样找到第k+1个元素，其前面的数集就是要返回的数组。
 *      不需要对其进行排序，因此时间复杂度为O(N)
 * 思路2：
 *      大根堆求前k小。可以用一个容量为k的大根堆，每次poll出一个最大的数，这样最后剩下的就是前k小。
 *      注意不是小根堆，小根堆是需要将所有元素放入堆中，这样前k个才是最小的。
 *      大根堆求前k小更快，且java自带PriorityQueue（默认小根堆），实现起来较为简单，时间复杂度是O(NlogK)
 *      具体方法是：若大根堆容量不够k个则直接添加；否则用堆顶元素与当前元素比较，堆顶元素大则poll出加入当前元素，否则跳过
 * 思路3：
 *      对于数据范围有限的情况，可以直接计算每个数出现的次数，然后将这个计数数组从头开始遍历，直到找到前k个即可
 *      时间复杂度为O(N)
 */

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution1 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k <= 0){
            return new int[0];
        }else if (k >= arr.length){
            return Arrays.copyOf(arr, arr.length);
        }else{
            return quickSearch(arr, 0, arr.length-1, k-1);
        }
    }

    public int[] quickSearch(int[] arr, int st, int ed, int p){  // p为第k小的数的下标
        int index = partition(arr, st, ed);  // 划分好后已知最小的前index+1个数
        if(index < p){
            return quickSearch(arr, index+1, ed, p);
        }else if(index > p){
            return quickSearch(arr, st, index-1, p);
        }else{
            return Arrays.copyOf(arr, index+1);
        }
    }

    public int partition(int[] arr, int st, int ed){
        int key = arr[st];
        int i = st;
        int j = ed;
        while(i < j){
            while(j > i && arr[j] >= key){  // 注意：从后往前找
                j -= 1;
            }
            while(i < j && arr[i] <= key){
                i += 1;
            }
            if(i < j){
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        arr[st] = arr[j];
        arr[j] = key;
        return j;
    }
}

class Solution2 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k <= 0){
            return new int[0];
        }else if (k >= arr.length){
            return Arrays.copyOf(arr, arr.length);
        }

        // 默认小根堆，大根堆需要重写比较器
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((v1, v2) -> (v2 - v1));
        for(int item : arr){
            if(pq.size() < k){
                pq.offer(item);
            }else{
                int t = pq.peek();
                if(t > item){
                    pq.poll();
                    pq.offer(item);
                }
            }
        }

        int[] ret = new int[k];
        int i = 0;
        for(int item : pq){
            ret[i] = item;
            i += 1;
        }

        return ret;
    }
}

class Solution3 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k <= 0){
            return new int[0];
        }else if (k >= arr.length){
            return Arrays.copyOf(arr, arr.length);
        }

        int[] num = new int[10005];
        for(int i : arr){
            num[i] += 1;
        }

        int[] ret = new int[k];
        int inx = 0;
        for(int i=0; i<=10000 && inx < k; i++){
            while(num[i] > 0 && inx < k){
                num[i] -= 1;
                ret[inx] = i;
                inx += 1;
            }
        }

        return ret;
    }
}

public class The_smallest_k_number {
}
