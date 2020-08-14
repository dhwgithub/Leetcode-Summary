/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 
 * 方法一：使用栈，若匹配则出栈，不匹配则入栈。注意栈空的情况
 * 方法二：数组模拟栈，注意奇数时一定是false；同时一旦不满足情况即可返回false
 *
 */
class isValid2 {
    public boolean isValid(String s) {
        int len = s.length();
        if ((len & 1) == 1) {
            return false;
        }

        char[] stack = new char[len + 1];
        stack[0] = ' ';
        int index = 1;  // 排除栈空的情况

        char[] arr = s.toCharArray();
        for (int i=0; i<len; i++) {
            if (arr[i] == ')') {
                if (stack[--index] != '(') return false;
            } else if (arr[i] == ']') {
                if (stack[--index] != '[') return false;
            } else if (arr[i] == '}') {
                if (stack[--index] != '{') return false;
            } else {
                stack[index ++] = arr[i];
            }
        }

        return index == 1 ? true : false;
    }
}
class isValid1 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        char[] arr = s.toCharArray();
        for (int i=0; i<arr.length; i++) {
            char c = arr[i];
            
            if ( !stack.isEmpty() && c == ')' && stack.peek() == '(') {
                stack.pop();
            }else if ( !stack.isEmpty() && c == '}' && stack.peek() == '{') {
                stack.pop();
            }else if ( !stack.isEmpty() && c == ']' && stack.peek() == '[') {
                stack.pop();
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty() ? true : false;
    }
}