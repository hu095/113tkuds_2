import java.util.Arrays;

public class lt_35_SearchInsertPosition {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 測試案例 1
        int[] nums1 = {1, 3, 5, 6};
        int target1 = 5;
        System.out.println("Example 1:");
        System.out.println("Input:  " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + sol.searchInsert(nums1, target1));
        // 預期結果: 2

        // 測試案例 2
        int[] nums2 = {1, 3, 5, 6};
        int target2 = 2;
        System.out.println("Example 2:");
        System.out.println("Input:  " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + sol.searchInsert(nums2, target2));
        // 預期結果: 1

        // 測試案例 3
        int[] nums3 = {1, 3, 5, 6};
        int target3 = 7;
        System.out.println("Example 3:");
        System.out.println("Input:  " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Output: " + sol.searchInsert(nums3, target3));
        // 預期結果: 4
    }
}

class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        // 使用二分搜尋來找到插入的位置
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;  // 如果找到目標數字，直接返回索引
            } else if (nums[mid] < target) {
                left = mid + 1;  // 目標比中間數字大，縮小範圍至右半邊
            } else {
                right = mid - 1;  // 目標比中間數字小，縮小範圍至左半邊
            }
        }
        
        // 若未找到目標，返回插入位置（即 `left` 的位置）
        return left;
    }
}

/*
解題思路：
1. 使用二分搜尋法查找目標數字的位置。
2. 如果找到目標數字，直接返回其索引。
3. 如果目標數字不在陣列中，則返回目標數字應該插入的位置，即 `left` 的位置。
4. 由於陣列是已排序的，因此當搜尋結束後，`left` 位置即為插入位置。

時間複雜度：O(log n)，每次進行二分搜尋，將搜尋範圍縮小一半。  
空間複雜度：O(1)，因為只使用常數空間進行搜尋。
*/

