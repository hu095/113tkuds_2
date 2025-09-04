// lt_23_MergeKSortedLists.java

import java.util.*;

// 單向鏈結串列定義
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class lt_23_MergeKSortedLists {

    // 合併 K 條已排序的鏈結串列
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // 最小堆：每次取出當前最小的節點
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 初始化：把所有鏈表的頭節點放入堆
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        // 不斷從堆中取出最小節點，接到結果鏈表後，若有 next 則丟回堆
        while (!pq.isEmpty()) {
            ListNode min = pq.poll();
            cur.next = min;
            cur = cur.next;
            if (min.next != null) pq.offer(min.next);
        }

        return dummy.next;
    }

    // 輔助函數：將陣列轉成鏈結串列
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int num : arr) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return dummy.next;
    }

    // 輔助函數：將鏈結串列轉為字串輸出
    public static String listToString(ListNode head) {
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        return result.toString();
    }

    // 主程式：測試三個範例
    public static void main(String[] args) {
        // Example 1
        ListNode[] lists1 = {
            buildList(new int[]{1,4,5}),
            buildList(new int[]{1,3,4}),
            buildList(new int[]{2,6})
        };
        System.out.println("Example 1:");
        System.out.println("Input: lists = [[1,4,5],[1,3,4],[2,6]]");
        System.out.println("Output: " + listToString(mergeKLists(lists1)));

        // Example 2
        ListNode[] lists2 = {};
        System.out.println("\nExample 2:");
        System.out.println("Input: lists = []");
        System.out.println("Output: " + listToString(mergeKLists(lists2)));

        // Example 3
        ListNode[] lists3 = { buildList(new int[]{}) };
        System.out.println("\nExample 3:");
        System.out.println("Input: lists = [[]]");
        System.out.println("Output: " + listToString(mergeKLists(lists3)));
    }
}

/*
解題思路：
1) 問題：合併 k 條已排序的鏈結串列，輸出一條新的已排序鏈結串列。
2) 解法：使用最小堆（PriorityQueue），每次取出最小節點。
   - 初始將每條鏈的頭節點放入堆。
   - 每次從堆中取出最小節點接到結果鏈，若該節點有 next，則將 next 丟回堆。
3) 正確性：堆中始終保存候選最小節點，取出的順序必然有序。
4) 複雜度：
   - 時間：O(N log k)，其中 N 是總節點數，k 是鏈表數。
   - 空間：O(k)，堆最多存 k 個節點。
*/
