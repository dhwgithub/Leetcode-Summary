/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 
 * 方法：回溯。关键在于去重，由于存在重复的相同元素又不能重复结果，因此可以先排序使得相等元素相邻，接着在每层节点中循环
 * 		若当前节点遍历过则跳过，若当前节点与前一个节点一样，且前一个节点已用过则跳过
 * 		这样可以保证同一级不重复（可通过反向来得到相邻相同的元素但又只有一次），vis标记保证每个数字只用一次
 *
 */

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Arrays.sort(nums, 0, nums.length);

        dfs(0, ans, new ArrayList<Integer>(), new boolean[nums.length], nums);

        return ans;
    }

    private void dfs(int index, List<List<Integer>> ans, List<Integer> list, boolean[] vis, int[] nums) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<Integer>(list));
            return ;
        }

        // for循环为保证同级树节点不重复, vis标记保证每个位置的数字只能被用一次
        for (int i = 0; i < nums.length; i ++) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && vis[i - 1])) {
                continue;
            }

            list.add(nums[i]);
            vis[i] = true;

            dfs(i + 1, ans, list, vis, nums);

            list.remove(list.size() - 1);
            vis[i] = false;
        }
    }
}