import java.io.*;
import java.util.*;

public class M01_BuildHeap {
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        FastScanner(InputStream is) { br = new BufferedReader(new InputStreamReader(is)); }
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
        long nextLong() throws IOException { return Long.parseLong(next()); }
        String nextLine() throws IOException { return br.readLine(); }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);   // ✅ 已可用
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String type = fs.next();              // "max" 或 "min"
        int n = Integer.parseInt(fs.next());  // 元素數
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = Long.parseLong(fs.next());

        boolean isMax = type.equalsIgnoreCase("max");
        buildHeap(a, isMax);

        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(" ");
            out.print(a[i]);
        }
        out.println();
        out.flush();
    }

    static void buildHeap(long[] a, boolean isMax) {
        int n = a.length;
        for (int i = (n >>> 1) - 1; i >= 0; i--) heapifyDown(a, i, n, isMax);
    }

    static void heapifyDown(long[] a, int i, int n, boolean isMax) {
        while (true) {
            int left = (i << 1) + 1;
            if (left >= n) break;
            int right = left + 1;
            int best = left;
            if (right < n && better(a[right], a[left], isMax)) best = right;
            if (better(a[best], a[i], isMax)) {
                long t = a[i]; a[i] = a[best]; a[best] = t;
                i = best;
            } else break;
        }
    }

    static boolean better(long x, long y, boolean isMax) { return isMax ? x > y : x < y; }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * • Bottom-up Build-Heap：從最後非葉節點開始 heapifyDown，節點位於高度 h 的成本 O(h)。
 * • 各高度節點數量呈倍增遞減，Σ(節點數·h) ≤ 2n，因此總時間 O(n)。
 * • 原地重排，額外空間 O(1)。
 */
