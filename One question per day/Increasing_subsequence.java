/**
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 * 
 * 方法一：枚举所有情况然后去重。注意去重计算列表哈希值（初始为0）的写法：
 * 		hashValue = hashValue * 256 % mod + (x + 101)
 * 
 * 方法二：枚举的过程中进行去重，效率较高。将每一位看做可取可不取，
 *			当可取时需要满足非严格递增的条件；
 * 			当不可取的时候需要满足当前位置的值不等于上一个取到的值（否则会重复）
 * 			其他情况直接返回，没有满足条件的子序列
 *
 */
class findSubsequences2 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 2) {
            return list;
        }

        List<Integer> t = new ArrayList<Integer>();
        getList(0, Integer.MIN_VALUE, t, list, nums);

        return list;
    }

    private void getList(int cur, int last_v, List<Integer> list, 
                            List<List<Integer>> ans, int[] nums){
        if (cur == nums.length) {
            if (list.size() >= 2) {
                ans.add(new ArrayList<Integer>(list));
            }
            return ;
        }

        if (nums[cur] >= last_v) {  // 取当前位置的值
            list.add(nums[cur]);
            getList(cur + 1, nums[cur], list, ans, nums);
            list.remove(list.size() - 1);
        }

        if (nums[cur] != last_v) {  // 不取当前位置的值
            getList(cur + 1, last_v, list, ans, nums);
        }
    }
}
class findSubsequences1 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        
        if (nums == null || nums.length < 2) {
            return list;
        }

        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> mul = new HashSet<Integer>();

        for (int i = 0; i < nums.length - 1; i ++) {
            if ( ! set.contains(nums[i])) {
                List<Integer> t = new ArrayList<Integer>();
                t.add(nums[i]);
                set.add(nums[i]);
                getList(i, t, nums, list, mul);
            }
        }

        return list;
    }

    private void getList(int index, List<Integer> list, int[] nums, List<List<Integer>> ans, 
                        Set<Integer> mul) {
        if (list.size() > nums.length) {
            return ;
        }
        if (list.size() >= 2) {
            long hashValue = 0;
            int mod = 1000000007;
            for (Integer x : list) {
                hashValue = hashValue * 256 % mod + (x + 101);
                hashValue %= mod;
            }
            
            if ( ! mul.contains((int)hashValue)) {
                mul.add((int)hashValue);
                ans.add(new ArrayList<Integer>(list));
            }
        }

        for (int i = index + 1; i < nums.length; i ++) {
            if (nums[i] >= list.get(list.size() - 1)) {
                list.add(nums[i]);
                getList(i, list, nums, ans, mul);
                list.remove(list.size() - 1);
            }
        }
    }
}