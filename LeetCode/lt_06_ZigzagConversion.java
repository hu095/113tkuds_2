// LeetCode #6: Zigzag Conversion
// 走訪每個字元，往下到最底再往上折返，分別累積到各行的 StringBuilder。
// 時間 O(n)、空間 O(n)。

public class lt_06_ZigzagConversion {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 範例 1
        String s1 = "PAYPALISHIRING";
        int rows1 = 3;
        System.out.println("Example 1 -> " + sol.convert(s1, rows1));
        // 預期：PAHNAPLSIIGYIR

        // 範例 2
        String s2 = "PAYPALISHIRING";
        int rows2 = 4;
        System.out.println("Example 2 -> " + sol.convert(s2, rows2));
        // 預期：PINALSIGYAHRPI

        // 範例 3
        String s3 = "A";
        int rows3 = 1;
        System.out.println("Example 3 -> " + sol.convert(s3, rows3));
        // 預期：A
    }
}

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

        int r = 0;   // 目前所在列
        int dir = 1; // 方向：1 往下、-1 往上
        for (int i = 0; i < s.length(); i++) {
            rows[r].append(s.charAt(i));
            if (r == 0) dir = 1;                  // 觸頂，往下
            else if (r == numRows - 1) dir = -1;  // 觸底，往上
            r += dir;
        }

        StringBuilder ans = new StringBuilder();
        for (StringBuilder sb : rows) ans.append(sb);
        return ans.toString();
    }
}
