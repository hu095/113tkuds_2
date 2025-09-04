// 檔名：lt_24_SwapNodesInPairs.java

// 定義單向鏈結串列
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class lt_24_SwapNodesInPairs {

    // 主解法：交換鏈結串列中的相鄰節點
    public ListNode swapPairs(ListNode head) {
        // 使用虛擬頭節點 dummy，方便處理鏈表頭部交換
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // prev 指向即將交換的 pair 的前一個節點
        ListNode prev = dummy;

        // 只要還有兩個節點可以交換就進入迴圈
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;       // 第一個節點
            ListNode second = prev.next.next; // 第二個節點

            // 交換步驟：
            // 1. 先把 first 指向 second 後面的節點
            first.next = second.next;
            // 2. 再讓 second 指向 first
            second.next = first;
            // 3. prev 連到 second，完成交換
            prev.next = second;

            // prev 移動到第一個節點，準備處理下一組 pair
            prev = first;
        }

        return dummy.next;
    }

    // 工具函式：把鏈結串列轉成字串 [a,b,c] 方便輸出
    private static String listToString(ListNode head) {
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

    // 測試用 main：輸出符合題目格式
    public static void main(String[] args) {
        lt_24_SwapNodesInPairs solution = new lt_24_SwapNodesInPairs();

        // Example 1
        ListNode ex1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println("Example 1:\n");
        System.out.println("Input: head = [1,2,3,4]");
        System.out.println("Output: " + listToString(solution.swapPairs(ex1)));
        System.out.println("Explanation:\n\n");

        // Example 2
        ListNode ex2 = null;
        System.out.println("Example 2:\n");
        System.out.println("Input: head = []");
        System.out.println("Output: " + listToString(solution.swapPairs(ex2)));
        System.out.println();

        // Example 3
        ListNode ex3 = new ListNode(1);
        System.out.println("Example 3:\n");
        System.out.println("Input: head = [1]");
        System.out.println("Output: " + listToString(solution.swapPairs(ex3)));
        System.out.println();

        // Example 4
        ListNode ex4 = new ListNode(1, new ListNode(2, new ListNode(3)));
        System.out.println("Example 4:\n");
        System.out.println("Input: head = [1,2,3]");
        System.out.println("Output: " + listToString(solution.swapPairs(ex4)));
    }
}

/*
解題思路：

題目要求：交換鏈結串列中每兩個相鄰的節點，回傳新的頭節點。
限制：不能修改節點的值，只能改變指標指向。

方法：
1. 使用 dummy 虛擬頭節點，避免頭節點交換時需要額外判斷。
2. 用 prev 指標追蹤待交換 pair 的前一個節點。
3. 每次取出兩個節點 first 和 second，調整指標順序：
   - first.next = second.next
   - second.next = first
   - prev.next = second
4. 把 prev 移動到 first，繼續處理下一對節點。
5. 直到節點不足兩個，迴圈結束。

時間複雜度：O(n)，每個節點僅被訪問一次。
空間複雜度：O(1)，只使用常數額外空間。
*/
