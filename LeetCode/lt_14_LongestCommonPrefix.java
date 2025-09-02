public class lt_14_LongestCommonPrefix {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        String[] strs1 = {"flower", "flow", "flight"};
        String out1 = sol.longestCommonPrefix(strs1);
        System.out.println("Example 1:");
        System.out.println();
        System.out.println("Input: strs = [\"flower\",\"flow\",\"flight\"]");
        System.out.println("Output: \"" + out1 + "\""); // 預期 "fl"
        System.out.println();

        // Example 2
        String[] strs2 = {"dog", "racecar", "car"};
        String out2 = sol.longestCommonPrefix(strs2);
        System.out.println("Example 2:");
        System.out.println();
        System.out.println("Input: strs = [\"dog\",\"racecar\",\"car\"]");
        System.out.println("Output: \"" + out2 + "\""); // 預期 ""
    }
}

// 題目：Longest Common Prefix
// 給定一個字串陣列，找出最長的共同前綴字串，若不存在則回傳空字串。
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // 先假設第一個字串是共同前綴
        String prefix = strs[0];

        // 依序和後面的字串比較，逐步縮短 prefix
        for (int i = 1; i < strs.length; i++) {
            // 當前字串和 prefix 不斷比較，直到符合開頭
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return ""; // 沒有共同前綴
            }
        }
        return prefix;
    }
}

/*
解題思路：
1. 假設第一個字串是共同前綴 (prefix)。
2. 從第二個字串開始，逐一檢查是否以 prefix 開頭：
   - 若不是，逐步縮短 prefix（去掉最後一個字元），直到符合或變成空字串。
3. 若縮到空字串，代表沒有共同前綴，回傳 ""。
4. 時間複雜度：O(n * m)，其中 n 為字串數量，m 為最長字串長度。
   空間複雜度：O(1)。
*/
