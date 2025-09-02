// 題目：Container With Most Water
// 給定一個整數陣列 height，代表每根垂直線的高度。
// 任意兩根線 (i, j) 與 X 軸可形成一個容器，容量 = min(height[i], height[j]) × (j - i)。
// 請找出能盛最多水的容器面積。

public class lt_11_ContainerWithMostWater {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] h1 = {1,8,6,2,5,4,8,3,7};
        int out1 = sol.maxArea(h1);
        System.out.println("Example 1:");
        System.out.println();
        System.out.println("Input:  height = [1,8,6,2,5,4,8,3,7]");
        System.out.println("Output: " + out1); // 預期 49
        System.out.println();

        // Example 2
        int[] h2 = {1,1};
        int out2 = sol.maxArea(h2);
        System.out.println("Example 2:");
        System.out.println();
        System.out.println("Input:  height = [1,1]");
        System.out.println("Output: " + out2); // 預期 1
    }
}

class Solution {
    public int maxArea(int[] height) {
        // 使用雙指針：左指針 i 從 0 開始，右指針 j 從陣列最後開始
        int i = 0, j = height.length - 1;
        int ans = 0; // 紀錄最大面積

        // 當左右指針尚未相遇時，不斷嘗試更新最大面積
        while (i < j) {
            // 容器高度由左右較短的邊決定
            int h = Math.min(height[i], height[j]);

            // 面積 = 高度 × 寬度
            ans = Math.max(ans, h * (j - i));

            // 移動策略：
            // - 移動較短的一邊，才可能遇到更高的柱子，增加容器高度
            // - 若移動較高的一邊，只會縮小寬度，面積不會變大
            if (height[i] < height[j]) {
                i++; // 左邊較短 → 移動左指針
            } else {
                j--; // 右邊較短或相等 → 移動右指針
            }
        }

        return ans; // 回傳最大面積
    }
}

/*
解題思路：
1. 容器面積 = 寬度 × 高度，高度取決於較短的邊。
2. 暴力法需要枚舉所有 (i, j)，時間複雜度 O(n²)，無法通過大數據測試。
3. 雙指針法：
   - 一開始 i = 0, j = n-1，寬度最大。
   - 每次計算當前面積，並移動較短的一邊。
   - 因為移動較長的一邊只會縮小寬度，且高度仍受短邊限制，無法增大面積。
   - 移動較短的一邊，才可能增加高度，進而提升面積。
4. 時間複雜度 O(n)，空間複雜度 O(1)，效率最佳。
*/
