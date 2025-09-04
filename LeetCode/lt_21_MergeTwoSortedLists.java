// 題目：Merge Two Sorted Lists
// 給定兩個已經排序好的鏈結串列 list1 和 list2，
// 請將它們合併成一個新的排序鏈結串列，並回傳結果。

public class lt_21_MergeTwoSortedLists {
    // 定義鏈結串列節點類別
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 主方法：合併兩個排序好的鏈結串列
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 建立虛擬頭節點 dummy，避免處理首節點的特殊情況
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy; // cur 指向新鏈表的尾端

        // 當 list1 和 list2 都還沒走完時，逐一比較
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;   // list1 當前節點較小，接到新鏈表
                list1 = list1.next; // 移動 list1 指標
            } else {
                cur.next = list2;   // list2 當前節點較小，接到新鏈表
                list2 = list2.next; // 移動 list2 指標
            }
            cur = cur.next; // 移動新鏈表的尾端
        }

        // 接上剩下的節點
        cur.next = (list1 != null) ? list1 : list2;

        return dummy.next; // 回傳合併後的鏈表（跳過 dummy）
    }

    // 輔助方法：將鏈結串列轉成輸出格式 [x,y,z]
    public static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(",");
            head = head.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // 測試主程式
    public static void main(String[] args) {
        // Example 1
        ListNode list1_1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2_1 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode merged1 = mergeTwoLists(list1_1, list2_1);
        System.out.println("Example 1:");
        System.out.println("Input: list1 = [1,2,4], list2 = [1,3,4]");
        System.out.println("Output: " + listToString(merged1));

        // Example 2
        ListNode list1_2 = null;
        ListNode list2_2 = null;
        ListNode merged2 = mergeTwoLists(list1_2, list2_2);
        System.out.println("Example 2:");
        System.out.println("Input: list1 = [], list2 = []");
        System.out.println("Output: " + listToString(merged2));

        // Example 3
        ListNode list1_3 = null;
        ListNode list2_3 = new ListNode(0);
        ListNode merged3 = mergeTwoLists(list1_3, list2_3);
        System.out.println("Example 3:");
        System.out.println("Input: list1 = [], list2 = [0]");
        System.out.println("Output: " + listToString(merged3));
    }
}

/*
解題思路：
1. 使用虛擬頭節點 dummy，簡化處理鏈表首節點的邏輯。
2. 使用 cur 指標追蹤新鏈表的尾端，逐步比較 list1 與 list2 當前節點。
3. 每次取較小的節點接到新鏈表，並移動對應的指標。
4. 當其中一個鏈表走完，直接接上另一個鏈表剩餘的部分。
5. 回傳 dummy.next 即為合併後的完整鏈表。
6. 時間複雜度：O(m+n)，其中 m 和 n 為 list1、list2 的長度。
7. 空間複雜度：O(1)，因為除了輸出鏈表外，僅使用了常數額外空間。
*/
