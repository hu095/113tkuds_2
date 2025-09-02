import java.util.*;

// 題目：3Sum
// 給定一個整數陣列 nums，找出所有三元組 [nums[i], nums[j], nums[k]]，
// 使得 nums[i] + nums[j] + nums[k] == 0，並且不能有重複的三元組。
public class lt_15_3Sum {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Example 1:");
        System.out.println("Input:  " + Arrays.toString(nums1));
        System.out.println("Output: " + sol.threeSum(nums1));
        System.out.println();

        // Example 2
        int[] nums2 = {0, 1, 1};
        System.out.println("Example 2:");
        System.out.println("Input:  " + Arrays.toString(nums2));
        System.out.println("Output: " + sol.threeSum(nums2));
        System.out.println();

        // Example 3
        int[] nums3 = {0, 0, 0};
        System.out.println("Example 3:");
        System.out.println("Input:  " + Arrays.toString(nums3));
        System.out.println("Output: " + sol.threeSum(nums3));
    }
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // 先排序，方便去重複
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;

        // 固定第一個數字
        for (int i = 0; i < n - 2; i++) {
            // 跳過重複的起始數字
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 如果當前數字已大於 0，則不可能再找到三元組
            if (nums[i] > 0) break;

            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));

                    // 跳過重複的 l
                    int lv = nums[l];
                    while (l < r && nums[l] == lv) l++;
                    // 跳過重複的 r
                    int rv = nums[r];
                    while (l < r && nums[r] == rv) r--;
                } else if (sum < 0) {
                    l++; // 總和太小，移動左指針
                } else {
                    r--; // 總和太大，移動右指針
                }
            }
        }
        return res;
    }
}

/*
解題思路：
1. 對陣列排序，方便處理重複元素與雙指針操作。
2. 外層迴圈固定 nums[i]，內層用雙指針 (l, r) 找出 nums[l] + nums[r] = -nums[i]。
3. 找到後，記錄三元組並移動指針，跳過所有重複的數字以避免重複解。
4. 若和小於 0，左指針右移；若和大於 0，右指針左移。
5. 時間複雜度 O(n^2)，因為外層 O(n)，內層 O(n)。空間複雜度 O(1)（輸出除外）。
*/
