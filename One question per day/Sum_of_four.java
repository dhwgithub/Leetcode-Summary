/**
给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
注意：
答案中不可以包含重复的四元组。

方法：模拟。常规方法是直接四层循环+去重。而我们可以首先两层循环遍历前两个数，接着用left和right指针确定后两个数。将升序好的数组放入遍历
	若当前和等于目标值，那么left++且right--，同时注意不要使得left和right停留在与上一次一样的数上
	若当前和大于目标值，那么right--
	若当前和小于目标值，那么left++
	
	剪枝：若当前值等于上一个值（在同一层循环中），那么跳过
		若此时能达到的最大和小于目标值，那么跳过当前值往后遍历
		若此时能达到的最小和大于目标值，那么break
 */

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return ans;
        }

        int len = nums.length;
        Arrays.sort(nums, 0, len);
        
        for (int i = 0; i < len - 3; i ++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3] < target) {
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            for (int j = i + 1; j < len - 2; j ++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[len - 1] + nums[len - 2] < target) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                int left = j + 1;
                int right = len - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left ++;
                        }
                        left ++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right --;
                        }
                        right --;
                    } else if (sum > target) {
                        right --;
                    } else {
                        left ++;
                    }
                }
            }
        }
        return ans;
    }
}