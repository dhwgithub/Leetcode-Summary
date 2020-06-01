package leetcode.binary_search_items;

/**
 *  题意：
 *      稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
 *      words的长度在[1, 1000000]之间
 *  思路：
 *      二分查找，首先找到非""的两个左右边界，然后比较中间元素，若为""则向右移动直到非""，最后比较确定新的区间
 *      注意每次变化后都要确保i,j,mid对应的数都是非""
 */
class Sparse_array_search1 {
    public int findString(String[] words, String s) {
        int i = 0;
        int j = words.length - 1;

        while(i < j){
            while(i < j && words[j].equals("") == true) j -= 1;
            while(i < j && words[i].equals("") == true) i += 1;

            int mid = j + ((i - j) >> 1);
            int t = mid;
            while(t < j && words[t].equals("") == true) t += 1;
            if(t > j) return -1;

            if(words[t].compareTo(s) > 0) j = mid - 1;
            else if(words[t].compareTo(s) < 0) i = mid + 1;
            else return t;
        }
        if(words[i].equals(s) == true) return i;
        else return -1;
    }
}
public class Sparse_array_search {
}
