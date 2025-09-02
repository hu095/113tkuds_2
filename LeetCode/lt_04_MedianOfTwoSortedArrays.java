// LeetCode #4: Median of Two Sorted Arrays
// 使用二分搜尋切分法 O(log(min(m,n)))

public class lt_04_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 範例 1
        int[] nums1a = {1, 3};
        int[] nums2a = {2};
        double ans1 = sol.findMedianSortedArrays(nums1a, nums2a);
        System.out.println("範例 1 輸出 -> " + fmt(ans1)); // 預期 2.00000

        // 範例 2
        int[] nums1b = {1, 2};
        int[] nums2b = {3, 4};
        double ans2 = sol.findMedianSortedArrays(nums1b, nums2b);
        System.out.println("範例 2 輸出 -> " + fmt(ans2)); // 預期 2.50000

        // 你可以在這裡加更多測資
    }

    // 輔助函式：把 double 格式化成 5 位小數（和 LeetCode 一樣）
    private static String fmt(double v) {
        return String.format("%.5f", v);
    }
}

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 確保 nums1 是比較短的那個陣列，方便做二分搜尋
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int m = nums1.length, n = nums2.length;
        int totalLeft = (m + n + 1) / 2; // 左半部需要的元素數

        int lo = 0, hi = m;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1;     // nums1 切左邊 i 個
            int j = totalLeft - i;       // nums2 切左邊 j 個

            // 取出邊界數字，若切到頭或尾則用極值代替
            int L1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int R1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int L2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int R2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // 檢查是否切分正確
            if (L1 <= R2 && L2 <= R1) {
                if (((m + n) & 1) == 1) { 
                    // 總長度為奇數，中位數是左半邊最大值
                    return Math.max(L1, L2);
                } else { 
                    // 總長度為偶數，中位數是「左半最大 + 右半最小」除 2
                    return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
                }
            } else if (L1 > R2) {
                // i 太大，往左移
                hi = i - 1;
            } else {
                // i 太小，往右移
                lo = i + 1;
            }
        }
        return 0.0; // 理論上不會到這裡
    }
}
