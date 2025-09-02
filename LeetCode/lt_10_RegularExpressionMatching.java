// 題目：Regular Expression Matching
// 給定字串 s 和模式 p，實作正則比對，支援以下規則：
// 1. '.' 可以匹配任意單一字元。
// 2. '*' 代表前一個字元可出現 0 次或多次。
// 要求整個字串完全匹配模式。

public class lt_10_RegularExpressionMatching {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        String s1 = "aa", p1 = "a";
        boolean out1 = sol.isMatch(s1, p1);
        System.out.println("Example 1:");
        System.out.println();
        System.out.println("Input: s = \"" + s1 + "\", p = \"" + p1 + "\"");
        System.out.println("Output: " + out1);
        System.out.println("Explanation: \"a\" does not match the entire string \"aa\".");
        System.out.println();

        // Example 2
        String s2 = "aa", p2 = "a*";
        boolean out2 = sol.isMatch(s2, p2);
        System.out.println("Example 2:");
        System.out.println();
        System.out.println("Input: s = \"" + s2 + "\", p = \"" + p2 + "\"");
        System.out.println("Output: " + out2);
        System.out.println("Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes \"aa\".");
        System.out.println();

        // Example 3
        String s3 = "ab", p3 = ".*";
        boolean out3 = sol.isMatch(s3, p3);
        System.out.println("Example 3:");
        System.out.println();
        System.out.println("Input: s = \"" + s3 + "\", p = \"" + p3 + "\"");
        System.out.println("Output: " + out3);
        System.out.println("Explanation: \".*\" means \"zero or more (*) of any character (.)\".");
    }
}

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();

        // dp[i][j] 表示 s[i:] 與 p[j:] 是否能匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 空字串和空模式是匹配的
        dp[m][n] = true;

        // 從字串尾端往前填表 (Bottom-up DP)
        for (int i = m; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // 當前字元能否匹配
                boolean firstMatch = (i < m) &&
                        (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

                if (j + 1 < n && p.charAt(j + 1) == '*') {
                    // 若下一個字元是 '*'，有兩種選擇：
                    // 1. 跳過「x*」這兩個字元 → dp[i][j+2]
                    // 2. 若當前字元能匹配，繼續比對 s[i+1:] 與 p[j:] → dp[i+1][j]
                    dp[i][j] = dp[i][j + 2] || (firstMatch && dp[i + 1][j]);
                } else {
                    // 否則必須當前匹配，且繼續比對下一個字元
                    dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
        }

        return dp[0][0]; // 回傳是否能完全匹配
    }
}

/*
解題思路：
1. 題目要求字串 s 與模式 p 是否完全匹配，模式支援 '.' 和 '*'。
2. 採用動態規劃 (DP)：
   - 定義 dp[i][j] 表示 s[i:] 與 p[j:] 是否能匹配。
   - 狀態轉移：
     a. 若 p[j+1] == '*'：
        - 跳過「x*」：dp[i][j] = dp[i][j+2]
        - 或當前匹配成功，繼續比對：dp[i][j] = firstMatch && dp[i+1][j]
     b. 否則：dp[i][j] = firstMatch && dp[i+1][j+1]
   - firstMatch 表示 s[i] 與 p[j] 是否能匹配 (相等或 p[j]=='.')。
3. 初始化：dp[m][n] = true，代表空字串與空模式匹配。
4. 最終答案為 dp[0][0]。
5. 時間複雜度 O(m×n)，空間複雜度 O(m×n)。
*/
