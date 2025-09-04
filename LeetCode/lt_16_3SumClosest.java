import java.util.*;

// 題目：3Sum Closest
// 給定一個整數陣列 nums 和一個目標值 target，請找出三個整數，
// 使得這三個數字的總和最接近 target，並回傳該總和。
public class lt_16_3SumClosest {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 範例 1
        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        System.out.println("Example 1:");
        System.out.println("Input:  " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + sol.threeSumClosest(nums1, target1));
        System.out.println("Expected: 2\n");

        // 範例 2
        int[] nums2 = {0, 0, 0};
        int target2 = 1;
        System.out.println("Example 2:");
        System.out.println("Input:  " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + sol.threeSumClosest(nums2, target2));
        System.out.println("Expected: 0\n");
    }
}

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 排序，方便雙指標操作

        // 初始化最接近的總和
        int closest = nums[0] + nums[1] + nums[2];

        // 外層固定一個數字
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, r = nums.length - 1;

            // 內層雙指標
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                // 若命中目標，直接回傳
                if (sum == target) return sum;

                // 更新答案
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                // 移動指標
                if (sum < target) {
                    l++; // 總和太小，往右
                } else {
                    r--; // 總和太大，往左
                }
            }
        }
        return closest;
    }
}

/*
解題思路：
1. 先將 nums 排序，讓雙指標能單調調整總和大小。
2. 外層固定一個數 nums[i]，內層使用雙指標 (l, r) 尋找另外兩數。
3. 每次計算 sum，若 |sum - target| 更小，更新 closest。
4. sum < target → l++，sum > target → r--，逐步逼近。
5. 若 sum == target，直接回傳，因為已經是最佳答案。
時間複雜度：O(n^2)，空間複雜度：O(1)。
*/
