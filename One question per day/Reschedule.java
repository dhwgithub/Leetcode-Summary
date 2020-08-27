/**
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 * 说明:

    如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
    所有的机场都用三个大写字母表示（机场代码）。
    假定所有机票至少存在一种合理的行程。
 *
 * 方法：DFS。由题意可知是一个无向（半）欧拉图，即所有边走一遍，相当于一笔画问题。
 * 		由于需要按字典序，因此使用优先队列进行处理
 * 		对于入度和初度相差1的点，该点是一个"死胡同"，需要最后走
 * 		搜索的时候，可以先把该点所有的边走完再添加顶点，那么第一个被添加的是终点。这样得到的顺序是反的，再反转即可
 * 		每次走完后需要将该边删除，由于只有一个路径（按字典序），因此无需回溯
 */

class findItinerary1 {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> list = new ArrayList<String>();
        if (tickets == null || tickets.size() == 0) {
            return list;
        }

        Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
        for (int i = 0; i < tickets.size(); i ++) {
            List<String> t = tickets.get(i);
            String k = t.get(0);
            if ( ! map.containsKey(k)) {
                map.put(k, new PriorityQueue<String>());
            }
            map.get(k).offer(t.get(1));
        }

        dfs("JFK", map, list);
        Collections.reverse(list);

        return list;
    }

    private void dfs(String find, Map<String, PriorityQueue<String>> map, List<String> list) {
        while (map.containsKey(find) && map.get(find).size() > 0) {
            String t = map.get(find).poll();
            dfs(t, map, list);
        }
        list.add(find);
    }
}