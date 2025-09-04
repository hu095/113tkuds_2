// 題目：Remove Nth Node From End of List
// 給定一個鏈結串列 head，刪除倒數第 n 個節點，並回傳新的 head。

public class lt_19_RemoveNthNodeFromEnd {
    public static void main(String[] args) {
        // 測試案例 1
        ListNode head1 = createLinkedList(new int[]{1,2,3,4,5});
        int n1 = 2;
        head1 = removeNthFromEnd(head1, n1);
        System.out.print("Example 1 Output: ");
        printLinkedList(head1); // 預期 [1,2,3,5]

        // 測試案例 2
        ListNode head2 = createLinkedList(new int[]{1});
        int n2 = 1;
        head2 = removeNthFromEnd(head2, n2);
        System.out.print("Example 2 Output: ");
        printLinkedList(head2); // 預期 []

        // 測試案例 3
        ListNode head3 = createLinkedList(new int[]{1,2});
        int n3 = 1;
        head3 = removeNthFromEnd(head3, n3);
        System.out.print("Example 3 Output: ");
        printLinkedList(head3); // 預期 [1]
    }

    // 核心函數：刪除倒數第 n 個節點
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); // 虛擬頭節點
        ListNode fast = dummy;
        ListNode slow = dummy;

        // 先讓 fast 走 n+1 步，保持快慢指針距離
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // 同步移動 fast 和 slow
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow 在待刪節點的前一個位置
        slow.next = slow.next.next;

        return dummy.next; // 回傳新的頭節點
    }

    // 工具函數：建立鏈表
    public static ListNode createLinkedList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int num : arr) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return dummy.next;
    }

    // 工具函數：輸出鏈表
    public static void printLinkedList(ListNode head) {
        ListNode cur = head;
        System.out.print("[");
        while (cur != null) {
            System.out.print(cur.val);
            cur = cur.next;
            if (cur != null) System.out.print(",");
        }
        System.out.println("]");
    }
}

// 鏈表節點定義
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/*
解題思路：
1. 使用一個虛擬節點 dummy 指向 head，方便處理刪除頭節點的情況。
2. 設置快慢指針 fast、slow，初始都在 dummy。
3. 先讓 fast 前進 n+1 步，保持 fast 與 slow 之間的距離為 n。
4. 同時移動 fast 與 slow，直到 fast 到達鏈表末尾。
5. 此時 slow 停在待刪節點的前一個節點，直接修改 slow.next = slow.next.next 即可刪除。
6. 最後回傳 dummy.next 作為新的鏈表頭。
7. 時間複雜度 O(L)，其中 L 是鏈表長度；空間複雜度 O(1)。
*/
