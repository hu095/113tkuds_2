// 題目：Generate Parentheses
// 給定一個整數 n，表示要生成 n 對括號，請產生所有「有效括號組合」。

import java.util.*;

public class lt_22_GenerateParentheses {
    // 主函式：呼叫遞迴生成所有組合
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    // 遞迴回溯：生成所有可能的括號組合
    private static void backtrack(List<String> result, String cur, int open, int close, int max) {
        // 當字串長度達到 2*n，加入結果
        if (cur.length() == max * 2) {
            result.add(cur);
            return;
        }

        // 如果還能加左括號，就加一個 "("
        if (open < max) {
            backtrack(result, cur + "(", open + 1, close, max);
        }

        // 如果右括號數量 < 左括號，才能加一個 ")"
        if (close < open) {
            backtrack(result, cur + ")", open, close + 1, max);
        }
    }

    // 測試主程式
    public static void main(String[] args) {
        // Example 1
        int n1 = 3;
        List<String> result1 = generateParenthesis(n1);
        System.out.println("Example 1:");
        System.out.println("Input: n = " + n1);
        System.out.println("Output: " + result1);

        // Example 2
        int n2 = 1;
        List<String> result2 = generateParenthesis(n2);
        System.out.println("Example 2:");
        System.out.println("Input: n = " + n2);
        System.out.println("Output: " + result2);
    }
}

/*
解題思路：
1. 本題要求生成所有「有效」的括號組合。
2. 有效的條件是：
   - 左括號 "(" 的數量不能超過 n。
   - 右括號 ")" 的數量不能超過左括號數量。
3. 使用回溯法 (Backtracking)：
   - 每次選擇加 "(" 或 ")"。
   - 當字串長度達到 2*n 時，將組合加入結果。
4. 透過條件控制遞迴，避免生成無效的括號字串。
5. 時間複雜度為 O(4^n / sqrt(n))，對應卡特蘭數 (Catalan Number)。
*/
