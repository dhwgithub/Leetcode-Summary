package leetcode.recursion_items;

/**
 *  题意：
 *      你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。
 *      你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 *      返回的长度需要从小到大排列。
 *      0 < shorter <= longer
 *      0 <= k <= 100000
 *  思路1：
 *      递归。当k=0和shorter=longer时，答案是确定的一个。下同
 *  思路2：
 *      非递归。可以看成等差数列，从shorter*k开始，每次增加他们的差值。
 *      那么一共需要求（（尾项 - 首项） / 差值 + 1）项，也就是一般情况下的 k + 1
 */
class Diving_board2 {
    public int[] divingBoard(int shorter, int longer, int k) {
        if(k == 0){
            return new int[0];
        }
        if(shorter == longer){
            return new int[]{shorter * k};
        }

        int minx = shorter * k;
        int maxx = longer * k;
        int diff = longer - shorter;
        int cnt = (maxx - minx) / diff + 1;

        int[] nums = new int[cnt];
        nums[0] = minx;
        for(int i=1; i<cnt; i++){
            nums[i] = nums[i-1] + diff;
        }

        return nums;
    }
}
class Diving_board1 {
    public int[] divingBoard(int shorter, int longer, int k) {
        if(k == 0){
            return new int[0];
        }
        if(shorter == longer){
            return new int[]{shorter * k};
        }
        int[] nums = new int[k+1];
        search(nums, shorter, longer, 0, k);
        return nums;
    }

    public void search(int[] nums, int shorter, int longer, int num, int k){
        if(num > k){
            return ;
        }
        nums[num] = shorter * (k - num) + longer * num;
        search(nums, shorter, longer, num+1, k);
    }
}
public class Diving_board {
}
