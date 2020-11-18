/*
在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

说明: 
    如果题目有解，该答案即为唯一答案。
    输入数组均为非空数组，且长度相同。
    输入数组中的元素均为非负数。

题解：
	贪心。
	1、直接求解
	2、不仅统计当前油量（不能小于0）
		还要统计总油量，这样遍历一遍就可知道是否存在这样的点。若总油量小于0则不可能存在，否则输出计算过程中的那个值
 */
class Solution2 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tatolGas = 0;
        int curGas = 0;
        int ans = 0;
        
        for (int i = 0; i < gas.length; i ++) {
            tatolGas += gas[i] - cost[i];
            curGas += gas[i] - cost[i];
            if (curGas < 0){
                ans = i + 1;
                curGas = 0;
            }
        }

        return tatolGas < 0 ? -1 : ans;
    }
}
class Solution1 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int num = gas.length;
        for (int i = 0; i < num; i ++) {
            if (gas[i] < cost[i]) {
                continue;
            }

            int sumGas = gas[i];
            for (int j = i, n = 0; n < num; j ++, n ++) {
                j %= num;
                sumGas -= cost[j];
                if (sumGas < 0) {
                    break;
                }
                sumGas += gas[(j + 1) % num];
            }
            if (sumGas >= 0) {
                return i;
            }
        }
        return -1;
    }
}