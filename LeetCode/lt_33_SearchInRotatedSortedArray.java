import java.util.*;

// 題目：Search in Rotated Sorted Array
// 給定一個已排序但可能經過旋轉的整數陣列 nums 和目標值 target，
// 回傳 target 在陣列中的索引位置，若 target 不在陣列中，則回傳 -1。
// 陣列的旋轉意味著從某個未知位置 k (1 <= k < nums.length) 旋轉，
// 例如，從 [0,1,2,4,5,6,7] 旋轉 3 次後變成 [4,5,6,7,0,1,2]。

public class lt_33_SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 測試案例 1
        int[] nums1 = {4,5,6,7,0,1,2};
        int target1 = 0;
        System.out.println("Example 1:");
        System.out.println("Input:  " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + sol.search(nums1, target1));
        // 預期結果: 4

        // 測試案例 2
        int[] nums2 = {4,5,6,7,0,1,2};
        int target2 = 3;
        System.out.println("Example 2:");
        System.out.println("Input:  " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + sol.search(nums2, target2));
        // 預期結果: -1

        // 測試案例 3
        int[] nums3 = {1};
        int target3 = 0;
        System.out.println("Example 3:");
        System.out.println("Input:  " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Output: " + sol.search(nums3, target3));
        // 預期結果: -1
    }
}

class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        // 使用二分搜尋
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 找到目標
            if (nums[mid] == target) {
                return mid;
            }

            // 判斷左半部是否有序
            if (nums[left] <= nums[mid]) {
                // 目標在左半部
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右半部有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1; // 未找到目標
    }
}

/*
解題思路：
1) 二分搜尋：使用 `left`, `right` 和 `mid` 定位目標。
2) 判斷 `nums[left]` 到 `nums[mid]` 是否有序（即沒有旋轉）：
   • 如果 `nums[left] <= nums[mid]`，表示左半部有序，目標在此區間內則縮小右區間。
   • 否則右半部有序，目標在此區間內則縮小左區間。
3) 利用二分搜尋特性，使搜尋在 O(log n) 的時間內完成。

時間複雜度：O(log n)，因為每次將搜尋區間對半縮小。  
空間複雜度：O(1)，因為只使用了常數空間進行搜尋。
*/
