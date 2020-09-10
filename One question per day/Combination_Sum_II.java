/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

    所有数字（包括目标数）都是正整数。
    解集不能包含重复的组合。 

 *
 *	方法：DFS+剪枝。注意剪枝，保证每一层级相同元素只遍历一次，而不同级相同元素可以重复遍历
 */

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();

        Arrays.sort(candidates, 0, candidates.length);
        dfs(0, candidates, target, list, ans);

        return ans;
    }

    private void dfs(int index, int[] arr, int target, List<Integer> list, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<Integer>(list));
            return ;
        }
        if (index >= arr.length || arr[index] > target) {
            return ;
        }
        
        // 保证每一层级相同元素只遍历一次，而不同级相同元素可以重复遍历
        for (int i = index; i < arr.length; i ++) {
            if (i > index && arr[i] == arr[i - 1]) {
                continue;
            }
            list.add(arr[i]);
            dfs(i + 1, arr, target - arr[i], list, ans);  // i + 1 保证不重复
            list.remove(list.size() - 1);
        }
    }
}