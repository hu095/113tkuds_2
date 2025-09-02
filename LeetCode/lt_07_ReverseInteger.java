public class lt_07_ReverseInteger {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int x1 = 123;
        int x2 = -123;
        int x3 = 120;
        int x4 = 1534236469; // 測試溢位案例

        System.out.println("Input: " + x1 + " -> Output: " + sol.reverse(x1)); // 321
        System.out.println("Input: " + x2 + " -> Output: " + sol.reverse(x2)); // -321
        System.out.println("Input: " + x3 + " -> Output: " + sol.reverse(x3)); // 21
        System.out.println("Input: " + x4 + " -> Output: " + sol.reverse(x4)); // 0 (溢位)
    }
}

class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
