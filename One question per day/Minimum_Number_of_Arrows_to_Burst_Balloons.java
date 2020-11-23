/*
在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
一支弓箭可以沿着 x 轴从不同点完全垂直地射出。
在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。
可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。

提示：
    0 <= points.length <= 104
    points[i].length == 2
    -231 <= xstart < xend <= 231 - 1

题解：
	将所有的气球按end从小到大排序，然后每次取第一个气球的end作为基准，若后面的气球宽度在end两边（包括）则一支箭可以同时刺破
	否则需要另外添加箭的个数。而这里的第一个气球，是指每次处理后没有被刺破的气球数

 */
class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;
        
        int len = points.length;
        Arrays.sort(points, 0, len, (p1, p2) -> p1[1] - p2[1]);

        int sum = 1;
        int last = points[0][1];
        for (int i = 1; i < len; i ++) {
            if (points[i][0] <= last && points[i][1] >= last) continue;

            sum ++;
            last = points[i][1];
        }

        return sum;
    }
}