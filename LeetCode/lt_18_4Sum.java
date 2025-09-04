import java.util.*;

// 題目：4Sum
// 給定一個整數陣列 nums 和一個目標值 target，
// 找出所有不重複的四元組 [nums[a], nums[b], nums[c], nums[d]]，
// 使得 nums[a] + nums[b] + nums[c] + nums[d] == target。
// 條件：
//   - 0 <= a, b, c, d < n
//   - a, b, c, d 互不相同
//   - 可以以任意順序回傳答案
public class lt_18_4Sum {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 測試案例 1
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        System.out.println("Example 1:");
        System.out.println("Input:  " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + sol.fourSum(nums1, target1));
        // 預期結果: [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]

        // 測試案例 2
        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;
        System.out.println("Example 2:");
        System.out.println("Input:  " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + sol.fourSum(nums2, target2));
        // 預期結果: [[2,2,2,2]]
    }
}

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        if (n < 4) return ans; // 長度不足 4，直接回傳空結果

        Arrays.sort(nums); // 排序方便雙指標與去重

        // 第一層：固定 i
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重 i

            // 剪枝：最小可能和 > target，直接結束
            long min1 = (long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (min1 > target) break;
            // 剪枝：最大可能和 < target，直接跳過
            long max1 = (long) nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3];
            if (max1 < target) continue;

            // 第二層：固定 j
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 去重 j

                // 剪枝：最小可能和 > target，直接結束
                long min2 = (long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                if (min2 > target) break;
                // 剪枝：最大可能和 < target，直接跳過
                long max2 = (long) nums[i] + nums[j] + nums[n - 1] + nums[n - 2];
                if (max2 < target) continue;

                // 內層雙指標搜尋
                int l = j + 1, r = n - 1;
                while (l < r) {
                    long sum = (long) nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));

                        // 跳過重複的 l、r
                        int lv = nums[l], rv = nums[r];
                        while (l < r && nums[l] == lv) l++;
                        while (l < r && nums[r] == rv) r--;
                    } else if (sum < target) {
                        l++; // 總和太小 → 左指標右移
                    } else {
                        r--; // 總和太大 → 右指標左移
                    }
                }
            }
        }
        return ans;
    }
}

/*
解題思路：
1. 先將陣列排序，方便雙指標操作與去重。
2. 外層用兩層迴圈固定 nums[i], nums[j]，內層用雙指標 (l, r) 搜索其餘兩個數字。
3. 每次計算總和 sum 與 target 比較：
   - 相等 → 加入結果，並跳過重複的 l、r。
   - 小於 target → l++。
   - 大於 target → r--。
4. 剪枝優化：利用當前 i, j 的最小和、最大和提前結束不必要的迴圈。
5. 使用 long 避免 int 溢位（因為 nums[i] 可達 ±1e9）。
6. 時間複雜度 O(n^3)，空間複雜度 O(1)（輸出除外）。
*/
