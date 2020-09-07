import java.util.List;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 提示：

    你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
    你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
    题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
    你可以按任意顺序返回答案。
 *
 * 方法1：最小堆，堆的最大空间是k。存储元素时，当堆大小小于k时直接加入；
 * 	当当前元素个数大于堆顶元素个数时，移除堆顶元素加入当前元素
 * 	事先用哈希表存储好每个元素出现的次数
 * 	时间复杂度O(Nlogk)，空间复杂度O(N)
 * 方法2：桶排序。哈希表存储每个元素出现的次数，然后创建一个数组，每个元素存储的位置是当前元素出现的次数
 * 		注意链表数组声明方式，时空复杂度都是O(N)
 * 方法3：
 */

class topKFrequent2 {
    public int[] topKFrequent(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        List<Integer>[] list = new List[nums.length + 1];
        for (int i : map.keySet()) {
            int index = map.get(i);
            if (list[index] == null) {
                list[index] = new ArrayList<Integer>();
            }
            list[index].add(i);
        }

        int[] ans = new int[k];
        int index = 0;
        for (int i = nums.length; i >= 0 && index < k; i --) {
            if (list[i] == null) continue;
            for (int j : list[i]) {
                ans[index ++] = j;
                if (index >= k) break;
            }
        }

        return ans;
    }
}
class topKFrequent1 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }

        // 创建最小堆
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });

        for (Integer i : map.keySet()) {
            if (queue.size() < k) {
                queue.offer(i);
            } else if (map.get(queue.peek()) < map.get(i)) {
                queue.poll();
                queue.offer(i);
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i ++) {
            ans[i] = queue.poll();
        }

        return ans;
    }
}