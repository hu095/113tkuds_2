// 題目：LeetCode 30. Substring with Concatenation of All Words
// 說明：找出 s 中所有起點，使得從該位置開始、長度為 words.length * wordLen 的子字串
//       可以分割成等長單字，且剛好由 words 的某個排列組成（每字使用次數相同）。


import java.util.*;

public class lt_30_SubstringWithAllWords {

    static class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> ans = new ArrayList<>();
            if (s == null || words == null || words.length == 0) return ans;

            int n = s.length();
            int m = words.length;
            int w = words[0].length();
            int total = m * w;
            if (n < total) return ans;

            // 1) 建立目標詞頻表
            Map<String, Integer> need = new HashMap<>();
            for (String t : words) {
                need.put(t, need.getOrDefault(t, 0) + 1);
            }

            // 2) 對每個偏移量跑一次「長度為 w 的滑動視窗」
            for (int offset = 0; offset < w; offset++) {
                int left = offset;                // 視窗左端（以 w 為單位前進）
                int count = 0;                    // 視窗內已匹配單字數（最多 m）
                Map<String, Integer> window = new HashMap<>();

                // 右端每次吃一個長度為 w 的單字
                for (int right = offset; right + w <= n; right += w) {
                    String word = s.substring(right, right + w);

                    if (need.containsKey(word)) {
                        // 放進視窗，並維持不超頻
                        window.put(word, window.getOrDefault(word, 0) + 1);
                        count++;

                        // 若某字超出需求次數，縮小左端直到符合
                        while (window.get(word) > need.get(word)) {
                            String leftWord = s.substring(left, left + w);
                            window.put(leftWord, window.get(leftWord) - 1);
                            left += w;
                            count--;
                        }

                        // 當視窗剛好包含 m 個單字，即是一個解
                        if (count == m) {
                            ans.add(left);

                            // 繼續滑動以找重疊解：移出最左單字
                            String leftWord = s.substring(left, left + w);
                            window.put(leftWord, window.get(leftWord) - 1);
                            left += w;
                            count--;
                        }
                    } else {
                        // 遇到不在 words 的單字 → 清空視窗
                        window.clear();
                        count = 0;
                        left = right + w;
                    }
                }
            }
            return ans;
        }
    }

    // 簡單測試
    public static void main(String[] args) {
        Solution sol = new Solution();

        String s1 = "barfoothefoobarman";
        String[] w1 = {"foo","bar"};
        System.out.println(sol.findSubstring(s1, w1)); // [0, 9]

        String s2 = "wordgoodgoodgoodbestword";
        String[] w2 = {"word","good","best","word"};
        System.out.println(sol.findSubstring(s2, w2)); // []

        String s3 = "barfoofoobarthefoobarman";
        String[] w3 = {"bar","foo","the"};
        System.out.println(sol.findSubstring(s3, w3)); // [6, 9, 12]
    }
}

/*
解題思路：
1. 設每個單字長度為 w、單字數 m，總長度 total = m * w；先建立 words 的詞頻表 need。
2. 因為每次切割都以 w 為單位，對 offset = 0..w-1 逐一跑「長度為 w 的滑動視窗」。
3. 視窗右端 right 每次向右吃一個長度為 w 的字串 word：
   - 若 word 不在 need：清空視窗（window、count），left = right + w。
   - 若在 need：window[word]++、count++，若某字超頻（window[word] > need[word]），
     便從左端 left 以 w 步進移出單字直到不超頻。
4. 當 count == m，表示視窗剛好包含 m 個單字（順序可任意），記錄當前 left 為一個起點。
   接著為了找重疊解，移除最左單字（left 前進 w、count--），持續滑動。
5. 如此每個 offset 只會線性掃過一次，總體時間為 O(w * n/w) = O(n)（常數因子與 map 操作）。
6. 時間複雜度 O(n)，空間複雜度 O(k)（k 為不同單字種類數，用於詞頻表）。
*/
