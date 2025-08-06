public class RecursiveMathCalculator {

    // 計算組合數 C(n, k)
    public static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 計算卡塔蘭數 C(n)
    public static int catalan(int n) {
        if (n <= 1) return 1;
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // 計算漢諾塔步數
    public static int hanoiSteps(int n) {
        if (n == 1) return 1;
        return 2 * hanoiSteps(n - 1) + 1;
    }

    // 判斷是否為回文數
    public static boolean isPalindrome(int num) {
        return isPalindromeHelper(Integer.toString(num));
    }

    private static boolean isPalindromeHelper(String str) {
        if (str.length() <= 1) return true;
        if (str.charAt(0) != str.charAt(str.length() - 1)) return false;
        return isPalindromeHelper(str.substring(1, str.length() - 1));
    }

    public static void main(String[] args) {
        System.out.println("C(5, 2) = " + combination(5, 2));       // 組合數
        System.out.println("Catalan(4) = " + catalan(4));           // 卡塔蘭數
        System.out.println("Hanoi steps for 3 disks: " + hanoiSteps(3)); // 漢諾塔
        System.out.println("Is 12321 a palindrome? " + isPalindrome(12321)); // 回文數
        System.out.println("Is 12345 a palindrome? " + isPalindrome(12345));
    }
}
