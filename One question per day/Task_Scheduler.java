/*
给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。
任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
你需要计算完成所有任务所需要的 最短时间 。

提示：
    1 <= task.length <= 104
    tasks[i] 是大写英文字母
    n 的取值范围为 [0, 100]

题解：
	当有空闲时间时需要冷却，此时：将所有任务计数，然后找到出现次数最多的任务次数。以最多出现的任务开始编排顺序，中间填充其他任务+冷却时间
		最后会剩余和当前任务出现次数一样的任务，将其个数加起来（包括当前任务），计算时第一步不加该项
		那么最终花费的（最小时间为出现最多次数-1）×（n+1）+和当前任务次数一样的任务数，其中的n+1是中间填充任务的时间+冷却时间+当前任务花费的时间
	参考：https://leetcode-cn.com/problems/task-scheduler/solution/jian-ming-yi-dong-de-javajie-da-by-lan-s-jfl9/
 */
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] num = new int[26];
        for (int i = 0; i < tasks.length; i ++) {
            num[tasks[i] - 'A'] ++;
        }
        Arrays.sort(num);

        int maxTimes = num[25];
        int maxSum = 0;
        for (int i = 25; i >= 0; i --) {
            if (num[i] == maxTimes) {
                maxSum ++;
            } else {
                break;
            }
        }

        int ans = (maxTimes - 1) * (n + 1) + maxSum;

        return Math.max(ans, tasks.length);
    }
}