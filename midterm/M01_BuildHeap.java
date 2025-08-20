
import java.io.*;
import java.util.*;

public class M01_BuildHeap {

    // 簡易高速輸入
    static class FastScanner {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        String nextLine() throws IOException {
            return br.readLine();
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String type = fs.next();              // "max" 或 "min"
        int n = Integer.parseInt(fs.next());  // 元素數
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(fs.next());
        }

        boolean isMax = type.equalsIgnoreCase("max");
        buildHeap(a, isMax);

        // 輸出 0-based 陣列表示
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                out.print(" ");
            }
            out.print(a[i]);
        }
        out.println();
        out.flush();
    }

    // Bottom-up Build-Heap：從最後一個非葉節點往上做 heapifyDown
    static void buildHeap(long[] a, boolean isMax) {
        int n = a.length;
        for (int i = (n >>> 1) - 1; i >= 0; i--) {
            heapifyDown(a, i, n, isMax);
        }
    }

    static void heapifyDown(long[] a, int i, int n, boolean isMax) {
        while (true) {
            int left = (i << 1) + 1;
            if (left >= n) {
                break;
            }
            int right = left + 1;

            // 選擇「較優」的子（max 取較大、min 取較小）；相等時偏左以避免不必要的交換
            int best = left;
            if (right < n && better(a[right], a[left], isMax)) {
                best = right;
            }

            if (better(a[best], a[i], isMax)) {
                swap(a, i, best);
                i = best;
            } else {
                break;
            }
        }
    }

    static boolean better(long x, long y, boolean isMax) {
        return isMax ? (x > y) : (x < y);
    }

    static void swap(long[] a, int i, int j) {
        long t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * • Build-Heap 採 Bottom-up，對每個非葉節點做一次 heapifyDown；節點在高度 h 的攤銷成本為 O(h)。
 * • 各高度節點數量呈倍增遞減，總成本為 Σ_{h=0}^{⌊log n⌋} (節點數·h) ≤ 2n = O(n)。
 * • 僅使用就地交換與比較，空間為 O(1)。
 */
