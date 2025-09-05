import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 題目 5 北捷進出站最長有效片段
 * 檔名：LC32_LongestValidParen_Metro.java
 *
 * 輸入：一行括號字串（僅含 '(' 或 ')'）
 * 輸出：最長合法片段長度（整數）
 *
 * 解法：索引堆疊（棧底放 -1 作基準）
 *  - 遇 '(' -> push 當前索引
 *  - 遇 ')' -> pop；若空則以當前索引作新基準；否則更新 ans = max(ans, i - stack.peek())
 * 複雜度：O(n) 時間，O(n) 空間
 */
public class LC32_LongestValidParen_Metro {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String s = br.readLine();
        if (s == null) s = "";

        System.out.println(longestValidParentheses(s));
    }

    /** 棧索引法：最長有效括號長度 */
    private static int longestValidParentheses(String s) {
        int n = s.length();
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1); // 基準：最後一個不合法位置

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(i);
            } else { // c == ')'
                if (!st.isEmpty()) st.pop();      // 嘗試匹配最近的 '('
                if (st.isEmpty()) {
                    st.push(i);                   // 無可匹配 -> 以此 ')' 當新基準
                } else {
                    ans = Math.max(ans, i - st.peek()); // 以當前索引減去最後不合法位置
                }
            }
        }
        return ans;
    }
}
