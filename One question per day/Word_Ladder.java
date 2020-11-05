/*
给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。
转换需遵循如下规则：
    每次转换只能改变一个字母。
    转换过程中的中间单词必须是字典中的单词。
说明:
    如果不存在这样的转换序列，返回 0。
    所有单词具有相同的长度。
    所有单词只由小写字母组成。
    字典中不存在重复的单词。
    你可以假设 beginWord 和 endWord 是非空的，且二者不相同。

题解1：
	哈希表存储之前步数出现过的单词避免重复查找，然后从开始单词遍历字典
	直到找到后存入队列中，继续查找下一个符合要求的单词，直到查到结束单词或总步数超过字典长度
题解2：
	设定三个哈希表，分别存开始单词begin，结束单词end和字典dict。
	每次从begin或end中有较少单词的表中开始搜索：
		对于搜索的某个表的每个单词，每次变换一位字符，然后查看是否存在于字典中。
			若存在则查看是否存在结束单词中，存在则输出最终的长度步数；否则存入新创建的开始表中用于下次使用
			若不存在则忽略这个单词继续查找下一个
		最后复原单词，结束开始表查询后进入下一次dfs查询
	剪枝：
		当最终单词不存在字典中时直接返回 0
		所有的存储都用哈希表
		每次查询把字典中包含开始表的词全部删除
		两个单词表没有连接后直接返回 0
		用数量最少的表开始查找
 */
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> begin = new HashSet<>();
        begin.add(beginWord);
        Set<String> end = new HashSet<>();
        end.add(endWord);
        Set<String> dict = new HashSet<>(wordList);

        if (! dict.contains(endWord)) {
            return 0;
        }

        return dfs(begin, end, dict, 2);  // 如果存在则一定从2开始计算
    }

    private int dfs(Set<String> begin, Set<String> end, Set<String> dict, int sum) {
        // 中间无法连接则表示没有连接序列
        if (begin.size() == 0) {  
            return 0;
        }
        // 用最少的列表查找
        if (begin.size() > end.size()) {  
            return dfs(end, begin, dict, sum);
        }

        // 去重
        dict.removeAll(begin);  

        // 存储下一层的单词
        Set<String> next = new HashSet<>();  
        
        for (String s : begin) {
            char[] t = s.toCharArray();

            for (int i = 0; i < t.length; i ++) {  // 更改每一位的字符查看字典是否存在（每次改一位）
                char temp = t[i];
                // 更改为的新字符
                for (char c = 'a'; c <= 'z'; c ++) {  
                    // 排除重复的
                    if (temp == c) continue;  

                    t[i] = c;
                    String str = String.valueOf(t);
                    // 新单词存在字典中
                    if (dict.contains(str)) {  
                        if (end.contains(str)) {  // 连接成功则返回步数
                            return sum;
                        } else {
                            next.add(str);
                        }
                    }
                }
                // 注意不要忘了复原
                t[i] = temp;
            }
        }
        return dfs(next, end, dict, sum + 1);
    }
}
class Solution1 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {-1, 0});

        Set<String> set = new HashSet<String>();
        set.add(beginWord);

        while (! queue.isEmpty()) {
            int[] t = queue.poll();
            String cur = t[0] == -1 ? beginWord : wordList.get(t[0]);
            int ans = t[1];

            set.add(cur);
            int sum = queue.size();
            while (sum -- > 0) {
                t = queue.poll();
                set.add(wordList.get(t[0]));
                queue.add(t);
            }

            if (cur.equals(endWord)) {
                return ans + 1;
            }

            if (ans > wordList.size()) {
                return 0;
            }
            
            for (int i = 0; i < wordList.size(); i ++) {
                if (set.contains(wordList.get(i))) {
                    continue;
                }
                if (dist(cur.toCharArray(), wordList.get(i).toCharArray())) {
                    // System.out.println(wordList.get(i) + " " + (ans + 1));
                    queue.offer(new int[] {i, ans + 1});
                }
            }
            // System.out.println("====================");
        }

        return 0;
    }

    private boolean dist(char[] c1, char[] c2) {
        int sum = 0;
        for (int i = 0; i < c1.length; i ++) {
            if (c1[i] != c2[i]) {
                sum ++;
            }
            if (sum > 1) {
                return false;
            }
        }

        return sum == 1;
    }
}