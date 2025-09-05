// 題目 14 班表 k 組反轉
// 檔名：LC25_ReverseKGroup_Shifts.java
//
// 輸入：
// k
// 整數序列（以空白分隔）
//
// 輸出：
// 每 k 個節點反轉後的序列（不足 k 的尾段保持原樣）
//
// 範例：
// Input :
// 2
// 1 2 3 4 5
// Output:
// 2 1 4 3 5

import java.io.*;

public class LC25_ReverseKGroup_Shifts {

    // 鏈結串列定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { this.val = v; }
    }

    // 主解法：反轉每 k 個節點
    static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, end = dummy;

        while (true) {
            // 嘗試找到一組 k 個節點
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;

            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;  // 暫時斷開

            // 反轉這一組
            pre.next = reverse(start);
            start.next = next;

            // 更新 pre 與 end
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    // 反轉鏈結串列
    private static ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }
        return prev;
    }

    // 建立鏈結串列
    static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int x : arr) {
            cur.next = new ListNode(x);
            cur = cur.next;
        }
        return dummy.next;
    }

    // 輸出鏈結串列
    static void printList(ListNode head, PrintWriter out) {
        boolean first = true;
        while (head != null) {
            if (!first) out.print(" ");
            out.print(head.val);
            first = false;
            head = head.next;
        }
        out.println();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));

        // 讀 k
        String line1 = br.readLine();
        if (line1 == null || line1.trim().isEmpty()) {
            out.println();
            out.flush();
            return;
        }
        int k = Integer.parseInt(line1.trim());

        // 讀序列
        String line2 = br.readLine();
        if (line2 == null || line2.trim().isEmpty()) {
            out.println();
            out.flush();
            return;
        }
        String[] parts = line2.trim().split("\\s+");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) arr[i] = Integer.parseInt(parts[i]);

        ListNode head = buildList(arr);
        ListNode res = reverseKGroup(head, k);
        printList(res, out);

        out.flush();
    }
}
