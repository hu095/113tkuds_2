import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class LC19_RemoveNth_Node_Clinic {

    /* 單向鏈結節點 */
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
        ListNode(int v, ListNode n) { val = v; next = n; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        Integer nObj = fs.nextIntOrNull();
        if (nObj == null) return; // 無輸入
        int n = nObj;

        // 建立鏈結串列
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(fs.nextInt());
            cur = cur.next;
        }

        int k = fs.nextInt(); // 倒數第 k 筆要刪除（1 <= k <= n）

        ListNode head = removeNthFromEnd(dummy.next, k);

        // 輸出刪除後序列（空白分隔；若為空則印空行）
        StringBuilder sb = new StringBuilder();
        for (ListNode p = head; p != null; p = p.next) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(p.val);
        }
        System.out.println(sb.toString());
    }

    /** 一趟掃描刪除倒數第 k 個節點：fast 先走 k 步，之後 fast/slow 同步 */
    private static ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy, slow = dummy;

        // fast 先走 k 步（指向待刪節點的下一個位置差距）
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        // fast 再一起走到尾端
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // slow 的下一個就是要刪除的節點
        slow.next = slow.next.next;
        return dummy.next;
    }

    /* 輕量高速輸入 */
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
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}
