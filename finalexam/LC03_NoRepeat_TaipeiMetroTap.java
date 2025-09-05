import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 題目 2 北捷刷卡最長無重複片段
 * 檔名：LC03_NoRepeat_TaipeiMetroTap.java
 *
 * 輸入：一行字串 s（可為空）
 * 輸出：最長不含重複字元之連續子字串長度
 *
 * 解法：滑動視窗 + last 出現位置
 * 時間 O(n)；空間 O(k)，此處 k = 128（ASCII）
 */
public class LC03_NoRepeat_TaipeiMetroTap {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));

        String s = br.readLine();
        if (s == null) s = "";          // 若無輸入，視為空字串

        System.out.println(lengthOfLongestSubstring(s));
    }

    /** 典型滑動視窗：右指針擴張；若遇重複，左指針收縮到該字元上次位置 + 1 */
    private static int lengthOfLongestSubstring(String s) {
        // 題目假設 ASCII，可用固定 128 大小陣列；初始化為 -1 代表尚未出現
        int[] last = new int[128];
        Arrays.fill(last, -1);

        int left = 0, ans = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            // 若字元超出 ASCII（理論上不會），可改用 HashMap<Character,Integer>
            if (c < 128) {
                if (last[c] >= left) {
                    left = last[c] + 1; // 收縮左界到上次此字元的位置 + 1
                }
                last[c] = right;
            } else {
                // 保險：非 ASCII 情境下退回到 Map 方案（本題不會用到）
                // 也可直接 throw，但這裡不做處理
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
