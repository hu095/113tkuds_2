// 題目：LeetCode 29. Divide Two Integers
// 限制：不得使用乘法(*)、除法(/)、取餘(%) 運算子；結果需朝 0 方向截斷；需處理 32 位整數溢位。


public class lt_29_DivideTwoIntegers {

    static class Solution {
        public int divide(int dividend, int divisor) {
            // LeetCode 保證 divisor != 0；但需處理唯一會溢位的情況：
            // INT_MIN / -1 = 2147483648，超過 INT_MAX → 需返回 INT_MAX
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }

            // 取號誌：兩數異號 → 結果為負
            boolean negative = ((dividend ^ divisor) < 0);

            // 用 long 做絕對值，避免 abs(INT_MIN) 溢位
            long a = Math.abs((long) dividend);
            long b = Math.abs((long) divisor);

            int ans = 0;

            // 位移減法：找出最大的 2^i * b <= a，然後從 a 減去，答案加上 2^i
            for (int i = 31; i >= 0; i--) {
                if ((a >> i) >= b) {      // 等價於 a >= (b << i)，但避免 (b << i) 溢位先右移比較
                    a -= (b << i);        // 扣掉這一份
                    ans += (1 << i);      // 累加對應的商
                }
            }

            return negative ? -ans : ans;  // 還原號誌
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // ===== Example 1 =====
        int dividend1 = 10, divisor1 = 3;
        int out1 = sol.divide(dividend1, divisor1);
        System.out.println("Example 1:\n");
        System.out.println("Input: dividend = " + dividend1 + ", divisor = " + divisor1);
        System.out.println("Output: " + out1);
        System.out.println("Explanation: 10/3 = 3.33333.. which is truncated to 3.");

        // 空行分隔
        System.out.println("Example 2:\n");
        int dividend2 = 7, divisor2 = -3;
        int out2 = sol.divide(dividend2, divisor2);
        System.out.println("Input: dividend = " + dividend2 + ", divisor = " + divisor2);
        System.out.println("Output: " + out2);
        System.out.println("Explanation: 7/-3 = -2.33333.. which is truncated to -2.");
    }
}

/*
解題思路：
1. 目標：以位運算+減法模擬除法；結果需朝 0 截斷（Java int / int 的預設行為）。
2. 特例處理：INT_MIN / -1 會超過 int 上限，依題意回傳 Integer.MAX_VALUE。
3. 先以異或判斷正負號；再把 dividend、divisor 轉為 long 的絕對值 a、b 以避免溢位。
4. 由高位到低位檢查：若 (a >> i) >= b，表示 b * 2^i <= a：
   - a 減去 (b << i)
   - 答案加上 (1 << i)
5. 迴圈結束後，依原本號誌決定正負；回傳最終商值。
6. 時間複雜度 O(32) ≈ O(1)（外圈固定 32 次；內部僅常數操作）。
7. 空間複雜度 O(1)，僅使用常數額外變數。
*/
