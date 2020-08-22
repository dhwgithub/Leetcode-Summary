/**
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 * 
 * 注意:

    除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
    每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
    你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 
    
 * 方法：首先将数组改变为double类型方便计算
 * 由于位数较少因此可以枚举所有情况进行判断，利用回溯的方法解题
 * 从数组中任取两个数，然后新建一个结果数组保存其他所有数，最后加上找出那两个数的结果（回溯遍历所有结果）
 * 然后递归计算，只要当传入的数字长度为1时，说明4个数字全部计算完毕，因此可以判断是否为24
 * 注意浮点数判断时含有误差，在较小误差下即可判定为等于24
 * 
 * 在计算两数运算时，减法和除法有两种情况，需要同时加入结果数组。注意数字顺序可以变
 *
 */

class Solution {
    public boolean judgePoint24(int[] nums) {
        double[] t = new double[4];
        for (int i=0; i<4; i++) {
            t[i] = (double)nums[i];
        }
        return dfs(t);
    }

    private boolean dfs(double[] nums) {
        if (nums.length == 1) {
            return Math.abs(nums[0] - 24) < 1e-6;
        }

        int len = nums.length;
        for (int i = 0; i < len; i ++) {
            for (int j = i+1; j < len; j ++) {
                double[] t = new double[len - 1];

                // 保存未被使用的数
                for (int k = 0, index = 0; k < len; k ++) {
                    if (k != i && k != j) {
                        t[index ++] = nums[k];
                    }
                }

                // 计算两数结果
                for (double res : computes(nums[i], nums[j])) {
                    t[len - 2] = res;
                    if (dfs(t) == true) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private double[] computes(double x, double y) {
        // 若 x/y 中 y=0，则结果为Infinity，但是不影响后续计算（仍为Infinity），因此可以不判断是否为0
        return new double[]{x + y, x - y, y - x, x * y, x / y, y / x};
    }
}