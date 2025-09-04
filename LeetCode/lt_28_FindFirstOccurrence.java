// 題目：LeetCode 28. Find the Index of the First Occurrence in a String
// 說明：給定兩字串 haystack 與 needle，回傳 needle 在 haystack 中第一次出現的索引。
// 若未出現，回傳 -1。


public class lt_28_FindFirstOccurrence {

    static class Solution {
        public int strStr(String haystack, String needle) {
            int n = haystack.length();
            int m = needle.length();

            // 從 haystack 的每個可能起點開始比對
            for (int i = 0; i <= n - m; i++) {
                // 截取長度等於 needle 的子字串，並比較
                if (haystack.substring(i, i + m).equals(needle)) {
                    return i; // 找到後立即回傳索引
                }
            }
            return -1; // 若沒找到，回傳 -1
        }
    }

    // 簡單測試
    public static void main(String[] args) {
        Solution sol = new Solution();

        String h1 = "sadbutsad";
        String n1 = "sad";
        System.out.println("Output: " + sol.strStr(h1, n1)); // 預期 0

        String h2 = "leetcode";
        String n2 = "leeto";
        System.out.println("Output: " + sol.strStr(h2, n2)); // 預期 -1
    }
}

/*
解題思路：
1. 設 haystack 長度為 n，needle 長度為 m。
2. 只需要檢查 haystack 中前 (n - m) 個起始位置，避免溢出。
3. 從 i = 0 開始，依序取 substring(i, i+m) 與 needle 比對。
4. 若相等，立即回傳當前 i，表示 needle 第一次出現的索引。
5. 若整個迴圈結束仍未找到，回傳 -1。
6. 時間複雜度 O((n-m+1) * m)，最壞情況需比對 n*m 字元。
7. 空間複雜度 O(1)，僅使用常數額外變數。
*/
