package leetcode.array_items;

/**
 *  题意：
 *      给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *      初始化 A 和 B 的元素数量分别为 m 和 n。A.length == n + m
 *  思路1：
 *      （不推荐）将两个数组直接合并，然后手写快排排序
 *  思路2：
 *      从A数组最后开始填充，定义指针i，j分别指向A、B的最后一位有效元素，定义k从A最后一位开始
 *      假定A、B数组都是升序，变换后也是升序，若不是则判断排序后进行指向调整，本题假定升序。
 *          若A[i] >= B[j]，则A[k] = A[i]，i--，k--
 *          若A[i] < B[j]，则A[k] = B[j]，j--，k--
 *      直到i<0或j<0停止，若i<0，则需要将B剩下的全部按序放入A中；若j<0，无需任何操作
 *
 */
class Merge_sorted_array2 {
    public void merge(int[] A, int m, int[] B, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while(i >=0 && j >= 0){
            if(A[i] >= B[j]){
                A[k] = A[i];
                i -= 1;
            }else{
                A[k] = B[j];
                j -= 1;
            }
            k -= 1;
        }
        while(j >= 0){
            A[k] = B[j];
            j -= 1;
            k -= 1;
        }
    }
}
class Merge_sorted_array1 {
    public void merge(int[] A, int m, int[] B, int n) {
        for(int i=m; i<(m + n); i++){
            A[i] = B[i-m];
        }
        quickSort(A, 0, (m+n-1));
    }

    public void quickSort(int[] arr, int left, int right){
        if(left >= right){
            return ;
        }
        int mid = merge(arr, left, right);
        quickSort(arr, left, mid-1);
        quickSort(arr, mid+1, right);
    }

    public int merge(int[] arr, int left, int right){
        if(left >= right){
            return right;
        }
        int i = left;
        int j = right;
        int key = arr[left];
        while(i < j){
            while(j > i && arr[j] >= key){
                j -= 1;
            }
            while(i < j && arr[i] <= key){
                i += 1;
            }
            if(i < j){
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        arr[left] = arr[j];
        arr[i] = key;
        return j;
    }
}
public class Merge_sorted_array {
}
