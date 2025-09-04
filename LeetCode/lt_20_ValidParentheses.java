// 題目：Valid Parentheses
// 給定一個只包含 '(', ')', '{', '}', '[' 和 ']' 的字串，判斷字串是否為有效括號。

import java.util.*;

public class lt_20_ValidParentheses {
    public static void main(String[] args) {
        // 測試案例
        System.out.println(isValid("()"));      // true
        System.out.println(isValid("()[]{}"));  // true
        System.out.println(isValid("(]"));      // false
        System.out.println(isValid("([])"));    // true
        System.out.println(isValid("([)]"));    // false
    }

    // 判斷括號是否有效
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                // 左括號入棧
                stack.push(c);
            } else {
                // 碰到右括號時，必須和棧頂匹配
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == ']' && top != '[') return false;
                if (c == '}' && top != '{') return false;
            }
        }
        // 若棧為空，說明所有括號都正確配對
        return stack.isEmpty();
    }
}

/*
解題思路：
1. 使用 Stack 來追蹤未匹配的左括號。
2. 遍歷字串：
   - 如果是左括號 → push 進 stack。
   - 如果是右括號 → stack 必須非空，且 pop 出的括號要與之匹配。
3. 若遇到右括號時 stack 為空，或 pop 出的括號不匹配 → 回傳 false。
4. 最後檢查 stack 是否為空，若不為空代表還有未匹配的左括號 → false。
5. 時間複雜度 O(n)，空間複雜度 O(n)。
*/
