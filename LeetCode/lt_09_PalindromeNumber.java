public class lt_09_PalindromeNumber {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int x1 = 121;
        int x2 = -121;
        int x3 = 10;
        int x4 = 0;
        int x5 = 12321;

        System.out.println(x1 + " -> " + sol.isPalindrome(x1)); // true
        System.out.println(x2 + " -> " + sol.isPalindrome(x2)); // false
        System.out.println(x3 + " -> " + sol.isPalindrome(x3)); // false
        System.out.println(x4 + " -> " + sol.isPalindrome(x4)); // true
        System.out.println(x5 + " -> " + sol.isPalindrome(x5)); // true
    }
}

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return x == rev || x == rev / 10;
    }
}
