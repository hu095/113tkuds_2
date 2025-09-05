import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class LC21_MergeTwoLists_Clinics {

    // 定義鏈結串列節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
        ListNode(int v, ListNode n) { val = v; next = n; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int m = fs.nextInt();

        ListNode list1 = buildList(fs, n);
        ListNode list2 = buildList(fs, m);

        ListNode merged = mergeTwoLists(list1, list2);

        // 輸出合併後序列，空白分隔
        StringBuilder sb = new StringBuilder();
        for (ListNode p = merged; p != null; p = p.next) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(p.val);
        }
        System.out.println(sb.toString());
    }

    /** 建立鏈結串列 */
    private static ListNode buildList(FastScanner fs, int n) throws IOException {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(fs.nextInt());
            cur = cur.next;
        }
        return dummy.next;
    }

    /** 合併兩個已排序鏈表 */
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    /** 輕量輸入工具 */
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
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}
