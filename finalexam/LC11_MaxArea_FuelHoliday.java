import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/**
 * 題目 6 連假油量促銷最大區間
 * 檔名：LC11_MaxArea_FuelHoliday.java
 *
 * 輸入：
 *   第一行：n
 *   第二行：n 個整數高度
 *
 * 輸出：
 *   最大面積（long）
 *
 * 解法：雙指針夾逼；每步以較短邊為高，計算面積並移動較短邊
 * 複雜度：時間 O(n)，空間 O(1)
 */
public class LC11_MaxArea_FuelHoliday {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) h[i] = fs.nextInt();

        System.out.println(maxArea(h));
    }

    /** 兩指針：唯有提升短邊才可能變大 → 移動較短邊 */
    private static long maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        long ans = 0L;

        while (i < j) {
            long hi = height[i];
            long hj = height[j];
            long h = Math.min(hi, hj);
            ans = Math.max(ans, h * (j - i));

            if (hi < hj) {
                i++;
            } else {
                j--;
            }
        }
        return ans;
    }

    /** 輕量高速讀取 */
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
        int nextInt() throws IOException {
            String s = next();
            return (s == null) ? 0 : Integer.parseInt(s);
        }
    }
}
