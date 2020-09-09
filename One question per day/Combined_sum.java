/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。

说明：

    所有数字（包括 target）都是正整数。
    解集不能包含重复的组合。 
    
提示：

    1 <= candidates.length <= 30
    1 <= candidates[i] <= 200
    candidate 中的每个元素都是独一无二的。
    1 <= target <= 500
 *
 *	方法：DFS+剪枝。其中剪枝包含对原数组进行排序，这样后续可以提前对超出target的做出停止搜索的判断
 *		还有需要注意的是，对于重复选择的，包含选择一次的，无需写重复且搜索下一位的情况
 */

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(candidates, 0, candidates.length);
        dfs(0, candidates, target, list, ans);
        return ans;
    }

    private void dfs(int index, int[] candidates, int target, List<Integer> list, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<Integer>(list));
            return ;
        }
        if (index >= candidates.length || candidates[index] > target || target < 0) {
            return ;
        }

        // 重复选当前（不需要继续写不重复选当前，后续过程已经包含了）
        list.add(candidates[index]);
        dfs(index, candidates, target - candidates[index], list, ans);
        list.remove(list.size() - 1);

        // 不选当前
        dfs(index + 1, candidates, target, list, ans);
    }
}