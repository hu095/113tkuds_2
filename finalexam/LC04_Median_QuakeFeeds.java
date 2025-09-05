import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/**
 * 題目 3 地震速報雙資料源中位數
 * 檔名：LC04_Median_QuakeFeeds.java
 *
 * 讀入：
 *   第一行：n m
 *   第二行：n 個浮點數（已排序，非遞減）
 *   第三行：m 個浮點數（已排序，非遞減）
 *
 * 輸出：
 *   聯合集的中位數（保留 1 位小數）
 *
 * 解題觀念：
 *   - 總左半元素數量 = (n + m + 1) / 2
 *   - 在較短陣列 A 上二分切割 i，另一陣列 B 對應切割 j = totalLeft - i
 *   - 驗證 L1 <= R2 且 L2 <= R1 成立即為正確切分
 *   - 偶數長度時取 (max(L1,L2) + min(R1,R2)) / 2
 *   - 邊界以 ±INF 處理 i=0/i=m 或 j=0/j=n
 *
 * 複雜度：時間 O(log(min(n,m)))；空間 O(1)
 */
public class LC04_Median_QuakeFeeds {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int m = fs.nextInt();

        double[] A = new double[n];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) A[i] = fs.nextDouble();
        for (int i = 0; i < m; i++) B[i] = fs.nextDouble();

        double median = findMedianSortedArrays(A, B);

        // 題目要求保留 1 位
        System.out.println(String.format("%.1f", median));
    }

    /** 在較短陣列上做二分切割的中位數解法（double 版本） */
    private static double findMedianSortedArrays(double[] nums1, double[] nums2) {
        // 確保 nums1 較短，方便二分
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int m = nums1.length, n = nums2.length;
        int totalLeft = (m + n + 1) / 2;

        int lo = 0, hi = m;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1;   // 在 nums1 切左邊 i 個
            int j = totalLeft - i;     // 在 nums2 切左邊 j 個

            double L1 = (i == 0) ? Double.NEGATIVE_INFINITY : nums1[i - 1];
            double R1 = (i == m) ? Double.POSITIVE_INFINITY : nums1[i];
            double L2 = (j == 0) ? Double.NEGATIVE_INFINITY : nums2[j - 1];
            double R2 = (j == n) ? Double.POSITIVE_INFINITY : nums2[j];

            if (L1 <= R2 && L2 <= R1) {
                if (((m + n) & 1) == 1) {
                    // 總長度為奇數：左半最大
                    return Math.max(L1, L2);
                } else {
                    // 總長度為偶數：左半最大與右半最小的平均
                    return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
                }
            } else if (L1 > R2) {
                // i 太大，往左
                hi = i - 1;
            } else {
                // i 太小，往右
                lo = i + 1;
            }
        }
        // 理論上不會到這裡
        throw new IllegalStateException("Invalid state for median calculation");
    }

    /** 輕量高速讀取（支援 int/double） */
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

        double nextDouble() throws IOException {
            String s = next();
            return (s == null) ? 0.0 : Double.parseDouble(s);
        }
    }
}
