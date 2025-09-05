// 題目 12 多院區即時叫號合併
// 檔名：LC23_MergeKLists_Hospitals.java
//
// 讀取格式：
// k
// <第1行升序序列，以 -1 結尾>
// <第2行升序序列，以 -1 結尾>
// ...
// <第k行升序序列，以 -1 結尾>
//
// 輸出格式：
// 合併後的升序序列（以空白分隔；若為空則輸出空行）
//
// 範例：
// 輸入：
// 3
// 1 4 5 -1
// 1 3 4 -1
// 2 6 -1
// 輸出：
// 1 1 2 3 4 4 5 6

import java.io.*;
import java.util.*;

public class LC23_MergeKLists_Hospitals {

    // 單向鏈結串列節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int v) { this.val = v; }
        ListNode(int v, ListNode n) { this.val = v; this.next = n; }
    }

    // 以最小堆合併 k 條已排序鍊
    static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        // 將每條鏈的頭節點壓入堆
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // 反覆彈出最小值，並將其 next 放回堆
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            tail.next = cur;
            tail = tail.next;
            if (cur.next != null) pq.offer(cur.next);
        }

        return dummy.next;
    }

    // 將一行（以 -1 結尾）建成鏈結串列；可能為空串列（行中第一個就是 -1）
    static ListNode readOneList(String line) {
        if (line == null) return null;
        String[] parts = line.trim().split("\\s+");
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (String p : parts) {
            if (p.isEmpty()) continue;
            int x = Integer.parseInt(p);
            if (x == -1) break;
            tail.next = new ListNode(x);
            tail = tail.next;
        }
        return dummy.next; // 若全是 -1，回傳 null
    }

    // 將鏈結串列輸出為「空白分隔」
    static void printList(ListNode head, PrintWriter out) {
        boolean first = true;
        while (head != null) {
            if (!first) out.print(" ");
            out.print(head.val);
            first = false;
            head = head.next;
        }
        out.println(); // 空串列會直接印空行
    }

    public static void main(String[] args) throws Exception {
        // 使用 BufferedReader 讀取、PrintWriter 輸出（效能佳、跨平台）
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));

        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) {
            // k 缺失 → 視為 0
            out.println();
            out.flush();
            return;
        }

        int k = Integer.parseInt(first.trim());
        ListNode[] lists = new ListNode[k];

        for (int i = 0; i < k; i++) {
            String line = br.readLine();
            // 若行數不足，視為空串列
            lists[i] = readOneList(line == null ? "-1" : line);
        }

        ListNode merged = mergeKLists(lists);
        printList(merged, out);

        out.flush();
    }
}
