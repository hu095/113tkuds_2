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
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[m][n] = true;

        for (int i = m; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                boolean firstMatch = (i < m) &&
                        (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

                if (j + 1 < n && p.charAt(j + 1) == '*') {
                    // 跳過「x*」或吃掉一個匹配字元後仍留在 j
                    dp[i][j] = dp[i][j + 2] || (firstMatch && dp[i + 1][j]);
                } else {
                    dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}
