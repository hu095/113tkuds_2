// LeetCode 25. Reverse Nodes in k-Group
// 題目：給定一個鏈表，每 k 個一組進行反轉。如果最後不足 k 個，則保持不變。

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class lt_25_ReverseNodesInKGroup {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        // 使用 dummy 節點簡化操作，dummy.next 指向頭節點
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // pre: 每一組的前一個節點
        // end: 每一組的最後一個節點
        ListNode pre = dummy, end = dummy;

        while (true) {
            // 嘗試找到一組 k 個節點
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break; // 節點不足 k 個，退出迴圈

            // start: 當前組的第一個節點
            ListNode start = pre.next;
            // next: 下一組的第一個節點
            ListNode next = end.next;

            end.next = null; // 斷開這組，方便反轉

            // 反轉當前組，並接回 pre 之後
            pre.next = reverse(start);
            start.next = next;

            // 更新 pre 和 end，準備處理下一組
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    // 鏈表反轉函式
    private static ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // 輔助方法：將陣列轉換為鏈表
    private static ListNode arrayToList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int num : arr) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }
        return dummy.next;
    }

    // 輔助方法：輸出鏈表
    private static void printList(ListNode head) {
        System.out.print("[");
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) System.out.print(",");
        }
        System.out.println("]");
    }

    // 測試程式
    public static void main(String[] args) {
        // Example 1
        int[] arr1 = {1,2,3,4,5};
        ListNode head1 = arrayToList(arr1);
        ListNode res1 = reverseKGroup(head1, 2);
        System.out.println("Example 1:");
        System.out.println("Input: head = [1,2,3,4,5], k = 2");
        System.out.print("Output: ");
        printList(res1);
        System.out.println();

        // Example 2
        int[] arr2 = {1,2,3,4,5};
        ListNode head2 = arrayToList(arr2);
        ListNode res2 = reverseKGroup(head2, 3);
        System.out.println("Example 2:");
        System.out.println("Input: head = [1,2,3,4,5], k = 3");
        System.out.print("Output: ");
        printList(res2);
        System.out.println();
    }
}

/*
解題思路：
1. 使用 dummy 節點處理頭節點，避免邊界問題。
2. 每次找到一組 k 個節點：
   - 若不足 k 個，直接結束。
   - 否則斷開這組，進行反轉。
3. 反轉後將這組鏈表接回，更新 pre 和 end。
4. 重複直到整個鏈表處理完。
5. 時間複雜度 O(n)，因為每個節點最多被訪問兩次。
6. 空間複雜度 O(1)，僅使用少數指標。
*/
