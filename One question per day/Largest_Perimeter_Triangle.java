/*
给定由一些正数（代表长度）组成的数组 A，
返回由其中三个长度组成的、面积不为零的三角形的最大周长。
如果不能形成任何面积不为零的三角形，返回 0。

提示：
    3 <= A.length <= 10000
    1 <= A[i] <= 10^6

题解：
	组成三角形，则任意两边之和大于第三边
	
	贪心。为求出最大周长，则三边长越大越好
	升序排序后倒序查找，如果找到前两个大于当前长度则输出
	否则输出0表示找不到
 */

class Solution {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        
        int len = A.length;
        for (int i = len - 1; i >= 2; i --) {
            if (A[i] < A[i - 1] + A[i - 2]) {
                return A[i] + A[i - 1] + A[i - 2];
            }
        }

        return 0;
    }
}