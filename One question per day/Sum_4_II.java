/*
给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。

题解：
	将部分组的所有和存入哈希表中，然后将其他组的和的相反数查询，可以得到等于0的情况数
	若将一组加入，则时间复杂度为O(n)+O(n^3)
	若将两组加入，则时间复杂度为O(n^2)+O(n^2)
	若将三组加入，则时间复杂度同一
	
	因此采用第二种方法，总的时间复杂度是O(n^2)
	然后for循环遍历即可

 */
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < A.length; i ++) {
            for (int j = 0; j < B.length; j ++) {
                int t = A[i] + B[j];
                if (map.containsKey(t)) {
                    map.put(t, map.get(t) + 1);
                } else {
                    map.put(t, 1);
                }
            }
        }

        int sum = 0;

        for (int i = 0; i < C.length; i ++) {
            for (int j = 0; j < D.length; j ++) {
                int t = - (C[i] + D[j]);
                if (map.containsKey(t)) sum += map.get(t);
            }
        }

        return sum;
    }
}