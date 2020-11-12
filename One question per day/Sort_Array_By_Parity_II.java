/*
给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
你可以返回任何满足上述条件的数组作为答案。

提示：
    2 <= A.length <= 20000
    A.length % 2 == 0
    0 <= A[i] <= 1000

 */

class Solution {
    public int[] sortArrayByParityII(int[] A) {
        
        int j = 1;  // 奇数区间开始下标

        for (int i = 0; i < A.length; i += 2) {  // 遍历偶数区间
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1){  // 从奇数区间找偶数
                    j += 2;
                }
                int t = A[i];
                A[i] = A[j];
                A[j] = t;
            }
        }

        return A;
    }
}