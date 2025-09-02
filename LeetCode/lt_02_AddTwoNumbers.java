
public class lt_02_AddTwoNumbers {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: l1 = [2,4,3], l2 = [5,6,4]
        ListNode l1 = createList(new int[]{2,4,3});
        ListNode l2 = createList(new int[]{5,6,4});
        ListNode result1 = sol.addTwoNumbers(l1, l2);
        System.out.print("Example 1 Output: ");
        printList(result1); // 預期 [7,0,8]

        // Example 2: l1 = [0], l2 = [0]
        ListNode l3 = createList(new int[]{0});
        ListNode l4 = createList(new int[]{0});
        ListNode result2 = sol.addTwoNumbers(l3, l4);
        System.out.print("Example 2 Output: ");
        printList(result2); // 預期 [0]

        // Example 3: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
        ListNode l5 = createList(new int[]{9,9,9,9,9,9,9});
        ListNode l6 = createList(new int[]{9,9,9,9});
        ListNode result3 = sol.addTwoNumbers(l5, l6);
        System.out.print("Example 3 Output: ");
        printList(result3); // 預期 [8,9,9,9,0,0,0,1]
    }

    // 工具函式：建立鏈結串列
    public static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int num : arr) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummy.next;
    }

    // 工具函式：列印鏈結串列
    public static void printList(ListNode head) {
        ListNode current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(",");
            current = current.next;
        }
        System.out.println("]");
    }
}

// 鏈結串列節點
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// 解題核心
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }

        return dummy.next;
    }
}
