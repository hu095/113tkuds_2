// 題目 18 旋轉陣列搜尋
// 檔名：LC33_SearchRotated_RentHot.java
//
// 輸入：
// n target
// n 個整數（被旋轉的升序陣列）
//
// 輸出：
// target 的索引，若不存在則輸出 -1
//
// 範例：
// Input :
// 7 0
// 4 5 6 7 0 1 2
// Output:
// 4

import java.io.*;

public class LC33_SearchRotated_RentHot {

    // 主解法：改良二分搜尋
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            // 左半部分有序
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右半部分有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String[] first = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(first[0]);
        int target = Integer.parseInt(first[1]);

        String[] parts = br.readLine().trim().split("\\s+");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(parts[i]);

        int result = search(nums, target);
        System.out.println(result);
    }
}
