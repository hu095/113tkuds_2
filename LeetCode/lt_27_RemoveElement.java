// 題目：LeetCode 27. Remove Element
// 說明：移除陣列中所有等於 val 的元素（in-place），回傳不等於 val 的元素個數 k。
// 注意：元素順序可以改變；本解維持穩定（原順序）亦可通過。


public class lt_27_RemoveElement {

    static class Solution {
        public int removeElement(int[] nums, int val) {
            // i：寫入索引（已保留區間的長度）
            int i = 0;

            // j：掃描索引，逐一檢查每個元素
            for (int j = 0; j < nums.length; j++) {
                // 如果 nums[j] 不是要刪掉的 val，就把它覆寫到前面
                if (nums[j] != val) {
                    nums[i] = nums[j]; // in-place 覆寫
                    i++;               // 已保留數量 +1
                }
            }
            // 迴圈結束時，前 i 個即為保留區；i 為答案 k
            return i;
        }
    }

    // 簡單測試
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] a1 = {3, 2, 2, 3};
        int k1 = sol.removeElement(a1, 3);
        System.out.println("Output: " + k1);           // 預期 2
        System.out.print("nums: ");
        for (int i = 0; i < k1; i++) System.out.print(a1[i] + " ");
        System.out.println("\n");

        int[] a2 = {0,1,2,2,3,0,4,2};
        int k2 = sol.removeElement(a2, 2);
        System.out.println("Output: " + k2);           // 預期 5
        System.out.print("nums: ");
        for (int i = 0; i < k2; i++) System.out.print(a2[i] + " ");
        System.out.println();
    }
}

/*
解題思路：
1. 採用雙指標：j 逐一掃描元素，i 負責覆寫保留區。
2. 當 nums[j] != val 時，表示此元素需要被保留，將其覆寫到 nums[i]，並讓 i 前進。
3. 若 nums[j] == val，跳過不覆寫即可，等效於被刪除（因為保留區不包含它）。
4. 迴圈結束時，前 i 個即為保留後的有效元素；回傳 i 作為 k。
5. 時間複雜度 O(n)：每個元素只被讀取與最多一次覆寫。
6. 空間複雜度 O(1)：原地操作，不使用額外陣列。
*/
