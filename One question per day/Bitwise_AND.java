/**
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 * 
 * 方法一：暴力。由题意知，将其变为二进制，只要某位含有0，则答案的对应二进制位也是0，模拟出答案即可（会超时）
 * 方法二：求公共前缀。分析知，由于是连续数字，因此每个数字最左相等的数一定是答案，右边的AND后都会变为0。
 * 		而求最左相等需要移动的位数，同时右移找到前缀位置，然后再左移即可
 * 方法三：当保持n<m时，不断去除n最右边的1，那么最后的结果就是答案。
 * 		
 * 方法二、三参考：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/solution/shu-zi-fan-wei-an-wei-yu-by-leetcode-solution/
 */
class rangeBitwiseAnd3 {
    public int rangeBitwiseAnd(int m, int n) {
        while (m < n) {
            n &= (n - 1); // 去除最右边的1
        }
        return n;
    }
}
class rangeBitwiseAnd2 {
    public int rangeBitwiseAnd(int m, int n) {
        int num = 0;
        while (m < n) {
            m >>= 1;
            n >>= 1;
            num ++;
        }
        return m << num;
    }
}
class rangeBitwiseAnd1 {
    public int rangeBitwiseAnd(int m, int n) {
        int[] num = new int[32];
		Arrays.fill(num, -1);
        for (int i = m; i <= n; i ++) {
            int index = num.length - 1;
            if (i == 0) {
                num[index] = 0;
                continue;
            }

            int t = i;
            while (t != 0) {
                if (num[index] != 0) {
                	int wi = num.length - index - 1;  //移动位数
                	int last = ((i - 1) >> wi) & 1;  // 上一个数当前位是1/0，如1和2AND时需要考虑
                	if (num[index] == -1 && last == 0 && i - 1 >= m) {
                		num[index] = 0;
                	} else {
                		num[index] = (t & 1) == 0 ? 0 : 1;
                	}
                }
                t >>= 1;
                index --;
            }
        }

        int ans = 0;
        for (int  i = num.length - 1, k = 1; num[i] != -1; i --, k <<= 1) {
            ans += num[i] * k;
        }
        return ans;
    }
}