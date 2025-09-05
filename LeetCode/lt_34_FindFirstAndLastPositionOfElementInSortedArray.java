import java.util.Arrays;

public class lt_34_FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 測試案例 1
        int[] nums1 = {5,7,7,8,8,10};
        int target1 = 8;
        System.out.println("Example 1:");
        System.out.println("Input:  " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + Arrays.toString(sol.searchRange(nums1, target1)));
        // 預期結果: [3, 4]

        // 測試案例 2
        int[] nums2 = {5,7,7,8,8,10};
        int target2 = 6;
        System.out.println("Example 2:");
        System.out.println("Input:  " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + Arrays.toString(sol.searchRange(nums2, target2)));
        // 預期結果: [-1, -1]

        // 測試案例 3
        int[] nums3 = {};
        int target3 = 0;
        System.out.println("Example 3:");
        System.out.println("Input:  " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Output: " + Arrays.toString(sol.searchRange(nums3, target3)));
        // 預期結果: [-1, -1]
    }
}

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1}; // 預設回傳 [-1, -1]

        // 使用二分搜尋，分別查找最左和最右的索引
        result[0] = findLeft(nums, target);  // 查找最左位置
        result[1] = findRight(nums, target); // 查找最右位置

        return result;
    }

    // 查找目標的最左位置（最早出現的位置）
    private int findLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int leftIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1; // 若中間數字大於或等於目標，縮小範圍至左半邊
            } else {
                left = mid + 1; // 若中間數字小於目標，縮小範圍至右半邊
            }

            // 如果找到目標，記錄位置並繼續向左查找
            if (nums[mid] == target) {
                leftIndex = mid;
            }
        }
        return leftIndex;
    }

    // 查找目標的最右位置（最後出現的位置）
    private int findRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int rightIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1; // 若中間數字小於或等於目標，縮小範圍至右半邊
            } else {
                right = mid - 1; // 若中間數字大於目標，縮小範圍至左半邊
            }

            // 如果找到目標，記錄位置並繼續向右查找
            if (nums[mid] == target) {
                rightIndex = mid;
            }
        }
        return rightIndex;
    }
}

/*
解題思路：
1. 使用兩次二分搜尋，分別查找目標數字在排序陣列中的最左和最右位置。
2. 查找最左位置時，若發現目標，繼續向左縮小範圍查找，以確保找到最早出現的索引。
3. 查找最右位置時，若發現目標，繼續向右縮小範圍查找，以確保找到最晚出現的索引。
4. 如果找不到目標，回傳 [-1, -1]。

時間複雜度：O(log n)，每次都進行二分搜尋。
空間複雜度：O(1)，因為只使用常數空間進行搜尋。
*/
