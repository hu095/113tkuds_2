// 題目：LeetCode 26. Remove Duplicates from Sorted Array

public class lt_26_RemoveDuplicates {

    static class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) return 0;

            int i = 0; // 慢指標：指向最後一個不重複元素的位置
            for (int j = 1; j < nums.length; j++) { // 快指標：從頭到尾掃描
                if (nums[j] != nums[i]) {
                    i++;            // 找到新元素 → 慢指標前進
                    nums[i] = nums[j]; // 更新陣列內容
                }
            }
            return i + 1; // 不重複元素的數量
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {1, 1, 2};
        int k1 = sol.removeDuplicates(nums1);
        System.out.println("Output: " + k1);
        for (int i = 0; i < k1; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println();

        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};
        int k2 = sol.removeDuplicates(nums2);
        System.out.println("Output: " + k2);
        for (int i = 0; i < k2; i++) {
            System.out.print(nums2[i] + " ");
        }
        System.out.println();
    }
}

/*
解題思路：
1. 使用雙指標法：慢指標 i、快指標 j。
2. 初始時 i=0，快指標 j 從第 1 個元素開始掃描。
3. 若 nums[j] 與 nums[i] 不同，代表遇到新元素：
   - 將 i 往前移一格。
   - 把 nums[j] 放到 nums[i]。
4. 重複上述過程，直到快指標走完整個陣列。
5. 最後返回 i+1，即為不重複元素的數量。
6. 時間複雜度 O(n)，每個元素最多比較一次。
7. 空間複雜度 O(1)，原地修改，不使用額外空間。
*/
