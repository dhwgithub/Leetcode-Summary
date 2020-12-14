/*
 * 
给定一个字符串数组，将字母异位词组合在一起。
字母异位词指字母相同，但排列不同的字符串。

示例:
输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

说明：
    所有输入均为小写字母。
    不考虑答案输出的顺序。

题解：
	方法一：
	统计每个字符串中每个数字出现的次数，
	然后以此（再加字符使其唯一）为key作为哈希表的索引来存储同异位词列表
	接着将每次处理后的列表存入哈希表中，最后得出答案
	
	新学到的转换：
	Map<String, List<String>> map = new HashMap<>();
	new ArrayList<List<String>>(map.values());
	
	方法二：
	同一思路，只不过将key值换为了字符排序后的字符串
	处理效率更好
 */
class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);

            List<String> list = null;
            if (map.containsKey(key)) {
                list = map.get(key);
            }
            else {
                list = new ArrayList<>();
            }
            
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<List<String>>(map.values());
    }
}
class Solution1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i ++) {
            // 求每个字符串对应字母的个数
            char[] arr = strs[i].toCharArray();
            int[] cnt = new int[26];
            for (int j = 0; j < arr.length; j ++) {
                cnt[arr[j] - 'a'] ++;
            }

            // 将字母与对应的数字作为key存入哈希表，作为异位词列表的key
            StringBuilder sb = new StringBuilder("");
            for (int j = 0; j < 26; j ++) {
                if (cnt[j] > 0) {
                    sb.append((char)('a' + j));
                    sb.append(cnt[j]);
                }
            }
            // 获取当前异位词列表，并加入当前字符串
            String key = sb.toString();
            List<String> curList = map.getOrDefault(key, new ArrayList<>());
            curList.add(strs[i]);
            // 将列表覆盖加入到map中
            map.put(key, curList);
        }

        // 类型转换
        return new ArrayList<List<String>>(map.values());
    }
}