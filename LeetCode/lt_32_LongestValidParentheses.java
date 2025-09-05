import java.util.*;

// 題目：Longest Valid Parentheses
// 給定一個字串 s，該字串只包含 '(' 和 ')'，
// 回傳該字串中最長的有效括號子字串長度。
// 有效括號子字串指的是每個 '(' 都有一個對應的 ')' 並且括號是閉合的。

public class lt_32_LongestValidParentheses {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 測試案例 1
        String s1 = "(()";
        System.out.println("Example 1:");
        System.out.println("Input:  s = \"" + s1 + "\"");
        System.out.println("Output: " + sol.longestValidParentheses(s1));
        // 預期結果: 2

        // 測試案例 2
        String s2 = ")()())";
        System.out.println("Example 2:");
        System.out.println("Input:  s = \"" + s2 + "\"");
        System.out.println("Output: " + sol.longestValidParentheses(s2));
        // 預期結果: 4

        // 測試案例 3
        String s3 = "";
        System.out.println("Example 3:");
        System.out.println("Input:  s = \"" + s3 + "\"");
        System.out.println("Output: " + sol.longestValidParentheses(s3));
        // 預期結果: 0
    }
}

class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1); // 基準：最後一個不合法位置

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(i); // 遇到 '('，將其索引推入堆疊
            } else {
                st.pop(); // 遇到 ')'，嘗試匹配最近的 '('
                if (st.isEmpty()) {
                    st.push(i); // 若堆疊為空，則此 ')' 是新的不合法基準
                } else {
                    ans = Math.max(ans, i - st.peek()); // 計算有效括號的長度
                }
            }
        }
        return ans;
    }
}

/*
解題思路：
1) 用堆疊來追蹤括號的有效性，堆疊中保存的是括號的索引。
2) 初始時，堆疊中放入 -1 作為基準，表示最初沒有有效的括號。
3) 遍歷字串：
   • 當遇到 '(' 時，將其索引推入堆疊。
   • 當遇到 ')' 時，彈出堆疊的元素（即最近的 '('）進行匹配：
       - 如果堆疊已空，將當前 ')' 的索引當作新的基準推入堆疊。
       - 否則，使用 `i - st.peek()` 計算當前有效括號的長度（`st.peek()` 存的是最後一個未匹配括號的索引）。
4) 這樣每次匹配到一對有效括號，就能計算並更新最長有效括號子字串的長度。

時間複雜度：O(n)，因為每個字符最多進出堆疊一次。  
空間複雜度：O(n)，需要用堆疊保存索引。
*/
