import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 題目 7 高鐵站點三元組 3Sum
 * 檔名：LC15_3Sum_THSRStops.java
 *
 * 輸入：
 *   第一行：n
 *   第二行：n 個整數（可正可負）
 *
 * 輸出：
 *   多行三元組（遞增，彼此不重複），每行格式：a b c
 *   若無解則不輸出任何行
 *
 * 解法：排序 + 固定 i + 兩指針去重，時間 O(n^2)、空間 O(1)（輸出除外）
 */
public class LC15_3Sum_THSRStops {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        Integer nObj = fs.nextIntOrNull();
        if (nObj == null) return;
        int n = nObj;

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = fs.nextInt();

        List<int[]> ans = threeSum(nums);
        StringBuilder sb = new StringBuilder();
        for (int[] t : ans) {
            sb.append(t[0]).append(' ')
              .append(t[1]).append(' ')
              .append(t[2]).append('\n');
        }
        System.out.print(sb.toString());
    }

    /** 排序 + 兩指針，輸出不重複且遞增的三元組 */
    private static List<int[]> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<int[]> res = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 跳過重複起點
            if (nums[i] > 0) break;                         // 後面皆 >0 不可能和為 0

            int l = i + 1, r = n - 1;
            while (l < r) {
                long sum = (long) nums[i] + nums[l] + nums[r]; // 防止中間溢位
                if (sum == 0) {
                    res.add(new int[]{nums[i], nums[l], nums[r]});
                    int lv = nums[l], rv = nums[r];
                    while (l < r && nums[l] == lv) l++;        // 去重
                    while (l < r && nums[r] == rv) r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }

    /** 輕量讀取器 */
    private static class FastScanner {
        private final BufferedReader br;
        private StringTokenizer st;
        FastScanner(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8), 1 << 16);
        }
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        Integer nextIntOrNull() throws IOException {
            String s = next();
            return (s == null) ? null : Integer.parseInt(s);
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
