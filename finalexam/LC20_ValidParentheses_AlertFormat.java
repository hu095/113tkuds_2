import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 題目 4 緊急通報格式括號檢查
 * 檔名：LC20_ValidParentheses_AlertFormat.java
 *
 * 輸入：
 *   一行括號字串（僅含 ()[]{}）
 *
 * 輸出：
 *   true / false
 *
 * 解法：
 *   - 使用 stack 模擬。
 *   - 遇到左括號 push。
 *   - 遇到右括號 → 檢查 stack 頂是否對應，否則 false。
 *   - 遍歷結束後 stack 必須為空。
 *
 * 複雜度：時間 O(n)，空間 O(n)
 */
public class LC20_ValidParentheses_AlertFormat {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));

        String s = br.readLine();
        if (s == null) s = ""; // 空輸入視為空字串

        System.out.println(isValid(s));
    }

    /** 驗證字串是否為有效括號格式 */
    public static boolean isValid(String s) {
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (pairs.containsValue(c)) {
                // 左括號：入棧
                stack.push(c);
            } else if (pairs.containsKey(c)) {
                // 右括號：需匹配棧頂
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) {
                    return false;
                }
            } else {
                // 題目已保證僅含括號，這裡理論上不會進來
                return false;
            }
        }
        return stack.isEmpty();
    }
}
