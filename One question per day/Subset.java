/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 方法：回溯。每个位置选或不选，最后保存结果即得到答案
 */

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        dfs(0, ans, new ArrayList<Integer>(), nums);
        return ans;
    }

    private void dfs(int cur, List<List<Integer>> ans, List<Integer> list, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(list));
            return ;
        }
        if (cur > nums.length) {
            return ;
        }

        list.add(nums[cur]);
        dfs(cur + 1, ans, list, nums);
        list.remove(list.size() - 1);

        dfs(cur + 1, ans, list, nums);
    }
}