// 題目 13 班表兩兩交換
// 檔名：LC24_SwapPairs_Shifts.java
//
// 輸入：一行整數序列，以空白分隔
// 輸出：兩兩交換後的序列（以空白分隔）
//
// 範例：
// Input : 1 2 3 4
// Output: 2 1 4 3

import java.io.*;

public class LC24_SwapPairs_Shifts {

    // 鏈結串列定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { this.val = v; }
    }

    // 主解法：交換相鄰節點
    static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // 交換指標
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // 移動 prev
            prev = first;
        }

        return dummy.next;
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

        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            out.println(); // 空輸入 → 空輸出
            out.flush();
            return;
        }

        String[] parts = line.trim().split("\\s+");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }

        ListNode head = buildList(arr);
        ListNode swapped = swapPairs(head);
        printList(swapped, out);

        out.flush();
    }
}
