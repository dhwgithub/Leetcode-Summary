/*
统计所有小于非负整数 n 的质数的数量。
提示：
    0 <= n <= 5 * 106


题解：
	方法1： 筛法求素数
	方法2： 线性筛求素数
		在筛法求素数基础上，将所有的素数记录下来并与当前元素相乘，结果一定不是素数
		无论是否是质数，都将当前数的倍数标记为非质数
			若当前元素可以被其中已知素数除尽则表示该质数的若干倍数已经被计算一遍了，无需继续记录当前数的倍数
		这样每个数都只能遍历一遍，时间复杂度降低
 */

class Solution2 {
    public int countPrimes(int n) {
        int sum = 0;
        int[] prime = new int[n];
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for (int i = 2; i < n; i ++) {
            // 记录每一个质数
            if (isPrime[i]) {
                prime[sum ++] = i;
            }
            // 当前数与已知质数的倍数一定不是质数，标记起来
            // 若当前数可被已知质数除尽，则表示之前已经把与已知质数的倍数都标记完成了，可以退出标记循环
            for (int j = 0; j < sum; j ++){
                if (i * prime[j] >= n) break;

                isPrime[i * prime[j]] = false;
                if (i % prime[j] == 0) break;
            }
        }
        return sum;
    }
}
class Solution1 {
    public int countPrimes(int n) {
        int sum = 0;
        boolean[] vis = new boolean[n + 5];
        for (int i = 2; i < n; i ++) {
            if ( ! vis[i]) {
                sum += 1;
                for (int j = i + i; j < n; j += i){
                    vis[j] = true;
                }
            }
        }
        return sum;
    }
}
