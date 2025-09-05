// 功能：展示「下一個排列」演算法，並印出多組範例結果。

import java.util.Arrays;

public class lt_31_NextPermutation {

    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) return;

        // Step 1: 找第一個下降位置 i
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        if (i >= 0) {
            // Step 2: 找第一個 > nums[i] 的 j
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }

        // Step 3: 反轉 i+1..end
        reverse(nums, i + 1, n - 1);
    }

    private static void swap(int[] a, int x, int y) {
        int t = a[x]; a[x] = a[y]; a[y] = t;
    }

    private static void reverse(int[] a, int l, int r) {
        while (l < r) swap(a, l++, r--);
    }

    public static void main(String[] args) {
        runCase(new int[]{1,2,3});   // Example 1 -> [1,3,2]
        runCase(new int[]{3,2,1});   // Example 2 -> [1,2,3]
        runCase(new int[]{1,1,5});   // Example 3 -> [1,5,1]
    }

    private static void runCase(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        nextPermutation(nums);
        System.out.println("Input : " + Arrays.toString(copy));
        System.out.println("Output: " + Arrays.toString(nums));
        System.out.println();
    }
}

/*
==============================
解題思路：
1) 從右往左找「第一個下降」的位置 i → nums[i] < nums[i+1]
   - 若不存在，代表序列已是最大，直接反轉成最小升序。
2) 從右往左找「第一個大於 nums[i]」的 j，交換 nums[i] 和 nums[j]。
3) 將 i+1..末尾反轉成升序 → 得到「剛好大一點」的排列。

時間複雜度：O(n)  
空間複雜度：O(1)
==============================
*/