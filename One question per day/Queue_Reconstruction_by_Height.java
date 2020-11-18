/*
假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 
编写一个算法来重建这个队列。
注意：
总人数少于1100人。

题解：
	首先让高个子排队（身高一样则按前面有几个人升序排列），最后让矮个子按前面有多少人进行插入对应位置
	第一步用数组排序即可，第二步用一个队列实现边添加边插入的方式重建队列，最后将队列转换为数组
	
	// [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
    // 再一个一个插入。
    // [7,0]
    // [7,0], [7,1]
    // [7,0], [6,1], [7,1]
    // [5,0], [7,0], [6,1], [7,1]
    // [5,0], [7,0], [5,2], [6,1], [7,1]
    // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]

	参考：https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/406-gen-ju-shen-gao-zhong-jian-dui-lie-java-xian-p/
 */
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (p1, p2) -> p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]);

        List<int[]> list = new ArrayList<>();
        for (int[] p : people) {
            list.add(p[1], p);
        }

        return list.toArray(new int[list.size()][2]);
    }
}