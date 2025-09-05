
/**
 * 題目：LeetCode 38. Count and Say
 *
 * 說明：
 *   countAndSay(1) = "1"
 *   countAndSay(n) = 對 countAndSay(n-1) 做 run-length encoding (RLE)
 *   → 對連續相同的數字進行壓縮 = 「出現次數 + 該數字」
 *
 * 範例：
 *   n=1 → "1"
 *   n=2 → "11"   (一個 1)
 *   n=3 → "21"   (兩個 1)
 *   n=4 → "1211" (一個 2、一個 1)
 *
 */
public class lt_38_CountAndSay {

    // ===== LeetCode 可提交版本 =====
    static class Solution {

        public String countAndSay(int n) {
            if (n == 1) return "1"; // 基本情況

            String result = "1"; // 初始值
            for (int i = 2; i <= n; i++) {
                result = buildNext(result);
            }
            return result;
        }

        private String buildNext(String s) {
            StringBuilder sb = new StringBuilder();
            int count = 1;

            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == s.charAt(i - 1)) {
                    count++;
                } else {
                    sb.append(count).append(s.charAt(i - 1));
                    count = 1;
                }
            }
            sb.append(count).append(s.charAt(s.length() - 1));
            return sb.toString();
        }
    }

    // ===== 測試用 main (VS Code 執行) =====
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int n1 = 4;
        String out1 = sol.countAndSay(n1);
        System.out.println("Example 1:\n");
        System.out.println("Input: n = " + n1);
        System.out.println("Output: \"" + out1 + "\"");
        System.out.println("Explanation:");
        System.out.println("countAndSay(1) = \"1\"");
        System.out.println("countAndSay(2) = RLE of \"1\" = \"11\"");
        System.out.println("countAndSay(3) = RLE of \"11\" = \"21\"");
        System.out.println("countAndSay(4) = RLE of \"21\" = \"1211\"\n");

        // Example 2
        int n2 = 1;
        String out2 = sol.countAndSay(n2);
        System.out.println("Example 2:\n");
        System.out.println("Input: n = " + n2);
        System.out.println("Output: \"" + out2 + "\"");
        System.out.println("Explanation:");
        System.out.println("This is the base case.");
    }
}

/**
 * 解題思路：
 *   1. 從基底字串 "1" 開始
 *   2. 每次對字串進行「連續字元分組統計」
 *   3. 對每組字元輸出「次數 + 字元」，組合成新字串
 *   4. 重複此過程直到第 n 項
 *
 * 時間複雜度：
 *   - 每一層需要遍歷一次字串 O(m)，m 為字串長度
 *   - 因 n ≤ 30，字串長度雖增長，但在題目限制下能接受
 */
