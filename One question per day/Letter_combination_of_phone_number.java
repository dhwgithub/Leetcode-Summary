/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。（原文有数字字母对应表）
 *
 * 方法：搜索每一个字母（每次取该字母的一位，只有当新字符串长度是给定字符串长度时表明符合要求）
 */

class letterCombinations1 {
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return list;
        }

        Map<Character, String> map = new HashMap<Character, String>(){{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        getAns(0, new StringBuffer(), list, map, digits.toCharArray());

        return list;
    }

    private void getAns(int index, StringBuffer str, List<String> list, Map<Character, String> map, char[] arr) {
        if (index == arr.length) {
            list.add(str.toString());
            return ;
        }

        String temp = map.get(arr[index]);
        int len = temp.length();
        for (int i = 0; i < len; i ++) {
            str.append(temp.charAt(i));
            getAns(index + 1, str, list, map, arr);
            str.deleteCharAt(index);
        }
    }
}